package JDBCtest;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.controller.ArticleController;
import JDBCtest.controller.MemberController;

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

			if (cmd.startsWith("member ")) {
				memberController.doCmd(cmd);
			}

			else if (cmd.startsWith("article ")) {
				articleController.doCmd(cmd);
			} else if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}

		}

		sc.close();

	}

}
