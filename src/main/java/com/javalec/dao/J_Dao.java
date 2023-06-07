package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.J_pdExplainDto;
import com.javalec.dto.J_pdPageDto;
import com.javalec.dto.J_userDto;
import com.javalec.dto.J_userOrderDto;

public class J_Dao {
	DataSource dataSource;
	
	
	public J_Dao() {
		// TODO Auto-generated constructor stub
		// DB 연결. 
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop"); 		//context로 가서 디비 로그인 정보 알아냄 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 1. 사용자가 선택한 제품 상세정보 페이지 : product 테이블의 모든 데이터 불러와서 상세정보 상단 섹션에 띄우기
	public J_pdPageDto productDetailView(String getPid) {
		J_pdPageDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from product where pid = '" + getPid + "'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String pid = resultSet.getString("pid");
				String pname = resultSet.getString("pname");
				String pcategory = resultSet.getString("pcategory");
				int pprice = resultSet.getInt("pprice");
				int pstock = resultSet.getInt("pstock");
				int available = resultSet.getInt("available");
				String pthumbnail = resultSet.getString("pthumbnail");
				String pth2 = resultSet.getString("pth2");
				String pth3 = resultSet.getString("pth3");
				
				dto = new J_pdPageDto(pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	// 2. 사용자가 선택한 제품 상세정보 설명 : product 테이블의 모든 데이터 불러와서 상세정보 하단 섹션에 띄우기
	public J_pdExplainDto productExplainView(String getPid) {
		J_pdExplainDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from productimage where pid = '" + getPid + "'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String pid = resultSet.getString("pid");
				String p_filename = resultSet.getString("p_filename");
				String p_filename2 = resultSet.getString("p_filename2");
				String p_filename3 = resultSet.getString("p_filename3");
				String p_filename4 = resultSet.getString("p_filename4");
				String p_filename5 = resultSet.getString("p_filename5");
				
				dto = new J_pdExplainDto(pid, p_filename, p_filename2, p_filename3, p_filename4, p_filename5);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	//------------------- userPage(order, user table) ---------------------------
	
	// 3. order 테이블의 데이터들 불러오기 : 유저아이디로 총 적립금, 총 구매 금액, 총 구매 횟수 띄워주기 
	public J_userOrderDto userOrderMileage (String getUserid) { 		
		J_userOrderDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select userid, count(orderseq) as ordercount, cast(sum(orderprice)*0.01 as unsigned) as total_Mileage, sum(orderprice) as total_Price "
					+ " from orders where userid = '" + getUserid + "' group by userid";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String userid = resultSet.getString("userid");
				int ordercount = resultSet.getInt("ordercount");
				int totalMileage = resultSet.getInt("total_Mileage");
				int totalPrice = resultSet.getInt("total_Price");
				
				dto = new J_userOrderDto(userid, ordercount, totalMileage, totalPrice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
		
	}
	
	// 4. user 테이블의 데이터들 불러오기 : 유저아이디로  사용 가능 적립금, 적립금 사용 내역들 띄워주기 
	public J_userDto userMileage (String getUserid) {
		J_userDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select userid, sum(mileage) as 'availableMilage', sum(usedmileage) as 'usedMileage'"
					+ " from user where userid = '" + getUserid + "' group by userid";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String userid = resultSet.getString("userid");
				int mileage = resultSet.getInt("availableMilage");
				int usedmileage = resultSet.getInt("usedMileage");
				
				
				dto = new J_userDto(userid, mileage, usedmileage);
				System.out.println("유저아이디 : " + userid);
				System.out.println("사용가능한 마일리지 : " + mileage);
				System.out.println("사용한 마일리지 : " + usedmileage);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
		
	}
		
			
	
	
//	// 5. 사용자 페이지 - 구매 내역 리스트 : 조회버튼 클릭하면 적립금 + 적립내역 보여주기(주문 날짜, 적립금, 주문번호)
//	public J_userOrderDto userOrderList(String getUserid) {
//		J_userOrderDto dto = null;
//		
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		try {
//			connection = dataSource.getConnection();
//			String query = "select * from orders where userid = '" + getUserid + "'";
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//			
//			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
//				String ordernum = resultSet.getString("ordernum");
//				int count = resultSet.getInt("count");
//				int orderprice = resultSet.getInt("orderprice");
//				String username = resultSet.getString("username");
//				String userpostcode = resultSet.getString("userpostcode");
//				String shipaddress = resultSet.getString("shipaddress");
//				String usertel = resultSet.getString("usertel");
//				String userid = resultSet.getString("userid");
//				String pid = resultSet.getString("pid");
//				String ordermessage = resultSet.getString("ordermessage");
//				String payment = resultSet.getString("payment");
//				int point = resultSet.getInt("point");
//				
//				dto = new J_userOrderDto(ordernum, count, orderprice, username, userpostcode, shipaddress, usertel, userid, pid, ordermessage, payment, point);
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(resultSet != null) resultSet.close(); 	// ResultSet이 비면 닫아.
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return dto;
//		
//	}
	
	
	
	
	
	
		

}
