package com.javalec.dto;

public class W_SalesMonthly_Dto {

	
	String pid;
	String pname;
	int pprice;
	int count;
	int orderprice;
	int usedmileage;
	String orderdate;
	
	
	public W_SalesMonthly_Dto() {
		// TODO Auto-generated constructor stub
	}


	
	
	public W_SalesMonthly_Dto(String pid, String pname, int pprice, int count, int orderprice, int usedmileage,
			String orderdate) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
		this.count = count;
		this.orderprice = orderprice;
		this.usedmileage = usedmileage;
		this.orderdate = orderdate;
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


	public int getUsedmileage() {
		return usedmileage;
	}


	public void setUsedmileage(int usedmileage) {
		this.usedmileage = usedmileage;
	}


	public String getOrderdate() {
		return orderdate;
	}


	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
	
	
	
	
}
