package com.javalec.dto;

public class A_dto {

	String pid;
	String pname;
	int pprice;
	String pthumbnail;

	public A_dto(String pid, String pname, int pprice, String pthumbnail) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
		this.pthumbnail = pthumbnail;
	}


	public A_dto(String pid, String pname, int pprice) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
	}

	public A_dto(String pid) {
		super();
		this.pid = pid;
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

	public String getPthumbnail() {
		return pthumbnail;
	}
	
	public void setPthumbnail(String pthumbnail) {
		this.pthumbnail = pthumbnail;
	}
}
