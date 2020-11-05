package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Article;

public class ArticleDao {

	private List<Article> articles;
	private int lastArticleNum;

	public ArticleDao() {
		articles = new ArrayList<>();
		lastArticleNum = 0;

		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i < 6; i++) {
			add("title" + i, "body" + i, 1);
		}
		for (int i = 6; i < 11; i++) {
			add("title" + i, "body" + i, 2);
		}
	}

	public int add(String title, String body, int writerNum) {

		Article article = new Article();

		article.aNum = lastArticleNum + 1;
		article.title = title;
		article.body = body;
		article.writerNum = writerNum;
		articles.add(article);
		lastArticleNum = article.aNum;

		return article.aNum;
	}

	public List<Article> articles() {
		return articles;
	}

	public Article getArticleByNum(int inputedNum) {
		for(Article article : articles) {
			if(article.aNum == inputedNum) {
				return article;
			}
		}
		return null;
	}

	public Article getArticleByKeyword(String inputedKeyword) {
		for(Article article : articles) {
			if(article.body.contains(inputedKeyword)) {
				return article;
			}
		}
		return null;
	}

}
