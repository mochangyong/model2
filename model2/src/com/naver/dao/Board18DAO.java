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
			System.out.println("DB연결 실패:" + e);
			return;
		}

	}

	// 글의 개수 구하기
	public int getListCount() {
		int x = 0;

		try {
			pstmt = con.prepareStatement("select count(*) from board18");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);

			}
		} catch (Exception e) {
			System.out.println("getListCount에러" + e);

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

	// 글목록 보기
	public List getBoardList(int page, int limit) {
		String board_list_sql = "select *from"
				+ "(select rownum rnum,board_no,board_name,board_title,"
				+ "board_cont, board_file,board_re_ref,board_re_lev,board_re_seq"
				+ "board_hit,board_date from"
				+ "(select*from board orderby board_re_ref desc,board_re_seq asc))"
				+ "wher rnum>=?and rnum<=?";

		List list = new ArrayList();

		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row번호
		int endrow = startrow + limit - 1;// 일을 마지막 row번호

		try {

			pstmt = con.prepareStatement(board_list_sql);
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
			System.out.println("getBoardList 에러:" + e);

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

	// 글 내용 보기

	public Board18Bean getDetail(int no) throws Exception {
		Board18Bean board = null;
		try {
			pstmt = con
					.prepareStatement("select * from board18 where board_no=?");

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
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
		} catch (Exception e) {
			System.out.println("getDetail 에러" + e);
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
		return null;
	}

	// 글 등록
	public boolean boardInsert(Board18Bean board) {

		int no = 0;
		String sql = "";
		int result = 0;
		try {
			pstmt = con.prepareStatement("selef max (board_no) from board18");
			rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1) + 1;
			else
				no = 1;

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
			if (result == 0)
				return false;

			return true;
		} catch (Exception e) {
			System.out.println("boardInser 에러 " + e);

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
		return false;
	}

	// 글 답변
	public int boardReply(Board18Bean board) {
		String board_max_sql = "select max(board_no)from board";
		String sql = "";
		int no = 0;
		int result = 0;

		int re_ref = board.getBoard_re_ref();
		int re_lev = board.getBoard_re_lev();
		int re_seq = board.getBoard_re_seq();

		try {
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1) + 1;
			else
				no = 1;

			sql = "udate board18 set board_re_seq=board_re_seq+1"
					+ "where board_re_ref=? and board_re_seq>?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_lev);

			result = pstmt.executeUpdate();
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;

			sql = "insert into board(board_no,board_name, board_pass,"
					+ "board_title, board_cont,board_file,board_re_ref,"
					+ "board_re_lev,board_re_seq,board_hit,board_date)"
					+ "values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, board.getBoard_name());
			pstmt.setString(3, board.getBoard_pass());
			pstmt.setString(4, board.getBoard_title());
			pstmt.setString(5, board.getBoard_cont());
			pstmt.setString(6, "");// 답장에는 팡일을 업로드하지 않음
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			return no;

		} catch (SQLException e) {
			System.out.println("boardReply 에러:" + e);

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
		return 0;
	}

	// 글 수정
	public boolean boardModify(Board18Bean modifyboard) throws Exception {
		String sql = "update board18 set board_title=?,board_cont=?"
				+ "where board_no=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBoard_title());
			pstmt.setString(2, modifyboard.getBoard_cont());
			pstmt.setInt(3, modifyboard.getBoard_no());

			pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			System.out.println("boardMOdify 에러 " + e);

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
		return false;

	}

	// 글 삭제
	public boolean boardDelete(int no) {
		String board_delete_sql = "delete from board18 where board_no=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception e) {
			System.out.println("boardDelet 에러 " + e);

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {

			}

		}
		return false;
	}

	// 조회수 업데이트
	public void setReadCountUpdate(int no) throws Exception {
		String sql = "update board18 set board_hit = "
				+ "board_hit+1 where board_no=" + no;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("setReadCountUpdate 에러:" + e);
		}

	}

	// 글쓴이인지 확인
	public boolean isBoardWriter(int no, String pass) {
		String board_sql = "select*from board where board_no=?";

		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			rs.next();

			if (pass.equals(rs.getString("board_pass")))
				return true;

		} catch (SQLException e) {
			System.out.println("isBoardWriter 에러" + e);

		}
		return false;
	}
}
