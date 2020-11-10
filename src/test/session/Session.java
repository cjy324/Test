package test.session;

public class Session {

	public int loginedMemberNum;
	public int seletedBoardNum;

	public boolean loginStatus() {
		return loginedMemberNum != 0;
	}

}
