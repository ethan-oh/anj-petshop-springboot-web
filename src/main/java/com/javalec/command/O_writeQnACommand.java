package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_QnaDao;

public class O_writeQnACommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_QnaDao dao = new O_QnaDao();
		
		String category = request.getParameter("qCategory");
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");
		String userid = request.getParameter("userid");
		String adminid = "dummy";
		
		dao.writeQnA(category, qna_title, qna_content, userid, adminid);
		
	}

}
