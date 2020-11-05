package test.service;

import test.container.Container;
import test.dao.MemberDao;
import test.dto.Member;

public class MemberService {

	MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public int join(String mId, String mPw, String mName) {
		return memberDao.join(mId, mPw, mName);
	}

	public Member getMemberById(String loginId) {
		return memberDao.getMemberByEqualsId(loginId);
	}

	public boolean checkUsableMemberId(String mId) {
		Member member = memberDao.getMemberByEqualsId(mId);
		if (member == null) {
			return true;
		}
		return false;
	}

	public Member getMemberByMemberNum(int MemberNum) {
		return memberDao.getMemberByEqualsNum(MemberNum);
	}

}
