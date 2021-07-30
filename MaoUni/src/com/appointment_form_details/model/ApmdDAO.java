package com.appointment_form_details.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApmdDAO implements ApmdDAO_interface {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	private String userid = "David";
	private String passwd = "123456";

	private final String INSERT_STMT = "insert into APPOINTMENT_FORM_DETAILS values (?, ?, ?)";
	private final String GET_ONELIST_STMT = "select a.APMID,a.SVCID,a.PRICE,s.ITEM from APPOINTMENT_FORM_DETAILS a join SERVICE_ITEMS s on (a.SVCID = s.SVCID) where a.APMID = ?";

	@Override
	public void insert(ApmdVO apmdVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, apmdVO.getApmId());
			pstmt.setInt(2, apmdVO.getSvcId());
			pstmt.setInt(3, apmdVO.getPrice());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ApmdVO apmdVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer apmId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ApmdVO> getByAppointmentId(Integer apmId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApmdVO> list = new ArrayList<ApmdVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONELIST_STMT);
			pstmt.setInt(1, apmId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ApmdVO apmdVO = new ApmdVO();
				apmdVO.setApmId(rs.getInt(1));
				apmdVO.setSvcId(rs.getInt(2));
				apmdVO.setItem(rs.getString(4));
				
				list.add(apmdVO);
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

}
