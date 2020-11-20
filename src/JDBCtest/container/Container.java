package JDBCtest.container;

import java.util.Scanner;

import JDBCtest.controller.ArticleController;
import JDBCtest.controller.MemberController;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dao.MemberDao;
import JDBCtest.service.ArticleSerivice;
import JDBCtest.service.MemberService;
import JDBCtest.session.Session;

public class Container {

	public static Scanner scanner;
	public static MemberController memberController;
	public static ArticleController articleController;
	public static ArticleSerivice articleSerivice;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static Session session;
	public static ArticleDao articleDao;

	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleSerivice = new ArticleSerivice();
		
		memberController = new MemberController();
		articleController = new ArticleController();
		
	}
	

}
