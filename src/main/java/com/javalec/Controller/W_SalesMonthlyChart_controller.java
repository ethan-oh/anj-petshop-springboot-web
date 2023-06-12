package com.javalec.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.command.Acommand;
import com.javalec.command.W_SalesDailyCommand;
import com.javalec.command.W_SalesMothlyChartCommand;
import com.javalec.command.W_SalesMothlyCommand;

/**
 * Servlet implementation class W_controll
 */
@WebServlet("/salesmonthlychart")
public class W_SalesMonthlyChart_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public W_SalesMonthlyChart_controller() {
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
		command = new W_SalesMothlyChartCommand();
		command.execute(request, response);

	}
	

	
		
}//end
