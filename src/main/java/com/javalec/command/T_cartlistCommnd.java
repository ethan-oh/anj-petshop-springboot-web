package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.javalec.dao.T_Dao;
import com.javalec.dto.T_productDto;

public class T_cartlistCommnd implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	 	
	 	String userid = (String) session.getAttribute("USERID");
		
		T_Dao dao = new T_Dao();	// BDao에 생성자에 db연결하는것을 불러옴
		ArrayList<T_productDto> dtos = dao.list(userid);	// list 받기위해 같은 list타입으로 불러와주기
		
		request.setAttribute("list", dtos);

	}

}
