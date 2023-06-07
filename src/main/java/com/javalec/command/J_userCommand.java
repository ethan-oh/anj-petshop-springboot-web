package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;
import com.javalec.dto.J_userDto;
import com.javalec.dto.J_userOrderDto;

public class J_userCommand implements Acommand { 			// user table 관련 작업들 수행 command 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
//		String userid = request.getParameter("userid");
		String userid = "do";
		
		J_Dao dao = new J_Dao();
		J_userDto dto = dao.userMileage(userid);
		
		request.setAttribute("userMileage", dto); 			// 마일리지 보여주기
		System.out.println("여긴 왔어?");
		

	}

}
