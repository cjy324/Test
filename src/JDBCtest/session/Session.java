package JDBCtest.session;

public class Session {

	public int loginedMemberId;
	public int selectedBoardId;

	public boolean loginStatus() {
		// TODO Auto-generated method stub
		return loginedMemberId != 0;
	}

}
