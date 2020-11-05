package test.controller;

import java.util.Scanner;

import test.container.Container;
import test.dto.Member;
import test.service.MemberService;

public class MemberController extends Controller{

	MemberService memberService;
	Scanner sc;

	public MemberController() {
		memberService = Container.memberService;
		sc = Container.scanner;
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
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		Container.session.loginedMemberNum = 0;

		System.out.println("로그아웃 되었습니다.");

	}

	private void whoami(String cmd) {
		if (Container.session.loginStatus() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}
		int loginedMemberNum = Container.session.loginedMemberNum;

		Member member = memberService.getMemberByMemberNum(loginedMemberNum);

		System.out.println("== 회원정보 ==");
		System.out.printf("회원번호 : %d\n", loginedMemberNum);
		System.out.printf("회원아이디 : %s\n", member.mId);
		System.out.printf("회원이름 : %s\n", member.mName);

	}

	private void login(String cmd) {

		Member member = null;

		System.out.printf("아이디 입력 : ");
		String loginId = sc.nextLine();

		member = memberService.getMemberById(loginId);

		if (member == null) {
			System.out.println("해당 아이디는 존재하지 않습니다.");
			return;
		}

		System.out.printf("비밀번호 입력 : ");
		String loginPw = sc.nextLine();

		if (member.mPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		Container.session.loginedMemberNum = member.mNum;

		System.out.printf("%s님, 반갑습니다.\n", member.mName);

	}

	private void join(String cmd) {

		System.out.printf("아이디 입력 : ");
		String mId = sc.nextLine();

		boolean checkUsableMemberId = memberService.checkUsableMemberId(mId);
		if (checkUsableMemberId == false) {
			System.out.println("이미 사용중인 아이디 입니다.");
			return;
		}

		System.out.printf("비밀번호 입력 : ");
		String mPw = sc.nextLine();
		System.out.printf("이름 입력 : ");
		String mName = sc.nextLine();

		int mNum = memberService.join(mId, mPw, mName);

		System.out.printf("== %d번 회원 회원가입 완료 ==\n", mNum);
		System.out.printf("회원번호 : %d\n", mNum);
		System.out.printf("회원아이디 : %s\n", mId);
		System.out.printf("회원이름 : %s\n", mName);
		

	}

}
