package JDBCtest.dto;

import java.util.Map;

public class Board {
	
	
	public int boardId;
	public String boardName;
	
	/*
	 * public Board(int boardId, String boardName) { this.boardId = boardId;
	 * this.boardName = boardName; }
	 */

	public Board(Map<String, Object> boardMap) {
		this.boardId = (int)boardMap.get("boardId");
		this.boardName = (String)boardMap.get("boardName");
	}

}
