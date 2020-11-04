package test3.dao;

import java.util.ArrayList;
import java.util.List;

import test3.dto.Member;

public class MemberDao {
	private List<Member> members;
	private int lastId;

	public MemberDao() {
		members = new ArrayList<>();
		lastId = 0;

		makeTestData();
	}

	private void makeTestData() {
		for(int i = 1; i < 6; i ++) {
			join("asd", "asd", "asd");
		}
		
	}

	public int join(String loginId, String loginPw, String name) {
		Member member = new Member();

		member.id = lastId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;

		members.add(member);

		lastId = member.id;

		return member.id;
	}

	public Member getMemberByLoginId(String inputedLoginId) {
		for (Member member : members) {
			if (member.loginId.equals(inputedLoginId)) {
				return member;
			}
		}
		return null;
	}

	public boolean getEqualsLoginIdBy(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}
		return false;

	}

	public Member getEqualsLoginMemberBy(int isLoginedMemberNum) {
		for(Member member : members) {
			if(member.id == isLoginedMemberNum) {
				return member;
			}
		}
		return null;
	}

}

