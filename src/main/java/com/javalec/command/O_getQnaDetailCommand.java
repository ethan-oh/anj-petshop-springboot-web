package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_QnaDao;
import com.javalec.dto.O_QnaDto;

public class O_getQnaDetailCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_QnaDao dao = new O_QnaDao();
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		O_QnaDto dto = dao.getQnaDetail(seq);
		
		request.setAttribute("qnaDetail", dto);
		
	}

}
