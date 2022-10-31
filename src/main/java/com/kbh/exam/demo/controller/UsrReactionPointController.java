package com.kbh.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbh.exam.demo.service.ReactionPointService;
import com.kbh.exam.demo.vo.Rq;

@Controller
public class UsrReactionPointController {

	// 인스턴스 변수

	@Autowired
	private Rq rq;
	@Autowired
	private ReactionPointService reactionPointService;

	// 액션메서드
	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	public String doGoodReaction(String relTypeCode, String replaceUri, int relId) {
		boolean actorCanMakeReaction = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(), relTypeCode,
				relId).isSuccess();
		if (actorCanMakeReaction == false) {
			return rq.jsHistoryBackOnView("이미 처리 되었습니다");
		}
		reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
 
		return rq.jsReplace("좋아요",replaceUri);
	}

	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	public String doBadReaction(String relTypeCode, String replaceUri, int relId) {
		boolean actorCanMakeReaction = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(), relTypeCode,
				relId).isSuccess();
		if (actorCanMakeReaction == false) {
			return rq.jsHistoryBackOnView("이미 처리 되었습니다");
		}
		reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return rq.jsReplace("싫어요",replaceUri);
	}

}