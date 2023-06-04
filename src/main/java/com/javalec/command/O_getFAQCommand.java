package com.javalec.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.dao.O_NoticeDao;
import com.javalec.dto.O_NoticeDto;
import com.javalec.dto.O_PageNationDto;

public class O_getFAQCommand implements Acommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		O_NoticeDao dao = new O_NoticeDao();


		ArrayList<O_NoticeDto> dtos = dao.getFAQList();


		request.setAttribute("FAQList", dtos);

	}

}
