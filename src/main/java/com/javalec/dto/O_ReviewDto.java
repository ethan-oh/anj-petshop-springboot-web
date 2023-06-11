package com.javalec.dto;

public class O_ReviewDto {

	// Field
	int seq;
	String r_title;
	String r_content;
	String writedate;
	String userid;
	String orders_pid;
	
	// Constructor
	public O_ReviewDto() {
		// TODO Auto-generated constructor stub
	}

	public O_ReviewDto(int seq, String r_title, String r_content, String writedate, String userid, String orders_pid) {
		super();
		this.seq = seq;
		this.r_title = r_title;
		this.r_content = r_content;
		this.writedate = writedate;
		this.userid = userid;
		this.orders_pid = orders_pid;
	}

	
	// Getter And Setter
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrders_pid() {
		return orders_pid;
	}

	public void setOrders_pid(String orders_pid) {
		this.orders_pid = orders_pid;
	}
	
	
} // End