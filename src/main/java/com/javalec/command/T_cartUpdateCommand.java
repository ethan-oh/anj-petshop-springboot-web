package com.javalec.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.T_Dao;

public class T_cartUpdateCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pid = request.getParameter("pid"); 

		String countParam = request.getParameter("count_" + pid);
        if (countParam != null) {
            int count = Integer.parseInt(countParam);
            T_Dao dao = new T_Dao();
            dao.update(pid, count);
        }
	}

}
