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

	}
}
