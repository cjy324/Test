package JDBCtest.controller;

import java.util.Scanner;

import JDBCtest.container.Container;
import JDBCtest.dto.Member;
import JDBCtest.service.MemberService;

public class MemberController {

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

	}

	private void login() {
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

		System.out.printf("%s님 로그인 완료\n", member.name);
		Container.session.loginedMemberId = member.memberId;
	}

	private void join() {
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

		System.out.printf("%d번 회원 회원가입 완료\n", memberId);
	}

}
