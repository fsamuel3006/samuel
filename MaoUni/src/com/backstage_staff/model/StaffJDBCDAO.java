package com.backstage_staff.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.StaffSignIn.model.signInVO;
import com.adopt_imf.model.ImfJDBCDAO;
import com.adopt_imf.model.ImfVO;

public class StaffJDBCDAO implements StaffDAO_interface {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO BACKSTAGE_staff(MG_ID,MG_STATUS,MG_NAME,MG_AGE,MG_EDU,MG_ADD,MG_CONT,MG_TEL,MG_PASSWORD,MG_USERNAME) VALUES (?,?,?,?,?,?,?,?,?,?);";
	private static final String GET_ALL_STMT = "SELECT MG_ID,MG_STATUS,MG_NAME,MG_AGE,MG_EDU,MG_ADD,MG_CONT,MG_TEL,MG_PASSWORD,MG_USERNAME FROM BACKSTAGE_staff order by MG_ID";
	private static final String GET_ONE_STMT = "SELECT  MG_ID,MG_STATUS,MG_NAME,MG_AGE,MG_EDU,MG_ADD,MG_CONT,MG_TEL,MG_PASSWORD,MG_USERNAME FROM BACKSTAGE_staff where MG_ID = ?";
	private static final String DELETE = "DELETE FROM BACKSTAGE_staff where MG_ID = ?";
	private static final String UPDATE = "UPDATE BACKSTAGE_staff set MG_STATUS = ?, MG_NAME = ?, MG_AGE = ?, MG_EDU =?, MG_ADD=?, MG_CONT=?, MG_Tel=?,MG_PASSWORD=?,MG_USERNAME=? where MG_ID = ?";

	private static final String findByMGnameAndPassword = "select * from backstage_Staff where MG_USERNAME = ? and MG_PASSWORD = ?";

	@Override
	public void insert(StaffVO StaffVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, StaffVO.getId());
			pstmt.setInt(2, StaffVO.getStatus());
			pstmt.setString(3, StaffVO.getName());
			pstmt.setInt(4, StaffVO.getAge());
			pstmt.setString(5, StaffVO.getEdu());
			pstmt.setString(6, StaffVO.getAdd());
			pstmt.setString(7, StaffVO.getCont());
			pstmt.setString(8, StaffVO.getTel());
			pstmt.setString(9, StaffVO.getPassword());
			pstmt.setString(10, StaffVO.getUsername());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			se.printStackTrace();
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(StaffVO StaffVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, StaffVO.getStatus());
			pstmt.setString(2, StaffVO.getName());
			pstmt.setInt(3, StaffVO.getAge());
			pstmt.setString(4, StaffVO.getEdu());
			pstmt.setString(5, StaffVO.getAdd());
			pstmt.setString(6, StaffVO.getCont());
			pstmt.setString(7, StaffVO.getTel());
			pstmt.setString(8, StaffVO.getPassword());
			pstmt.setString(9, StaffVO.getUsername());
			pstmt.setInt(10, StaffVO.getId());

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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, id);

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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public StaffVO findByPrimaryKey(Integer id) {
		StaffVO staffVO = null;
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
				staffVO = new StaffVO();
				staffVO.setId(rs.getInt("MG_ID"));
				staffVO.setStatus(rs.getInt("MG_STATUS"));
				staffVO.setName(rs.getString("MG_NAME"));
				staffVO.setAge(rs.getInt("MG_AGE"));
				staffVO.setEdu(rs.getString("MG_EDU"));
				staffVO.setAdd(rs.getString("MG_ADD"));
				staffVO.setCont(rs.getString("MG_CONT"));
				staffVO.setTel(rs.getString("MG_TEL"));
				staffVO.setPassword(rs.getString("MG_PASSWORD"));
				staffVO.setUsername(rs.getString("MG_USERNAME"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
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
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}
		return staffVO;
	}

	@Override
	public List<StaffVO> getAll() {
		List<StaffVO> list = new ArrayList<StaffVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StaffVO staffVO = new StaffVO();
				staffVO.setId(rs.getInt("MG_ID"));
				staffVO.setStatus(rs.getInt("MG_STATUS"));
				staffVO.setName(rs.getString("MG_NAME"));
				staffVO.setAge(rs.getInt("MG_AGE"));
				staffVO.setEdu(rs.getString("MG_EDU"));
				staffVO.setAdd(rs.getString("MG_ADD"));
				staffVO.setCont(rs.getString("MG_CONT"));
				staffVO.setTel(rs.getString("MG_TEL"));
				staffVO.setPassword(rs.getString("MG_PASSWORD"));
				staffVO.setUsername(rs.getString("MG_USERNAME"));

				list.add(staffVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
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
	public StaffVO findByMGnameAndPassword(String username, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StaffVO staffVO = null;

		ResultSet s = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findByMGnameAndPassword);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			s = pstmt.executeQuery();
			while (s.next()) {
				staffVO = new StaffVO();
				staffVO.setName(s.getString("MG_USERNAME"));
				staffVO.setPassword(s.getString("MG_PASSWORD"));

				String usname = s.getString("MG_USERNAME");
				String pwd = s.getString("MG_PASSWORD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return staffVO;
	}

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		StaffJDBCDAO dao = new StaffJDBCDAO();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		pstmt = con.prepareStatement(UPDATE);
//		pstmt = con.prepareStatement(GET_ONE_STMT);
//		pstmt = con.prepareStatement(GET_ALL_STMT);
//		pstmt = con.prepareStatement(findByMGnameAndPassword);

//		StaffVO staffVO5=dao.findByPrimaryKey(3);
//		System.out.println(staffVO5.getUsername()+",");
//		System.out.println(staffVO5.getPassword()+",");
//		
////		//新增
//		StaffVO staffVO1=new StaffVO();
//		staffVO1.setAdd("桃園市中壢區");
//		staffVO1.setAge(29);
//		staffVO1.setCont("我是誰");
//		staffVO1.setEdu("私立淡江大學");
//		staffVO1.setId(26);
//		staffVO1.setName("walk");
//		staffVO1.setTel("0917394728");
//		staffVO1.setStatus(0);
//		staffVO1.setUsername("123@gmail.com");
//		staffVO1.setPassword("123456");
//		dao.insert(staffVO1);
//		
//		//�ק�
//		StaffVO staffVO2 = new StaffVO();
//		staffVO2.setStatus(0);
//		staffVO2.setName("吳震漢");
//		staffVO2.setAge(65);
//		staffVO2.setEdu("美國普林斯頓大學");
//		staffVO2.setAdd("新店");
//		staffVO2.setCont("kevin");
//		staffVO2.setId(13);
//		staffVO2.setTel("0921039472");
//		staffVO2.setUsername("123@gmail.com");
//		staffVO2.setPassword("123456");
//		dao.update(staffVO2);
		
//		
//		//�R��
//		dao.delete(21);

//		//�d��
//		StaffVO staffVO3=dao.findByPrimaryKey(1);
//		System.out.println(staffVO3.getId()+",");
//		System.out.println(staffVO3.getAdd()+",");
//		System.out.println(staffVO3.getCont()+",");
//		System.out.println(staffVO3.getEdu()+",");
//		System.out.println(staffVO3.getName()+",");
//		System.out.println(staffVO3.getAge()+",");
//		
//		System.out.println(staffVO3.getStatus()+",");
//		System.out.println(staffVO3.getTel()+",");
////		
//////		//�d��
//		
//		List<StaffVO> list = dao.getAll();
//		for(StaffVO staffVO : list) {
//			System.out.println(staffVO.getAdd()+",");
//			System.out.println(staffVO.getCont()+",");
//			System.out.println(staffVO.getEdu()+",");
//			System.out.println(staffVO.getName()+",");
//			System.out.println(staffVO.getAge()+",");
//			System.out.println(staffVO.getId()+",");
//			System.out.println(staffVO.getStatus()+",");
//			System.out.println(staffVO.getTel()+",");
//			System.out.println();
			
//		
	
	
}
