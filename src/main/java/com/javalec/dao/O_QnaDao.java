package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
	
	public int getQnaCount(String queryName, String queryContent){ // 전체 게시물의 카운트 구하기.

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		if(queryName == null){ // 화면이 처음 열릴 때
			queryName = "qna_title";
			queryContent = "";
		}
		
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from qna";
			String query2 = " where " + queryName + " like '%" + queryContent + "%'";
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
			String query1 = "select * from qna where  ";
			String query2 = queryName + " like '%" + queryContent + "%'";
			String query3 = " order by parentseq desc limit " + itemPerPage + " offset " + startNum + ";";
			ps = connection.prepareStatement(query1 + query2 + query3);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String category = rs.getString("category");
				String qna_title = rs.getString("qna_title");
				String qna_content = rs.getString("qna_content");
				int parentseq = rs.getInt("parentseq");
				Timestamp tmp_writedate  = rs.getTimestamp("writedate");
				String userid = rs.getString("userid");
				String adminid  = rs.getString("adminid");
				
				String writedate = format.format(tmp_writedate);
				
				O_QnaDto dto = new O_QnaDto(seq, category, qna_title, qna_content, parentseq, writedate, userid, adminid);
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
	
	public O_QnaDto getQnaDetail(int nSeq){

		O_QnaDto dto = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from qna where seq = " + nSeq;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				int seq = rs.getInt("seq");
				String category = rs.getString("category");
				String qna_title = rs.getString("qna_title");
				String qna_content = rs.getString("qna_content");
				int parentseq = rs.getInt("parentseq");
				Timestamp tmp_writedate  = rs.getTimestamp("writedate");
				String userid = rs.getString("userid");
				String adminid  = rs.getString("adminid");
				
				String writedate = format.format(tmp_writedate);
				
				dto = new O_QnaDto(seq, category, qna_title, qna_content, parentseq, writedate, userid, adminid);
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
	
	public void deleteQuestion(int seq) { // 질문글 및 답변글 모두 삭제
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "delete from qna where parentseq = " + seq;
			ps = connection.prepareStatement(query);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // deleteQuestion
	public void deleteAnswer(int seq) { // 질문글 및 답변글 모두 삭제
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "delete from qna where seq = " + seq;
			ps = connection.prepareStatement(query);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // deleteAnswer
	
	public void writeQuestion(String category, String title, String content, String userid, String adminid) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "INSERT INTO qna (category, qna_title, qna_content, writedate, userid, adminid) "
					+ "VALUES (?, ?, ?, NOW(), ?, ?)"; // parentseq를 제외한 데이터들을 입력해준다.
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // ai로 자동생성된 seq값을 받아온다.
			
			ps.setString(1, category);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setString(4, userid);
			ps.setString(5, adminid);
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys(); // 방금 insert된 데이터를 들고온다.
			int seq = 0; // seq 초기화
			if (rs.next()) {
				seq = rs.getInt(1); // 방금 insert된 로우의 seq값을 가져온다.
			}
			
			ps.close();
			
			String query2 = "UPDATE qna SET parentseq = ? WHERE seq = ?";
			ps = connection.prepareStatement(query2);
			ps.setInt(1, seq);
			ps.setInt(2, seq);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // writeQuestion
	
	public void writeAnswer(int seq, String category, String title, String content, String userid, String adminid) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "INSERT INTO qna (category, qna_title, qna_content, parentseq, writedate, userid, adminid) "
					+ "VALUES (?, ?, ?, ?, NOW(), ?, ?)"; // parentseq를 제외한 데이터들을 입력해준다.
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // ai로 자동생성된 seq값을 받아온다.
			
			ps.setString(1, category);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setInt(4, seq);
			ps.setString(5, userid);
			ps.setString(6, adminid);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // writeQuestion
	
}
