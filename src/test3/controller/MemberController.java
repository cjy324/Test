package test3.controller;

import java.util.Scanner;

import test3.container.Container;
import test3.dto.Member;
import test3.service.MemberService;

public class MemberController extends Controller{

	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	Scanner sc = Container.scanner;

	public void doCommand(String cmd) {
		// 회원가입
		if (cmd.equals("member join")) {
			join(cmd);
		}
		// 로그인
		else if (cmd.equals("member login")) {
			login(cmd);
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
		if (Container.session.loginedStatus() == false) {
			System.out.println("현재 로그인 상태가 아닙니다.");
			return;
		}

		Container.session.isLoginedMemberNum = 0;
		System.out.println("== 로그아웃 되었습니다.==");

	}

	private void whoami(String cmd) {
		if (Container.session.loginedStatus() == false) {
			System.out.println("로그인 먼저 해 주세요.");
			return;
		}
		int memberNum = Container.session.isLoginedMemberNum;

		Member member = memberService.getMemberByLoginedMemberNum(memberNum);

		System.out.printf("== %d번 회원님 정보 ==\n", member.id);
		System.out.printf("아이디 : %s\n", member.loginId);
		System.out.printf("이름 : %s\n", member.loginPw);

	}

	private void login(String cmd) {
		
		if (Container.session.loginedStatus() == true) {
			System.out.println("먼저 로그아웃 해주세요.");
			return;
		}

		Member member = null;

		System.out.printf("아이디 : ");
		String inputedLoginId = sc.nextLine();

		member = memberService.getMemberByLoginId(inputedLoginId);

		if (member == null) {
			System.out.println("해당 아이디는 존재하지 않습니다.");
			return;
		}

		System.out.printf("비번 : ");
		String inputedLoginPw = sc.nextLine();
		if (member.loginPw.equals(inputedLoginPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		Container.session.isLoginedMemberNum(member.id);

		System.out.printf("== %s님 로그인 완료 ==\n", member.name);

	}

	private void join(String cmd) {

		String loginId;
		String loginPw;
		String name;

		System.out.printf("로그인아이디 : ");
		loginId = sc.nextLine();

		if (memberService.checkJoinableLoginIdBy(loginId) == false) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}

		System.out.printf("로그인비번 : ");
		loginPw = sc.nextLine();

		System.out.printf("이름 : ");
		name = sc.nextLine();

		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("== %d번 회원이 생성되었습니다. ==\n", id);
	}
}
