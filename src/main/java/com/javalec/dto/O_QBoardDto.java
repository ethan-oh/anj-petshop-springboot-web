package com.javalec.dto;

import java.security.Timestamp;

public class O_QBoardDto {

	// Field
	int q_num;
	String q_title;
	int q_group;
	int q_seq;
	int q_level;
	Timestamp writedate;
	String product_pid;
	String admin_adminid;
	String user_userid;
	
	// Constructor
	 public O_QBoardDto() {
		// TODO Auto-generated constructor stub
	}

	public O_QBoardDto(int q_num, String q_title, int q_group, int q_seq, int q_level, Timestamp writedate,
			String product_pid, String admin_adminid, String user_userid) {
		super();
		this.q_num = q_num;
		this.q_title = q_title;
		this.q_group = q_group;
		this.q_seq = q_seq;
		this.q_level = q_level;
		this.writedate = writedate;
		this.product_pid = product_pid;
		this.admin_adminid = admin_adminid;
		this.user_userid = user_userid;
	}

	// Getter And Setter
	public int getQ_num() {
		return q_num;
	}

	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public int getQ_group() {
		return q_group;
	}

	public void setQ_group(int q_group) {
		this.q_group = q_group;
	}

	public int getQ_seq() {
		return q_seq;
	}

	public void setQ_seq(int q_seq) {
		this.q_seq = q_seq;
	}

	public int getQ_level() {
		return q_level;
	}

	public void setQ_level(int q_level) {
		this.q_level = q_level;
	}

	public Timestamp getWritedate() {
		return writedate;
	}

	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}

	public String getProduct_pid() {
		return product_pid;
	}

	public void setProduct_pid(String product_pid) {
		this.product_pid = product_pid;
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