package com.javalec.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.T_Dao;
import com.javalec.dto.T_ordersDto;

public class T_orderCommand implements Acommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 필수 필드 검사 로직 추가
        String username = request.getParameter("username");
        String userpostcode = request.getParameter("userpostcode");
        String useraddress = request.getParameter("useraddress");
        String userdetailaddress = request.getParameter("userdetailaddress");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String phone3 = request.getParameter("phone3");
        

        //List<Integer> countList = new ArrayList<>();
        List<String> pidList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();

        T_Dao dao = new T_Dao();
        int count = 0;
        String ordermessage = request.getParameter("ordermessage");
        String payment = request.getParameter("payment");
        String userid = "do";
        
        String usedmileageParameter = request.getParameter("usedmileage");
        int usedmileage = 0; // 기본값 설정
        if (usedmileageParameter != null && !usedmileageParameter.isEmpty()) {
        	usedmileage = Integer.parseInt(usedmileageParameter);
        } else {
        	System.out.println("null");
        }

        if (request.getParameter("pid") != null) {
            String[] pidArray = request.getParameterValues("pid");
            for (String pid : pidArray) {
                pidList.add(pid);
            }
        } else {
            System.out.println("null");
        }

        if (request.getParameter("count") != null) {
            String[] countArray = request.getParameterValues("count");
            for (String countStr : countArray) {
                if (!countStr.isEmpty()) {
                    int productCount = Integer.parseInt(countStr);
                    countList.add(productCount);
                } else {
                    System.out.println("count is null or empty");
                    // 적절한 기본값 또는 오류 처리를 수행하세요.
                }
            }
        } else {
            System.out.println("count is null");
            // 적절한 기본값 또는 오류 처리를 수행하세요.
        }


        
//        System.out.println("username = " + username);
//        System.out.println("userpostcode = " + userpostcode);
//        System.out.println("useraddress = " + useraddress);
//        System.out.println("userdetailaddress = " + userdetailaddress);
//        System.out.println("phone1 = " + phone1);
//        System.out.println("phone2 = " + phone2);
//        System.out.println("phone3 = " + phone3);
//        System.out.println("ordermessage = " + ordermessage);
//        System.out.println("payment = " + payment);
//        System.out.println("usedmileage = " + usedmileage);

        dao.orders(pidList, countList, username, userpostcode, useraddress, userdetailaddress, phone1, phone2, phone3, ordermessage, payment, usedmileage);
        
       
        // 주문 처리 로직 실행 후의 코드
        // 성공적으로 주문되었다는 메시지 등을 설정하거나 리디렉션할 수 있습니다.
        try {
            response.getWriter().println("주문이 성공적으로 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
