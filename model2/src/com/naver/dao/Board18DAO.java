package com.naver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.naver.model.Board18Bean;

public class Board18DAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Board18DAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init
					.lookup("java:comp/env/jdbc/OracleDB");
			con = ds.getConnection();

		} catch (Exception e) {
			System.out.println("DB���� ����:" + e);
			return;
		}

	}

	// ���� ���� ���ϱ�
	public int getListCount() {
		int x = 0;

		try {
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);

			}
		} catch (Exception e) {
			System.out.println("getListCount����" + e);

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	// �۸�� ����
	public List getBoardList(int page, int limit) {
		String board_ilsst_sql = "select *from"
				+ "(select rownum rnum,board_no,board_name,board_title,"
				+ "board_cont, board_file,board_re_ref,board_re_lev,board_re_seq"
				+ "board_hitboard_date from"
				+ "(select*from board orderby board_re_ref desc,board_re_seq asc))"
				+ "wher rnum>=?and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * 10 + 1; // �б� ������ row��ȣ
		int endrow = startrow + limit - 1;// ���� ������ row��ȣ

		try {

			pstmt = con.prepareStatement(board_ilsst_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board18Bean board = new Board18Bean();
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_cont(rs.getString("board_cont"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_hit(rs.getInt("board_hit"));
				board.setDate(rs.getDate("board_date"));
				
				list.add(board);

			}
			return list;
		} catch (Exception e) {
			System.out.println("getBoardList ����:" + e);

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}

		}
		return null;
	}
//�� ���� ����
	
	public Board18Bean getDetail(int no)throws Exception{
		Board18Bean board = null;
		try{
			pstmt = con.prepareStatement("select * from board18 where board_no=?");
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				board = new Board18Bean();
				
			board.setBoard_no(rs.getInt("board_no"));
			board.setBoard_name(rs.getString("board_name"));
			board.setBoard_pass(rs.getString("board_pass"));
			board.setBoard_title(rs.getString("board_title"));
			board.setBoard_cont(rs.getString("board_cont"));
			board.setBoard_file(rs.getString("board_file"));
			board.setBoard_re_ref(rs.getInt("board_re_ref"));
			board.setBoard_re_lev(rs.getInt("board_re_lev"));
			board.setBoard_re_seq(rs.getInt("board_re_seq"));
			board.setBoard_hit(rs.getInt("board_hit"));
			board.setDate(rs.getDate("board_date"));
			
			
			
			
			}
			
			return board;
		}catch(Exception e){
			System.out.println("getDetail ����"+e);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	//�� ���
	public boolean boardInsert(Board18Bean board){
		
		int no =0;
		String sql="";
		int result = 0;
		try{
			pstmt = con.prepareStatement("selef max (board_no) from board18");
			rs=pstmt.executeQuery();
			if(rs.next())
				no=rs.getInt(1)+1;
			else
				no=1;
			
			sql = "inser into board18 (board_no,board_name,board_pass,board_title,board_cont,"
					+ "board_file,board_re_ref,board_re_lev,board_re_seq,board_hit,board_date)"
					+ "values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			 pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, no);
			 pstmt.setString(2, board.getBoard_name());
			 pstmt.setString(3, board.getBoard_pass());
			 pstmt.setString(4, board.getBoard_title());
			 pstmt.setString(5, board.getBoard_cont());
			 pstmt.setString(6, board.getBoard_file());
			 pstmt.setInt(7, no);		
			 pstmt.setInt(8, 0);		
			 pstmt.setInt(9, 0);		
			 pstmt.setInt(10, 0);
			 
			 result = pstmt.executeUpdate();
			 if(result==0)
				 return false;
			 
			 return true;
		}catch(Exception e){
			System.out.println("boardInser ���� "+e);
			
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
}

