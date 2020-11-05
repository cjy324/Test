package test.controller;

import java.util.List;
import java.util.Scanner;

import test.container.Container;
import test.dto.Article;
import test.dto.Member;
import test.service.ArticleService;
import test.service.MemberService;

public class ArticleController extends Controller {

	Scanner sc;
	ArticleService articleService;
	MemberService memberService;

	public ArticleController() {
		sc = Container.scanner;
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCmd(String cmd) {

		// 게시물 작성
		if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스트
		else if (cmd.startsWith("article list ")) {
			list(cmd);
		}
		// 게시물 상세보기
		else if (cmd.startsWith("article detail ")) {
			detail(cmd);
		}
		// 게시물 수정
		else if (cmd.startsWith("article modify ")) {
			modify(cmd);
		}
		// 게시물 삭제
		else if (cmd.startsWith("article delete ")) {
			delete(cmd);
		}
		// 게시물 검색
		else if (cmd.startsWith("article search ")) {
			search(cmd);
		}

	}

	private void search(String cmd) {
		//아직 진행중
		String[] cmdBits = cmd.split(" ");
		String inputedKeyword = cmdBits[2];
		int inputedPage = 1;
		
		if(cmdBits.length >= 4) {
			inputedPage = Integer.parseInt(cmd.split(" ")[3]);
		}

		List<Article> searchedArticles = articleService.getSearchedArticlesByKeyword(inputedKeyword);


		int articlesInAPage = 10;
		int startPoint = searchedArticles.size() - 1;
		startPoint -= (inputedPage - 1) * articlesInAPage;
		int endPoint = startPoint - (articlesInAPage - 1);

		if (startPoint <= 0) {
			System.out.println("해당 페이지는 존재하지 않습니다.");
			return;
		}
		if (endPoint <= 0) {
			endPoint = 0;
		}

		System.out.println("== 게시물 리스트 ==");
		System.out.println("번호 / 제목 / 작성자");

		for (int i = startPoint; i >= endPoint; i--) {
			Member member = memberService.getMemberByMemberNum(searchedArticles.get(i).writerNum);
			System.out.printf("%d / %s / %s\n", searchedArticles.get(i).aNum, searchedArticles.get(i).title, member.mName);
		}
		
	}

	private void delete(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleByNum(inputedNum);
		if (article == null) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
			return;
		}

		List<Article> articles = articleService.getArticles();

		articles.remove(inputedNum - 1);
		System.out.printf("%d번 게시물 삭제 완료\n", inputedNum);

	}

	private void modify(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleByNum(inputedNum);
		if (article == null) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.printf("수정할 제목 입력 : ");
		article.title = sc.nextLine();
		System.out.printf("수정할 내용 입력 : ");
		article.body = sc.nextLine();
		article.writerNum = Container.session.loginedMemberNum;

		System.out.printf("%d번 게시물 수정 완료\n", inputedNum);
		System.out.printf("%s / %s / %d\n", article.title, article.body, article.writerNum);

	}

	private void detail(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticleByNum(inputedNum);
		if (article == null) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
			return;
		}

		Member member = memberService.getMemberByMemberNum(article.writerNum);

		System.out.printf("== %d번 게시물 상세보기 == \n", inputedNum);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("작성자 : %s\n", member.mName);

	}

	private void list(String cmd) {
		int inputedPage = Integer.parseInt(cmd.split(" ")[2]);

		if (inputedPage <= -1) {
			inputedPage = 1;
		}

		int articlesInAPage = 10;
		int startPoint = articleService.getArticles().size() - 1;
		startPoint -= (inputedPage - 1) * articlesInAPage;
		int endPoint = startPoint - (articlesInAPage - 1);

		if (startPoint <= 0) {
			System.out.println("해당 페이지는 존재하지 않습니다.");
			return;
		}
		if (endPoint <= 0) {
			endPoint = 0;
		}

		List<Article> articles = articleService.getArticles();

		System.out.println("== 게시물 리스트 ==");
		System.out.println("번호 / 제목 / 작성자");

		for (int i = startPoint; i >= endPoint; i--) {
			Member member = memberService.getMemberByMemberNum(articles.get(i).writerNum);
			System.out.printf("%d / %s / %s\n", articles.get(i).aNum, articles.get(i).title, member.mName);
		}
	}

	private void add(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용 가능");
			return;
		}

		System.out.printf("제목 입력 : ");
		String title = sc.nextLine();
		System.out.printf("내용 입력 : ");
		String body = sc.nextLine();
		int writerNum = Container.session.loginedMemberNum;

		int aNum = articleService.add(title, body, writerNum);

		System.out.printf("%d번 게시물 등록 완료\n", aNum);
		System.out.printf("%s / %s / %d\n", title, body, writerNum);

	}

}
