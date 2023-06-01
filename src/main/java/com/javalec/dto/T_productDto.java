package com.javalec.dto;

public class T_productDto {

	// Field
	int seq;
	int count;
	String userid;
	String pid;
	String pname;
	int pprice;
	
	// Constructor
	public T_productDto() {
		// TODO Auto-generated constructor stub
	}

	public T_productDto(String pid, String pname, int pprice, int count) {
		super();
		//this.seq = seq;
		//this.userid = userid;
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
		this.count = count;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	
	
	
}
