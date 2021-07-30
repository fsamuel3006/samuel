package com.variety.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.pet.model.PetJDBCDAO;

import java.sql.*;

public class VarietyJDBCDAO implements VarietyDAO_interface {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
	"insert into VARIETY (VAR_NAME, VAR_KIND) values (?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT * FROM VARIETY";
	private static final String GET_ONE_STMT = 
	"SELECT VAR_ID, VAR_NAME, VAR_KIND FROM VARIETY where VAR_ID = ?";
	private static final String DELETE = 
	"DELETE FROM VARIETY where VAR_ID = ?";
	private static final String UPDATE = 
	"UPDATE VARIETY set VAR_NAME = ?, VAR_KIND = ? where VAR_ID = ?";

	@Override
	public void insert(VarietyVO varietyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, varietyVO.getVarName());
			pstmt.setString(2, varietyVO.getVarKind());

			pstmt.executeUpdate();

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

	@Override
	public void update(VarietyVO varietyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, varietyVO.getVarName());
			pstmt.setString(2, varietyVO.getVarKind());

			pstmt.executeUpdate();

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

	@Override
	public void delete(Integer VarId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, VarId);

			pstmt.executeUpdate();

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

	@Override
	public VarietyVO findByPrimaryKey(Integer varId) {

		VarietyVO varietyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, varId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				varietyVO = new VarietyVO();
				varietyVO.setVarId(rs.getInt("VAR_ID"));
				varietyVO.setVarName(rs.getString("VAR_NAME"));
				varietyVO.setVarKind(rs.getString("VAR_KIND"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		
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
		return varietyVO;
	}

	@Override
	public List<VarietyVO> getAll() {

		List<VarietyVO> list = new ArrayList<VarietyVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

        try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				VarietyVO varietyVO = new VarietyVO();
				varietyVO.setVarId(rs.getInt("VAR_ID"));
				varietyVO.setVarName(rs.getString("VAR_NAME"));
				varietyVO.setVarKind(rs.getString("VAR_KIND"));

				list.add(varietyVO);
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


	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		  VarietyJDBCDAO dao = new VarietyJDBCDAO();
		  
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  Class.forName(driver);
		  con = DriverManager.getConnection(url, userid, passwd);
		  pstmt = con.prepareStatement(UPDATE);
		  System.out.println("【大嘻哈時代】EP2 100秒搶進賽｜純享版｜KUNG - 龔保雞丁");
//		  
//			VarietyVO varietyVO1 = new VarietyVO();
//			varietyVO1.setVarName("");
//			varietyVO1.setVarKind("");
//			dao.insert(varietyVO1);

//			List<VarietyVO> list = dao.getAll();
//			for (VarietyVO v : list) {
//				System.out.print(v.getVarId() + ",");
//				System.out.print(v.getVarName() + ",");
//				System.out.print(v.getVarKind() + ",");
//
//				System.out.println();
		  
//			VarietyVO v = dao.findByPrimaryKey(1);
//			System.out.print(v.getVarId() + ",");
//			System.out.print(v.getVarName() + ",");
//			System.out.print(v.getVarKind() + ",");
//			System.out.println("---------------------");
		  
//			VarietyVO v = new VarietyVO();
//			v.setVarName("");
//			v.setVarKind("");
//			dao.update(v);
//		  dao.delete(3);
}
}
