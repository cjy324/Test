package JDBCtest.controller;

import java.util.List;
import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.dto.Member;
import JDBCtest.dto.Recommand;
import JDBCtest.dto.Reply;
import JDBCtest.dto.View;
import JDBCtest.service.ArticleService;
import JDBCtest.service.MemberService;

public class ArticleController extends Controller {

	Scanner sc;
	ArticleService articleService;
	MemberService memberService;
	int viewCount;

	public ArticleController() {

		sc = Container.scanner;
		articleService = Container.articleService;
		memberService = Container.memberService;
		viewCount = 0;
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
		// 게시물 댓글 추가
		else if (cmd.startsWith("article reply ")) {
			reply(cmd);
		}
		// 게시물 댓글 수정
		else if (cmd.startsWith("article replyModify ")) {
			replyModify(cmd);
		}
		// 게시물 댓글 삭제
		else if (cmd.startsWith("article replyDelete ")) {
			replyDelete(cmd);
		}
		// 게시물 추천
		else if (cmd.startsWith("article recommand ")) {
			recommand(cmd);
		}
		// 게시물 추천 취소
		else if (cmd.startsWith("article cancelRecommand ")) {
			cancelRecommand(cmd);
		}
	}

	private void cancelRecommand(String cmd) {
		int articleId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		int recommandMemberId = Container.session.loginedMemberId;
		Recommand recommand = articleService.getRecommandByArticleId(articleId, recommandMemberId);

		if (recommand == null) {
			System.out.println("(해당 게시물에 대한 추천이 존재하지 않습니다.)");
			return;
		}

		if (recommand.recommandMemberId != recommandMemberId) {
			System.out.println("(추천한 회원만 취소 가능합니다.)");
			return;
		}

		articleService.cancelRecommand(articleId, recommandMemberId);

		System.out.printf("== %d번 게시물 추천 취소 ==\n", articleId);

	}

	private void recommand(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		Article selectArticle = articleService.getArticle(inputedId);

		if (selectArticle == null) {
			System.out.println("(해당 게시물은 존재하지 않습니다.)");
			return;
		}

		int recommandMemberId = Container.session.loginedMemberId;

		boolean checkRecommandMember = articleService.getRecommand(selectArticle.id, recommandMemberId);

		if (checkRecommandMember == false) {
			System.out.println("(회원님은 이미 추천하셨습니다.)");
			return;
		}

		int recommandId = articleService.addRecommand(selectArticle.id, recommandMemberId);

		System.out.printf("== %d번 게시물을 추천하셨습니다. ==\n", selectArticle.id);
	}

	private void replyDelete(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Reply selectedReply = articleService.getReply(inputedId);

		if (selectedReply == null) {
			System.out.printf("(%d번 댓글은 존재하지 않습니다.)\n", inputedId);
			return;
		}

		articleService.replyDelete(inputedId);

		System.out.printf("== %d번 댓글 삭제완료 ==\n", inputedId);

	}

	private void replyModify(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Reply selectedReply = articleService.getReply(inputedId);

		if (selectedReply == null) {
			System.out.printf("(%d번 댓글은 존재하지 않습니다.)\n", inputedId);
			return;
		}

		System.out.printf("수정할 내용) ");
		String replyBody = sc.nextLine();

		articleService.replyModify(inputedId, replyBody);

		System.out.printf("== %d번 댓글 수정완료 ==\n", inputedId);

	}

	private void reply(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		Article selectArticle = articleService.getArticle(inputedId);

		if (selectArticle == null) {
			System.out.println("(해당 게시물은 존재하지 않습니다.)");
			return;
		}

		System.out.printf("댓글 내용 입력) ");
		String replyBody = sc.nextLine();
		int replyMemberId = Container.session.loginedMemberId;

		int replyId = articleService.addReply(selectArticle.id, replyBody, replyMemberId);

		System.out.printf("== %d번 게시물, %d번 댓글 입력 완료 ==\n", selectArticle.id, replyId);
	}

	private void selectBoard(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoard(inputedId);

		if (board == null) {
			System.out.println("(해당 게시판은 존재하지 않습니다.)");
			return;
		}

		System.out.printf("== (%s 게시판)이 선택되었습니다. ==\n", board.boardName);

		Container.session.selectedBoardId = board.boardId;
	}

	private void boardAdd(String cmd) {

		if (Container.session.managerLoginStatus() == false) {            //'관리자'만 게시판 생성 가능
			System.out.println("('관리자'로 로그인 후 이용가능)");
			return;
		}

		System.out.printf("게시판 이름 입력) ");
		String boardName = sc.nextLine();

		int boardId = articleService.addBoard(boardName);

		System.out.printf("== (%d) %s 게시판 생성 완료 ==\n", boardId, boardName);

	}

	private void detail(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		Article selectArticle = articleService.getArticle(inputedId);

		if (selectArticle == null) {
			System.out.println("(해당 게시물은 존재하지 않습니다.)");
			return;
		}

		System.out.printf("== %d번 게시물 상세보기 == \n", inputedId);

		Article article = articleService.detailArticle(inputedId);
		Member member = memberService.getMemberByMemberId(article.memberId);
		Board board = articleService.getBoard(article.boardId);
		List<Recommand> recommands = articleService.getRecommands(inputedId);

		articleService.addView(inputedId);                          //상세보기 할 때마다 조회수 추가

		List<View> views = articleService.getViews(inputedId);

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성일 : %s\n", article.regDate);
		System.out.printf("수정일 : %s\n", article.updateDate);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("등록된 게시판 : %s\n", board.boardName);
		System.out.printf("작성자 : %s\n", member.name);
		System.out.printf("추천수 : %d\n", recommands.size());
		System.out.printf("조회수 : %d\n", views.size());
		System.out.printf("----- 댓글 -----\n");

		List<Reply> replies = articleService.getRepliesByArticleId(article.id);

		if (replies.size() <= 0) {
			System.out.println("(등록된 댓글이 없습니다.)");
			return;
		}

		for (Reply reply : replies) {
			Member replyMember = memberService.getMemberByMemberId(reply.replyWriterId);
			System.out.printf("%d번 - %s / 작성자 - %s\n", reply.replyId, reply.replyBody, replyMember.name);
		}

	}

	private void delete(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article selectArticle = articleService.getArticle(inputedId);

		if (selectArticle == null) {
			System.out.println("(해당 게시물은 존재하지 않습니다.)");
			return;
		}

		if (selectArticle.memberId != Container.session.loginedMemberId) {  
			System.out.println("(권한이 없습니다.)");                 //20.11.19 작성자만 삭제가능하도록 권한 확인
			return;
		}

		articleService.deleteArticle(inputedId);

		System.out.printf("== %d번 게시물 삭제 완료 ==\n", inputedId);
	}

	private void modify(String cmd) {
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Article selectArticle = articleService.getArticle(inputedId);

		if (selectArticle == null) {
			System.out.println("(해당 게시물은 존재하지 않습니다.)");
			return;
		}

		if (selectArticle.memberId != Container.session.loginedMemberId) { 
			System.out.println("(권한이 없습니다.)");                //20.11.19 작성자만 삭제가능하도록 권한 확인
			return;
		}

		System.out.printf("수정할 제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 입력) ");
		String body = sc.nextLine();

		articleService.modifyArticle(selectArticle.id, title, body);
		System.out.printf("== %d번 게시물 수정 완료 ==\n", inputedId);

	}

	private void list(String cmd) {

		Board board = articleService.getBoard(Container.session.selectedBoardId);

		List<Article> boardArticles = articleService.getBoardArticlesByBoardId(board.boardId); 

		if (boardArticles.size() <= 0) {
			System.out.printf("(현재 (%s 게시판) 내 등록된 게시물이 없습니다.)\n", board.boardName);
			return;
		}

		System.out.printf("== (%s 게시판) 게시물 리스트 ==\n", board.boardName);
		System.out.println("번호 / 제목 / 작성자 / 작성일 / 조회수 / 추천수");

		List<Article> articles = articleService.getBoardArticlesForPrintByBoardId(board.boardId); 
		
		for (Article article : articles) {
			String writerName = article.extra_memberName;
			List<View> views = articleService.getViews(article.id);              //20.11.19 조회수 추가
			List<Recommand> recommands = articleService.getRecommands(article.id);  //20.11.19 추천수 추가
			System.out.printf("%d / %s / %s / %s / %d / %d\n", article.id, article.title, writerName, article.regDate,recommands.size(), views.size());
		}
	}

	private void add(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		System.out.printf("제목 입력) ");
		String title = sc.nextLine();
		System.out.printf("내용 입력) ");
		String body = sc.nextLine();
		int memberId = Container.session.loginedMemberId;
		int boardId = Container.session.selectedBoardId;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("== %d번 게시물 생성 완료 ==\n", id);

	}

}
