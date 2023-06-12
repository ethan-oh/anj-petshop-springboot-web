package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;
import com.javalec.dao.O_ReviewDao;
import com.javalec.dto.O_CommentsDto;
import com.javalec.dto.O_NoticeDto;
import com.javalec.dto.O_ReviewDto;

public class O_getRDetailCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		O_ReviewDao dao = new O_ReviewDao();
		O_ReviewDto dto = dao.getReviewDetail(seq);
		
		ArrayList<O_CommentsDto> dtos = dao.getCommentsList(seq);
		
		request.setAttribute("RDetail", dto);
		request.setAttribute("seq", seq);
		request.setAttribute("CommentList", dtos);
	}

}
