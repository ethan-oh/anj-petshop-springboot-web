package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.T_productDto;
import com.javalec.dto.T_userinfoDto;

public class T_Dao {
	
	DataSource dataSource;
	
	public T_Dao() {
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop"); // context.xml에서 정보 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 장바구니에 담긴 상품 보여주기
	public ArrayList<T_productDto> list(){
		ArrayList<T_productDto> dtos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		 try {
		        connection = dataSource.getConnection();
		        String sql = "SELECT c.seq, c.count, c.userid, c.pid, p.pname, p.pprice " +
	                     "FROM user u, cart c, product p " +
	                     "WHERE u.userid = c.userid AND p.pid = c.pid"; // AND u.userid = ?";
		        preparedStatement = connection.prepareStatement(sql);
		       // preparedStatement.setString(1, userid);
		        resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		        	int seq = resultSet.getInt("seq");
		            String pid = resultSet.getString("pid");
		            String pname = resultSet.getString("pname");
		            int pprice = resultSet.getInt("pprice");
		            int count = resultSet.getInt("count");
		            //int pstock = resultSet.getInt("pstock");
		           // String pimage = resultSet.getString("pimage");

		            T_productDto dto = new T_productDto(seq, pid, pname, pprice, count);
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
	
	// 장바구니에서 수량변경
		public void update(String pid, int count) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		        connection = dataSource.getConnection();
		        String query = "update cart set count = ? where pid = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setInt(1, count);
		        preparedStatement.setString(2, pid);

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
		
		// 장바구니에서 삭제
		public void delete(String pid) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = dataSource.getConnection();
				String query = "delete from cart where pid = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, pid);
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
		
		// 유저 정보 띄우기
		public T_userinfoDto userlist(String userid) {
		    T_userinfoDto dto = null;
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    String fixedUserId = "do"; // userid를 고정값으로 설정

		    try {
		        connection = dataSource.getConnection();
		        String query = "SELECT * FROM user WHERE userid = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, fixedUserId); // 고정값으로 설정된 변수를 사용
		        resultSet = preparedStatement.executeQuery();

		        if (resultSet.next()) {
		            String username = resultSet.getString("username");
		            String userpostcode = resultSet.getString("userpostcode");
		            String useraddress = resultSet.getString("useraddress");
		            String userdetailaddress = resultSet.getString("userdetailaddress");
		            String usertel1 = resultSet.getString("usertel").substring(0, 3);
		            String usertel2 = resultSet.getString("usertel").substring(4, 8);
		            String usertel3 = resultSet.getString("usertel").substring(9);
		            String useremail = resultSet.getString("useremail");

		            dto = new T_userinfoDto();
		            dto.setUserid(fixedUserId); // 고정값으로 설정된 변수를 사용
		            dto.setUsername(username);
		            dto.setUserpostcode(userpostcode);
		            dto.setUseraddress(useraddress);
		            dto.setUserdetailaddress(userdetailaddress);
		            dto.setUsertel(usertel1 + "-" + usertel2 + "-" + usertel3);
		            dto.setUseremail(useremail);
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
		    return dto;
		}


	
	
	
}
