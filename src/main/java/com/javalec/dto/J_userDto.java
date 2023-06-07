package com.javalec.dto;

public class J_userDto {		// user table dto
	
	// field
	String userid;
	String userpasswd;
	String username;
	String usertel;
	String usermail;
	String userpostcode;
	String useraddress;
	int point; 		// 사용자가 사용 가능한 적립금
	
	public J_userDto() {
		// TODO Auto-generated constructor stub
	}

	public J_userDto(String userid, String userpasswd, String username, String usertel, String usermail,
			String userpostcode, String useraddress, int point) {
		super();
		this.userid = userid;
		this.userpasswd = userpasswd;
		this.username = username;
		this.usertel = usertel;
		this.usermail = usermail;
		this.userpostcode = userpostcode;
		this.useraddress = useraddress;
		this.point = point;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpasswd() {
		return userpasswd;
	}

	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUsermail() {
		return usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	public String getUserpostcode() {
		return userpostcode;
	}

	public void setUserpostcode(String userpostcode) {
		this.userpostcode = userpostcode;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	


}
