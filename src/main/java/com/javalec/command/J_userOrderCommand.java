package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;
import com.javalec.dto.J_userOrderDto;

public class J_userOrderCommand implements Acommand { 			// orders 테이블 관련 작업들 수행 command 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		String userid = request.getParameter("userid");
		String userid = "do";
		
		J_Dao dao = new J_Dao();
		J_userOrderDto dto = dao.userOrderMileage(userid);
		
		request.setAttribute("orderMileage", dto); 			// 마일리지 보여주기
		
	}

}
