package com.javalec.dto;

import java.security.Timestamp;

public class O_RBoardDto {

	// Field
	int r_num;
	String r_title;
	String writer;
	int r_group;
	int r_seq;
	int r_level;
	Timestamp writedate;
	String order_orderseq;
	String admin_adminid;
	String user_userid;
	
	// Constructor
	public O_RBoardDto() {
		// TODO Auto-generated constructor stub
	}

	public O_RBoardDto(int r_num, String r_title, String writer, int r_group, int r_seq, int r_level,
			Timestamp writedate, String order_orderseq, String admin_adminid, String user_userid) {
		super();
		this.r_num = r_num;
		this.r_title = r_title;
		this.writer = writer;
		this.r_group = r_group;
		this.r_seq = r_seq;
		this.r_level = r_level;
		this.writedate = writedate;
		this.order_orderseq = order_orderseq;
		this.admin_adminid = admin_adminid;
		this.user_userid = user_userid;
	}
	
	// Getter And Setter
	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getR_group() {
		return r_group;
	}

	public void setR_group(int r_group) {
		this.r_group = r_group;
	}

	public int getR_seq() {
		return r_seq;
	}

	public void setR_seq(int r_seq) {
		this.r_seq = r_seq;
	}

	public int getR_level() {
		return r_level;
	}

	public void setR_level(int r_level) {
		this.r_level = r_level;
	}

	public Timestamp getWritedate() {
		return writedate;
	}

	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}

	public String getOrder_orderseq() {
		return order_orderseq;
	}

	public void setOrder_orderseq(String order_orderseq) {
		this.order_orderseq = order_orderseq;
	}

	public String getAdmin_adminid() {
		return admin_adminid;
	}

	public void setAdmin_adminid(String admin_adminid) {
		this.admin_adminid = admin_adminid;
	}

	public String getUser_userid() {
		return user_userid;
	}

	public void setUser_userid(String user_userid) {
		this.user_userid = user_userid;
	}
	
	
}
