package JDBCtest.dto;

import java.util.Map;

public class Recommand {

	public Recommand(Map<String, Object> recommandMap) {
		this.recommandId = (int)recommandMap.get("recommandId");
		this.recommandArticleId = (int)recommandMap.get("recommandArticleId");
		this.recommandMemberId = (int)recommandMap.get("recommandMemberId");
	}
	public int recommandId;
	public int recommandArticleId;
	public int recommandMemberId;
	
	
}
