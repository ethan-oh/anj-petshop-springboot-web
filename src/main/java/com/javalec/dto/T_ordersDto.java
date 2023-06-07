package com.javalec.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T_ordersDto {
	
	// Filed
	String userid;
	String username;
	String userpostcode;
	String shipaddress;
	String ordernum;
	int count;
	int orderprice;
	String usertel;
	String pid;
	String orderdate;
	String payment;
	int point;
	
	// Construct
	public T_ordersDto(String userid, String username, String userpostcode, String shipaddress, String ordernum,
			int count, int orderprice, String usertel, String pid, String orderdate, String payment, int point) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpostcode = userpostcode;
		this.shipaddress = shipaddress;
		this.ordernum = ordernum;
		this.count = count;
		this.orderprice = orderprice;
		this.usertel = usertel;
		this.pid = pid;
		this.orderdate = orderdate;
		this.payment = payment;
		this.point = point;
	}

	public String getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getUserpostcode() {
		return userpostcode;
	}

	public String getShipaddress() {
		return shipaddress;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public int getCount() {
		return count;
	}

	public int getOrderprice() {
		return orderprice;
	}

	public String getUsertel() {
		return usertel;
	}

	public String getPid() {
		return pid;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public String getPayment() {
		return payment;
	}

	public int getPoint() {
		return point;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserpostcode(String userpostcode) {
		this.userpostcode = userpostcode;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	
}
