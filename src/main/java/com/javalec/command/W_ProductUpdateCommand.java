package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.W_ProductList_Dao;
import com.javalec.dto.W_ProductList_Dto;

public class W_ProductUpdateCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		String pid = request.getParameter("pid");
		
		W_ProductList_Dao dao = new W_ProductList_Dao();
		
		ArrayList<W_ProductList_Dto> dto = dao.productUpdataView(pid);
		
		request.setAttribute("W_UpdataView", dto);
		
		
		
		
		
		
	}

}
