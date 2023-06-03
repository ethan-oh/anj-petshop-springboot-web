package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.A_dao;
import com.javalec.dto.A_dto;

public class A_ProductCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
			String queryName = request.getParameter("query");
		    String queryContent = request.getParameter("content");
		    String sortOrder = request.getParameter("sortOrder"); // 추가: 가격순 정렬을 위한 파라미터

		    A_dao dao = new A_dao();
		    ArrayList<A_dto> dtos = dao.A_ProductView(queryName, queryContent, sortOrder); // 수정: sortOrder 전달
		    request.setAttribute("A_ProductView", dtos);

	}

}
