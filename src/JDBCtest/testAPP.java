package JDBCtest;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.controller.ArticleController;
import JDBCtest.controller.Controller;
import JDBCtest.controller.MemberController;
import JDBCtest.mysqlutil.MysqlUtil;
import JDBCtest.service.ArticleService;
import JDBCtest.service.MemberService;

public class testAPP {

	Scanner sc;
	MemberController memberController;
	ArticleController articleController;

	public testAPP() {
		sc = Container.scanner;
		memberController = Container.memberController;
		articleController = Container.articleController;

		 init();
	}

	private void init() { // DB연결
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");

		// defult 게시판 
		ArticleService articleService = Container.articleService;
		Container.session.selectedBoardId = articleService.getDefultBoardId(1);

		// defult 로그인 멤버 
		MemberService memberService = Container.memberService;
		Container.session.loginedMemberId = memberService.getDefultMemberId(1);

	}

	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			// DB연결
			MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");
			MysqlUtil.setDevMode(true);  //어떤 쿼리가 실행되었는지 확인할 수 있는 모드
			// DB 연결 종료 필요한지 확인
			boolean needToExit = false;

			if (cmd.equals("exit")) {
				System.out.println("종료");
				needToExit = true;
				// DB 연결 종료 필요
			}
			// 명령어를 작성할 때마다 controller를 선택해야하니 Controller라는 상위개념이 필요
			else {
				Controller controller = getControllerByCmd(cmd);
				if (controller != null) {
					controller.doCmd(cmd);
				}
			}

			// DB 연결 종료 필요
			MysqlUtil.closeConnection();

			if (needToExit) {
				break;
			} // 만약, DB 연결 종료됐으면 프로그램도 종료

		}
		sc.close();
	}

	private Controller getControllerByCmd(String cmd) {
		if (cmd.startsWith("member ")) {
			return memberController;
		} else if (cmd.startsWith("article ")) {
			return articleController;
		}
		return null;
	}
}
