package com.javalec.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.javalec.dao.W_ProductList_Dao;
import com.javalec.dto.W_ProductList_Dto;

public class W_ProductLIstCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		 String pagenum = request.getParameter("pagenum");
		    String pagesize = request.getParameter("pagesize");
		    String selectType = request.getParameter("selectType");
		    String selectText = request.getParameter("selectText");
		    
		    W_ProductList_Dao dao = new W_ProductList_Dao();

		    ArrayList<W_ProductList_Dto> dtos = dao.productList(pagenum, pagesize, selectType, selectText);
		    
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
