package JDBCtest.dto;

import java.util.Map;

public class View {

	public View(Map<String, Object> viewMap) {
		this.viewArticleId = (int)viewMap.get("viewArticleId");
	}
	public int viewArticleId;
}
