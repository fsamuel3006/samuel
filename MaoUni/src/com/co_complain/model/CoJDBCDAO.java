package com.co_complain.model;

import java.sql.*;
import java.util.*;

public class CoJDBCDAO implements CoDAO_interface { //此頁面為商品訂單客服

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "insert into CO_COMPLAIN (COMPLAIN_MEM_ID, COMPLAIN_MG_ID, COMPLAIN_O_ID, COMPLAI_QA, COMPLAIN_DATE, COMPLAIN_OUT, COMPLAIN_QAC)values (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CO_COMPLAIN";
	private static final String GET_ONE_STMT = "SELECT COMPLAIN_MEM_ID, COMPLAIN_MG_ID,COMPLAIN_O_ID, COMPLAI_QA, COMPLAIN_DATE, COMPLAIN_OUT, COMPLAIN_QAC FROM CO_COMPLAIN where COMPLAIN_ID =?";
	private static final String DELETE = "DELETE FROM CO_COMPLAIN where COMPLAIN_ID = ?";
	private static final String UPDATE = "UPDATE CO_COMPLAIN set COMPLAIN_MEM_ID=?, COMPLAIN_MG_ID=?, COMPLAIN_O_ID=?, COMPLAI_QA=?, COMPLAIN_DATE=?, COMPLAIN_OUT=?, COMPLAIN_QAC where COMPLAIN_ID=?";
	
	@Override
	public void insert(CoVO coVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, coVO.getComplainMemId());
			pstmt.setInt(2, coVO.getComplainMgId());
			pstmt.setInt(3, coVO.getComplainOId());
			pstmt.setString(4, coVO.getComplainQa());
			pstmt.setInt(5, coVO.getComplainDate());
			pstmt.setInt(6, coVO.getComplainOut());
			pstmt.setString(7, coVO.getComplainQac());

			pstmt.executeUpdate();

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
	public void update(CoVO coVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, coVO.getComplainMemId());
			pstmt.setInt(2, coVO.getComplainMgId());
			pstmt.setInt(3, coVO.getComplainOId());
			pstmt.setString(4, coVO.getComplainQa());
			pstmt.setInt(5, coVO.getComplainDate());
			pstmt.setInt(6, coVO.getComplainOut());
			pstmt.setString(7, coVO.getComplainQac());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer ComplainId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ComplainId);

			pstmt.executeUpdate();

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
	public CoVO findByPrimaryKey(Integer ComplainId) {
	
		CoVO coVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ComplainId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				coVO = new CoVO();
				coVO.setComplainId(rs.getInt("COMPLAIN_ID"));
				coVO.setComplainMemId(rs.getInt("COMPLAIN_MEM_ID"));
				coVO.setComplainMgId(rs.getInt("COMPLAIN_MG_ID"));
				coVO.setComplainOId(rs.getInt("COMPLAIN_O_ID"));
				coVO.setComplainQa(rs.getString("COMPLAI_QA"));
				coVO.setComplainDate(rs.getInt("COMPLAIN_DATE"));
				coVO.setComplainOut(rs.getInt("COMPLAIN_OUT"));
				coVO.setComplainQac(rs.getString("COMPLAIN_QAC"));

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
		return coVO;
	}
	@Override
	public List<CoVO> getAll() {
		
		List<CoVO> list = new ArrayList<CoVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

        try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				CoVO coVO = new CoVO();
				coVO.setComplainId(rs.getInt("COMPLAIN_ID"));
				coVO.setComplainMemId(rs.getInt("COMPLAIN_MEM_ID"));
				coVO.setComplainMgId(rs.getInt("COMPLAIN_MG_ID"));
				coVO.setComplainOId(rs.getInt("COMPLAIN_O_ID"));
				coVO.setComplainQa(rs.getString("COMPLAI_QA"));
				coVO.setComplainDate(rs.getInt("COMPLAIN_DATE"));
				coVO.setComplainOut(rs.getInt("COMPLAIN_OUT"));
				coVO.setComplainQac(rs.getString("COMPLAIN_QAC"));

				list.add(coVO);
			}

			// Handle any driver errors
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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