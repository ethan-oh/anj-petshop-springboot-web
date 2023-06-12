package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.W_SalesToday_Dao;
import com.javalec.dto.W_SalesToday_Dto;

public class W_SalesTodayCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		W_SalesToday_Dao dao = new W_SalesToday_Dao();
		
		W_SalesToday_Dto dto = dao.salesTable();
		
		int yesterday = dao.salesYesterday();
		int avg = dao.salesAvg();
		
		request.setAttribute("dto", dto);
		request.setAttribute("yesterday", yesterday);
		request.setAttribute("avg", avg);
		
	}

}
