package com.adopt_imf.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Imf_BLOB {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	
//	private static final String SQL = "update adopt_imf set AI_ADOPT=?,AI_MECH=?,AI_NAME=?,AI_YEAR=?,AI_SIT=?,AI_PHOTO=? where AI_ID=?";

	private static final String INSERT_STMT = "INSERT INTO adopt_imf(AI_ID,AI_ADOPT,AI_MECH,AI_NAME,AI_YEAR,AI_SIT,AI_PHOTO) VALUES (?,?,?,?,?,?,?)";
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;	

		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			// 1. setBlob (JDBC 4.0)
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			pstmt.setInt(3,18);	
			pstmt.setString(4,"小胖");
			pstmt.setInt(5, 2);
			pstmt.setString(6, "狀況良好");
			byte[] pic = getPictureByteArray("/src/image/1.jpg");
			pstmt.setBytes(7, pic);
			pstmt.executeUpdate();
//			
			System.out.println("成功");

//			// 2. setBytes
//			pstmt.setInt(1, 2);
//			pstmt.setString(2, "�ڶ붩��");
//			byte[] pic = getPictureByteArray("1.png");
//			pstmt.setBytes(3, pic);
//			pstmt.executeUpdate();
////
////			// 3. setBinaryStream
//			pstmt.setInt(1, 3);
//			pstmt.setString(2, "�Ӯa���w��");
//			InputStream is2 = getPictureStream("3.gif");
//			pstmt.setBinaryStream(3, is2);
//			pstmt.executeUpdate();
//			is2.close();

			System.out.println("成功");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// �̫إ߶��������귽 (�V�߫إ߶V������)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// �ϥ�InputStream��Ƭy�覡
//	public static InputStream getPictureStream(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		return fis;
//	}

	// �ϥ�byte[]�覡
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		//.available�O�Φbbyte�����Ƥj�p����k�A�p�G�Ofile�ɮת���k�h�Olength()�C
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}