package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;

public class O_changeFAQStatusCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_NoticeDao dao = new O_NoticeDao();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		dao.changeFAQStatus(seq, status);
	}
}
