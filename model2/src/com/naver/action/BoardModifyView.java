package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;
import com.naver.model.Board18Bean;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");
		
		Board18DAO boarddao = new Board18DAO();
		Board18Bean boarddata = new Board18Bean();
		
		int no = Integer.parseInt(request.getParameter("no"));
		boarddata = boarddao.getDetail(no);
		
		if(boarddata == null ){
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		forward.setPath("./board/board_modify.jsp");
		
		return forward;
	}

}
