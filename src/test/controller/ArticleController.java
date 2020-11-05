package test.controller;

import java.util.List;
import java.util.Scanner;

import test.container.Container;
import test.dto.Article;
import test.dto.Member;
import test.service.ArticleService;
import test.service.MemberService;

public class ArticleController extends Controller {

	ArticleService articleService;
	MemberService memberService;
	Scanner sc;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
		sc = Container.scanner;
	}

	public void doCmd(String cmd) {
		// 게시물 등록
		if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스트
		else if (cmd.startsWith("article list ")) {
			list(cmd);
		}
		// 게시물 검색
		else if (cmd.startsWith("article search ")) {
			search(cmd);
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
		else if (cmd.startsWith("article remove ")) {
			remove(cmd);
		}
	}

	private void search(String cmd) {
		String[] cmdBits = cmd.split(" ");
		String inputedKeyword = cmdBits[2];
		int inputedPage = 1;

		if (cmdBits.length >= 4) {
			inputedPage = Integer.parseInt(cmd.split(" ")[3]);
		}

		System.out.println("== 검색 결과 ==");
		System.out.println("번호 / 작성자 / 제목");

		List<Article> searchedArticles = articleService.getArticlesByKeyword(inputedKeyword);

		if (searchedArticles.size() <= 0) {
			System.out.println("해당 키워드는 존재하지 않습니다.");
			return;
		}

		int articlesInAPage = 10;
		int startPoint = searchedArticles.size() - 1;
		startPoint -= (inputedPage - 1) * articlesInAPage;
		int endPoint = startPoint - (articlesInAPage - 1);

		if (startPoint <= 0) {
			System.out.println("해당 페이지가 없습니다.");
			return;
		}
		if (endPoint < 0) {
			endPoint = 0;
		}

		for (int i = startPoint; i >= endPoint; i--) {
			Member member = memberService.getMemberByMemberNum(searchedArticles.get(i).writerNum);
			System.out.printf("%d / %s / %s\n", searchedArticles.get(i).aNum, member.mName,
					searchedArticles.get(i).title);
		}
	}

	private void remove(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인이 필요합니다.");
			return;
		}

		Article article = articleService.getArticleByNum(inputedNum);

		if (article == null) {
			System.out.println("해당 게시물은 없습니다.");
			return;
		}

		articleService.getArticles().remove(inputedNum - 1);
		System.out.printf("%d번 게시물 삭제 완료\n", inputedNum);

	}

	private void detail(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인이 필요합니다.");
			return;
		}

		Article article = articleService.getArticleByNum(inputedNum);

		if (article == null) {
			System.out.println("해당 게시물은 없습니다.");
			return;
		}

		Member member = memberService.getMemberByMemberNum(inputedNum);

		System.out.printf("== %d번 게시물 상세보기 ==\n", article.aNum);
		System.out.printf("번호 : %d\n", article.aNum);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("작성자 : %s\n", member.mName);

	}

	private void modify(String cmd) {
		int inputedNum = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인이 필요합니다.");
			return;
		}

		Article article = articleService.getArticleByNum(inputedNum);

		if (article == null) {
			System.out.println("해당 게시물은 없습니다.");
			return;
		}

		System.out.printf("%d번 게시물 수정\n", article.aNum);
		System.out.printf("수정할 제목 : ");
		article.title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		article.body = sc.nextLine();
		article.writerNum = Container.session.loginedMemberNum;

		System.out.printf("== %d번 게시물 수정완료 ==\n", article.aNum);
		System.out.printf("%d / %s / %s / %d\n", article.aNum, article.title, article.body, article.writerNum);

	}

	private void list(String cmd) {
		int inputedPage = Integer.parseInt(cmd.split(" ")[2]);

		if (inputedPage <= -1) {
			inputedPage = 1;
		}

		System.out.println("== 게시물 리스트 ==");
		System.out.println("번호 / 작성자 / 제목");

		int articlesInAPage = 10;
		int startPoint = articleService.getArticles().size() - 1;
		startPoint -= (inputedPage - 1) * articlesInAPage;
		int endPoint = startPoint - (articlesInAPage - 1);

		if (startPoint < 0) {
			System.out.println("해당 페이지가 없습니다.");
			return;
		}
		if (endPoint < 0) {
			endPoint = 0;
		}

		List<Article> articles = articleService.getArticles();

		for (int i = startPoint; i >= endPoint; i--) {
			Member member = memberService.getMemberByMemberNum(articles.get(i).writerNum);
			System.out.printf("%d / %s / %s\n", articles.get(i).aNum, member.mName, articles.get(i).title);
		}

	}

	private void add(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인이 필요합니다.");
			return;
		}

		System.out.printf("제목 입력 : ");
		String title = sc.nextLine();
		System.out.printf("내용 입력 : ");
		String body = sc.nextLine();
		int writerNum = Container.session.loginedMemberNum;

		int aNum = articleService.add(title, body, writerNum);

		System.out.printf("%d번 게시물 등록 완료\n", aNum);
		System.out.printf("%d / %s / %s / %d\n", aNum, title, body, writerNum);

	}

}
