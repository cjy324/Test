package JDBCtest.controller;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.dto.Member;
import JDBCtest.service.MemberService;

public class MemberController extends Controller {

	Scanner sc;
	MemberService memberService;

	public MemberController() {
		sc = Container.scanner;
		memberService = Container.memberService;

	}

	public void doCmd(String cmd) {
		// 회원가입
		if (cmd.equals("member join")) {
			join();
		}
		// 로그인
		else if (cmd.equals("member login")) {
			login();
		}
		// 후엠아이
		else if (cmd.equals("member whoami")) {
			whoami(cmd);
		}
		// 로그아웃
		else if (cmd.equals("member logout")) {
			logout(cmd);
		}

	}

	private void logout(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 상태가 아닙니다.)");
			return;
		}

		Container.session.loginedMemberId = 0;

		System.out.println("== 로그아웃 완료 ==");

	}

	private void whoami(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("(로그인 후 이용가능)");
			return;
		}

		int loginMemberId = Container.session.loginedMemberId;

		Member member = memberService.getMemberByMemberId(loginMemberId);
		System.out.println("== 회원 정보 ==");
		System.out.printf("회원 번호 : %d\n", member.memberId);
		System.out.printf("회원 아이디 : %s\n", member.loginId);
		System.out.printf("회원 이름 : %s\n", member.name);
		System.out.printf("회원 유형 : %s\n", member.getType());   //20.11.19 추가

	}

	private void login() {

		if (Container.session.loginStatus() == true) {
			System.out.println("(로그아웃 후 이용가능)");
			return;
		}

		System.out.println("== 로그인 ==");
		System.out.printf("아이디) ");
		String loginId = sc.nextLine();

		Member member = memberService.getMemberByloginId(loginId);

		if (member == null) {
			System.out.println("해당 아이디 회원이 없음");
			return;
		}

		System.out.printf("비밀번호) ");
		String loginPw = sc.nextLine();

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호 틀렸습니다.");
			return;
		}

		if (member.isAdmin() == false) {
			System.out.printf("(%s님, 반갑습니다.)\n", member.name);
			Container.session.loginedMemberId = member.memberId;
			return;
		}

		System.out.printf("('관리자'로 로그인하였습니다.)\n", member.name);
		Container.session.loginedMemberId = member.memberId;
		Container.session.mangerMemberId = member.memberId;
		

	}

	private void join() {

		if (Container.session.loginStatus() == true) {
			System.out.println("(로그아웃 후 이용가능)");
			return;
		}

		System.out.println("== 회원가입 ==");
		System.out.printf("아이디 입력) ");
		String joinId = sc.nextLine();

		boolean checkUsableLoginId = memberService.checkUsableLoginIdBy(joinId);

		if (checkUsableLoginId == true) {
			System.out.println("해당 아이디는 이미 사용중");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String joinPw = sc.nextLine();
		System.out.printf("이름 입력) ");
		String name = sc.nextLine();

		int memberId = memberService.join(joinId, joinPw, name);

		System.out.printf("== %d번 회원 회원가입 완료 ==\n", memberId);
	}

}
