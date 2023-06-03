package com.javalec.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.command.Acommand;
import com.javalec.command.J_PDExplainCommand;
import com.javalec.command.J_PDetailCommand;
import com.javalec.command.O_getNDetailCommand;
import com.javalec.command.O_getNoticeCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
		System.out.println("요긴가??");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
		System.out.println("아니면 여긴가!");
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String viewPage = null;
		Acommand command = null;
		Acommand command1 = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
	
		switch(com) {
		case ("/"):
			viewPage = "A_mainView.do";
			break;
		case("/j_productClicked.do") :  		// 상품목록 페이지에서 상품 선택할 때
			command = new J_PDetailCommand();
			command1 = new J_PDExplainCommand();
			command.execute(request, response);
			command1.execute(request, response);
			viewPage = "J_productDetail.jsp"; 		// 제품 상세 페이지 보여주기
			break;
		case ("/O_Notice.do"):
			command = new O_getNoticeCommand();
			command.execute(request, response);
			viewPage = "O_NBoard.jsp";
			break;
		case ("/O_NDetail.do"):
			command = new O_getNDetailCommand();
			command.execute(request, response);
			viewPage = "O_NDetail.jsp";
	
		}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response) ;
		}	
	}


