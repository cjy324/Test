package JDBCtest.service;

import java.io.File;
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

	
			//5개씩 페이징
			int articlesInAPage = 5;
			int startPoint = articles.size() - 1;
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
				html += "<td>" + articles.get(i).id + "</td>";
				html += "<td>" + articles.get(i).regDate + "</td>";
				html += "<td><a href=\"" + articles.get(i).id + ".html\">" + articles.get(i).title + "</a></td>";
				html += "<td><a href=\"" + articles.get(i).id + ".html\">" + articles.get(i).extra_memberName + "</a></td>";
				html += "</tr>";
			}

			fileName = board.code + "-list-" + listNo + ".html";
			
			html = template.replace("${TR}", html);
			if (listNo > 1) {
				html += "<div><a href=\"" + board.code + "-list-" + (listNo-1) + ".html\">이전페이지</a></div>";
			}
			if (listNo < 2) {
				html += "<div><a href=\"" + board.code + "-list-" + (listNo+1) + ".html\">다음페이지</a></div>";
			}
			
			html = head + html + foot;

			Util.writeFileContents("site/article/" + fileName, html);
			
			listNo++;
			
			//다음 5개씩 페이징
			startPoint -= (listNo - 1) * articlesInAPage;
			endPoint = startPoint - (articlesInAPage - 1);

			
			if (startPoint < 0) {
				return;
			}
			if (endPoint < 0) {
				endPoint = 0;
			}
			String html2 = "";
			for (int i = startPoint; i >= endPoint; i--) {
				html2 += "<tr>";
				html2 += "<td>" + articles.get(i).id + "</td>";
				html2 += "<td>" + articles.get(i).regDate + "</td>";
				html2 += "<td><a href=\"" + articles.get(i).id + ".html\">" + articles.get(i).title + "</a></td>";
				html2 += "<td><a href=\"" + articles.get(i).id + ".html\">" + articles.get(i).extra_memberName + "</a></td>";
				html2 += "</tr>";
			}

			fileName = board.code + "-list-" + listNo + ".html";

			html2 = template.replace("${TR}", html2);
			
			if (listNo > 1) {
				html2 += "<div><a href=\"" + board.code + "-list-" + (listNo-1) + ".html\">이전페이지</a></div>";
			}
			if (listNo < 2) {
				html2 += "<div><a href=\"" + board.code + "-list-" + (listNo+1) + ".html\">다음페이지</a></div>";
			}
			
			html2 = head + html2 + foot;
			
			Util.writeFileContents("site/article/" + fileName, html2);
			
		}
		

		// 게시물 별 파일 생성
		
		for(Board board : boards) {
			List<Article> articles = articleSerivice.getBoardArticlesByCodeForPrint(board.code);
			int articlesSize = articles.size();
			int x = 0;
			int beforeArticleId = articles.get(x).id; 
		for (Article article : articles) {
//			Board board = articleSerivice.getBoardById(article.boardId);
			
			// String fileName = "article_" + article.id + ".html";
			String html = "<meta charset=\"UTF-8\">";
			html += "<div>번호 : " + article.id + "</div>";  //4
			html += "<div>날짜 : " + article.regDate + "</div>";
			html += "<div>작성자 : " + article.extra_memberName + "</div>";
			html += "<div>제목 : " + article.title + "</div>";
			html += "<div>내용 : " + article.body + "</div>";
			html += "<div>게시판 : " + board.name + " 게시판" + "</div>";

			
			
			if (article.id > beforeArticleId) {
				html += "<div><a href=\"" + articles.get(beforeArticleId-1).id + ".html\">이전글</a></div>";
			}
			if (article.id < articlesSize) {
				html += "<div><a href=\"" + articles.get(beforeArticleId+1).id + ".html\">다음글</a></div>";
			}

			html = head + html + foot;

			Util.writeFileContents("site/article/" + article.id + ".html", html);
			x++;
			beforeArticleId = articles.get(x).id;
		}
		}
	}

}