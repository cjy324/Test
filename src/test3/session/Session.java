package test3.session;

public class Session {

	public int isLoginedMemberNum;


	public boolean loginedStatus() {
		return isLoginedMemberNum != 0;
	}
	public void isLoginedMemberNum(int id) {
		isLoginedMemberNum = id;
		
	}
}
