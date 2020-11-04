package test3;

import java.util.Scanner;

import test3.container.Container;
import test3.controller.ArticleController;
import test3.controller.Controller;
import test3.controller.MemberController;

public class App {

	private MemberController memberController;
	private ArticleController articleController;

	public App() {
		memberController = new MemberController();
		articleController = new ArticleController();

	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("system exit")) {
				break;
			}

			Controller controller = getControllerByCmd(cmd);
			controller.doCommand(cmd);
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
