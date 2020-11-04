package test3.container;

import java.util.Scanner;

import test3.dao.ArticleDao;
import test3.dao.MemberDao;
import test3.service.ArticleService;
import test3.service.MemberService;
import test3.session.Session;

public class Container {
	public static Scanner scanner;
	public static Session session;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}