package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.W_ProductInsert_Dao;

public class W_ProductInsertCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String pid = request.getParameter("pid");
		String pname = request.getParameter("pname");
		String pcategory = request.getParameter("pcategory");
		int pprice = Integer.parseInt(request.getParameter("pprice"));
		int pstock = Integer.parseInt(request.getParameter("pstock"));
		int available =  Integer.parseInt(request.getParameter("available"));
		String pthumbnail = request.getParameter("pthumbnail");
		String pth2 = request.getParameter("pth2");
		String pth3 = request.getParameter("pth3");
		
		W_ProductInsert_Dao dao = new W_ProductInsert_Dao();
		dao.productInsertAction(pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3);
		
		
	}

}
