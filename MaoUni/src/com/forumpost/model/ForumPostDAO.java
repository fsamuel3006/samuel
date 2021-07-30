package com.forumpost.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Forum_Post;

public class ForumPostDAO implements ForumPostDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.FORUM_POST (FP_MEM_ID,FP_TITLE,FP_CONTENT,FP_STATUS,FP_UPDATE) VALUES (?, ?, ?, ?, now())";
	private static final String GET_ALL_STMT = 
		"SELECT FP_ID,FP_MEM_ID,FP_TITLE,FP_CONTENT,FP_STATUS,FP_UPDATE FROM MAOUNI.FORUM_POST";
	private static final String GET_ONE_STMT = 
		"SELECT FP_ID,FP_MEM_ID,FP_TITLE,FP_CONTENT,FP_STATUS,FP_UPDATE FROM MAOUNI.FORUM_POST WHERE FP_ID = ?";
	private static final String DELETE = 
		"DELETE FROM MAOUNI.FORUM_POST WHERE FP_ID = ?";
	private static final String UPDATE = 
		"UPDATE MAOUNI.FORUM_POST SET FP_MEM_ID=?, FP_TITLE=?, FP_CONTENT=?, FP_STATUS=?, FP_UPDATE=now() WHERE FP_ID = ?";
	
	
	//新增
	@Override
	public void insert(ForumPostVO forumPostVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostVO.getFpMemId());
			pstmt.setString(2, forumPostVO.getFpTitle());
			pstmt.setString(3, forumPostVO.getFpContent());
			pstmt.setInt(4, forumPostVO.getFpStatus());
			//因為FpUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明
			
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
	public void update(ForumPostVO forumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, forumPostVO.getFpMemId());
			pstmt.setString(2, forumPostVO.getFpTitle());
			pstmt.setString(3, forumPostVO.getFpContent());
			pstmt.setInt(4, forumPostVO.getFpStatus());
			pstmt.setInt(5, forumPostVO.getFpId());
			//因為FpUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明

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
	public void delete(Integer fpId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, fpId);

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

	//以FpId(貼文ID)查詢
	@Override
	public ForumPostVO findByPrimaryKey(Integer fpId) {
		
		ForumPostVO forumPostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fpId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setFpId(rs.getInt("FP_ID"));
				forumPostVO.setFpMemId(rs.getInt("FP_MEM_ID"));
				forumPostVO.setFpTitle(rs.getString("FP_TITLE"));
				forumPostVO.setFpContent(rs.getString("FP_CONTENT"));
				forumPostVO.setFpStatus(rs.getInt("FP_STATUS"));
				forumPostVO.setFpUpdate(rs.getDate("FP_UPDATE"));
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
		return forumPostVO;
	}

	//全部列出
	@Override
	public List<ForumPostVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ForumPostVO forumPostVO = new ForumPostVO();
				forumPostVO.setFpId(rs.getInt("FP_ID"));
				forumPostVO.setFpMemId(rs.getInt("FP_MEM_ID"));
				forumPostVO.setFpTitle(rs.getString("FP_TITLE"));
				forumPostVO.setFpContent(rs.getString("FP_CONTENT"));
				forumPostVO.setFpStatus(rs.getInt("FP_STATUS"));
				forumPostVO.setFpUpdate(rs.getDate("FP_UPDATE"));  
				list.add(forumPostVO); // Store the row in the list
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
	
	
	// 萬用複合查詢
	public List<ForumPostVO> getAll(Map<String, String[]> map) {
		List<ForumPostVO> list = new ArrayList<ForumPostVO>();
		ForumPostVO forumPostVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
//			con = ds.getConnection();
			String finalSQL = "SELECT * FROM FORUM_POST "
		          + jdbcUtil_CompositeQuery_Forum_Post.get_WhereCondition(map)
		          + "ORDER BY FP_ID";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				forumPostVO = new ForumPostVO();
				forumPostVO.setFpId(rs.getInt("fpId"));
				forumPostVO.setFpMemId(rs.getInt("fpMemId"));
				forumPostVO.setFpTitle(rs.getString("fpTitle"));
				forumPostVO.setFpContent(rs.getString("fpContent"));
				forumPostVO.setFpStatus(rs.getInt("fpStatus"));
				forumPostVO.setFpUpdate(rs.getDate("fpUpdate"));
				list.add(forumPostVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
