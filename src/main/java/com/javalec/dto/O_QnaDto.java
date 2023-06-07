package com.javalec.dto;

public class O_QnaDto {

	// Field
	int seq;
	String category;
	String question;
	String answer;
	String q_date;
	String a_date;
	String userid;
	String adminid;
	
	// Constructor
	public O_QnaDto() {
		// TODO Auto-generated constructor stub
	}

	public O_QnaDto(int seq, String category, String question, String answer, String q_date, String a_date,
			String userid, String adminid) {
		super();
		this.seq = seq;
		this.category = category;
		this.question = question;
		this.answer = answer;
		this.q_date = q_date;
		this.a_date = a_date;
		this.userid = userid;
		this.adminid = adminid;
	}
	
	// Getter And Setter
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQ_date() {
		return q_date;
	}

	public void setQ_date(String q_date) {
		this.q_date = q_date;
	}

	public String getA_date() {
		return a_date;
	}

	public void setA_date(String a_date) {
		this.a_date = a_date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	
	
}
