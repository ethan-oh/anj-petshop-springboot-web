package com.javalec.dto;

public class J_userOrderDto {  		// order table의 데이터 가져오기
	
	// Field
	String ordernum;
	int count;
	int orderprice;
	String username;
	String userpostcode;
	String shipaddress;
	String usertel;
	String userid;
	String pid;
	String ordermessage;
	String payment;
	int point;
	
	public J_userOrderDto() {
		// TODO Auto-generated constructor stub
	}

	public J_userOrderDto(String ordernum, int count, int orderprice, String username, String userpostcode,
			String shipaddress, String usertel, String userid, String pid, String ordermessage, String payment,
			int point) {
		super();
		this.ordernum = ordernum;
		this.count = count;
		this.orderprice = orderprice;
		this.username = username;
		this.userpostcode = userpostcode;
		this.shipaddress = shipaddress;
		this.usertel = usertel;
		this.userid = userid;
		this.pid = pid;
		this.ordermessage = ordermessage;
		this.payment = payment;
		this.point = point;
	}


	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpostcode() {
		return userpostcode;
	}

	public void setUserpostcode(String userpostcode) {
		this.userpostcode = userpostcode;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOrdermessage() {
		return ordermessage;
	}

	public void setOrdermessage(String ordermessage) {
		this.ordermessage = ordermessage;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	

}
