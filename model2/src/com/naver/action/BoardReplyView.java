package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;
import com.naver.model.Board18Bean;

public class BoardReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ActionForward forward = new ActionForward();
			
			Board18DAO boarddao = new Board18DAO();
			Board18Bean boarddata = new Board18Bean();
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			boarddata=boarddao.getDetail(no);
			
			if(boarddata==null){
				System.out.println("������������ �̵� ����");
				return null;
				
			}
			System.out.println("���� �������� �̵� �Ϸ�");
			
			request.setAttribute("boarddata", boarddata);
			
			forward.setRedirect(false);
			forward.setPath("./board/board_reply.jsp");
		return forward;
	}

}
