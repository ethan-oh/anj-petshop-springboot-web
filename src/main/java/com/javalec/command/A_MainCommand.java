package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.A_dao;
import com.javalec.dto.A_dto;

public class A_MainCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 메인페이지 전체상품 데이터베이스 연결 (Command)
		A_dao dao = new A_dao();
		ArrayList<A_dto> dtos = dao.A_MainView();
		request.setAttribute("A_MainView", dtos);
		// 메인페이지 오더량 순으로 노출
		ArrayList<A_dto> dtos2 = dao.A_MainView2();
		request.setAttribute("A_MainView2", dtos2);
		// 메인페이지 랜덤순으로 전체상품 노출
		ArrayList<A_dto> dtos3 = dao.A_MainView3();
		request.setAttribute("A_MainView3", dtos3);

		System.out.println("여기까지는 오고있긴 하냐? 여기까지 온다는건 랜덤박스까지 오는거구나 성장했어 ");
	}

}
