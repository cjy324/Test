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

	public Member getMemberByMId(String inputedId) {
		return memberDao.getMemberByMId(inputedId);
	}

	public boolean checkUsableIdByMId(String mId) {
		Member member = memberDao.getMemberByMId(mId);
		if (member == null) {
			return true;
		}
		return false;
	}

	public Member getMemberByMNum(int num) {
		return memberDao.getMemberByMNum(num);
	}

}
