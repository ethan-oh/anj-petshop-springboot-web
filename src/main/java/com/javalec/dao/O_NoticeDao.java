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
	
	public int getNoticeCount(String queryName, String queryContent){ // 검색된 게시물의 카운트 구하기.

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		if(queryName == null){ // 화면이 처음 열릴 때
			queryName = "n_title";
			queryContent = "";
		}
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from notice where category = '공지' and isdelete = 0";
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
	
	public ArrayList<O_NoticeDto> getNoticeList(String queryName, String queryContent, int startNum, int itemPerPage){
		ArrayList<O_NoticeDto> dtos = new ArrayList<>();

		if(queryName == null){ // 화면이 처음 열릴 때
			queryName = "n_title";
			queryContent = "";
		}
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connection = dataSource.getConnection();
			String query1 = "select * from notice where isdelete = 0 and category = '공지'";
			String query2 = " and " + queryName + " like '%" + queryContent + "%'";
			String query3 = " order by writedate desc limit " + itemPerPage + " offset " + startNum + ";";
			ps = connection.prepareStatement(query1 + query2 + query3);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt(1);
				String adminid = rs.getString(2);
				String n_title = rs.getString(4);
				String n_content = rs.getString(5);
				Timestamp tmp_writedate = rs.getTimestamp(6);
				
				String writedate = format.format(tmp_writedate);
				
				O_NoticeDto dto = new O_NoticeDto(seq, adminid, n_title, n_content, writedate);
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
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connection = dataSource.getConnection();
			String query = "select n_title, n_content, writedate from notice where seq = " + nSeq;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				String n_title = rs.getString(1);
				String n_content = rs.getString(2);
				Timestamp tmp_writedate = rs.getTimestamp(3);
				
				String writedate = format.format(tmp_writedate);
				
				dto = new O_NoticeDto(n_title, n_content, writedate);
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
	
	public ArrayList<O_NoticeDto> getFAQList(){
		ArrayList<O_NoticeDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from notice where isdelete = 0 and category = 'FAQ' order by n_title;";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt(1);
				String n_title = rs.getString("n_title");
				String n_content = rs.getString("n_content");
				int isdelete = rs.getInt("isdelete");
				
				O_NoticeDto dto = new O_NoticeDto(seq, n_title, n_content, isdelete);
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
	} // getFAQList
	
	public ArrayList<O_NoticeDto> getDeletedFAQList(){
		ArrayList<O_NoticeDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from notice where isdelete = 1 and category = 'FAQ' order by n_title;";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt(1);
				String n_title = rs.getString("n_title");
				String n_content = rs.getString("n_content");
				int isdelete = rs.getInt("isdelete");
				
				O_NoticeDto dto = new O_NoticeDto(seq, n_title, n_content, isdelete);
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
	} // getDeletedFAQList

	public void writeNotice(String adminid, String n_title, String n_content) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into notice (adminid, category, n_title, n_content, writedate, isdelete)"
							+ "values (?,'공지',?,?,now(),0)";
			ps = connection.prepareStatement(query);
			
			ps.setString(1, adminid);
			ps.setString(2, n_title);
			ps.setString(3, n_content);
			
			ps.executeUpdate();
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
	} // writeNotice
	
	public void updateNotice(int seq, String n_title, String n_content) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update notice set n_title = ?, n_content = ?, writedate = now() where seq = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, n_title);
			preparedStatement.setString(2, n_content);
			preparedStatement.setInt(3, seq);

			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // deleteFAQ
	
	public void writeFAQ(String adminid, String n_title, String n_content) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into notice (adminid, category, n_title, n_content, writedate, isdelete)"
					+ "values (?,'FAQ',?,?,now(),0)";
			ps = connection.prepareStatement(query);
			
			ps.setString(1, adminid);
			ps.setString(2, n_title);
			ps.setString(3, n_content);
			
			ps.executeUpdate();
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
	} // writeNotice
	
	public void changeFAQStatus(int seq, int status) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update notice set isdelete = ? where seq = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, status);
			preparedStatement.setInt(2, seq);

			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // deleteFAQ
	
	public void updateFAQ(int seq, String n_title, String n_content) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update notice set n_title = ?, n_content = ?, writedate = now() where seq = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, n_title);
			preparedStatement.setString(2, n_content);
			preparedStatement.setInt(3, seq);

			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 생성한 순서의 역순대로 닫아준다! -> 퍼포먼스가 좋아짐.
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // deleteFAQ
}
