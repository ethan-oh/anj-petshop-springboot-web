package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.dao.T_Dao;
import com.javalec.dto.T_userinfoDto;

public class T_userinfoCommand implements Acommand {

	 @Override
	 public void execute(HttpServletRequest request, HttpServletResponse response) {
		 
		 
		 	HttpSession session = request.getSession();
		 	
		 	String userid = (String) session.getAttribute("USERID");
		 	System.out.println("userid = " + userid);
		 	
	        T_Dao dao = new T_Dao();
	        T_userinfoDto dto = dao.userlist(userid);
	        request.setAttribute("content_View", dto);

			
			//배송지 정보를 보여주는 form에도 동일한 속성을 설정 
	        request.setAttribute("delivery_View", dto);
			 
	    }
	}

