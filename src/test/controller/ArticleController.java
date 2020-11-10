package test.controller;

import java.util.List;
import java.util.Scanner;

import test.container.Container;
import test.dto.Article;
import test.dto.Board;
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
		// 게시판 생성
		if (cmd.equals("article addBoard")) {
			addBoard(cmd);
		}
		// 게시판 선택
		else if (cmd.startsWith("article selectBoard ")) {
			selectBoard(cmd);
		}
		// 게시물 생성
		else if (cmd.equals("article add")) {
			add(cmd);
		}
		// 게시물 리스트
		else if (cmd.equals("article list")) {
			list(cmd);
		}
	}

	private void list(String cmd) {

		int selectedBoard = Container.session.seletedBoardNum;
		Board board = articleService.getBoardByBNum(selectedBoard);
		List<Article> boardArticles = articleService.getArticlesBySelectedBoard(selectedBoard);

		System.out.printf("== %s 게시판 글 리스트 ==\n", board.bName);
		System.out.println("번호 / 제목 / 작성자");

		for (Article article : boardArticles) {
			Member member = memberService.getMemberByMNum(article.writerNum);
			System.out.printf("%d / %s / %s\n", article.aNum, article.title, member.mName);
		}

	}

	private void add(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인이 필요합니다.");
			return;
		}

		System.out.printf("제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("내용 입력) ");
		String body = sc.nextLine();
		int writerNum = Container.session.loginedMemberNum;
		int boardNum = Container.session.seletedBoardNum;

		int aNum = articleService.add(boardNum, title, body, writerNum);

		System.out.printf("%d번 게시글 생성 완료\n", aNum);

	}

	private void selectBoard(String cmd) {
		int selectedBoardNum = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoardByBNum(selectedBoardNum);

		if (board == null) {
			System.out.println("존재하지 않는 게시판입니다.");
			return;
		}

		System.out.printf("%s 게시판이 선택되었습니다.\n", board.bName);

		Container.session.seletedBoardNum = board.bNum;

	}

	private void addBoard(String cmd) {
		System.out.printf("이름 입력) ");
		String bName = sc.nextLine();

		int bNum = articleService.addBoard(bName);

		System.out.printf("(%d)%s 게시판 생성 완료\n", bNum, bName);
	}

}
