package com.forumpostmessage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ForumPostMessageDAO implements ForumPostMessageDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.FORUM_POST_MESSAGE (FPM_POST_ID,FPM_MEM_ID,FPM_CONTENT,FPM_UPDATE) VALUES (?, ?, ?, now())";
	private static final String GET_ALL_STMT = 
		"SELECT FPM_ID,FPM_POST_ID,FPM_MEM_ID,FPM_CONTENT,FPM_UPDATE FROM MAOUNI.FORUM_POST_MESSAGE";
	private static final String GET_ONE_STMT = 
		"SELECT FPM_ID,FPM_POST_ID,FPM_MEM_ID,FPM_CONTENT,FPM_UPDATE FROM MAOUNI.FORUM_POST_MESSAGE WHERE FPM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MAOUNI.FORUM_POST_MESSAGE WHERE FPM_ID = ?";
	private static final String UPDATE = 
		"UPDATE MAOUNI.FORUM_POST_MESSAGE SET FPM_POST_ID=?, FPM_MEM_ID=?, FPM_CONTENT=? ,FPM_UPDATE=now() WHERE FPM_ID = ?";
	
	
	//新增
	@Override
	public void insert(ForumPostMessageVO forumPostMessageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostMessageVO.getFpmPostId());
			pstmt.setInt(2, forumPostMessageVO.getFpmMemId());
			pstmt.setString(3, forumPostMessageVO.getFpmContent());
			//因為FpmUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	//修改
	@Override
	public void update(ForumPostMessageVO forumPostMessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, forumPostMessageVO.getFpmPostId());
			pstmt.setInt(2, forumPostMessageVO.getFpmMemId());
			pstmt.setString(3, forumPostMessageVO.getFpmContent());
			pstmt.setInt(4, forumPostMessageVO.getFpmId());
			//因為FpmUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	//刪除
	@Override
	public void delete(Integer fpmId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, fpmId);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}		

	//以fpmId(貼文留言ID)查詢
	@Override
	public ForumPostMessageVO findByPrimaryKey(Integer fpmId) {
		
		ForumPostMessageVO forumPostMessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fpmId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumPostMessageVO = new ForumPostMessageVO();
				forumPostMessageVO.setFpmId(rs.getInt("FPM_ID"));
				forumPostMessageVO.setFpmPostId(rs.getInt("FPM_POST_ID"));
				forumPostMessageVO.setFpmMemId(rs.getInt("FPM_MEM_ID"));
				forumPostMessageVO.setFpmContent(rs.getString("FPM_CONTENT"));
				forumPostMessageVO.setFpmUpdate(rs.getDate("FPM_UPDATE"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return forumPostMessageVO;
	}

	//全部列出
	@Override
	public List<ForumPostMessageVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumPostMessageVO> list = new ArrayList<ForumPostMessageVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ForumPostMessageVO forumPostMessageVO = new ForumPostMessageVO();
				forumPostMessageVO.setFpmId(rs.getInt("FPM_ID"));
				forumPostMessageVO.setFpmPostId(rs.getInt("FPM_POST_ID"));
				forumPostMessageVO.setFpmMemId(rs.getInt("FPM_MEM_ID"));
				forumPostMessageVO.setFpmContent(rs.getString("FPM_CONTENT"));
				forumPostMessageVO.setFpmUpdate(rs.getDate("FPM_UPDATE"));
				list.add(forumPostMessageVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
