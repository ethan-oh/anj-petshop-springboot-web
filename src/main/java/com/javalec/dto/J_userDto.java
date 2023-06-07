package com.javalec.dto;

public class J_userDto {		// user table dto
	
	// field
	String userid;
	String userpasswd;
	String username;
	String usertel;
	String useremail;
	String userpostcode;
	String useraddress;
	String userdetailaddress;
	int mileage; 		// 사용자가 사용 가능한 적립금
	int usedmileage; 		// 사용자가 사용 완료한 적립금
	
	public J_userDto() {
		// TODO Auto-generated constructor stub
	}

	public J_userDto(String userid, String userpasswd, String username, String usertel, String useremail,
			String userpostcode, String useraddress, String userdetailaddress, int mileage, int usedmileage) {
		super();
		this.userid = userid;
		this.userpasswd = userpasswd;
		this.username = username;
		this.usertel = usertel;
		this.useremail = useremail;
		this.userpostcode = userpostcode;
		this.useraddress = useraddress;
		this.userdetailaddress = userdetailaddress;
		this.mileage = mileage;
		this.usedmileage = usedmileage;
	}
	
	// 4. user 테이블에서 사용 가능한 적립금과 사용한 적립금 띄워주기
	public J_userDto(String userid, int mileage, int usedmileage) {
		super();
		this.userid = userid;
		this.mileage = mileage;
		this.usedmileage = usedmileage;
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

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
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

	public String getUserdetailaddress() {
		return userdetailaddress;
	}

	public void setUserdetailaddress(String userdetailaddress) {
		this.userdetailaddress = userdetailaddress;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getUsedmileage() {
		return usedmileage;
	}

	public void setUsedmileage(int usedmileage) {
		this.usedmileage = usedmileage;
	}
	
	
	
}
