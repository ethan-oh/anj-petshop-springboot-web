package com.javalec.dto;

public class J_pdPageDto { 		// ANJ pet shop 프로젝트 - product table dto
	
	// Field 
	String pid;
	String pname;
	String pcategory;
	int pprice;
	int pstock;
	int available;
	String pthumbnail;
	String pth2;
	String pth3;
	
	
	// Constructor
	public J_pdPageDto() {
		// TODO Auto-generated constructor stub
	}

	// J_Dao 1. 제품 페이지 - 사용자 보여주는 오른쪽 상단 구역에 정보 띄워주기
	public J_pdPageDto(String pid, String pname, String pcategory, int pprice, int pstock, int available, String pthumbnail,
			String pth2, String pth3) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pcategory = pcategory;
		this.pprice = pprice;
		this.pstock = pstock;
		this.available = available;
		this.pthumbnail = pthumbnail;
		this.pth2 = pth2;
		this.pth3 = pth3;
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


	public String getPcategory() {
		return pcategory;
	}


	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}


	public int getPprice() {
		return pprice;
	}


	public void setPprice(int pprice) {
		this.pprice = pprice;
	}


	public int getPstock() {
		return pstock;
	}


	public void setPstock(int pstock) {
		this.pstock = pstock;
	}


	public int getAvailable() {
		return available;
	}


	public void setAvailable(int available) {
		this.available = available;
	}


	public String getPthumbnail() {
		return pthumbnail;
	}


	public void setPthumbnail(String pthumbnail) {
		this.pthumbnail = pthumbnail;
	}


	public String getPth2() {
		return pth2;
	}


	public void setPth2(String pth2) {
		this.pth2 = pth2;
	}


	public String getPth3() {
		return pth3;
	}


	public void setPth3(String pth3) {
		this.pth3 = pth3;
	}
	
	
	
	
	
	
	
	

}
