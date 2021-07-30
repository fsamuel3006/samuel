package com.announcemet.model;

import java.util.*;
import java.sql.*;

//���i  ANNOUNCEMET
public class AnnJDBCDAO implements AnnDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	// �s�W
	private static final String INSERT_STMT = 
			"INSERT INTO ANNOUNCEMET ( ANN_CONTENT, ANN_UPDATE,ANN_PIC ) VALUES (?, ? , ?)"; 
	// �d�ߥ���o
	private static final String GET_ALL_STMT = 
			"SELECT ANN_ID, ANN_CONTENT ,ANN_UPDATE,ANN_PIC FROM announcemet";
	// �d�ߤ@�����o
	private static final String GET_ONE_STMT = 
			"SELECT ANN_ID, ANN_CONTENT ,ANN_UPDATE,ANN_PIC FROM announcemet where ANN_ID = ?";
	// �R���@�����o
	private static final String DELETE = 
			"DELETE FROM announcemet where ANN_ID = ?";
	// �ק�@�����o
	private static final String UPDATE = 
			"UPDATE announcemet set ANN_CONTENT=?, ANN_UPDATE=? ,ANN_PIC=?  where ANN_ID = ?"; 

	@Override
	//�s�W
	public void insert(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, annVO.getContent());
			pstmt.setDate(2, annVO.getUpdate());
		    pstmt.setBytes(3, annVO.getPic());

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

	@Override
	//�ק�
	public void update(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, annVO.getContent());
			pstmt.setDate(2, annVO.getUpdate());
		    pstmt.setBytes(3, annVO.getPic());
		    pstmt.setInt(4, annVO.getId());
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

	@Override
	//�R��
	public void delete(Integer id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, id);

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

	@Override
	//�d�ߤ@�����
	public AnnVO findByPrimaryKey(Integer id) {

		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				annVO = new AnnVO();
				annVO.setId(rs.getInt("ANN_ID"));
				annVO.setContent(rs.getString("ANN_CONTENT"));
				annVO.setUpdate(rs.getDate("ANN_UPDATE"));
			    annVO.setPic(rs.getBytes("ANN_PIC"));
				
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
		return annVO;
	}

	@Override
	//�d�ߥ���
	public List<AnnVO> getAll() {
		List<AnnVO> list = new ArrayList<AnnVO>();
		AnnVO annVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				annVO = new AnnVO();
				annVO.setId(rs.getInt("ANN_ID"));
				annVO.setContent(rs.getString("ANN_CONTENT"));
				annVO.setUpdate(rs.getDate("ANN_UPDATE"));
				annVO.setPic(rs.getBytes("ANN_PIC"));
				
				list.add(annVO); // Store the row in the list
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

	public static void main(String[] args) {

		AnnJDBCDAO dao = new AnnJDBCDAO();

		// �s�W
		AnnVO annVO1 = new AnnVO();
		annVO1.setContent("�s�W���i����");
		annVO1.setUpdate(java.sql.Date.valueOf("2021-01-01"));
		annVO1.setPic(null);
		dao.insert(annVO1);

		// �ק�
		AnnVO annVO2 = new AnnVO();
		annVO2.setId(1);
		annVO2.setContent("�ק綠�i����2");
		annVO2.setUpdate(java.sql.Date.valueOf("2021-02-02"));
		annVO2.setPic(null);
		dao.update(annVO2);

		// �R��
		dao.delete(3);

		// �d��
		AnnVO annVO3 = dao.findByPrimaryKey(1);
		System.out.print(annVO3.getId() + ",");
		System.out.print(annVO3.getContent() + ",");
		System.out.print(annVO3.getUpdate() + ",");
		System.out.println(annVO3.getPic() + ",");

		System.out.println("---------------------");

		// �d��
		List<AnnVO> list = dao.getAll();
		for (AnnVO aAnn : list) {
			System.out.print(aAnn.getId() + ",");
			System.out.print(aAnn.getContent() + ",");
			System.out.print(aAnn.getUpdate() + ",");
			System.out.print(aAnn.getPic() + ",");
			System.out.println();
		}
	}
}
