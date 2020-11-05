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

	public Member getMemberById(String inputdeId) {
		return memberDao.getMemberById(inputdeId);
	}

	public boolean checkUsableMemberId(String mId) {
		Member member = memberDao.checkEqualsMemberId(mId);
		if (member == null) {
			return false;
		}
		return true;
	}

	public Member getMemberByMemberNum(int loginedMemberNum) {
		return memberDao.getMemberByNum(loginedMemberNum);
	}

}
