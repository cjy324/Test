package JDBCtest.container;

import java.util.Scanner;

import JDBCtest.controller.ArticleController;
import JDBCtest.controller.MemberController;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dao.MemberDao;
import JDBCtest.service.ArticleService;
import JDBCtest.service.MemberService;
import JDBCtest.session.Session;

public class Container {

	public static Scanner scanner;
	public static MemberController memberController;
	public static ArticleController articleController;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static Session session;
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	
	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}

}
