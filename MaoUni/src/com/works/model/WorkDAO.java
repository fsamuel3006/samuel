package com.works.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class WorkDAO implements WorkDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private final String INSERT_STMT = "insert into WORKS(GROOMERID, ITEM , WUPDATE) values (?, ?, now())";
	private final String GET_ONELIST_STMT = "select * from WORKS where GROOMERID = ?";
	private final String DELETE_STMT = "delete from WORKS where WID = ?";
	
	@Override
	public void insert(WorkVO workVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Date date = new Date();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, workVO.getGroomerId());
			pstmt.setBytes(2, workVO.getItem());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}


	@Override
	public void delete(Integer wid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, wid);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	
	@Override
	public List<WorkVO> getOneList(Integer groomerId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<WorkVO> list = new ArrayList<WorkVO>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONELIST_STMT);
			pstmt.setInt(1, groomerId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WorkVO workVO = new WorkVO();
				workVO.setWid(rs.getInt("WID"));
				workVO.setGroomerId(rs.getInt("GROOMERID"));
				if(rs.getBytes("ITEM") != null) {
					workVO.setItemBase64(base64Change(rs.getBytes("ITEM")));
				}
				workVO.setLikes(rs.getInt("LIKES"));
				workVO.setWupdate(rs.getTimestamp("WUPDATE"));
				list.add(workVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}


	
	public String base64Change(byte[] b) {
		String pic = Base64.getEncoder().encodeToString(b);
		return pic;
	}
	

}
