package com.javalec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.dto.W_UserList_Dto;

public class W_UserList_Dao {
	DataSource dataSource;
	
	
	public W_UserList_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/anjpetshop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
public ArrayList<W_UserList_Dto> userList(String pageNum, String pageSize, String selectType, String selectText){
		
		ArrayList<W_UserList_Dto> dtos = new ArrayList<>();
		W_UserList_Dto dto = null;
		
		int queryPageSize = Integer.parseInt(pageSize); 
		int queryPageNum = (Integer.parseInt(pageNum)-1)*queryPageSize;
		String querySelectType = selectType;
		String querySelectText = selectText;
		
		System.out.println(querySelectType);
		System.out.println(querySelectText);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "select userid, username, usertel, useremail, useraddress, point, insertdate, deletedate, ( select count(*) from user where "+querySelectType+" like '%"+querySelectText+"%' ) as count from user WHERE "+querySelectType+" LIKE '%"+querySelectText+"%' order by userid limit ?,?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, queryPageNum);
			preparedStatement.setInt(2, queryPageSize);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String userid = resultSet.getString(1);
				String username = resultSet.getString(2);
				String usertel = resultSet.getString(3);
				String useremail = resultSet.getString(4);
				String useraddress = resultSet.getString(5);
				int point = resultSet.getInt(6);
				String insertdate = resultSet.getString(7);
				String deletedate = resultSet.getString(8);
				int countSQL = resultSet.getInt(9);
				
				if(deletedate == null) {
					deletedate = "-";
				}
				
				int count =  (int) Math.ceil((double) countSQL / queryPageSize);
				dto = new W_UserList_Dto(userid, username, usertel, useremail, useraddress, point, insertdate, deletedate, count);
				dtos.add(dto);
			
				
			}
			
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
		return dtos;
		
	}
	
	
	
	
	
}
