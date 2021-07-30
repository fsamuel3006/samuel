package com.sick_class.model;

import java.util.*;
import java.sql.*;

public class ClassJDBCDAO implements ClassDAO_interface {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "insert into SICK_CLASS (SC_PAIN) values (?) ;";
	private static final String GET_ALL_STMT = "SELECT SC_SL_ID,SC_PAIN FROM SICK_CLASS";
	
//	private static final String UPDATE = " UPDATE SICK_CLASS set SC_PAIN= ? where SC_ID = ? ";
//	private static final String GET_ONE_STMT = " SELECT SC_PAIN FROM SICK_CLASS where SC_ID = ?";
//	private static final String DELETE = " DELETE FROM SICK_CLASS where SC_ID = ?";

	@Override
	public Integer insert(String scPain) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer completeNum = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, scPain);
			completeNum = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return completeNum;
	}




	@Override
	public List<ClassVO> getAll() {
		List<ClassVO> list = new ArrayList<ClassVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ClassVO classVO = new ClassVO();
				classVO.setScId(rs.getInt("SC_SL_ID"));
				classVO.setScPain(rs.getString("SC_PAIN"));
				list.add(classVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

	
//	@Override
//	public Integer update(String scPain) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Integer completeNum = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, classVO.getScPain());
//			pstmt.setInt(2, classVO.getScId());
//
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return completeNum;
//	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public ClassVO findByPrimaryKey(Integer ScId) {
//
//		ClassVO classVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, ScId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				classVO = new ClassVO();
//				classVO.setScId(rs.getInt("scId"));
//				classVO.setScPain(rs.getString("scPain"));
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return classVO;
//
//	}
	
	
//	@Override
//	public void delete(Integer ScId) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, ScId);
//			pstmt.executeUpdate();
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
	
	
	
	
	
	
	
	
	
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//	
//		ClassJDBCDAO dao =new ClassJDBCDAO();
//		
//		  Connection con = null;
//		  PreparedStatement pstmt = null;
//		  Class.forName(driver);
//		  con = DriverManager.getConnection(url, userid, passwd);
//		  pstmt = con.prepareStatement(INSERT_STMT);
//		  System.out.println("fuck");
//		
//		  ClassVO classVO1 = new ClassVO();
//		  classVO1.setScId(3);
//		  classVO1.setScPain("�o�˴��ڦn�Y��");
//		  dao.insert(classVO1);
//		  dao.delete(3);
	
}