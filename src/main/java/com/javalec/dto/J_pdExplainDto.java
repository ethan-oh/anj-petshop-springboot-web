package com.javalec.dto;

public class J_pdExplainDto { 			// 없어도 될듯??
	String pid;
	String p_filename;
	String p_filename2;
	String p_filename3;
	String p_filename4;
	String p_filename5;
	
	public J_pdExplainDto() {
		// TODO Auto-generated constructor stub
	}
	
	// J_Dao 2. 사용자 페이지 - 제품 설명 이미지 띄워주기 (사용자 하단 화면)
	public J_pdExplainDto(String pid, String p_filename, String p_filename2, String p_filename3, String p_filename4,
			String p_filename5) {
		super();
		this.pid = pid;
		this.p_filename = p_filename;
		this.p_filename2 = p_filename2;
		this.p_filename3 = p_filename3;
		this.p_filename4 = p_filename4;
		this.p_filename5 = p_filename5;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getP_filename() {
		return p_filename;
	}

	public void setP_filename(String p_filename) {
		this.p_filename = p_filename;
	}

	public String getP_filename2() {
		return p_filename2;
	}

	public void setP_filename2(String p_filename2) {
		this.p_filename2 = p_filename2;
	}

	public String getP_filename3() {
		return p_filename3;
	}

	public void setP_filename3(String p_filename3) {
		this.p_filename3 = p_filename3;
	}

	public String getP_filename4() {
		return p_filename4;
	}

	public void setP_filename4(String p_filename4) {
		this.p_filename4 = p_filename4;
	}

	public String getP_filename5() {
		return p_filename5;
	}

	public void setP_filename5(String p_filename5) {
		this.p_filename5 = p_filename5;
	}
	
	
	
	
	
	
	
	
	
	
	

}
