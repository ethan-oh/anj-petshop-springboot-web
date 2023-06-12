package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;

public class J_insertCartCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
//		String uid = request.getParameter("userid");
		String uid = "do";
		String pid = request.getParameter("pid");
		int qty = Integer.parseInt(request.getParameter("qty"));
		
		J_Dao dao = new J_Dao();
		dao.insertcart(uid, pid, qty);

	}

}
