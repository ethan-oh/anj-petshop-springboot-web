//package com.javalec.command;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.javalec.dao.T_Dao;
//import com.javalec.dto.T_ordersDto;
//import com.javalec.dto.T_productDto;
//
//public class T_purchaseCompleteCommand implements Acommand {
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) {
//    	T_Dao dao = new T_Dao();
//        String ordernum = request.getParameter("ordernum"); // 요청에서 ordernum 파라미터를 가져옵니다.
//
//        T_ordersDto dto = dao.orderlist(ordernum, request);
//        
//        
//        ArrayList<T_productDto> dtos = dao.orderproduct(ordernum);
//        request.setAttribute("orderproduct", dtos);
//        System.out.println("ordernum**** = " + ordernum);
//        System.out.println("orderproduct dtos**** = " + dtos);
//        
//        System.out.println("ordernum!!! = " + ordernum);
//        if (dto != null) {
//            request.setAttribute("content_View", dto);
//            request.setAttribute("ordernum", dto.getOrdernum());
//            request.setAttribute("orderdate", dto.getOrderdate());
//            request.setAttribute("usedmileage", dto.getUsedmileage());
//            request.setAttribute("orderprice", dto.getOrderprice());
//            request.setAttribute("payment", dto.getPayment());
//            request.setAttribute("pid", dto.getPid());
//            request.setAttribute("pname", dto.getPname());
//            request.setAttribute("count", dto.getCount());
//            request.setAttribute("username", dto.getUsername());
//            request.setAttribute("userpostcode", dto.getUserpostcode());
//            request.setAttribute("shipaddress", dto.getShipaddress());
//            request.setAttribute("usertel", dto.getUsertel());
//            request.setAttribute("ordermessage", dto.getOrdermessage());
//            
//        }
//    }
//}
