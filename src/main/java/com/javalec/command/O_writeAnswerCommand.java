package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_QnaDao;

public class O_writeAnswerCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_QnaDao dao = new O_QnaDao();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String category = "관리자";
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		String userid = request.getParameter("userid");
		String adminid = request.getParameter("adminid");
		
		dao.writeAnswer(seq, category, qna_title, qna_content, userid, adminid);

	}

}
