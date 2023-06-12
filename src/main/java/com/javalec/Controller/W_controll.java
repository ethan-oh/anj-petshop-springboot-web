package com.javalec.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.command.Acommand;
import com.javalec.command.W_ProductInsertCommand;
import com.javalec.command.W_ProductLIstCommand;
import com.javalec.command.W_ProductUpdataActionCommand;
import com.javalec.command.W_ProductUpdateCommand;
import com.javalec.command.W_UserLIstCommand;

/**
 * Servlet implementation class W_controll
 */
@WebServlet("")
public class W_controll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public W_controll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			actionDo(request, response);
	}
	

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String viewPage = null;
		Acommand command = null; // 커맨드 호출
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();

		String com = uri.substring(conPath.length());
		// 각 조원 별 컨트롤러 추가
		switch (com) {
		case ("/W_ProductUpdate.do"):
			command = new W_ProductUpdateCommand();
			command.execute(request, response);
			viewPage = "W_ProductUpdate.jsp";
			break;
		case ("/W_ProductInsert.do"):
			command = new W_ProductInsertCommand();
			command.execute(request, response);
			viewPage = "W_ProductInsert.jsp";
			break;
		case ("/W_ProductUpdataAction.do"):
			command = new W_ProductUpdataActionCommand();
			command.execute(request, response);
			viewPage = "W_ProductList.jsp";
			break;
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
		
}//end
