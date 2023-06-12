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
import com.javalec.dto.O_ReviewDto;

public class O_ReviewDao {

	DataSource dataSource;

	public O_ReviewDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getReviewCount(String queryName, String queryContent){ // 검색된 게시물의 카운트 구하기.

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		if(queryName == null){ // 화면이 처음 열릴 때
			queryName = "r_title";
			queryContent = "";
		}
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from review where isdelete = 0";
			String query2 = " and " + queryName + " like '%" + queryContent + "%'";
			ps = connection.prepareStatement(query + query2);
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
	
	public ArrayList<O_ReviewDto> getReviewList(String queryName, String queryContent, int startNum, int itemPerPage){
		ArrayList<O_ReviewDto> dtos = new ArrayList<>();

		if(queryName == null){ // 화면이 처음 열릴 때
			queryName = "r_title";
			queryContent = "";
		}
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connection = dataSource.getConnection();
			String query1 = "select * from review where isdelete = 0";
			String query2 = " and " + queryName + " like '%" + queryContent + "%'";
			String query3 = " order by writedate desc limit " + itemPerPage + " offset " + startNum + ";";
			ps = connection.prepareStatement(query1 + query2 + query3);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt(1);
				String r_title = rs.getString(2);
				String r_content = rs.getString(3);
				Timestamp tmp_writedate = rs.getTimestamp(4);
				int isdelete = rs.getInt(5);
				String userid = rs.getString(6);
				int orderseq = rs.getInt(7);
				
				String writedate = format.format(tmp_writedate);
				
				O_ReviewDto dto = new O_ReviewDto(seq, r_title, r_content, writedate, isdelete, userid, orderseq);
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
	
	public O_ReviewDto getReviewDetail(int nSeq){

		O_ReviewDto dto = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connection = dataSource.getConnection();
	
			String query1 = "SELECT r.*, p.pname, p.pthumbnail FROM review r";
			String query2 = " JOIN orders o ON r.orderseq = o.orderseq";
			String query3 = " JOIN product p ON o.pid = p.pid";
			String query4 = " where r.seq=" + nSeq;
			String query = query1 + query2 + query3 + query4;
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				int seq = rs.getInt(1);
				String r_title = rs.getString(2);
				String r_content = rs.getString(3);
				Timestamp tmp_writedate = rs.getTimestamp(4);
				int isdelete = rs.getInt(5);
				String userid = rs.getString(6);
				int orderseq = rs.getInt(7);
				String pname = rs.getString(8);
				String pthumbnail = rs.getString(9);
				
				String writedate = format.format(tmp_writedate);
				
				
				dto = new O_ReviewDto(seq, r_title, r_content, writedate, isdelete, userid, orderseq, pname, pthumbnail);
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
		return dto;
	}
} // End
