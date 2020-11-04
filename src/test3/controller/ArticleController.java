package test3.controller;

import java.util.List;
import java.util.Scanner;

import test3.container.Container;
import test3.dto.Article;
import test3.dto.Member;
import test3.service.ArticleService;
import test3.service.MemberService;

public class ArticleController extends Controller{

	ArticleService articleService;
	MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	Scanner sc = Container.scanner;

	public void doCommand(String cmd) {

		// 게시물 등록
		if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스트
		else if (cmd.equals("article list")) {
			list(cmd);
		}
	}

	private void list(String cmd) {
		
		List<Article> articles = articleService.getArticles();
	
		System.out.println("== 게시물 리스트 ==");
		System.out.println("== 번호 / 제목 / 작성자 ==");
		
		
		for (Article article : articles) {
			Member member = memberService.getMemberByLoginedMemberNum(article.writerNum);
			System.out.printf("%d / %s / %s\n", article.id,
					article.title, member.name);
		}

	}

	private void add(String cmd) {

		if (Container.session.loginedStatus() == false) {
			System.out.println("로그인이 필요한 기능입니다.");
			return;
		}

		String title;
		String body;
		int wtiter;

		System.out.printf("제목 입력 : ");
		title = sc.nextLine();
		System.out.printf("내용 입력 : ");
		body = sc.nextLine();

		wtiter = Container.session.isLoginedMemberNum;

		int id = articleService.add(title, body, wtiter);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

	}

}
