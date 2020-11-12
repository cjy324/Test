package JDBCtest;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.controller.ArticleController;

public class testAPP {

	Scanner sc = Container.scanner; 
	ArticleController articleController;
	
	public testAPP() {
		articleController = Container.articleController;
	}
	
	public void run() {
		
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
		
			
			if(cmd.startsWith("article ")) {
				articleController.doCmd(cmd);
			}
			else if(cmd.equals("exit")) {
				break;
			}
			
		}
		
		sc.close();
		
	}

}
