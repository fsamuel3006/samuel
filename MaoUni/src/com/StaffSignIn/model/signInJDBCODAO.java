package com.StaffSignIn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signInJDBCODAO implements signInDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	private static final String GET_ONE_USERNAME_PASSWORD = "select * from backstage_signin where USERNAME = ? and PASSWORD = ?";

	@Override
	public signInVO findByUsernameAndPassword(String username, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		signInVO signInVO = null;
		
		ResultSet s = null;
		try {
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_USERNAME_PASSWORD);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			s = pstmt.executeQuery();
			while(s.next()) {
				 
				signInVO = new signInVO();
				
//				signInVO.setId(s.getInt("id"));
				signInVO.setUsername(s.getString("MG_"));
				signInVO.setPassword(s.getString("password"));
				
				String usname = s.getString("username");
				Integer pwd = s.getInt("password");
				
//			if(username!=0) {
//					
//				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return signInVO;
	}
	
	
	
	
	

	@Override
	public signInVO Logout(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

//	public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		signInJDBCODAO dao = new signInJDBCODAO();
//		
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//	
//		String signInVO3=dao.GET_ONE_USERNAME_PASSWORD;
//		
//		System.out.println(signInVO3);
//		System.out.println(signInVO3);
//	
//	}
	
//@Override
//public signInVO Logout(String username, int password) {
//
//	
//	
//	String Logout = "select * from backstage_signin where USERNAME = ? and PASSWORD = ?";
//Connection con = null;
//PreparedStatement pstmt = null;
//signInVO signInVO = null;
//boolean islogin=false; 
//ResultSet s = null;
//try {
//	con=DriverManager.getConnection(url,userid,passwd);
//	pstmt=con.prepareStatement(Logout);
//	pstmt.setString(1, username);
//	pstmt.setInt(2, password);
//	
//	s = pstmt.executeQuery();
//	while(s.next()) {
//		islogin=true;
//		signInVO = new signInVO();
//		
////		signInVO.setId(s.getInt("id"));
//		signInVO.setUsername(s.getString("username"));
//		signInVO.setPassword(s.getInt("password"));
//		
//		String usname = s.getString("username");
//		Integer pwd = s.getInt("password");
//		
//		
//		
//	}
//} catch (SQLException e) {
//	e.printStackTrace();
//	throw new RuntimeException("A database error occured. " + e.getMessage());
//} finally {
//	if(s != null) {
//		try {
//			s.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	if(pstmt != null) {
//		try {
//			pstmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	if(con != null) {
//		try {
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}
//return signInVO;
//}
//
//
//
}

