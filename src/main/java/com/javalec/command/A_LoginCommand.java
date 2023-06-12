package com.javalec.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.A_dao;
import com.javalec.dto.A_dto;

public class A_LoginCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		A_dao dao = new A_dao();
		String result = dao.loginChack(id, pw);

		System.out.println(" 됨? ");

		response.setContentType("text/plain; charset=utf-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
