package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		            int mileage = resultSet.getInt("mileage");

		            dto = new T_userinfoDto();
		            dto.setUserid(fixedUserId); // 고정값으로 설정된 변수를 사용
		            dto.setUsername(username);
		            dto.setUserpostcode(userpostcode);
		            dto.setUseraddress(useraddress);
		            dto.setUserdetailaddress(userdetailaddress);
		            dto.setUsertel(usertel1 + "-" + usertel2 + "-" + usertel3);
		            dto.setUseremail(useremail);
		            dto.setMileage(mileage);
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

		// 주문하기
		private String userid = "do"; // 사용자 ID를 여기에 설정


		public void orders(List<String> pidList, List<Integer> countList, String username, String userpostcode, String useraddress, String userdetailaddress, String phone1, String phone2, String phone3, String ordermessage, String payment, int usedmileage, int enteredmileage) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		        connection = dataSource.getConnection();

		        // 트랜잭션 시작
		        connection.setAutoCommit(false);

		        String query = "INSERT INTO orders (ordernum, count, orderprice, username, userpostcode, shipaddress, usertel, orderdate, userid, pid, ordermessage, payment, usedmileage)"
		                + " SELECT CONCAT(DATE_FORMAT(NOW(), '%Y%m%d'), '-', DATE_FORMAT(NOW(), '%H%i%s')), ?, ?, ?, ?, ?, ?, NOW(), 'do', ?, ?, ?, ?"
		                + " FROM cart c"
		                + " JOIN user u ON u.userid = c.userid "
		                + " JOIN product p ON p.pid = c.pid"
		                + " WHERE u.userid = ? AND p.pid = ?;";

		        preparedStatement = connection.prepareStatement(query);

		        String priceQuery = "SELECT pprice FROM product WHERE pid = ?";
		        PreparedStatement priceStatement = connection.prepareStatement(priceQuery);

		        int totalOrderPrice = 0; // 주문 총 가격 변수 추가

		        for (int i = 0; i < pidList.size(); i++) {
		            String pid = pidList.get(i);
		            int count = countList.get(i);

		            // 제품 가격 조회
		            priceStatement.setString(1, pid);
		            ResultSet priceResultSet = priceStatement.executeQuery();

		            if (priceResultSet.next()) {
		                int pprice = priceResultSet.getInt("pprice");
		                int orderPrice = count * pprice; // 주문 가격 계산
		                totalOrderPrice += orderPrice; // 주문 총 가격 누적

		                preparedStatement.setInt(1, count);
		                preparedStatement.setInt(2, orderPrice);
		                preparedStatement.setString(3, username);
		                preparedStatement.setString(4, userpostcode);
		                preparedStatement.setString(5, useraddress + userdetailaddress);
		                preparedStatement.setString(6, phone1 + "-" + phone2 + "-" + phone3);
		                preparedStatement.setString(7, pid);
		                preparedStatement.setString(8, ordermessage);
		                preparedStatement.setString(9, payment);
		                preparedStatement.setInt(10, usedmileage);
		                preparedStatement.setString(11, "do"); // userid를 'do'로 고정
		                preparedStatement.setString(12, pid); // 12번째 매개변수에는 pid 값을 설정

		                preparedStatement.addBatch();
		            }

		            priceResultSet.close();
		        }

		        preparedStatement.executeBatch();
		        priceStatement.close();
		        
		        String query1 = "DELETE FROM cart WHERE userid = ?";
		        preparedStatement = connection.prepareStatement(query1);
		        preparedStatement.setString(1, "do"); // userid를 'do'로 고정
		        preparedStatement.executeUpdate();

		        if (usedmileage > 0) {
		            String deductMileageQuery = "UPDATE user SET mileage = mileage - ? WHERE userid = ?";
		            PreparedStatement deductMileageStatement = connection.prepareStatement(deductMileageQuery);
		            deductMileageStatement.setInt(1, usedmileage); // 사용한 마일리지 값
		            deductMileageStatement.setString(2, "do"); // 사용자 ID
		            deductMileageStatement.executeUpdate();
		        }


		        if (enteredmileage > 0) {
		            String enteredmileageQuery = "UPDATE user SET mileage = mileage + ? WHERE userid = ?";
		            PreparedStatement enteredmileageStatement = connection.prepareStatement(enteredmileageQuery);
		            enteredmileageStatement.setInt(1, enteredmileage); // 적립될 마일리지 값
		            enteredmileageStatement.setString(2, "do"); // 사용자 ID
		            enteredmileageStatement.executeUpdate();
		        }

		        // 트랜잭션 커밋
		        connection.commit();
		        		        
		        
		    } catch (Exception e) {
		        // 트랜잭션 롤백
		        if (connection != null) {
		            try {
		                connection.rollback();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		        e.printStackTrace();
		    } finally {
		        try {
		            // 트랜잭션 종료
		            if (preparedStatement != null)
		                preparedStatement.close();
		            if (connection != null)
		                connection.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}


		
		
		
		
		
		// 상품 수량 업데이트
		public void updatePstock(List<String> pidList, List<Integer> countList) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		        connection = dataSource.getConnection();

		        for (int i = 0; i < pidList.size(); i++) {
		            String pid = pidList.get(i);
		            int count = countList.get(i);

		            String query = "UPDATE product SET pstock = pstock - ? WHERE pid = ?";
		            preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setInt(1, count);
		            preparedStatement.setString(2, pid);
		            preparedStatement.executeUpdate();
		        }
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
		
		
		
		
		
		// 구매 후 내역 띄우기
//		public T_userinfoDto userlist(String userid) {
//		    T_userinfoDto dto = null;
//		    Connection connection = null;
//		    PreparedStatement preparedStatement = null;
//		    ResultSet resultSet = null;
//		    String fixedUserId = "do"; // userid를 고정값으로 설정
//
//		    try {
//		        connection = dataSource.getConnection();
//		        String query = "SELECT * FROM orders WHERE ordernum = ?;";
//		        preparedStatement = connection.prepareStatement(query);
//		        preparedStatement.setString(1, fixedUserId); // 고정값으로 설정된 변수를 사용
//		        resultSet = preparedStatement.executeQuery();
//
//		        if (resultSet.next()) {
//		            String username = resultSet.getString("username");
//		            String userpostcode = resultSet.getString("userpostcode");
//		            String useraddress = resultSet.getString("useraddress");
//		            String userdetailaddress = resultSet.getString("userdetailaddress");
//		            String usertel1 = resultSet.getString("usertel").substring(0, 3);
//		            String usertel2 = resultSet.getString("usertel").substring(4, 8);
//		            String usertel3 = resultSet.getString("usertel").substring(9);
//		            String useremail = resultSet.getString("useremail");
//		            int mileage = resultSet.getInt("mileage");
//
//		            dto = new T_userinfoDto();
//		            dto.setUserid(fixedUserId); // 고정값으로 설정된 변수를 사용
//		            dto.setUsername(username);
//		            dto.setUserpostcode(userpostcode);
//		            dto.setUseraddress(useraddress);
//		            dto.setUserdetailaddress(userdetailaddress);
//		            dto.setUsertel(usertel1 + "-" + usertel2 + "-" + usertel3);
//		            dto.setUseremail(useremail);
//		            dto.setMileage(mileage);
//		        }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    } finally {
//		        try {
//		            if (resultSet != null)
//		                resultSet.close();
//		            if (preparedStatement != null)
//		                preparedStatement.close();
//		            if (connection != null)
//		                connection.close();
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		        }
//		    }
//		    return dto;
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
}
		
		
		
		
