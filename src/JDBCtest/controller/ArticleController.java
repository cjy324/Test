package JDBCtest.controller;

import java.util.List;
import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
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
		// 게시물 상세보기
		else if (cmd.startsWith("article detail ")) {
			detail(cmd);
		}
		// 게시판 추가
		else if (cmd.equals("article boardAdd")) {
			boardAdd(cmd);
		}
		// 게시판 선택
		else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		}
	}

	private void selectBoard(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		Board board = articleService.getBoard(inputedId);

		if (board == null) {
			System.out.println("해당 게시판은 존재하지 않습니다.");
			return;
		}

		System.out.printf("%s 게시판 선택 완료\n", board.boardName);
		Container.session.selectedBoardId = board.boardId;

	}

	private void boardAdd(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		System.out.printf("게시판 이름 입력) ");
		String boardName = sc.nextLine();

		int boardId = articleService.boardAdd(boardName);

		System.out.printf("%d(%s) 게시판 생성 완료\n", boardId, boardName);

	}

	private void detail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		boolean checkExistArticle = articleService.getArticle(inputedId);

		if (checkExistArticle == false) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
			return;
		}

		System.out.printf("%d번 게시물 상세보기\n", inputedId);

		Article article = articleService.detailArticle(inputedId);
		Member member = memberService.getMemberByMemberId(article.memberId);

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성일 : %s\n", article.regDate);
		System.out.printf("수정일 : %s\n", article.updateDate);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("게시판 번호 : %d\n", article.boardId);
		System.out.printf("작성자 : %s\n", member.name);

	}

	private void delete(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용가능");
			return;
		}

		boolean checkExistArticle = articleService.getArticle(inputedId);

		if (checkExistArticle == false) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
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

		boolean checkExistArticle = articleService.getArticle(inputedId);

		if (checkExistArticle == false) {
			System.out.println("해당 게시물은 존재하지 않습니다.");
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

//		Board board = articleService.getBoard(Container.session.selectedBoardId);

//		List<Article> boardArticles = articleService.getBoardArticlesByBoardId(board.boardId);

//		if (boardArticles.size() <= 0) {
//			System.out.printf("== %s 게시판 내 게시물이 없습니다. ==\n", board.boardName);
//			return;
//		}

		System.out.printf("==게시판 게시물 리스트 ==\n");
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
		int boardId = Container.session.selectedBoardId;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("%d번 게시물 생성 완료\n", id);

	}

}
