package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.J_Dao;

public class J_userUpdateCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
//		String userid = request.getParameter("userid");
		String userid = "do";
		String userpasswd = request.getParameter("userpasswd");
		String userpostcode = request.getParameter("userpostcode");
		String useraddress = request.getParameter("useraddress");
		String userdetailaddress = request.getParameter("userdetailaddress");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String usertel = phone1 + "-" + phone2 + "-" + phone3;
		
		String inputmail = request.getParameter("useremail");
		String userdomain = request.getParameter("userdomain");
		String useremail = inputmail + "@" + userdomain;
		
		System.out.println(userdomain);
		System.out.println(userid);
		System.out.println(userpasswd);
		System.out.println(userpostcode);
		System.out.println(useraddress);
		System.out.println(userdetailaddress);
		System.out.println(useremail);
		System.out.println(usertel);
		
		
		
		J_Dao dao = new J_Dao();
		dao.updateUser(userid, userpasswd, userpostcode, useraddress, userdetailaddress, usertel, useremail);
		

	}

}
