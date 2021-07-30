package com.sick_list.model;

import java.util.*;


import java.sql.*;

public class ListJDBCDAO implements ListDAO_interface{

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "insert into SICK_LIST (SL_ID, SL_PET_ID, SL_OTHER) values (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SICK_LIST";
	private static final String GET_ONE_STMT = "SELECT SL_ID, SL_PET_ID , SL_OTHER FROM SICK_LIST where SL_ID = ?";
	private static final String UPDATE = "UPDATE SICK_LIST set SL_ID =?, SL_PET_ID =?, SL_OTHER=? where SL_ID=?";
	private static final String DELETE = "DELETE FROM SICK_LIST where SL_ID = ?";
	
	
	@Override
	public void insert(ListVO listVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, listVO.getSlId());
			pstmt.setInt(2, listVO.getSlPetId());
			pstmt.setString(3, listVO.getSlOther());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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

	@Override
	public void update(ListVO listVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, listVO.getSlId());
			pstmt.setInt(2, listVO.getSlPetId());
			pstmt.setString(3, listVO.getSlOther());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


	@Override
	
	public ListVO findByPrimaryKey(Integer slId) {
		
		ListVO listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, slId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
			listVO = new ListVO();
			
			listVO.setSlId(rs.getInt("SL_ID"));
			listVO.setSlPetId(rs.getInt("SL_PET_ID"));
			listVO.setSlOther(rs.getString("SL_Other"));
				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return listVO;
	}

	@Override
	public List<ListVO> getAll() {
		
		List<ListVO> list = new ArrayList<ListVO>();
		ListVO listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				listVO = new ListVO();
				
				listVO.setSlId(rs.getInt("SL_ID"));
				listVO.setSlPetId(rs.getInt("SL_PET_ID"));
				listVO.setSlOther(rs.getString("SL_Other"));
				list.add(listVO); 
			}

		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	
public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
	ListJDBCDAO dao =new ListJDBCDAO();
	
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  Class.forName(driver);
	  con = DriverManager.getConnection(url, userid, passwd);
	  pstmt = con.prepareStatement(INSERT_STMT);
	  System.out.println("男女童裝搶先曝光■ 《鬼滅之刃》UT 聯名系列7/23準時上市！");
//	
	  ListVO listVO1 = new ListVO();
	  listVO1.setSlId(13);
	  listVO1.setSlPetId(1);
	  listVO1.setSlOther("sql");
	  dao.insert(listVO1);
//	  dao.delete(3);

//		ListVO c = dao.findByPrimaryKey(4);
//		System.out.print(c.getSlId() + ",");
//		System.out.print(c.getSlPetId() + ",");
//		System.out.print(c.getSlOther() + ",");
//		
//		System.out.println("---------------------");
	  
//		List<ListVO> list = dao.getAll();
//		for (ListVO l : list) {
//			System.out.print(l.getSlId() + ",");
//			System.out.print(l.getSlPetId() + ",");
//			System.out.print(l.getSlOther() + ",");
//			System.out.println();

//		ListVO l = new ListVO();
//		l.setSlId(2);
//		l.setSlPetId(2);
//		l.setSlOther("在想晚餐吃甚麼");
//
//		dao.update(l);
	  
	  
}
}