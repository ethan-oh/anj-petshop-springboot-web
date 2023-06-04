package com.javalec.command;

import java.awt.TextArea;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;

public class O_writeNoticeCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_NoticeDao dao = new O_NoticeDao();
		String adminid = request.getParameter("adminid");
		String n_title = request.getParameter("n_title");
		String n_content = request.getParameter("n_content");
		
		// textArea로 저장된 값에서 
		
		dao.writeNotice(adminid, n_title, n_content);
		
	}

}
