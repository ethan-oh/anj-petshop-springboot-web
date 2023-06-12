package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_ReviewDao;

public class O_writeCommentCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String userid = request.getParameter("userid");
		String comment = request.getParameter("comment");
		
		O_ReviewDao dao = new O_ReviewDao();
		int maxRef = dao.getMaxRef();
		
		dao.writeComment(seq, userid, maxRef, comment);
		
	}

}
