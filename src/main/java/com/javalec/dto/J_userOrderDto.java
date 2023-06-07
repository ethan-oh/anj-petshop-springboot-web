package com.javalec.dto;

import java.sql.Timestamp;

public class J_userOrderDto {  		// order table의 데이터 가져오기
	
	// Field
	String ordernum;
	int count;				// 주문한 수량 
	int orderprice;
	String username;
	String userpostcode;
	String shipaddress;
	String usertel;
	Timestamp orderdate; 
	String userid;
	String pid;
	String ordermessage;
	String payment;
	int mileage;
	
	int ordercount; 		// 주문 횟수 
	int totalMileage;		// 총 적립된 마일리지 합 
	int totalPrice;			// 총 구매 금액 
	
	public J_userOrderDto() {
		// TODO Auto-generated constructor stub
	}

	// J_Dao 3. 사용자 페이지 - 유저의 적립금, 구매금액, 구매횟수 띄워주기
	public J_userOrderDto(String userid, int ordercount, int totalMileage, int totalPrice) {
		super();
		this.userid = userid;
		this.ordercount = ordercount;
		this.totalMileage = totalMileage;
		this.totalPrice = totalPrice;
	}


	public J_userOrderDto(String ordernum, int count, int orderprice, String username, String userpostcode,
			String shipaddress, String usertel, Timestamp orderdate, String userid, String pid, String ordermessage,
			String payment, int mileage) {
		super();
		this.ordernum = ordernum;
		this.count = count;
		this.orderprice = orderprice;
		this.username = username;
		this.userpostcode = userpostcode;
		this.shipaddress = shipaddress;
		this.usertel = usertel;
		this.orderdate = orderdate;
		this.userid = userid;
		this.pid = pid;
		this.ordermessage = ordermessage;
		this.payment = payment;
		this.mileage = mileage;
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

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
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

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(int ordercount) {
		this.ordercount = ordercount;
	}

	public int getTotalMileage() {
		return totalMileage;
	}

	public void setTotalMileage(int totalMileage) {
		this.totalMileage = totalMileage;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
