package com.javalec.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.T_Dao;
import com.javalec.dto.T_ordersDto;

public class T_orderCommand implements Acommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int count = 0;
        List<String> pidList = new ArrayList<>();

        if (request.getParameter("pid") != null) {
            String[] pidArray = request.getParameterValues("pid");
            for (String pid : pidArray) {
                pidList.add(pid);
            }
        } else {
            System.out.println("null");
        }

        if (request.getParameter("count") != null) {
            String countParameter = request.getParameter("count");
            if (!countParameter.isEmpty()) {
                count = Integer.parseInt(countParameter);
            } else {
                System.out.println("null");
            }
        }

        String username = request.getParameter("username");
        String userpostcode = request.getParameter("userpostcode");
        String useraddress = request.getParameter("useraddress");
        String userdetailaddress = request.getParameter("userdetailaddress");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");
        String useremail = request.getParameter("useremail");
        String ordermessage = request.getParameter("ordermessage");
        String payment = request.getParameter("payment");
        
        
        System.out.println("count = " + count);
        System.out.println("username = " + username);
        System.out.println("userpostcode = " + userpostcode);
        System.out.println("useraddress = " + useraddress);
        System.out.println("userdetailaddress = " + userdetailaddress);
        System.out.println("useremail = " + useremail);
        System.out.println("ordermessage = " + ordermessage);
        System.out.println("payment = " + payment);
        String userid = "do";
        T_Dao dao = new T_Dao();
        dao.orders(pidList, count, username, userpostcode, useraddress, userdetailaddress, phone1, phone2, phone3, useremail, ordermessage, payment, userid);

       
        
        if (username == null || username.isEmpty() ||
        	    userpostcode == null || userpostcode.isEmpty() ||
        	    useraddress == null || useraddress.isEmpty() ||
        	    userdetailaddress == null || userdetailaddress.isEmpty() ||
        	    phone1 == null || phone1.isEmpty() ||
        	    phone2 == null || phone2.isEmpty() ||
        	    phone3 == null || phone3.isEmpty() ||
        	    useremail == null || useremail.isEmpty()) {

        	    // 필수 필드가 비어있을 경우 오류 처리
        	    try {
        	        // 오류 메시지 전송 또는 팝업 창을 통해 사용자에게 알리는 방식으로 처리합니다.
        	        response.getWriter().println("필수 필드를 모두 입력해주세요.");
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        	} else {
        	    // 필수 필드가 모두 채워진 경우, 주문 처리 로직 실행
//        		T_Dao dao = new T_Dao();
//        	    dao.orders(pidList, count, username, userpostcode, useraddress, userdetailaddress, useremail, ordermessage, payment);

        	    // 성공적으로 주문되었다는 메시지 등을 설정하거나 리디렉션할 수 있습니다.
        	    try {
        	        response.getWriter().println("주문이 성공적으로 완료되었습니다.");
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        	}
    }
}
