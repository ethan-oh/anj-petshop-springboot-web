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
		// O_Notice.do에서 page값을 전달하지 않았을 때는 default로 1로 세팅해주기 위한 3항 연산자
		int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int totalCount = dao.getNoticeCount();
		double totalPages = (Math.ceil(totalCount / itemsPerPage)); // 전체 페이지 계산
		if (totalPages == Math.floor(totalPages)) { // 나눈 결과가 .0일 경우에는 +1을 해줘야 정확한 페이지 계산이 된다!
			totalPages += 1;
		}
		int pageSize = 5;
		int startIndex = (currentPage - 1) * itemsPerPage;
		
		//		System.out.println(" itemsPerPage:" + itemsPerPage);
		//		System.out.println(" currentPage:" + currentPage);
		//		System.out.println(" totalCount:" + totalCount);
		//		System.out.println(" totalPages:" + totalPages);
		//		System.out.println(" pageSize:" + pageSize);
		//		System.out.println(" startIndex:" + startIndex);
		
		String queryName = request.getParameter("query");
		String queryContent = request.getParameter("content");
		
		ArrayList<O_NoticeDto> dtos = dao.getNoticeList(queryName, queryContent, startIndex, itemsPerPage); // 검색결과 불러와 저장
		
		
		
		O_PageNationDto page = new O_PageNationDto(itemsPerPage, totalCount, currentPage, (int)totalPages, pageSize);
		
		request.setAttribute("noticeList", dtos);
		request.setAttribute("p", page); // 페이지네이션 값 전달
		
	}

}
