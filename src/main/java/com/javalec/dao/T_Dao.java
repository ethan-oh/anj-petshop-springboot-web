package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.T_productDto;

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
		        	
		            String pid = resultSet.getString("pid");
		            String pname = resultSet.getString("pname");
		            int pprice = resultSet.getInt("pprice");
		            int count = resultSet.getInt("count");
		            //int pstock = resultSet.getInt("pstock");
		           // String pimage = resultSet.getString("pimage");

		            T_productDto dto = new T_productDto(pid, pname, pprice, count);
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
	
	
	
	
	
}
