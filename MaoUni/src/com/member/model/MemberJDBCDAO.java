package com.member.model;

import java.util.*;

import java.sql.*;
import java.sql.Date;
import java.lang.Object;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.text.DateFormat;

public class MemberJDBCDAO implements MemberDAO_interface {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "insert into MEMBER (MEM_NAME, MEM_EMAIL, MEM_PASSWORD, MEM_IDENTITY, MEM_GENDER, MEM_PH, MEM_ADDRES, MEM_BIRTHDAY, MEM_POSITION, MEM_RESERVE, MEM_SURVIVE, MEM_UPDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,NOW())";
	private static final String GET_ALL = "SELECT * FROM MEMBER";
	private static final String GET_ONE_STMT = "SELECT MEM_ID, MEM_NAME, MEM_EMAIL, MEM_PASSWORD, MEM_IDENTITY, MEM_GENDER, MEM_PH, MEM_ADDRES, MEM_BIRTHDAY, MEM_POSITION, MEM_RESERVE, MEM_SURVIVE, MEM_UPDATE FROM MEMBER where MEM_ID = ?";
//	private static final String GET_ONEPOSITION = " SELECT MEM_NAME, MEM_EMAIL, MEM_PASSWORD, MEM_IDENTITY, MEM_GENDER, MEM_PH, MEM_ADDRES, MEM_BIRTHDAT,��MEM_SURVIVE FROM MEMBER where MEM_POSITION = ?";
	// 美容師檢測
	private static final String DELETE = "DELETE FROM MEMBER where MEM_ID = ?";
	private static final String UPDATE = "UPDATE MEMBER set MEM_NAME =?, MEM_EMAIL=?, MEM_PASSWORD=?, MEM_IDENTITY=? , MEM_GENDER=?, MEM_PH=?, MEM_ADDRES=?, MEM_BIRTHDAY=?, MEM_POSITION=? ,MEM_RESERVE=?, MEM_SURVIVE=? ,MEM_UPDATE=now() where MEM_ID = ?";
	private static final String CHECK ="SELECT * FROM MEMBER where MEM_EMAIL = ?";
	
	@Override
	public String checkemail(String memEmail) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String email = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHECK);
			pstmt.setString(1, memEmail);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

			email = rs.getString("MEM_EMAIL");
				
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
		return email;
	}

	private static final String SIGNUP = "insert into MEMBER (MEM_NAME, MEM_EMAIL, MEM_PASSWORD, MEM_IDENTITY, MEM_GENDER, MEM_PH, MEM_ADDRES, MEM_BIRTHDAY, MEM_POSITION, MEM_RESERVE, MEM_SURVIVE, MEM_UPDATE)VALUES (?, ? ,? , ?, ? ,? , ? , ? , 0, 0, 0,NOW())";

	@Override
	public void insert(MemberVO memberVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memberVo.getMemName());
			pstmt.setString(2, memberVo.getMemEmail());
			pstmt.setString(3, memberVo.getMemPassword());
			pstmt.setString(4, memberVo.getMemIdenity());
			pstmt.setString(5, memberVo.getMemGender());
			pstmt.setInt(6, memberVo.getMemPh());
			pstmt.setString(7, memberVo.getMemAddres());
			pstmt.setDate(8, memberVo.getMemBirthday());
			pstmt.setInt(9, memberVo.getMemPosition());
			pstmt.setInt(10, memberVo.getMemReserve());
			pstmt.setInt(11, memberVo.getMemSurvive());
//			pstmt.setTimestamp(12, memberVo.getMemUpdate());
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

	public void update(MemberVO memberVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVo.getMemName());
			pstmt.setString(2, memberVo.getMemEmail());
			pstmt.setString(3, memberVo.getMemPassword());
			pstmt.setString(4, memberVo.getMemIdenity());
			pstmt.setString(5, memberVo.getMemGender());
			pstmt.setInt(6, memberVo.getMemPh());
			pstmt.setString(7, memberVo.getMemAddres());
			pstmt.setDate(8, memberVo.getMemBirthday());
			pstmt.setInt(9, memberVo.getMemPosition());
			pstmt.setInt(10, memberVo.getMemReserve());
			pstmt.setInt(11, memberVo.getMemSurvive());
			pstmt.setInt(12, memberVo.getMemId());
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
	public void delete(Integer MemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, MemId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemberVO findByPrimaryKey(Integer MemId) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, MemId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt("MEM_ID"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memberVO.setMemIdenity(rs.getString("MEM_IDENTITY"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemPh(rs.getInt("MEM_PH"));
				memberVO.setMemAddres(rs.getString("MEM_ADDRES"));
				memberVO.setMemBirthday(rs.getDate("MEM_BIRTHDAY"));
				memberVO.setMemPosition(rs.getInt("MEM_POSITION"));
				memberVO.setMemReserve(rs.getInt("MEM_RESERVE"));
				memberVO.setMemSurvive(rs.getInt("MEM_SURVIVE"));
				memberVO.setMemUpdate(rs.getTimestamp("MEM_UPDATE"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return memberVO;
	}

//	@Override
//	public MemberVO findByUseremailAndpassword(String memEmail, String memPassword) {
//
//		String SQLLOGIN = "Select * from MEMBER WHERE MEM_EMAIL= ? && MEM_PASSWORD=?";
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(SQLLOGIN);
//
//			pstmt.setString(1, memEmail);
//			pstmt.setString(2, memPassword);
//
//			rs = pstmt.executeQuery();
//
//			MemberVO memberVO = null;
//
//			while (rs.next()) {
//
//				memberVO = new MemberVO();
//
//				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
//				memberVO.setMemPassword(rs.getString("MEM_PASSWORD"));
//
//			}
//			return memberVO;
//
//		} catch (ClassNotFoundException e) {
//
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		}
//	}

	@Override
	public MemberVO findByUseremailAndpassword(String memEmail, String memPassword) {

		 	String SQLLOGIN = "Select * from MEMBER WHERE MEM_EMAIL= ? && MEM_PASSWORD=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
//			List<MemberVO> list = new ArrayList<MemberVO>();
			
			try {	
				
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SQLLOGIN); 

			pstmt.setString(1,memEmail);
			pstmt.setString(2,memPassword);
			
			rs = pstmt.executeQuery(); 

			MemberVO memberVO = null; ////要給null才可以判斷使用者是否為空，也等於把vo串聯過來

			while (rs.next()) { //結果集中

			memberVO = new MemberVO();
			
			memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
			memberVO.setMemPassword(rs.getString("MEM_PASSWORD"));
					 
		}
			return memberVO;

			} catch (ClassNotFoundException e) {
				
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} 
	}	
	
	@Override
	public MemberVO OutUser(String memEmail, String memPassword) {

		String SQLLOGIN = "Select * from MEMBER WHERE MEM_EMAIL= ? && MEM_PASSWORD=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SQLLOGIN);

			pstmt.setString(1, memEmail);
			pstmt.setString(2, memPassword);

			rs = pstmt.executeQuery();

			MemberVO memberVO = null;

			while (rs.next()) {

				memberVO = new MemberVO();

				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setMemPassword(rs.getString("MEM_PASSWORD"));

			}
			return memberVO;

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void signup(MemberVO memberVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SIGNUP);
			pstmt.setString(1, memberVo.getMemName());
			pstmt.setString(2, memberVo.getMemEmail());
			pstmt.setString(3, memberVo.getMemPassword());
			pstmt.setString(4, memberVo.getMemIdenity());
			pstmt.setString(5, memberVo.getMemGender());
			pstmt.setInt(6, memberVo.getMemPh());
			pstmt.setString(7, memberVo.getMemAddres());
			pstmt.setDate(8, memberVo.getMemBirthday());
			pstmt.executeUpdate();
			System.out.println("upupsuupppppppppp");
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
	public MemberVO upemail(String memEmail) {

		String upemail = "Select * from MEMBER WHERE MEM_EMAIL= ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(upemail);

			pstmt.setString(1, memEmail);

			rs = pstmt.executeQuery();

			MemberVO memberVO = null;

			while (rs.next()) {

				memberVO = new MemberVO();

				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));

			}
			return memberVO;

		} catch (ClassNotFoundException e) {

			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public List<MemberVO> getAll() {

		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt("MEM_ID"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setMemPassword(rs.getString("MEM_PASSWORD"));
				memberVO.setMemIdenity(rs.getString("MEM_IDENTITY"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemPh(rs.getInt("MEM_PH"));
				memberVO.setMemAddres(rs.getString("MEM_ADDRES"));
				memberVO.setMemBirthday(rs.getDate("MEM_BIRTHDAY"));
				memberVO.setMemPosition(rs.getInt("MEM_POSITION"));
				memberVO.setMemReserve(rs.getInt("MEM_RESERVE"));
				memberVO.setMemSurvive(rs.getInt("MEM_SURVIVE"));
				memberVO.setMemUpdate(rs.getTimestamp("MEM_UPDATE"));
				list.add(memberVO);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			return list;
		}
	}

}
