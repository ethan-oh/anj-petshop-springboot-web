package com.javalec.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.javalec.dao.W_SalesDaily_Dao;
import com.javalec.dao.W_SalesMonthlyChart_Dao;
import com.javalec.dao.W_SalesMonthly_Dao;
import com.javalec.dto.W_SalesDaily_Dto;
import com.javalec.dto.W_SalesMonthly_Dto;
import com.javalec.dto.W_SalesMothlyChart_Dto;

public class W_SalesMothlyChartCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	
		String day = request.getParameter("day");
		
		
		W_SalesMonthlyChart_Dao dao = new W_SalesMonthlyChart_Dao();
		
		ArrayList<W_SalesMothlyChart_Dto> dtos = dao.salesMonthlyChart(day);
		
		// ArrayList를 JSON 형태로 변환
	    Gson gson = new Gson();
	    String json = gson.toJson(dtos);
	 
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");	
	    try {
	        PrintWriter out = response.getWriter();
	        out.print(json);
	        out.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
	}

}
