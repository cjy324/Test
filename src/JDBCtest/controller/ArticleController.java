package JDBCtest.controller;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.dto.Article;
import JDBCtest.service.ArticleService;

public class ArticleController {

	Scanner sc;
	ArticleService articleService;

	public ArticleController() {
		sc = Container.scanner;
		articleService = new ArticleService();
	}

	public void doCmd(String cmd) {
		// 게시물 추가
		if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스팅
		else if (cmd.equals("article list")) {
			list(cmd);
		}
		// 게시물 삭제
		else if (cmd.startsWith("article delete ")) {
			delete(cmd);
		}
		// 게시물 수정
		else if (cmd.startsWith("article modify ")) {
			modify(cmd);
		}

	}

	private void modify(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		System.out.printf("수정할 제목 입력) ");
		String modifyTitle = sc.nextLine();
		System.out.printf("내용 입력) ");
		String modifyBody = sc.nextLine();

		articleService.modifyArticleById(inputedId, modifyTitle, modifyBody);

		System.out.printf("%d번 게시물 수정완료\n", inputedId);
	}

	private void delete(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		articleService.deleteArticleById(inputedId);

		System.out.printf("%d번 게시물 삭제완료\n", inputedId);

	}

	private void add(String cmd) {

		System.out.printf("제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("내용 입력) ");
		String body = sc.nextLine();
		

		int aNum = articleService.add(title, body);

		System.out.printf("%d번 게시글 생성 완료\n", aNum);

	}

	private void list(String cmd) {
		System.out.println("== 게시판 리스트 ==");
		System.out.println("번호 / 제목 / 작성자");

		for (Article article : articleService.getArticles()) {
			System.out.printf("%d / %s /%s\n", article.id, article.title, article.nickname);
		}
	}

}
