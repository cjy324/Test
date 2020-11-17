package JDBCtest.dto;

import java.util.Map;

public class Reply {
	
	public Reply(Map<String, Object> replyMap) {
		this.replyId = (int)replyMap.get("replyId");
		this.replyBody = (String)replyMap.get("replyBody");
		this.replyArticleId = (int)replyMap.get("replyArticleId");
		this.replyWriterId = (int)replyMap.get("replyWriterId");
	}
	public int replyId;
	public String replyBody;
	public int replyArticleId;
	public int replyWriterId;

}
