package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.T_Dao;
import com.javalec.dto.T_productDto;

public class T_orderlistCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	    T_Dao dao = new T_Dao();
	    String ordernum = (String) request.getAttribute("ordernum"); // ordernum 값을 가져옴
	    //String ordernum = request.getParameter("ordernum"); // ordernum 값을 가져옴
	    System.out.println("command ordernum!@!@! = " + ordernum);
	    
	    ArrayList<T_productDto> dtos = dao.orderproduct(ordernum, request); // 주문 상품 목록을 가져옴
	    
	    request.setAttribute("orderList", dtos);
	}

}
