package test.container;

import java.util.Scanner;

import test.controller.ArticleController;
import test.controller.MemberController;
import test.dao.ArticleDao;
import test.dao.MemberDao;
import test.service.ArticleService;
import test.service.MemberService;
import test.session.Session;

public class Container {

	public static Scanner scanner;
	public static Session session;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static ArticleController articleController;
	public static MemberController memberController;
	
	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		
		articleController = new ArticleController();
		memberController = new MemberController();
	}

}
