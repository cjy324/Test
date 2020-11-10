package test;

import java.util.Scanner;

import test.container.Container;
import test.controller.ArticleController;
import test.controller.Controller;
import test.controller.MemberController;
import test.service.ArticleService;
import test.service.MemberService;

public class App {

	Scanner sc;
	MemberController memberController;
	ArticleController articleController;

	public App() {
		sc = Container.scanner;
		memberController = Container.memberController;
		articleController = Container.articleController;

		makeTestData();

		init();
	}

	private void init() {
		ArticleService articleService = Container.articleService;
		Container.session.seletedBoardNum = articleService.getBoardDefultNum();

	}

	private void makeTestData() {

		MemberService memberService = Container.memberService;

		memberService.join("asd", "asd", "asd");
		memberService.join("bbb", "bbb", "bbb");

		ArticleService articleService = Container.articleService;

		articleService.addBoard("공지사항");
		articleService.addBoard("자유");

		for (int i = 1; i < 6; i++) {
			articleService.add(1, "title" + i, "body" + i, 1);
		}
		for (int i = 6; i < 11; i++) {
			articleService.add(2, "title" + i, "body" + i, 2);
		}

	}

	public void run() {
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}

			Controller controller = getControllerByCmd(cmd);
			if (controller != null) {
				controller.doCmd(cmd);
			}
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
