package JDBCtest.container;

import java.util.Scanner;

import JDBCtest.controller.ArticleController;
import JDBCtest.dao.ArticleDao;
import JDBCtest.service.ArticleService;

public class Container {
	public static ArticleDao articleDao; 
	
	public static ArticleService articleService;
	
	public static ArticleController articleController;

	public static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
		
		articleDao = new ArticleDao();
		
		articleService = new ArticleService();
		
		articleController = new ArticleController();
		
	}
	
}
