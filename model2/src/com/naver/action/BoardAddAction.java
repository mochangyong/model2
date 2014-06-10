package com.naver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naver.dao.Board18DAO;
import com.naver.model.Board18Bean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			Board18DAO boarddao = new Board18DAO();
			Board18Bean boarddata = new Board18Bean();
			ActionForward forward= new ActionForward();
			
			String realFolder="";
			String saveFolder ="boardupload";
			
			int fileSize=5*1024*1024;
			
			realFolder=request.getRealPath(saveFolder);
			
			boolean result = false;
			try{
				
				MultipartRequest multi = null;
				
				multi = new MultipartRequest(request,realFolder,fileSize,"euc-kr",new DefaultFileRenamePolicy());
				
				boarddata.setBoard_name(multi.getParameter("board_name"));
				boarddata.setBoard_pass(multi.getParameter("board_pass"));
				boarddata.setBoard_title(multi.getParameter("board_title"));
				boarddata.setBoard_cont(multi.getParameter("board_cont"));
				boarddata.setBoard_file(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
				result=boarddao.boardInsert(boarddata);
				
				if(result==false){
					System.out.println("게시판 등록 실패");
					return null;
					
				}
				System.out.println("게시판 등록 완료");
				
				forward.setRedirect(true);
				forward.setPath("./BoardList.bo");
				return forward;
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return null;
	}

}
