package com.kbh.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbh.exam.demo.service.ReplyService;
import com.kbh.exam.demo.util.Ut;
import com.kbh.exam.demo.vo.ResultData;
import com.kbh.exam.demo.vo.Rq;

@Controller
public class UsrReplyController {

	// 인스턴스 변수
	@Autowired
	private ReplyService replyService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {

		if (Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode을(를) 입력해주세요");
		}

		if (Ut.empty(relId)) {
			return rq.jsHistoryBack("relId을(를) 입력해주세요");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body을(를) 입력해주세요");
		}

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);

		int id = (int) writeReplyRd.getData1();

		if (Ut.empty(replaceUri)) {
			switch (relTypeCode) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", id);
				break;
			}

		}

		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}
}