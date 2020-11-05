package test.controller;

import java.util.Scanner;

import test.container.Container;
import test.dto.Member;
import test.service.MemberService;

public class MemberController extends Controller{

	Scanner sc;
	MemberService memberService;

	public MemberController() {
		sc = Container.scanner;
		memberService = new MemberService();
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
		// whoami
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
			System.out.println("로그인 후 이용 가능");
			return;
		}

		Container.session.loginedMemberNum = 0;
		System.out.println("== 로그아웃 == ");
	}

	private void whoami(String cmd) {

		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 후 이용 가능");
			return;
		}

		Member member = memberService.getMemberByMemberNum(Container.session.loginedMemberNum);

		System.out.printf("== 회원 정보 ==\n");
		System.out.printf("회원 번호 : %d\n", member.mNum);
		System.out.printf("회원 아이디 : %s\n", member.mId);
		System.out.printf("회원 이름 : %s\n", member.mName);

	}

	private void login(String cmd) {

		System.out.printf("아이디 입력) ");
		String inputdeId = sc.nextLine();

		Member member = memberService.getMemberById(inputdeId);

		if (member == null) {
			System.out.println("없는 아이디 입니다.");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String inputdePw = sc.nextLine();
		if (member.mPw.equals(inputdePw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		Container.session.loginedMemberNum = member.mNum;
		System.out.printf("%s 회원님 로그인 완료.\n", member.mName);

	}

	private void join(String cmd) {
		System.out.printf("아이디 입력) ");
		String mId = sc.nextLine();

		boolean checkUsableMemberId = memberService.checkUsableMemberId(mId);

		if (checkUsableMemberId == true) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}

		System.out.printf("비밀번호 입력) ");
		String mPw = sc.nextLine();
		System.out.printf("이름 입력) ");
		String mName = sc.nextLine();

		int mNum = memberService.join(mId, mPw, mName);

		System.out.printf("%d번 회원 회원가입 완료.\n", mNum);
		System.out.printf("%s / %s / %s\n", mId, mPw, mName);
	}

}
