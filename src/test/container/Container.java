package test.container;

import java.util.Scanner;

import test.dao.ArticleDao;
import test.dao.MemberDao;
import test.service.ArticleService;
import test.service.MemberService;
import test.session.Sesseion;

public class Container {

	public static Scanner scanner;
	public static Sesseion session;
	public static MemberDao memberDao;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static ArticleService articleService;
	
	static {
		scanner = new Scanner(System.in);
		session = new Sesseion();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		memberService = new MemberService();
		articleService = new ArticleService();
	}

}
