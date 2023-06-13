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
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.javalec.dto.T_ordersDto;
import com.javalec.dto.T_productDto;
import com.javalec.dto.T_userinfoDto;



public class T_Dao {
	
	DataSource dataSource;
	
	 private String ordernum; // 멤버 변수로 선언
	
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
	public ArrayList<T_productDto> list(String userid){
		ArrayList<T_productDto> dtos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		 try {
		        connection = dataSource.getConnection();
		        String sql = "SELECT c.seq, p.pthumbnail, c.count, c.userid, c.pid, p.pname, p.pprice " +
	                     "FROM user u, cart c, product p " +
	                     "WHERE u.userid = c.userid AND p.pid = c.pid AND u.userid = ?";
		        preparedStatement = connection.prepareStatement(sql);
		        preparedStatement.setString(1, userid);
		        resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		        	int seq = resultSet.getInt("seq");
		        	String pthumbnail = resultSet.getString("pthumbnail");
		            String pid = resultSet.getString("pid");
		            String pname = resultSet.getString("pname");
		            int pprice = resultSet.getInt("pprice");
		            int count = resultSet.getInt("count");
		            //int pstock = resultSet.getInt("pstock");
		           // String pimage = resultSet.getString("pimage");

		            T_productDto dto = new T_productDto(seq, pthumbnail, pid, pname, pprice, count);
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
			System.out.println("삭제 pid = @@@@@ = " + pid);
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
		    //String fixedUserId = "do"; // userid를 고정값으로 설정

		    try {
		        connection = dataSource.getConnection();
		        String query = "SELECT * FROM user WHERE userid = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, userid); // 고정값으로 설정된 변수를 사용
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
		            dto.setUserid(userid); // 고정값으로 설정된 변수를 사용
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
		
		
		public T_ordersDto orders(List<String> pidList, List<Integer> countList, List<String> pnameList, List<Integer> orderpriceList, String username, String userpostcode, String useraddress, String userdetailaddress, String phone1, String phone2, String phone3, String ordermessage, String payment, int usedmileage, int enteredmileage, HttpServletRequest request) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    T_ordersDto dto = null; // Initialize the variable outside the try block

		    try {
		        connection = dataSource.getConnection();

		        // 트랜잭션 시작
		        connection.setAutoCommit(false);

		        String orderNumQuery = "SELECT CONCAT(DATE_FORMAT(NOW(), '%Y%m%d'), '-', DATE_FORMAT(NOW(), '%H%i%s')) AS ordernum;";
		        preparedStatement = connection.prepareStatement(orderNumQuery);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        String ordernum = null;

		        if (resultSet.next()) {
		            ordernum = resultSet.getString("ordernum");
		        }

		        String query = "INSERT INTO orders (ordernum, count, pname, orderprice, username, userpostcode, shipaddress, usertel, orderdate, userid, pid, ordermessage, payment, usedmileage)"
		                + " SELECT ?, ?, ?, ?, ?, ?, ?, ?, NOW(), 'do', ?, ?, ?, ?"
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
		            String pname = pnameList.get(i);

		            // 제품 가격 조회
		            priceStatement.setString(1, pid);
		            ResultSet priceResultSet = priceStatement.executeQuery();

		            if (priceResultSet.next()) {
		                int pprice = priceResultSet.getInt("pprice");
		                int orderPrice = count * pprice; // 주문 가격 계산
		                totalOrderPrice += orderPrice; // 주문 총 가격 누적

		                preparedStatement.setString(1, ordernum); // 주문번호 설정
		                preparedStatement.setInt(2, count);
		                preparedStatement.setString(3, pname);
		                preparedStatement.setInt(4, orderPrice);
		                preparedStatement.setString(5, username);
		                preparedStatement.setString(6, userpostcode);
		                preparedStatement.setString(7, useraddress + userdetailaddress);
		                preparedStatement.setString(8, phone1 + "-" + phone2 + "-" + phone3);
		                preparedStatement.setString(9, pid);
		                preparedStatement.setString(10, ordermessage);
		                preparedStatement.setString(11, payment);
		                preparedStatement.setInt(12, usedmileage);
		                preparedStatement.setString(13, "do"); // userid를 'do'로 고정
		                preparedStatement.setString(14, pid); // 13번째 매개변수에는 pid 값을 설정

		                preparedStatement.addBatch();
		            }

		            priceResultSet.close();
		        }

		        preparedStatement.executeBatch();
		        priceStatement.close();

		        String deleteCartQuery = "DELETE FROM cart WHERE userid = ?";
		        preparedStatement = connection.prepareStatement(deleteCartQuery);
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
		            String enteredMileageQuery = "UPDATE user SET mileage = mileage + ? WHERE userid = ?";
		            PreparedStatement enteredMileageStatement = connection.prepareStatement(enteredMileageQuery);
		            enteredMileageStatement.setInt(1, enteredmileage); // 적립될 마일리지 값
		            enteredMileageStatement.setString(2, "do"); // 사용자 ID
		            enteredMileageStatement.executeUpdate();
		        }

		        // 트랜잭션 커밋
		        connection.commit();


		        ArrayList<T_productDto> dtos = orderproduct(ordernum, request);

		        dto = orderlist(ordernum, request);
		        this.ordernum = ordernum;

		        
		        // 요청 객체에 주문 정보 설정
		        request.setAttribute("content_View", dto);
		        //request.setAttribute("product", dtos);
		        request.setAttribute("ordernum", dto.getOrdernum());
		        request.setAttribute("orderdate", dto.getOrderdate());
		        request.setAttribute("usedmileage", dto.getUsedmileage());
		        request.setAttribute("orderprice", dto.getOrderprice());
		        request.setAttribute("payment", dto.getPayment());
		        request.setAttribute("pid", dto.getPid());
		        request.setAttribute("pname", dto.getPname());
		        request.setAttribute("count", dto.getCount());
		        //request.setAttribute("username", dto.getUsername());
		        //request.setAttribute("userpostcode", dto.getUserpostcode());
		        request.setAttribute("shipaddress", dto.getShipaddress());
		        request.setAttribute("usertel", dto.getUsertel());
		        request.setAttribute("ordermessage", dto.getOrdermessage());
		        
		        
		        
		        
		        
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
		    }return dto;
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
		public T_ordersDto orderlist(String ordernum, HttpServletRequest request) {
		    T_ordersDto dto = null;
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        connection = dataSource.getConnection();
		        String query = "SELECT * FROM orders WHERE ordernum = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, ordernum);
		        resultSet = preparedStatement.executeQuery();
		        
		        if (resultSet.next()) {
		            dto = new T_ordersDto();
		            dto.setOrdernum(resultSet.getString("ordernum"));
		            dto.setOrderdate(resultSet.getString("orderdate"));
		            dto.setOrderprice(resultSet.getInt("orderprice"));
		            dto.setUsedmileage(resultSet.getInt("usedmileage"));
		            dto.setPayment(resultSet.getString("payment"));
		            dto.setUsername(resultSet.getString("username"));
		            dto.setUserpostcode(resultSet.getString("userpostcode"));
		            dto.setShipaddress(resultSet.getString("shipaddress"));
		            dto.setUsertel(resultSet.getString("usertel"));
		            dto.setOrdermessage(resultSet.getString("ordermessage"));
		            
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


		
		public ArrayList<T_productDto> orderproduct(String ordernum, HttpServletRequest request) {
		    ArrayList<T_productDto> dtos = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        connection = dataSource.getConnection();
		        String query = "SELECT p.pthumbnail, o.pid, p.pname, o.orderprice, o.count "
		                + "FROM orders o "
		                + "JOIN product p ON o.pid = p.pid "
		                + "WHERE o.ordernum = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, ordernum);
		        resultSet = preparedStatement.executeQuery();

		        this.ordernum = ordernum;

		        while (resultSet.next()) {		        	
		        	String pthumbnail = resultSet.getString("pthumbnail");
		            String pid = resultSet.getString("pid");
		            String pname = resultSet.getString("pname");
		            int orderprice = resultSet.getInt("orderprice");
		            int count = resultSet.getInt("count");

		            T_productDto dto = new T_productDto(pthumbnail, pid, pname, orderprice, count);
		            dtos.add(dto);
		        }
		        System.out.println("dao ordernum222 = " + ordernum);
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


		
}
		
		
		
		
