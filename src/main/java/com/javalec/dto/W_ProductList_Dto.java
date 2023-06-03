package com.javalec.dto;

public class W_ProductList_Dto {
	
	// Field
	
	String pthumbnail;
	String pid;
	String pname;
	String pcategory;
	int pprice;
	int pstock;
	String available;
	int count;
	
	public W_ProductList_Dto() {
		// TODO Auto-generated constructor stub
	}



	public W_ProductList_Dto(String pthumbnail, String pid, String pname, String pcategory, int pprice, int pstock,
			String available, int count) {
		super();
		this.pthumbnail = pthumbnail;
		this.pid = pid;
		this.pname = pname;
		this.pcategory = pcategory;
		this.pprice = pprice;
		this.pstock = pstock;
		this.available = available;
		this.count = count;
	}














	public int getCount() {
		return count;
	}














	public void setCount(int count) {
		this.count = count;
	}














	public String getPthumbnail() {
		return pthumbnail;
	}

	public void setPthumbnail(String pthumbnail) {
		this.pthumbnail = pthumbnail;
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

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	
	
	
	
	
	
	
}
