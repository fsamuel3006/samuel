package com.reportforumpost.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportForumPostDAO implements ReportForumPostDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.REPORT_FORUM_POST (PREP_POST_ID,PREP_MEM_ID,PREP_CONTENT,PREP_STATUS,PREP_RESULT,PREP_UPDATE) VALUES (?, ?, ?, ?, ?, now())";
	private static final String GET_ALL_STMT = 
		"SELECT PREP_ID,PREP_POST_ID,PREP_MEM_ID,PREP_CONTENT,PREP_STATUS,PREP_RESULT,PREP_UPDATE FROM MAOUNI.REPORT_FORUM_POST";
	private static final String DELETE = 
		"DELETE FROM MAOUNI.REPORT_FORUM_POST WHERE PREP_ID = ?";
	private static final String UPDATE = 
		"UPDATE MAOUNI.REPORT_FORUM_POST SET PREP_POST_ID=?,PREP_MEM_ID=?,PREP_CONTENT=?,PREP_STATUS=?,PREP_RESULT=?,PREP_UPDATE=now() WHERE PREP_ID = ?";
	
	
	//新增
	@Override
	public void insert(ReportForumPostVO reportForumPostVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, reportForumPostVO.getPrepPostId());
			pstmt.setInt(2, reportForumPostVO.getPrepMemId());
			pstmt.setString(3, reportForumPostVO.getPrepContent());
			pstmt.setInt(4, reportForumPostVO.getPrepStatus());
			pstmt.setInt(5, reportForumPostVO.getPrepResult());
			//因為FrepUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明
			
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
	public void update(ReportForumPostVO reportForumPostVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, reportForumPostVO.getPrepPostId());
			pstmt.setInt(2, reportForumPostVO.getPrepMemId());
			pstmt.setString(3, reportForumPostVO.getPrepContent());
			pstmt.setInt(4, reportForumPostVO.getPrepStatus());
			pstmt.setInt(5, reportForumPostVO.getPrepResult());
			pstmt.setInt(6, reportForumPostVO.getPrepId());
			//因為FrepUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明

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
	public void delete(Integer frepId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, frepId);

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


	//全部列出
	@Override
	public List<ReportForumPostVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReportForumPostVO> list = new ArrayList<ReportForumPostVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportForumPostVO reportForumPostVO = new ReportForumPostVO();
				reportForumPostVO.setPrepId(rs.getInt("PREP_ID"));
				reportForumPostVO.setPrepPostId(rs.getInt("PREP_POST_ID"));
				reportForumPostVO.setPrepMemId(rs.getInt("PREP_MEM_ID"));
				reportForumPostVO.setPrepContent(rs.getString("PREP_CONTENT"));
				reportForumPostVO.setPrepStatus(rs.getInt("PREP_STATUS"));
				reportForumPostVO.setPrepResult(rs.getInt("PREP_RESULT"));  
				reportForumPostVO.setPrepUpdate(rs.getDate("PREP_UPDATE"));  
				list.add(reportForumPostVO); // Store the row in the list
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
