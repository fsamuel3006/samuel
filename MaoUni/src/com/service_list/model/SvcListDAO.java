package com.service_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SvcListDAO implements SvcListDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	private final String INSERT_STMT = "insert into SERVICE_LIST (GROOMERID, SVCID, PRICE, SVCTIME) values (?,?,?,?)";
	private final String GET_ALL_STMT = "select sl.GROOMERID,sl.SVCID,sl.PRICE,sl.SVCTIME,si.item,si.pet from SERVICE_LIST sl join SERVICE_ITEMS si on sl.SVCID = si.SVCID where SVCPROVIDED = 1 && GROOMERID = ? order by si.pet;";
	private final String GET_ONE_STMT = "select sl.PRICE,si.item from SERVICE_LIST sl join SERVICE_ITEMS si on sl.SVCID = si.SVCID where sl.GROOMERID = ? && sl.SVCID = ? order by sl.SVCID";
	
	@Override
	public void insert(SvcListVO svcListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, svcListVO.getGroomerId());
			pstmt.setInt(2, svcListVO.getSvcId());
			pstmt.setInt(3, svcListVO.getPrice());
			pstmt.setInt(4, svcListVO.getSvcTime());
			
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
	public List<SvcListVO> getAll(Integer groomerId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SvcListVO> list = new ArrayList<SvcListVO>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, groomerId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SvcListVO svcListVO = new SvcListVO();
				svcListVO.setGroomerId(rs.getInt(1));
				svcListVO.setSvcId(rs.getInt(2));
				svcListVO.setPrice(rs.getInt(3));
				svcListVO.setSvcTime(rs.getInt(4));
				svcListVO.setSvcItem(rs.getString(5));
				svcListVO.setSvcPet(rs.getString(6));
				
				list.add(svcListVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public SvcListVO getItem(Integer groomerId, Integer svcId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SvcListVO svcListVO = new SvcListVO();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, groomerId);
			pstmt.setInt(2, svcId);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			svcListVO.setPrice(rs.getInt(1));
			svcListVO.setSvcItem(rs.getString(2));
			

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
		
			return svcListVO;	
	}
	
}
