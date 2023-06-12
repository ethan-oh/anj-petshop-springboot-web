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
import com.javalec.dto.T_productDto;

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

        List<String> pidList = new ArrayList<>();
        List<String> pnameList = new ArrayList<>();
        List<Integer> orderpriceList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();

        T_Dao dao = new T_Dao();

        String ordermessage = request.getParameter("ordermessage");
        String payment = request.getParameter("payment");
        String userid = "do";
        
        String enteredmileageParameter = request.getParameter("enteredmileage");
        int enteredmileage = 0;
        if (enteredmileageParameter != null && !enteredmileageParameter.isEmpty()) {
            enteredmileage = Integer.parseInt(enteredmileageParameter);
        } else {
            System.out.println("null");
        }
        
        String usedmileageParameter = request.getParameter("usedmileage");
        int usedmileage = 0;
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
        
        if (request.getParameter("pname") != null) {
            String[] pnameArray = request.getParameterValues("pname");
            for (String pname : pnameArray) {
            	pnameList.add(pname);
            	System.out.println("pname = " + pname);
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
        
        System.out.println("enteredmileage = " + enteredmileage);        
        
        String ordernum = request.getParameter("ordernum");
        List<T_productDto> productList = new ArrayList<>();


        ArrayList<T_productDto> orderList = new ArrayList<>();
        request.setAttribute("orderList", orderList);
        // T_ordersDto 객체 생성 및 속성 설정
        T_ordersDto dto = new T_ordersDto();
        dto.setProductList(productList);

        // T_ordersDto 객체를 request 속성에 저장
        request.setAttribute("content_View", dto);
        
        
        
        
        
        dao.orders(pidList, countList, pnameList, orderpriceList, username, userpostcode, useraddress, userdetailaddress, phone1, phone2, phone3, ordermessage, payment, usedmileage, enteredmileage, request);
        dao.updatePstock(pidList, countList);
        
        System.out.println("ordernum@#@%!%@$@!%@! = " + ordernum);
        try {
            response.getWriter().println("주문이 성공적으로 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

