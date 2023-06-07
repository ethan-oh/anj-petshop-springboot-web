package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;
import com.javalec.dto.J_userOrderDto;

public class J_userOrderCommand implements Acommand { 			// orders 연동

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		
		J_Dao dao = new J_Dao();
		J_userOrderDto dto = dao.userOrderView(userid);
		
		request.setAttribute("userPage", dto);
	}

}
