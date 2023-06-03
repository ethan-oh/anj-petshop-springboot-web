package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;
import com.javalec.dto.O_NoticeDto;

public class O_getNDetailCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		O_NoticeDao dao = new O_NoticeDao();
		O_NoticeDto dto = dao.getNoticeDetail(seq);
		
		request.setAttribute("NDetail", dto);
	}

}
