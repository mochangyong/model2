package com.naver.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;

public class boardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");
		
		boolean result = false;
		boolean usercheck=false;
		int no =Integer.parseInt(request.getParameter("no"));
		
		Board18DAO boarddao = new Board18DAO();
		usercheck=boarddao.isBoardWriter(no, request.getParameter("board_pass"));
		
		if(usercheck == false){
			response.setContentType("text/html);charset=euc=kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("arert('������ ������ �����ϴ�.');");
			out.println("location.href=',/BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
			
		}
		result=boarddao.boardDelete(no);
		if(result==false){
			System.out.println("�Խ��� ���� ����");
			return null;
			
		}
		System.out.println("�Խ��� ���� ����");
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		
		return forward;
	}

}
