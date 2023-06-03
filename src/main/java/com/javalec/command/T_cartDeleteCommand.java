package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.T_Dao;

public class T_cartDeleteCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pid = request.getParameter("pid");
		System.out.println(pid);
		T_Dao dao = new T_Dao();
		dao.delete(pid);
		
		
	}
}
//		String seqParam = request.getParameter("seq");
//    	int seq = 0; // 기본값 설정
//    	if (seqParam != null && !seqParam.isEmpty()) {
//    	    seq = Integer.parseInt(seqParam);
//    	}
//    	System.out.println(seq);
//        T_Dao dao = new T_Dao();
//        dao.delete(seq);
//	}


/*  // T_Dao 객체 생성
	    T_Dao dao = new T_Dao();
	    
	    // seq 매개변수 값을 가져옴
	    String[] seqParams = request.getParameterValues("seq");
	    
	    if (seqParams != null) {
	        for (String seqParam : seqParams) {
	            if (!seqParam.isEmpty()) {
	                try {
	                    // seq 값을 정수로 변환
	                    int seq = Integer.parseInt(seqParam);
	                    System.out.println(seq);
	                    
	                    // DAO를 사용하여 해당 seq 값에 해당하는 레코드 삭제
	                    dao.delete(seq);
	                } catch (NumberFormatException e) {
	                    // seq 매개변수가 잘못된 형식인 경우 처리할 내용을 여기에 작성하세요.
	                    e.printStackTrace();
	                }
	            }
	        }
	    } else {
	        // seq 매개변수가 비어 있거나 없는 경우 처리할 내용을 여기에 작성하세요.
	        System.out.println("매개변수 비어 있음");*/
