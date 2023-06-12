package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;
import com.javalec.dto.J_userDto;

public class J_userDeleteCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
//		String uid = request.getParameter("userid");
		String uid = "do";
		
		J_Dao dao = new J_Dao();
		dao.deleteUser(uid);

	}

}
