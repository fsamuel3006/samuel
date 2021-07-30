package com.commodity_details.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CdJDBCDAO implements CdDAO_interface { 
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "insert into COMMODITY_DETAILS (CD_O_ID, CD_ITEM_ID, CD_AMOUNT, CD_MONEY) values (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM COMMODITY_DETAILS";
	private static final String GET_ONE_STMT = "SELECT CD_ITEM_ID, CD_AMOUNT, CD_MONEY FROM COMMODITY_DETAILS where CD_O_ID = ?";
	private static final String DELETE = "DELETE FROM COMMODITY_DETAILS where CD_O_ID = ?";
	private static final String UPDATE = "UPDATE COMMODITY_DETAILS set CD_ITEM_ID=?, CD_AMOUNT=?, CD_MONEY=? WHERE CD_O_ID =?";
 
	
	@Override
	public void insert(CdVO cdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
 
			pstmt.setInt(1, cdVO.getCdoId());
			pstmt.setInt(2, cdVO.getCdItemId());
			pstmt.setInt(3, cdVO.getCdAmount());
			pstmt.setInt(4, cdVO.getCdMoney());

			pstmt.executeUpdate();
			
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

	}

	@Override
	public void update(CdVO cdVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, cdVO.getCdItemId());
			pstmt.setInt(2, cdVO.getCdAmount());
			pstmt.setInt(3, cdVO.getCdMoney());

			pstmt.executeUpdate();

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

	}

	@Override
	
	public void delete(Integer CdOId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, CdOId);
			pstmt.executeUpdate();

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

	}

	@Override
	public CdVO findByPrimaryKey(Integer CdOId) {

		CdVO cdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, CdOId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				cdVO = new CdVO();
				cdVO.setCdoId(rs.getInt("CD_O_ID"));
				cdVO.setCdItemId(rs.getInt("CD_ITEM_ID"));
				cdVO.setCdAmount(rs.getInt("CD_AMOUNT"));
				cdVO.setCdMoney(rs.getInt("CD_MONEY"));

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
		return cdVO;

	}

	@Override
	public List<CdVO> getAll() {
		List<CdVO> list = new ArrayList<CdVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CdVO cdVO = new CdVO();
				cdVO.setCdoId(rs.getInt("CD_O_ID"));
				cdVO.setCdItemId(rs.getInt("CD_ITEM_ID"));
				cdVO.setCdAmount(rs.getInt("CD_AMOUNT"));
				cdVO.setCdMoney(rs.getInt("CD_MONEY"));

				list.add(cdVO);
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

	@Override
	public void insert2 (CdVO CdVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, CdVO.getCdoId());
			pstmt.setInt(2, CdVO.getCdItemId());
			pstmt.setInt(3, CdVO.getCdAmount());
			pstmt.setInt(4, CdVO.getCdMoney());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}	
	
//
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//	CdJDBCDAO dao = new CdJDBCDAO();
//
//	Connection con = null;
//	PreparedStatement pstmt = null;
//	Class.forName(driver);
//	con = DriverManager.getConnection(url, userid, passwd);
//	pstmt = con.prepareStatement(INSERT_STMT);
//	System.out.println("fuck");
//
//	CdVO cdVO1 = new CdVO();
//	cdVO1.setCdoId(3);
//	cdVO1.setCdItemId(1);
//	cdVO1.setCdAmount(1);
//	cdVO1.setCdMoney(980);
//	cdVO1.setCdOTher("");	
//	dao.insert(cdVO1);
//	 dao.delete(3);
//	dao.getAll();
}