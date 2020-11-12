package JDBCtest.service;

import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dao.ArticleDao;
import JDBCtest.dto.Article;

public class ArticleService {
	
	ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}
	

	public List<Article> getArticles() {	
		return articleDao.getArticles();
	
	}


	public int add(String title, String body) {
		return articleDao.add(title,body);
	}


	public void deleteArticleById(int inputedId) {
		articleDao.deleteArticleById(inputedId);
		
	}


	public void modifyArticleById(int inputedId, String modifyTitle, String modifyBody) {
		articleDao.modifyArticleById(inputedId,modifyTitle,modifyBody);
		
	}

}
