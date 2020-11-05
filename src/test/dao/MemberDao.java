package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Member;

public class MemberDao {

	private List<Member> members;
	private int lastMemberNum;

	public MemberDao() {
		members = new ArrayList<>();
		lastMemberNum = 0;

		makeTestData();
	}

	private void makeTestData() {
		join("aaa", "aaa", "aaa");
		join("bbb", "bbb", "bbb");

	}

	public int join(String mId, String mPw, String mName) {
		Member member = new Member();

		member.mNum = lastMemberNum + 1;
		member.mId = mId;
		member.mPw = mPw;
		member.mName = mName;
		members.add(member);
		lastMemberNum = member.mNum;

		return member.mNum;

	}

	public Member getMemberByEqualsId(String loginId) {
		for (Member member : members) {
			if (member.mId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberByEqualsNum(int memberNum) {
		for (Member member : members) {
			if (member.mNum == memberNum) {
				return member;
			}
		}
		return null;
	}

}
