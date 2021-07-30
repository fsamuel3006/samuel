package com.forumposttagedetails.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumPostTagDetailsDAO implements ForumPostTagDetailsDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.FORUM_POST_TAG_DETAILS (FPTD_TAG_ID,FPTD_POST_ID) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT FPTD_TAG_ID,FPTD_POST_ID FROM MAOUNI.FORUM_POST_TAG_DETAILS";
	private static final String GET_ONE_STMT = 
		"SELECT FPTD_TAG_ID,FPTD_POST_ID FROM MAOUNI.FORUM_POST_TAG_DETAILS WHERE FPTD_TAG_ID = ?";	
	private static final String DELETE = 
		"DELETE FROM MAOUNI.FORUM_POST_TAG_DETAILS WHERE (FPTD_TAG_ID,FPTD_POST_ID) = (?,?)";
	
	//新增
	@Override
	public void insert(ForumPostTagDetailsVO forumPostTagDetailsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostTagDetailsVO.getFptdTagId());
			pstmt.setInt(2, forumPostTagDetailsVO.getFptdPostId());
			
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
	public void delete(Integer fptdTagId, Integer fptdPostId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, fptdTagId);
				pstmt.setInt(2, fptdPostId);

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

	//以fptTagId(標籤ID)查詢
	@Override
	public ForumPostTagDetailsVO findByPrimaryKey(Integer fptdTagId) {
		
		ForumPostTagDetailsVO forumPostTagDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fptdTagId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumPostTagDetailsVO = new ForumPostTagDetailsVO();
				forumPostTagDetailsVO.setFptdTagId(rs.getInt("FPTD_TAG_ID"));
				forumPostTagDetailsVO.setFptdPostId(rs.getInt("FPTD_POST_ID"));
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
		return forumPostTagDetailsVO;
	}
	
	//全部列出
	@Override
	public List<ForumPostTagDetailsVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumPostTagDetailsVO> list = new ArrayList<ForumPostTagDetailsVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ForumPostTagDetailsVO forumPostTagDetailsVO = new ForumPostTagDetailsVO();
				forumPostTagDetailsVO.setFptdTagId(rs.getInt(1));
				forumPostTagDetailsVO.setFptdPostId(rs.getInt(2));
				list.add(forumPostTagDetailsVO); // Store the row in the list
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
