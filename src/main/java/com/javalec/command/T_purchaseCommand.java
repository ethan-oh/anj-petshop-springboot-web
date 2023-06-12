package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.T_Dao;
import com.javalec.dto.T_productDto;

public class T_purchaseCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		T_Dao dao = new T_Dao();	// BDao에 생성자에 db연결하는것을 불러옴
		ArrayList<T_productDto> dtos = dao.list();	// list 받기위해 같은 list타입으로 불러와주기
		request.setAttribute("list", dtos);
		
		// 선택 갯수에 비례해서 총 주문금액 맞춰주기위함
					String[] selectedProducts = request.getParameterValues("selectedProducts");
					if (selectedProducts != null && selectedProducts.length > 0) { // 선택된 상품들에 대한 처리 로직을 // 예시로 선택된 상품
						// ID 리스트를 출력하는 코드
						for (String productId : selectedProducts) {
							System.out.println("Selected Product ID: " + productId);
						}
					}
	}
	
	}

