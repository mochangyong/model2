package com.naver.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;
import com.naver.model.Board18Bean;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("euc-kr");
		ActionForward forward = new ActionForward();
		boolean result = false;

		int no = Integer.parseInt(request.getParameter("board_no"));

		Board18DAO boarddao = new Board18DAO();
		Board18Bean boarddata = new Board18Bean();

		boolean usercheck = boarddao.isBoardWriter(no,
				request.getParameter("board_pass"));
		if (usercheck == false) {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.')");
			out.println("location ='./BoardList.bo'");
			out.println("</script>");
			out.close();
			return null;

		}

		try {
			boarddata.setBoard_no(no);
			boarddata.setBoard_title(request.getParameter("board_title"));
			boarddata.setBoard_cont(request.getParameter("board_cont"));
			result = boarddao.boardModify(boarddata);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");

			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.bo?no="
					+ boarddata.getBoard_no());
			return forward;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

}
