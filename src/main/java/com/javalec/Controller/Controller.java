package com.javalec.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.command.A_MainCommand;
import com.javalec.command.A_ProductCommand;
import com.javalec.command.Acommand;
import com.javalec.command.J_PDetailCommand;
import com.javalec.command.J_insertCartCommand;
import com.javalec.command.J_userDeleteCommand;
import com.javalec.command.J_userPageCommand;
import com.javalec.command.J_userUpdateCommand;
import com.javalec.command.O_getNDetailCommand;
import com.javalec.command.O_getNoticeCommand;
import com.javalec.command.T_cartUpdateCommand;
import com.javalec.command.T_cartlistCommnd;
import com.javalec.command.T_orderCommand;
import com.javalec.command.T_purchaseCommand;
import com.javalec.command.T_updatePstockCommand;
import com.javalec.command.T_userinfoCommand;
import com.javalec.command.W_ProductInsertCommand;
import com.javalec.command.W_ProductUpdataActionCommand;
import com.javalec.command.W_ProductUpdateCommand;
import com.javalec.command.W_SalesTodayCommand;
import com.javalec.dao.T_Dao;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");

		String viewPage = null;
		Acommand command = null;
		Acommand command1 = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		switch (com) {
		case ("/"):
			viewPage = "A_mainView.do";
			break;
		case ("/A_MainView.do"): // 최초 접속시 실행할 주소
			command = new A_MainCommand(); // DB에서 불러오는 것 ( 메인데이터베이스 전
			command.execute(request, response);
			viewPage = "A_MainView.jsp"; // 화면을 보여주는 곳
			break;
		case ("/A_ProductView.do"):
			command = new A_ProductCommand(); // DB에서 불러오는 것
			command.execute(request, response);
			viewPage = "A_ProductView.jsp";
			break;			
		////////////////////////////////////////////
		//////////////////////////////////////////// 여기서부터 진영
		case ("/j_productClicked.do"): // 상품목록 페이지에서 상품 선택할 때
			command = new J_PDetailCommand();
			command.execute(request, response);
			viewPage = "J_productDetail.jsp"; // 제품 상세 페이지 보여주기
			break;

		case ("/j_insertCart.do"): // 장바구니 선택시 데이터베이스에 옵션 넣기
			command = new J_insertCartCommand();
			command.execute(request, response);
			viewPage = "cart.do";
			break;

		case ("/j_purchase.do"): // 바로 구매 눌렀을 때 (
			command = new J_insertCartCommand();
			command.execute(request, response);
			viewPage = "purchase.do";
			break;

		case ("/j_userPage.do"): // 유저페이지
			command = new J_userPageCommand();
			command.execute(request, response);
			viewPage = "J_userMyPage.jsp";
			break;
		
		case ("/J_userProfilePage.do") : 
			command = new J_userPageCommand();
			command.execute(request, response);
			viewPage = "J_userProfilePage.jsp";
			break;
		case ("/j_userUpdate.do") :	
			command = new J_userUpdateCommand();
			command.execute(request, response);
			command = new J_userPageCommand();
			command.execute(request, response);
			System.out.println("왜그럴까나..");
			viewPage = "J_userProfilePage.jsp";
			break;
			
		case("/j_userUpdateCancel.do") : 
			viewPage = "A_MainView.jsp";
			break;
		case("/j_userDelete.do"): 
			command = new J_userDeleteCommand();
			command.execute(request, response);
			viewPage = "A_MainView.jsp";
			break;	
		////////////////////////////////////////////
		//////////////////////////////////////////// 여기서부터 성민
		case ("/O_Notice.do"):
			command = new O_getNoticeCommand();
			command.execute(request, response);
			viewPage = "O_NBoard.jsp";
			break;
		case ("/O_NDetail.do"):
			command = new O_getNDetailCommand();
			command.execute(request, response);
			viewPage = "O_NDetail.jsp";
			break;
		////////////////////////////////////////////
		//////////////////////////////////////////// 여기서부터 태영
		case ("/cart.do"):
			command = new T_cartlistCommnd();
			command.execute(request, response);
			viewPage = "/T_cart.jsp";
			break;
		case "/T_cart.do":
			command = new T_cartUpdateCommand();
			command.execute(request, response);
			viewPage = "cart.do";
			break;
		// cart테이블에서 삭제하기
		case "/delete.do":
			command = new T_cartlistCommnd();
			command.execute(request, response);
			viewPage = "cart.do";
			break;
		// 주문하기 누르면 purchase페이지로 가기
		case "/purchase.do":
			command = new T_userinfoCommand();
			command1 = new T_purchaseCommand();
			command.execute(request, response);
			command1.execute(request, response);
			viewPage = "T_purchase.jsp";
			break;
		// 구매하기 누르기
		case "/order.do":
			command = new T_orderCommand();
			//command1 = new T_updatePstockCommand();
			command.execute(request, response);
			//command1.execute(request, response);

			viewPage = "T_purchaseComplete.jsp";
			break;
		////////////////////////////////////////////
		//////////////////////////////////////////// 여기서부터 종욱
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
		
		case ("/W_Home.do"):
			command = new W_SalesTodayCommand();
			command.execute(request, response);
			viewPage = "W_Home.jsp";
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
