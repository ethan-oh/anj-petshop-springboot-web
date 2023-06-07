package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.O_NoticeDto;
import com.javalec.dto.O_QnaDto;


public class O_QnaDao {

	// Field
	DataSource dataSource;

	public O_QnaDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getQnaCount(){ // 전체 게시물의 카운트 구하기.

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from qna";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public ArrayList<O_QnaDto> getQnaList(String queryName, String queryContent, int startNum, int itemPerPage){
		ArrayList<O_QnaDto> dtos = new ArrayList<>();

		if(queryName == null){ // 화면이 처음 열릴 때
			queryName = "userid";
			queryContent = "";
		}
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connection = dataSource.getConnection();
			String query1 = "select * from qna where ";
			String query2 = queryName + " like '%" + queryContent + "%'";
			String query3 = " order by q_date desc limit " + itemPerPage + " offset " + startNum + ";";
			ps = connection.prepareStatement(query1 + query2 + query3);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String category = rs.getString("category");
				String question = rs.getString("question");
				String answer = rs.getString("question");
				Timestamp tmp_q_date  = rs.getTimestamp("q_date");
				Timestamp tmp_a_date  = rs.getTimestamp("a_date");
				String userid = rs.getString("userid");
				String adminid  = rs.getString("adminid");
				
				String q_date = format.format(tmp_q_date);
				String a_date = format.format(tmp_a_date);
				
				O_QnaDto dto = new O_QnaDto(seq, category, question, answer, q_date, a_date, userid, adminid);
				dtos.add(dto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
}
