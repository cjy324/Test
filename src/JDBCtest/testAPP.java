package JDBCtest;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.controller.ArticleController;
import JDBCtest.controller.Controller;
import JDBCtest.controller.MemberController;
import JDBCtest.mysqlutil.MysqlUtil;

public class testAPP {

	Scanner sc;
	MemberController memberController;
	ArticleController articleController;

	public testAPP() {
		sc = Container.scanner;
		memberController = Container.memberController;
		articleController = Container.articleController;

	}

	public void run() {

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");

			boolean needToExit = false;

			if (cmd.equals("exit")) {
				System.out.println("종료");
				needToExit = true;
			}

			Controller controller = getControllerByCmd(cmd);

			if (controller != null) {
				controller.doCmd(cmd);
			}

			if (needToExit) {
				MysqlUtil.closeConnection();
				break;
			}

		}
		sc.close();
	}

	public Controller getControllerByCmd(String cmd) {
		if (cmd.startsWith("member ")) {
			return memberController;
		} else if (cmd.startsWith("article ")) {
			return articleController;
		}
		return null;
	}

}
