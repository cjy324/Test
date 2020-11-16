package JDBCtest.controller;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.dto.Article;
import JDBCtest.dto.Member;
import JDBCtest.service.ArticleService;
import JDBCtest.service.MemberService;

public class ArticleController {

	Scanner sc;
	ArticleService articleService;
	MemberService memberService;

	public ArticleController() {

		sc = Container.scanner;
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCmd(String cmd) {
		// 게시물 생성
		if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스팅
		else if (cmd.equals("article list")) {
			list(cmd);
		}
		// 게시물 수정
		else if (cmd.startsWith("article modify ")) {
			modify(cmd);
		}
		// 게시물 삭제
		else if (cmd.startsWith("article delete ")) {
			delete(cmd);
		}
	}

	private void delete(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		articleService.deleteArticle(inputedId);

		System.out.printf("%d번 게시물 삭제 완료\n", inputedId);
	}

	private void modify(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		System.out.printf("수정할 제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 입력) ");
		String body = sc.nextLine();

		articleService.modifyArticle(inputedId, title, body);
		System.out.printf("%d번 게시물 수정 완료\n", inputedId);

	}

	private void list(String cmd) {

		System.out.println("== 게시물 리스트 ==");
		System.out.println("번호 / 제목 / 작성자 / 작성일");

		for (Article article : articleService.getArticles()) {
			Member member = memberService.getMemberByMemberId(article.memberId);
			System.out.printf("%d / %s / %s / %s\n", article.id, article.title, member.name, article.regDate);
		}
	}

	private void add(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		System.out.printf("제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("내용 입력) ");
		String body = sc.nextLine();
		int memberId = Container.session.loginedMemberId;
		int boardId = 1;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("%d번 게시물 생성 완료\n", id);

	}

}
