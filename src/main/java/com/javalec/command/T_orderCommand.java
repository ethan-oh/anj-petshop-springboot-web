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
        
//        if (username == null || username.isEmpty() ||
//            userpostcode == null || userpostcode.isEmpty() ||
//            useraddress == null || useraddress.isEmpty() ||
//            userdetailaddress == null || userdetailaddress.isEmpty() ||
//            phone1 == null || phone1.isEmpty() ||
//            phone2 == null || phone2.isEmpty() ||
//            phone3 == null || phone3.isEmpty()) {
//
//            // 필수 필드가 비어있을 경우 오류 처리
//            try {
//                // 오류 메시지 전송 또는 팝업 창을 통해 사용자에게 알리는 방식으로 처리합니다.
//                response.getWriter().println("필수 필드를 모두 입력해주세요.");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return; // 필수 필드가 비어있을 경우, 함수 실행 중단
//        }

        List<Integer> countList = new ArrayList<>();
        List<String> pidList = new ArrayList<>();

        T_Dao dao = new T_Dao();

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
                int count = 0;
                String countParameter = request.getParameter("count_" + pid);

                if (countParameter != null && !countParameter.isEmpty()) {
                    count = Integer.parseInt(countParameter);
                    System.out.println("count = " + count);
                    System.out.println("countParameter = " + countParameter);
                    System.out.println("countParameter = " + pid);
                } else {
                    System.out.println("null");
                }

                // pid와 count를 리스트에 추가
                pidList.add(pid);
                countList.add(count);
            }
        } else {
            System.out.println("pid is null");
        }
        
        System.out.println("username = " + username);
        System.out.println("userpostcode = " + userpostcode);
        System.out.println("useraddress = " + useraddress);
        System.out.println("userdetailaddress = " + userdetailaddress);

        System.out.println("phone1 = " + phone1);
        System.out.println("phone2 = " + phone2);
        System.out.println("phone3 = " + phone3);
        System.out.println("ordermessage = " + ordermessage);
        System.out.println("payment = " + payment);
        System.out.println("usedmileage = " + usedmileage);

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




//dao.productorders(pidList, count);
//dao.usersorders(username, userpostcode, useraddress, userdetailaddress, phone1, phone2, phone3, ordermessage, payment, usedmileage);
//productorders
//if (usedmileageParameter != null && !usedmileageParameter.isEmpty()) {
//  usedmileage = Integer.parseInt(usedmileageParameter);
//}