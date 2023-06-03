package com.javalec.dao;

import java.awt.TextArea;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.O_NoticeDto;

public class O_NoticeDao {

	// Field
	DataSource dataSource;

	public O_NoticeDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNoticeCount(){ // 전체 게시물의 카운트 구하기.

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from notice where isdelete = 0";
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
	
	public ArrayList<O_NoticeDto> getNoticeList(int startNum, int itemPerPage){
		ArrayList<O_NoticeDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from notice where isdelete = 0 order by writedate limit " + itemPerPage + " offset " + startNum;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt(1);
				String adminid = rs.getString(2);
				String n_title = rs.getString(3);
				String n_content = rs.getString(4);
				Timestamp tmp_writedate = rs.getTimestamp(5);
				Timestamp tmp_modifydate = rs.getTimestamp(6);
				int isdelete = rs.getInt(7);
				
				String writedate = format.format(tmp_writedate);
				String modifydate = format.format(tmp_modifydate);
				
				O_NoticeDto dto = new O_NoticeDto(seq, adminid, n_title, n_content, writedate, modifydate, isdelete);
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
	
	public O_NoticeDto getNoticeDetail(int nSeq){

		O_NoticeDto dto = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from notice where seq = " + nSeq;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				int seq = rs.getInt(1);
				String adminid = rs.getString(2);
				String n_title = rs.getString(3);
				String n_content = rs.getString(4);
				Timestamp tmp_writedate = rs.getTimestamp(5);
				Timestamp tmp_modifydate = rs.getTimestamp(6);
				int isdelete = rs.getInt(7);
				
				String writedate = format.format(tmp_writedate);
				String modifydate = format.format(tmp_modifydate);
				
				dto = new O_NoticeDto(seq, adminid, n_title, n_content, writedate, modifydate, isdelete);
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

}