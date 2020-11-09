package test.session;

public class Session {

	public int loginedMemberNum;
	public int selectedBoardId;

	public boolean loginStatus() {
		return loginedMemberNum != 0;
	}

	/*//App의 init에서 공지사항을 defult값으로 설정해서 이제 필요 없음
	 * public boolean boardStatus() {
	 * 
	 * return selectedBoardId != 0; }
	 */



}
