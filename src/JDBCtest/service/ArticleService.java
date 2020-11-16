package JDBCtest.service;

import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dao.MemberDao;
import JDBCtest.dto.Article;

public class ArticleService {
	
	ArticleDao articleDao;
	
	public ArticleService() {
		
		articleDao = Container.articleDao;
	}

	public int add(int boardId, String title, String body, int memberId) {
		
		return articleDao.add( boardId,  title,  body,  memberId);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public void modifyArticle(int inputedId, String title, String body) {
		articleDao.modifyArticle(inputedId, title,  body);
		
	}

	public void deleteArticle(int inputedId) {
		articleDao.deleteArticle(inputedId);
		
	}

}
