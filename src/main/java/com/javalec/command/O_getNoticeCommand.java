package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;
import com.javalec.dto.O_NoticeDto;
import com.javalec.dto.O_PageNationDto;

public class O_getNoticeCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_NoticeDao dao = new O_NoticeDao();
		
		
		int itemsPerPage = 2; // 한 페이지당 출력할 게시글 수
		int currentPage = Integer.parseInt(request.getParameter("page")); // 현재페이지 초기 세팅
		int totalCount = dao.getNoticeCount();
		int totalPages = (int)(Math.ceil(totalCount / itemsPerPage)); // 전체 페이지 계산
		int pageSize = 5;
		int startIndex = (currentPage - 1) * itemsPerPage;
		
		System.out.println(" itemsPerPage:" + itemsPerPage);
		System.out.println(" currentPage:" + currentPage);
		System.out.println(" totalCount:" + totalCount);
		System.out.println(" totalPages:" + totalPages);
		System.out.println(" pageSize:" + pageSize);
		System.out.println(" startIndex:" + startIndex);
		
		ArrayList<O_NoticeDto> dtos = dao.getNoticeList(startIndex, itemsPerPage);
		
		
		
		
		O_PageNationDto page = new O_PageNationDto(itemsPerPage, totalCount, currentPage, totalPages, pageSize);
		
		request.setAttribute("noticeList", dtos);
		request.setAttribute("p", page);
		
	}

}
