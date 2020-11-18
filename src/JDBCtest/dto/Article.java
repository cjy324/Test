package JDBCtest.dto;

import java.util.Map;

public class Article {

	
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int boardId;
	public int memberId;
	public String extra_memberName;
	
	
	/*
	 * public Article(int id, String regDate, String updateDate, String title,
	 * String body, int boardId, int memberId) {
	 * 
	 * this.id = id; this.regDate = regDate; this.updateDate =updateDate; this.title
	 * = title; this.body = body; this.boardId = boardId; this.memberId = memberId;
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public Article() {
	 * 
	 * }
	 */

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.boardId = (int) articleMap.get("boardId");
		this.memberId = (int) articleMap.get("memberId");	
		
		if(articleMap.containsKey("extra_memberName")) {    //이 변수는 리스트 출력용으로만 생성되기 때문에 
															//단지 articles 가져오기를 했을때는 퀴리에 입력값이 없으므로, 
															//값을 불러올 수 없다.
															//따라서 if를 사용해 null point exception를 방지
			this.extra_memberName = (String) articleMap.get("extra_memberName");
		}
	}
}
