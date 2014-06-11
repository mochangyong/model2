package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;
import com.naver.model.Board18Bean;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("euc-kr");
			ActionForward forward = new ActionForward();
			
			Board18DAO boarddao = new Board18DAO();
			Board18Bean boarddata = new Board18Bean();
			int result = 0;
			
			boarddata.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
			boarddata.setBoard_name(request.getParameter("board_name"));
			boarddata.setBoard_pass(request.getParameter("board_pass"));
			boarddata.setBoard_title(request.getParameter("board_title"));
			boarddata.setBoard_cont(request.getParameter("board_cont"));
			boarddata.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
			boarddata.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
			
			result = boarddao.boardReply(boarddata);
			if(result == 0 ){
			System.out.println("답장 실패");
			return null;
			}
			System.out.println("답장 완료");
			
			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.bo?no="+result);
			
		return forward;
	}

}
