package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;

public class O_updateFAQCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		String n_title = request.getParameter("n_title");
		String n_content = request.getParameter("n_content");
		
		O_NoticeDao dao = new O_NoticeDao();
		
		dao.updateFAQ(seq, n_title, n_content);
		
	}

}
