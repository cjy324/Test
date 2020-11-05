package test.service;

import java.util.ArrayList;
import java.util.List;

import test.container.Container;
import test.dao.ArticleDao;
import test.dto.Article;

public class ArticleService {

	ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int add(String title, String body, int writerNum) {
		return articleDao.add(title, body, writerNum);
	}

	public List<Article> getArticles() {
		return articleDao.articles();
	}

	public Article getArticleByNum(int inputedNum) {
		return articleDao.getArticleByNum(inputedNum);
	}

	public List<Article> getSearchedArticlesByKeyword(String inputedKeyword) {

		List<Article> searchedArticle = new ArrayList<>();

		for (Article article : getArticles()) {
			article = articleDao.getArticleByKeyword(inputedKeyword);
			searchedArticle.add(article);
		}

		return searchedArticle;
	}

}
