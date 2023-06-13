package com.javalec.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.command.A_IdCheckCommand;
import com.javalec.command.A_LoginCommand;
import com.javalec.command.A_LogoutCommand;
import com.javalec.command.A_MainCommand;
import com.javalec.command.A_ProductCommand;
import com.javalec.command.A_joinCommand;
import com.javalec.command.Acommand;
import com.javalec.command.J_PDetailCommand;
import com.javalec.command.J_insertCartCommand;
import com.javalec.command.J_userDeleteCommand;
import com.javalec.command.J_userPageCommand;
import com.javalec.command.J_userUpdateCommand;
import com.javalec.command.O_changeFAQStatusCommand;
import com.javalec.command.O_deleteAnswerCommand;
import com.javalec.command.O_deleteQuestionCommand;
import com.javalec.command.O_getFAQCommand;
import com.javalec.command.O_getNDetailCommand;
import com.javalec.command.O_getNoticeCommand;
import com.javalec.command.O_getQnaCommand;
import com.javalec.command.O_getQnaDetailCommand;
import com.javalec.command.O_getRDetailCommand;
import com.javalec.command.O_getReviewCommand;
import com.javalec.command.O_updateFAQCommand;
import com.javalec.command.O_updateNoticeCommand;
import com.javalec.command.O_writeAnswerCommand;
import com.javalec.command.O_writeCommentCommand;
import com.javalec.command.O_writeFAQCommand;
import com.javalec.command.O_writeNoticeCommand;
import com.javalec.command.O_writeQuestionCommand;
import com.javalec.command.T_cartUpdateCommand;
import com.javalec.command.T_cartlistCommnd;
import com.javalec.command.T_orderCommand;
import com.javalec.command.T_orderlistCommand;
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
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		switch (com) {
		case ("/idCheck.do"):
			idCheckDo(request, response);
			break;
		case ("/loginCheck.do"):
			loginCheckdo(request, response);
			break;
		default:
			actionDo(request, response);
		}

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
		case ("/A_JoinView.do"): // 최초 접속시 실행할 주소
			command = new A_joinCommand(); // DB에서 불러오는 것 ( 메인데이터베이스 전
			command.execute(request, response);
			viewPage = "A_loginView.jsp"; // 화면을 보여주는 곳
			break;
		case ("/A_logout.do"): // 최초 접속시 실행할 주소
			command = new A_LogoutCommand(); // DB에서 불러오는 것 ( 메인데이터베이스 전
			command.execute(request, response);
			viewPage = "A_MainView.do"; // 화면을 보여주는 곳
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

		case ("/J_userProfilePage.do"):
			command = new J_userPageCommand();
			command.execute(request, response);
			viewPage = "J_userProfilePage.jsp";
			break;
		case ("/j_userUpdate.do"):
			command = new J_userUpdateCommand();
			command.execute(request, response);
			command = new J_userPageCommand();
			command.execute(request, response);
			System.out.println("왜그럴까나..");
			viewPage = "J_userProfilePage.jsp";
			break;

		case ("/j_userUpdateCancel.do"):
			viewPage = "A_MainView.jsp";
			break;
		case ("/j_userDelete.do"):
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
		case ("/O_adminNotice.do"):
			command = new O_getNoticeCommand();
			command.execute(request, response);
			viewPage = "O_adminNBoard.jsp";
			break;
		case ("/O_NDetail.do"):
			command = new O_getNDetailCommand();
			command.execute(request, response);
			viewPage = "O_NDetail.jsp";
			break;
		case ("/O_adminNDetail.do"):
			command = new O_getNDetailCommand();
			command.execute(request, response);
			viewPage = "O_adminNDetail.jsp";
			break;

		case ("/O_writeNoticeView.do"):
			viewPage = "O_writeNoticeView.jsp";
			break;
		case ("/O_writeNotice.do"):
			command = new O_writeNoticeCommand();
			command.execute(request, response);
			viewPage = "O_adminNotice.do";
			break;
		case ("/O_updateNotice.do"):
			command = new O_updateNoticeCommand();
			command.execute(request, response);
			viewPage = "O_adminNotice.do";
			break;
		case ("/O_changeNoticeStatus.do"): // FAQ 삭제 및 복구 db에서 수정 후 목록 페이지 연결
			command = new O_changeFAQStatusCommand();
			command.execute(request, response);
			viewPage = "O_adminNotice.do";
			break;
		case ("/O_FAQ.do"):
			command = new O_getFAQCommand();
			command.execute(request, response);
			viewPage = "O_FBoard.jsp";
			break;
		case ("/O_adminFAQ.do"):
			command = new O_getFAQCommand();
			command.execute(request, response);
			viewPage = "O_adminFBoard.jsp";
			break;
		case ("/O_writeViewFAQ.do"): // FAQ 작성 페이지
			viewPage = "O_writeFAQ.jsp";
			break;
		case ("/O_writeFAQ.do"): // 작성한 FAQ db insert 후 목록 페이지 연결
			command = new O_writeFAQCommand();
			command.execute(request, response);
			viewPage = "O_adminFAQ.do";
			break;
		case ("/O_updateViewFAQ.do"): // FAQ 수정 페이지
			command = new O_getFAQCommand();
			command.execute(request, response);
			viewPage = "O_updateFAQ.jsp";
			break;
		case ("/O_updateFAQ.do"): // FAQ 수정 페이지
			command = new O_updateFAQCommand();
			command.execute(request, response);
			viewPage = "O_adminFAQ.do";
			break;
		case ("/O_deleteViewFAQ.do"): // FAQ 삭제 페이지
			command = new O_getFAQCommand();
			command.execute(request, response);
			viewPage = "O_deleteFAQ.jsp";
			break;
		case ("/O_changeFAQStatus.do"): // FAQ 삭제 및 복구 db에서 수정 후 목록 페이지 연결
			command = new O_changeFAQStatusCommand();
			command.execute(request, response);
			viewPage = "O_adminFAQ.do";
			break;
		case ("/O_QNA.do"): // qna 목록 들고와 순서대로 보여줌
			command = new O_getQnaCommand();
			command.execute(request, response);
			viewPage = "O_QBoard.jsp";
			break;
		case ("/O_adminQNA.do"): // qna 목록 들고와 순서대로 보여줌
			command = new O_getQnaCommand();
			command.execute(request, response);
			viewPage = "O_adminQBoard.jsp";
			break;
		case ("/O_getQnaDetail.do"): // 작성된 qna 의 내용 볼 수 있음
			command = new O_getQnaDetailCommand();
			command.execute(request, response);
			viewPage = "O_QnaDetail.jsp";
			break;
		case ("/O_adminGetQnaDetail.do"): // 작성된 qna 의 내용 볼 수 있음
			command = new O_getQnaDetailCommand();
			command.execute(request, response);
			viewPage = "O_adminQnaDetail.jsp";
			break;
		case ("/O_writeQuestionView.do"): // qna 작성 view 오픈
			viewPage = "O_writeQuestionView.jsp";
			break;
		case ("/O_writeQuestion.do"): // qna insert
			command = new O_writeQuestionCommand();
			command.execute(request, response);
			viewPage = "O_QNA.do";
			break;
		case ("/O_writeAnswerView.do"): // 답변 작성 뷰
			command = new O_getQnaDetailCommand();
			command.execute(request, response);
			viewPage = "O_writeAnswerView.jsp";
			break;
		case ("/O_writeAnswer.do"): // qna insert
			command = new O_writeAnswerCommand();
			command.execute(request, response);
			viewPage = "O_adminQNA.do";
			break;
		case ("/O_updateQnA.do"): // qna update
			viewPage = "O_adminQNA.do";
			break;
		case ("/O_deleteQuestion.do"): // 질문에 대한 답변까지 모두 삭제
			command = new O_deleteQuestionCommand();
			command.execute(request, response);
			viewPage = "O_adminQNA.do";
			break;
		case ("/O_deleteAnswer.do"): // 답변만 삭제
			command = new O_deleteAnswerCommand();
			command.execute(request, response);
			viewPage = "O_adminQNA.do";
			break;
		case ("/O_Review.do"): // 답변만 삭제
			command = new O_getReviewCommand();
			command.execute(request, response);
			viewPage = "O_RBoard.jsp";
			break;
		case ("/O_adminReview.do"): // 답변만 삭제
			command = new O_getReviewCommand();
			command.execute(request, response);
			viewPage = "O_adminRBoard.jsp";
			break;
		case ("/O_RDetail.do"):
			command = new O_getRDetailCommand();
			command.execute(request, response);
			viewPage = "O_RDetail.jsp";
			break;
		case ("/O_adminRDetail.do"):
			command = new O_getRDetailCommand();
			command.execute(request, response);
			viewPage = "O_adminRDetail.jsp";
			break;
		case ("/O_writeComment.do"):
			command = new O_writeCommentCommand();
			command.execute(request, response);
			viewPage = "O_RDetail.do";
			break;
		case ("/O_adminWriteComment.do"):
			command = new O_writeCommentCommand();
			command.execute(request, response);
			viewPage = "O_adminRDetail.do";
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
			command1 = new T_orderlistCommand();
			command.execute(request, response);
			command1.execute(request, response);
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

	private void idCheckDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Acommand command = null;
		command = new A_IdCheckCommand();
		command.execute(request, response);
	}

	private void loginCheckdo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Acommand command = null;
		command = new A_LoginCommand();
		command.execute(request, response);
	}

}
