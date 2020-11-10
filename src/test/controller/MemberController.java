package test.controller;

import java.util.Scanner;

import test.container.Container;
import test.dto.Member;
import test.service.MemberService;

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
			join(cmd);
		}
		// 로그인
		else if (cmd.equals("member login")) {
			login(cmd);
		}
	}

	private void login(String cmd) {

		System.out.printf("아이디) ");
		String inputedId = sc.nextLine();

		Member member = memberService.getMemberByMId(inputedId);

		if (member == null) {
			System.out.println("해당 아이디 회원이 없습니다.");
			return;
		}

		System.out.printf("패스워드) ");
		String inputedPw = sc.nextLine();

		if (member.mPw.equals(inputedPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		System.out.printf("%s님, 로그인 완료\n", member.mName);

		Container.session.loginedMemberNum = member.mNum;
	}

	private void join(String cmd) {

		System.out.printf("아이디) ");
		String mId = sc.nextLine();

		boolean checkUsableId = memberService.checkUsableIdByMId(mId);

		if (checkUsableId == false) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}

		System.out.printf("패스워드) ");
		String mPw = sc.nextLine();

		System.out.printf("이름) ");
		String mName = sc.nextLine();

		int mNum = memberService.join(mId, mPw, mName);

		System.out.printf("%d번 회원 회원가입 완료\n", mNum);

	}
}
