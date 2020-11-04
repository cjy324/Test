package test3.dao;

import java.util.ArrayList;
import java.util.List;

import test3.dto.Article;

public class ArticleDao {

	private List<Article> articles;
	private int lastId;

	public ArticleDao() {
		articles = new ArrayList<>();
		lastId = 0;
		
		makeTestData();
	}

	private void makeTestData() {
		for(int i = 1; i < 6; i ++) {
			add("title"+i, "body"+i, i);
		}
		
	}

	public int add(String title, String body, int writerNum) {

		Article article = new Article();

		article.id = lastId + 1;
		article.title = title;
		article.body = body;
		article.writerNum = writerNum;
		articles.add(article);
		lastId = article.id;

		return article.id;
	}

	public List<Article> getArticles() {
		return articles;
	}

}
