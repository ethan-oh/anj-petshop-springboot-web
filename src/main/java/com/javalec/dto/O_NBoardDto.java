package com.javalec.dto;

import java.security.Timestamp;

public class O_NBoardDto {

	// Field
	int seq;
	String adminid;
	String n_title;
	String n_content;
	Timestamp writedate;
	Timestamp modifydate;
	Timestamp deletedate;
	
	// Constructor
	public O_NBoardDto() {
		// TODO Auto-generated constructor stub
	}

	public O_NBoardDto(int seq, String adminid, String n_title, String n_content, Timestamp writedate,
			Timestamp modifydate, Timestamp deletedate) {
		super();
		this.seq = seq;
		this.adminid = adminid;
		this.n_title = n_title;
		this.n_content = n_content;
		this.writedate = writedate;
		this.modifydate = modifydate;
		this.deletedate = deletedate;
	}
	
	// Getter And Setter
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public Timestamp getWritedate() {
		return writedate;
	}

	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}

	public Timestamp getModifydate() {
		return modifydate;
	}

	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}

	public Timestamp getDeletedate() {
		return deletedate;
	}

	public void setDeletedate(Timestamp deletedate) {
		this.deletedate = deletedate;
	}
	
	
}
