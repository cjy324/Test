package test3.service;

import java.util.List;

import test3.container.Container;
import test3.dao.ArticleDao;
import test3.dto.Article;

public class ArticleService {

	ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;

	}

	public int add(String title, String body, int writerNum) {
		return articleDao.add(title, body, writerNum);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

}
