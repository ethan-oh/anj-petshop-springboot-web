package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.A_dto;

public class A_dao {

	DataSource dataSource;

	public A_dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1.메인페이지에 전체 상품 노출하는 것
	public ArrayList<A_dto> A_MainView() {
		ArrayList<A_dto> dtos = new ArrayList<A_dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "select pid, pname, pprice, pthumbnail from product;";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String pid = resultSet.getString("pid");
				String pname = resultSet.getString("pname");
				int pprice = resultSet.getInt("pprice");
				String pthumbnail = resultSet.getString("pthumbnail");

				A_dto dto = new A_dto(pid, pname, pprice, pthumbnail);
				dtos.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtos;
	}

	// 2.메인페이지에 판매량 순서로 노출시키는 것
	public ArrayList<A_dto> A_MainView2() {
		ArrayList<A_dto> dtos = new ArrayList<A_dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT orders.pid, product.pname, product.pprice, product.pthumbnail FROM orders INNER JOIN product ON orders.pid = product.pid GROUP BY orders.pid ORDER BY COUNT(*) DESC Limit 3";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String pid = resultSet.getString("pid");
				String pname = resultSet.getString("pname");
				int pprice = resultSet.getInt("pprice");
				String pthumbnail = resultSet.getString("pthumbnail");

				A_dto dto = new A_dto(pid, pname, pprice, pthumbnail);
				dtos.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtos;
	}

	// 금일의 추천 상품 리스트
	public ArrayList<A_dto> A_MainView3() {
		ArrayList<A_dto> dtos = new ArrayList<A_dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT pid, pname, pprice, pthumbnail from product order by rand() Limit 4";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String pid = resultSet.getString("pid");
				String pname = resultSet.getString("pname");
				int pprice = resultSet.getInt("pprice");
				String pthumbnail = resultSet.getString("pthumbnail");

				A_dto dto = new A_dto(pid, pname, pprice, pthumbnail);
				dtos.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return dtos;
	}

	// (3) 상품페이지 전체상품 노출 + 가격순 정렬 +카테고리 별 상품노출
	public ArrayList<A_dto> A_ProductView(String pcategory, String queryName, String queryContent, String sortOrder) {
		ArrayList<A_dto> dtos = new ArrayList<>();

		if (queryName == null) {
			queryName = "pname";
			queryContent = "";
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String query = "SELECT pid, pname, pprice, pthumbnail FROM product";
			String where = "";
			if (pcategory == null) {
				where = " WHERE " + queryName + " LIKE '%" + queryContent + "%'";
			} else {
				where = " WHERE " + queryName + " LIKE '%" + queryContent + "%' AND pcategory = ?";
			}

			if ("highprice".equals(sortOrder)) { // 높은 가격순으로 정렬
				query += where + " ORDER BY pprice DESC";
			} else if ("lowprice".equals(sortOrder)) { // 낮은 가격순으로 정렬
				query += where + " ORDER BY pprice ASC";
			} else { // 정렬하지 않음
				query += where;
			}

			preparedStatement = connection.prepareStatement(query);

			if (pcategory != null) {
				preparedStatement.setString(1, pcategory);
			}

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String pid = resultSet.getString("pid");
				String pname = resultSet.getString("pname");
				int pprice = resultSet.getInt("pprice");
				String pthumbnail = resultSet.getString("pthumbnail");

				A_dto dto = new A_dto(pid, pname, pprice, pthumbnail);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dtos;
	}

	// 관리자 인지 유저인지 아이디인지 체크하는 기능
	public String loginChack(String id, String pw) {
		String result = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = dataSource.getConnection();
			String query = "SELECT 'admin' AS type FROM admin WHERE adminid = ? AND adminpasswd = ? UNION ALL SELECT 'user' AS type FROM user WHERE userid = ? AND userpasswd = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			preparedStatement.setString(3, id);
			preparedStatement.setString(4, pw);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getString("type");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public String joinIdChack(String id) {
		String result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println("옴?ㅋ");
	
		try {
			connection = dataSource.getConnection();
			String query = "select count(userid) as id from user where userid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getString("id");
			}
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "NO";
			return result;
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	// 회원가입 기능
	public void join(String id, String pw, String name, String tel, String email, int postcode, String address, String detailaddress) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "insert into user (userid, userpasswd, username, usertel, useremail, userpostcode, useraddress, userdetailaddress, insertdate) values(?,?,?,?,?,?,?,?,now())";
		
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, tel);
			preparedStatement.setString(5, email);
			preparedStatement.setInt(6, postcode); 
			preparedStatement.setString(7, address);
			preparedStatement.setString(8, detailaddress);

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
