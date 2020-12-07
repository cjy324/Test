package JDBCtest.controller;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.service.BuildService;

public class BuildController extends Controller {

	Scanner sc;
	BuildService buildService;

	public BuildController() {
		sc = Container.scanner;
		buildService = Container.buildService;
	}

	public void doCmd(String cmd) {
		if (cmd.equals("build site")) {
			buildSite(cmd);
		}
	}

	private void buildSite(String cmd) {
		System.out.println("== html 생성을 시작합니다. ==");
		buildService.makeHtml();
		System.out.println("(html 생성 완료)");
	}
}
