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
@WebServlet("/productlist")
public class W_productlist_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public W_productlist_controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		Acommand command = null;
		command = new W_ProductLIstCommand();
		command.execute(request, response);

	}
	

	
		
}//end
