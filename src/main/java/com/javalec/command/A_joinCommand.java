package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.A_dao;

public class A_joinCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

			String id = request.getParameter("id");
		    String pw = request.getParameter("passwd");
		    String name = request.getParameter("name");
		    String tel = request.getParameter("tel");
		    String email = request.getParameter("email");
		    int postcode = Integer.parseInt(request.getParameter("postcode"));
		    String address = request.getParameter("address");
		    String detailaddress = request.getParameter("detailaddress"); // 
		    
		    A_dao dao = new A_dao();
		    dao.join(id, pw, name, tel, email, postcode, address, detailaddress); //
		    System.out.println("여기까지는 오고있긴 하냐? ㅅㅂ  ");                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	}

}
