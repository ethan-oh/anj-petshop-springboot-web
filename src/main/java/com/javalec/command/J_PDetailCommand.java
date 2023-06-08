package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;
import com.javalec.dto.J_pdPageDto;

public class J_PDetailCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pid = request.getParameter("pid");
//		String pid = "F1002"; 		// 테스트용 임시 제품 번호 ****** 나중에 꼭 command 처리 바꾸기!!!!
		
		
		J_Dao dao = new J_Dao();
		J_pdPageDto dto = dao.productDetailView(pid);
		
		request.setAttribute("productDetail", dto);

	}

}
