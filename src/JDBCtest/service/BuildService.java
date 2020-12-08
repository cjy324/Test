package JDBCtest.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import JDBCtest.container.Container;
import JDBCtest.dto.Article;
import JDBCtest.dto.Board;
import JDBCtest.util.Util;

public class BuildService {

	ArticleSerivice articleSerivice;
	MemberService memberService;

	public BuildService() {
		articleSerivice = Container.articleSerivice;
		memberService = Container.memberService;
	}

	public void buildSite() {
		// site라는 폴더가 없을 경우 폴더 생성
		File site = new File("site");
		File articleFolder = new File("site/article");

		if (site.exists() == false) {
			site.mkdir();
		}
		if (articleFolder.exists() == false) {
			articleFolder.mkdir();
		}

		String head = Util.getFileContents("site_template/part/head.html");
		String foot = Util.getFileContents("site_template/part/foot.html");

		// 각 게시판 별 게시물리스트 페이지 생성
		List<Board> boards = articleSerivice.getBoards();

		String fileName = "";
		
	
		for (Board board : boards) {
			int listNo = 1;
			String html = "";

			String template = Util.getFileContents("site_template/article/list.html");

			List<Article> articles = articleSerivice.getBoardArticlesByCodeForPrint(board.code);
			List<Article> newArticles = new ArrayList<Article>();
			for(Article article : articles) {
				newArticles.add(article);
			}
	
			//newArticles 2개씩 페이징
			int articlesInAPage = 2;
			int startPoint = newArticles.size() - 1;
			startPoint -= (listNo - 1) * articlesInAPage;
			int endPoint = startPoint - (articlesInAPage - 1);

			if (startPoint < 0) {
				return;
			}
			if (endPoint < 0) {
				endPoint = 0;
			}
			for (int i = startPoint; i >= endPoint; i--) {
				html += "<tr>";
				html += "<td>" + newArticles.get(i).id + "</td>";
				html += "<td>" + newArticles.get(i).regDate + "</td>";
				html += "<td><a href=\"" + newArticles.get(i).id + ".html\">" + newArticles.get(i).title + "</a></td>";
				html += "<td><a href=\"" + newArticles.get(i).id + ".html\">" + newArticles.get(i).extra_memberName + "</a></td>";
				html += "</tr>";
			}

			fileName = board.code + "-list-" + listNo + ".html";

			html = template.replace("${TR}", html);

			html = head + html + foot;

			Util.writeFileContents("site/article/" + fileName, html);
			
		}
		

		// 게시물 별 파일 생성
		List<Article> articles = articleSerivice.getArticlesForPrint();

		for (Article article : articles) {
			Board board = articleSerivice.getBoardById(article.boardId);

			// String fileName = "article_" + article.id + ".html";
			String html = "<meta charset=\"UTF-8\">";
			html += "<div>번호 : " + article.id + "</div>";
			html += "<div>날짜 : " + article.regDate + "</div>";
			html += "<div>작성자 : " + article.extra_memberName + "</div>";
			html += "<div>제목 : " + article.title + "</div>";
			html += "<div>내용 : " + article.body + "</div>";
			html += "<div>게시판 : " + board.name + " 게시판" + "</div>";

			if (article.id > 1) {
				html += "<div><a href=\"" + (article.id - 1) + ".html\">이전글</a></div>";
			}
			if (article.id != (articles.size())) {
				html += "<div><a href=\"" + (article.id + 1) + ".html\">다음글</a></div>";
			}

			html = head + html + foot;

			Util.writeFileContents("site/article/" + article.id + ".html", html);
		}
	}

}
