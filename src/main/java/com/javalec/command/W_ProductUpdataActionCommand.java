package com.javalec.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.W_ProductUpdataAction_Dao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class W_ProductUpdataActionCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

//		String directory = "/Users/leejonguk/eclipse-workspace/lju/Jsp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/anjshop/images/thumbnail";
		String directory = request.getServletContext().getRealPath("/images/thumbnail");
//		String directory = "anjshop/src/main/webapp/images/thumbnail";


		
		int sizeLimit = 100 * 1024 * 1024; // 100MB 제한
		try {
		    MultipartRequest multipartRequest = new MultipartRequest(request, directory, sizeLimit, "UTF-8",
		            new DefaultFileRenamePolicy());

		    String pid = multipartRequest.getParameter("pid");
		    String pname = multipartRequest.getParameter("pname");
		    String pcategory = multipartRequest.getParameter("pcategory");
		    int pprice = Integer.parseInt(multipartRequest.getParameter("pprice"));
		    int pstock = Integer.parseInt(multipartRequest.getParameter("pstock"));
		    int available = Integer.parseInt(multipartRequest.getParameter("available"));
		    String pthumbnail = multipartRequest.getOriginalFileName("pthumbnail");
		    
		    String pth2 = multipartRequest.getOriginalFileName("pth2");
		    String pth3 = multipartRequest.getOriginalFileName("pth3");
		    
		    
		    
		    W_ProductUpdataAction_Dao dao = new W_ProductUpdataAction_Dao();
		    
		    dao.productUpdataAction(pid, pname, pcategory, pprice, pstock, available, pthumbnail, pth2, pth3);
		} catch (IOException e) {
		    // 예외 처리 코드 작성
		    e.printStackTrace();
		}

		
		
	}

}
