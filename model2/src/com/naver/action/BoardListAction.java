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
		
		int listcount = boarddao.getListCount();//�Ѹ���Ʈ ���� �޾ƿ�
		boardlist = boarddao.getBoardList(page, limit);//����Ʈ�� �޾ƿ�
		
		//�� ������ ��
		int maxpage=(int)((double)listcount/limit+0.95);//0.95�� ���ؼ� �ø� ó��
		//���� �������� ������ ���� ������ ��(1,11,21��..)
		int startpage =(((int)((double)page/10+0.9))-1)*10+1;
		//������������ ������ ������ �������� (10,20,30��...)
		int endpage = startpage+10-1;
		
		if(endpage>maxpage)endpage = maxpage;
		
		request.setAttribute("page", page);//������������
		request.setAttribute("maxpage", maxpage);//�ִ� ��������
		request.setAttribute("startpage", startpage);//������������ ǥ���� ù ��������
		request.setAttribute("endpage", endpage);//���� �������� ǥ���� ����������
		request.setAttribute("listcount", listcount);//�ۼ�
		request.setAttribute("boardlist", boardlist);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/board_list.jsp");
		
		
		return forward;

	}

}
