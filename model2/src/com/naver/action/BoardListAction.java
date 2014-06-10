package com.naver.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;

public class BoardListAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Board18DAO boarddao = new Board18DAO();
		List boardlist = new ArrayList();

		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
			
		}
		
		int listcount = boarddao.getListCount();//총리스트 수를 받아옴
		boardlist = boarddao.getBoardList(page, limit);//리스트를 받아옴
		
		//총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95);//0.95를 데해서 올림 처리
		//현재 페이지에 보여줄 시작 페이지 수(1,11,21등..)
		int startpage =(((int)((double)page/10+0.9))-1)*10+1;
		//현제페이지에 보여줄 마지막 페이지수 (10,20,30등...)
		int endpage = startpage+10-1;
		
		if(endpage>maxpage)endpage = maxpage;
		
		request.setAttribute("page", page);//현제페이지수
		request.setAttribute("maxpage", maxpage);//최대 페이지수
		request.setAttribute("startpage", startpage);//현제페이지에 표시할 첫 페이지수
		request.setAttribute("endpage", endpage);//현제 페이지에 표시할 끝페이지수
		request.setAttribute("listcount", listcount);//글수
		request.setAttribute("boardlist", boardlist);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/board_list.jsp");
		
		
		return forward;

	}

}
