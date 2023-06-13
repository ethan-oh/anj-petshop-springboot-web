package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	// 1. 사용자가 선택한 제품 상세정보 페이지 : product 테이블의 모든 데이터 불러오기
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
				String pfilename = resultSet.getString("pfilename");
				
				dto = new J_pdPageDto(pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3, pfilename);
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
	
	
	// 2. 장바구니에 사용지가 선택한 옵션들 넘겨주기 
	public void insertcart(String uid, String pid, int qty) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "insert into cart (count, userid, pid) values(?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, qty);
			preparedStatement.setString(2, uid);
			preparedStatement.setString(3, pid);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	//------------------- userPage(order, user table) ---------------------------
	
	// 4. order 테이블의 데이터들 불러오기 : 유저아이디로 총 적립금, 총 구매 금액, 총 구매 횟수, 사용한 마일리지 띄워주기 
	public J_userOrderDto userOrderMileage (String getUserid) { 		
		J_userOrderDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select userid, count(orderseq) as ordercount, cast(sum(orderprice)*0.01 as unsigned) as total_Mileage, sum(orderprice) as total_Price, sum(usedmileage) as total_usedMileage "
					+ " from orders where userid = '" + getUserid + "' group by userid";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String userid = resultSet.getString("userid");
				int ordercount = resultSet.getInt("ordercount");
				int totalUsedMileage = resultSet.getInt("total_usedMileage");
				int totalMileage = resultSet.getInt("total_Mileage");
				int totalPrice = resultSet.getInt("total_Price");
				
				dto = new J_userOrderDto(userid, ordercount, totalUsedMileage, totalMileage, totalPrice);
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
	
	// 5. user 테이블의 모든 데이터들 불러오기 : 유저아이디로  사용 가능 적립금 띄워주기 
	public J_userDto userView (String getUserid) {
		J_userDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from user where userid = '" + getUserid + "'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) { 		// db에서 한 줄에 있는 데이터를 열마다 분리해서 할당하는 과정.
				String userid = resultSet.getString("userid");
				String userpasswd = resultSet.getString("userpasswd");
				String username = resultSet.getString("username");
				String usertel = resultSet.getString("usertel");
				String useremail = resultSet.getString("useremail");
				int userpostcode = resultSet.getInt("userpostcode");
				String useraddress = resultSet.getString("useraddress");
				String userdetailaddress = resultSet.getString("userdetailaddress");
				int mileage = resultSet.getInt("mileage");
				
				dto = new J_userDto(userid, userpasswd, username, usertel, useremail, userpostcode, useraddress, userdetailaddress, mileage);
				
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
		
	// 6. user 테이블의 모든 데이터들 불러오기 : 프로필 변경 1) 회원 정보 수정
	public void updateUser(String userid,String userpasswd, int userpostcode, String useraddress, String userdetailaddress, String usertel, String useremail) {
		System.out.println(userid);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "update user set userpasswd=?, usertel=?, useremail=?, userpostcode=?, useraddress=?, userdetailaddress=? where userid = '" + userid + "'";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userpasswd);
			preparedStatement.setString(2, usertel);
			preparedStatement.setString(3, useremail);
			preparedStatement.setInt(4, userpostcode);
			preparedStatement.setString(5, useraddress);
			preparedStatement.setString(6, userdetailaddress);
			
			
			preparedStatement.executeUpdate();
			
			System.out.println(userid);
			System.out.println(userpasswd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	// 7. user 테이블의 모든 데이터들 불러오기 : 프로필 변경 2) 회원 탈퇴
	public void deleteUser(String uid) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "update user set deletedate = now() where userid = '" + uid + "'";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
//	// 8. 사용자 페이지 - 구매 내역 리스트 : 조회버튼 클릭하면 적립금 + 적립내역 보여주기(주문 날짜, 적립금, 주문번호)
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
