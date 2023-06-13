package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.dao.J_Dao;

public class J_userUpdateCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
//		String userid = request.getParameter("userid");
//		String userid = "do";
		
		HttpSession session = request.getSession();
	 	
	 	String userid = (String) session.getAttribute("USERID");
		
		String userpasswd = request.getParameter("userpasswd");
		int userpostcode = Integer.parseInt(request.getParameter("userpostcode"));
		String useraddress = request.getParameter("useraddress");
		String userdetailaddress = request.getParameter("userdetailaddress");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String usertel = phone1 + "-" + phone2 + "-" + phone3;
		
		String inputmail = request.getParameter("useremail");
		String userdomain = request.getParameter("userdomain");
		String useremail = inputmail + "@" + userdomain;
		
//		String userpostcodeStr = request.getParameter("userpostcode");
//		int userpostcode = 0; // 기본값 설정
//
//		if (!userpostcodeStr.isEmpty()) {
//		    userpostcode = Integer.parseInt(userpostcodeStr);
//		}
		
		
		System.out.println("여까지 왔나");
		System.out.println("도메인" + userdomain);
		System.out.println("아이디" + userid);
		System.out.println("패스워드" + userpasswd);
		System.out.println("우편번호" + userpostcode);
		System.out.println("주소" + useraddress);
		System.out.println("상세주소" + userdetailaddress);
		System.out.println("이메일" + useremail);
		System.out.println("번호" + usertel);
		
		
		
		J_Dao dao = new J_Dao();
		dao.updateUser(userid, userpasswd, userpostcode, useraddress, userdetailaddress, usertel, useremail);
		
	}

}
