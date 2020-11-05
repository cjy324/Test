package test;

import java.util.Scanner;

import test.container.Container;
import test.controller.ArticleController;
import test.controller.Controller;
import test.controller.MemberController;

public class App {

	Scanner sc;
	MemberController memberController;
	ArticleController articleController;

	public App() {
		sc = Container.scanner;
		memberController = new MemberController();
		articleController = new ArticleController();
	}

	public void run() {

		while (true) {
			System.out.printf("명령어: ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			Controller controller = getControllerByCmd(cmd);
			controller.doCmd(cmd);
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
