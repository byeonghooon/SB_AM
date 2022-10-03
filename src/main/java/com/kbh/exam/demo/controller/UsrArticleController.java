package com.kbh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbh.exam.demo.service.ArticleService;
import com.kbh.exam.demo.util.Ut;
import com.kbh.exam.demo.vo.Article;
import com.kbh.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;

	// 액션 메서드
	@RequestMapping("usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		ResultData writeArticleRd = articleService.writeArticle(title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticle(id);

		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}

	@RequestMapping("usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "Article List", articles);
	}

	@RequestMapping("usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		articleService.deleteArticle(id);

		return id + "번 게시물을 삭제했습니다";
	}

	@RequestMapping("usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}
		articleService.modifyArticle(id, title, body);

		return article;
	}

	@RequestMapping("usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다", id));
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), article);
	}

}