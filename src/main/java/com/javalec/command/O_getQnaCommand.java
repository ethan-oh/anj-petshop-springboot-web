package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;
import com.javalec.dao.O_QnaDao;
import com.javalec.dto.O_NoticeDto;
import com.javalec.dto.O_PageNationDto;
import com.javalec.dto.O_QnaDto;

public class O_getQnaCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		O_QnaDao dao = new O_QnaDao();
		
		int itemsPerPage = 10; // 한 페이지당 출력할 게시글 수
		// O_Notice.do에서 page값을 전달하지 않았을 때는 default로 1로 세팅해주기 위한 3항 연산자
		int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int totalCount = dao.getQnaCount(); // 전체 게시물 수 검색
		 // 전체 페이지 계산. 전체 게시물 수 / itemPerPage의 나머지가 없을 때는 그대로, 있을 때는 올림.
		double totalPages = (Math.ceil(totalCount / (double)itemsPerPage));
		
		int pageSize = 5; // 한 번에 보여줄 페이지의 개수
		int startIndex = (currentPage - 1) * itemsPerPage;
		
//		System.out.println(" itemsPerPage:" + itemsPerPage);
//		System.out.println(" currentPage:" + currentPage);
//		System.out.println(" totalCount:" + totalCount);
//		System.out.println(" totalPages:" + totalPages);
//		System.out.println(" pageSize:" + pageSize);
//		System.out.println(" startIndex:" + startIndex);
//		System.out.println("---------------------------");
		
		String queryName = request.getParameter("query");
		String queryContent = request.getParameter("content");
		System.out.println("queryName: " + queryName);
		System.out.println("queryContent: " + queryContent);
		
		ArrayList<O_QnaDto> dtos = dao.getQnaList(queryName, queryContent, startIndex, itemsPerPage); // 검색결과 불러와 저장
		
		O_PageNationDto page = new O_PageNationDto(itemsPerPage, totalCount, currentPage, (int)totalPages, pageSize);
		
		request.setAttribute("qnaList", dtos);
		request.setAttribute("p", page); // 페이지네이션 값 전달
		
	}

}
