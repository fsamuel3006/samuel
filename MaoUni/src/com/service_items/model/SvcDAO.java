package com.service_items.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SvcDAO implements SvcDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private static final String INSERT_STMT = "insert into SERVICE_ITEMS(ITEM,PET) values (?,?)";
	private static final String UPDATE_STMT = "update SERVICE_ITEMS set ITEM = ?, PET = ? where SVCID = ?";
	private static final String GET_ONE_STMT = "select SVCID , ITEM , PET  from SERVICE_ITEMS where PET like ?";
	private static final String GET_ALL_STMT = "select SVCID , ITEM , PET  from SERVICE_ITEMS";

	@Override
	public void insert(SvcVO svcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, svcVO.getItem());
			pstmt.setString(2, svcVO.getPet());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(SvcVO svcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, svcVO.getItem());
			pstmt.setString(2, svcVO.getPet());
			pstmt.setInt(3, svcVO.getSvcId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A Database error occured. " + se.getMessage());
		}

	}

	@Override
	public List<SvcVO> findByPet(String pet) {

		Connection con = null;
		PreparedStatement pstmt = null;
		SvcVO svcVO = null;
		ResultSet rs = null;
		List<SvcVO> list = new ArrayList();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pet);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				svcVO = new SvcVO();
				svcVO.setSvcId(rs.getInt("SVCID"));
				svcVO.setItem(rs.getString("ITEM"));
				svcVO.setPet(rs.getString("PET"));
				list.add(svcVO);
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return list;
	};

	@Override
	public List<SvcVO> getAll() {

		List<SvcVO> list = new ArrayList<SvcVO>();
		SvcVO svcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				svcVO = new SvcVO();
				svcVO.setSvcId(rs.getInt("SVCID"));
				svcVO.setItem(rs.getString("ITEM"));
				svcVO.setPet(rs.getString("PET"));
				list.add(svcVO);
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
