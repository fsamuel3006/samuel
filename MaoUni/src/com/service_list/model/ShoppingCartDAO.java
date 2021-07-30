package com.service_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.shopping_cart.model.ShoppingCartItemVO;

public class ShoppingCartDAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql:/localhost:3306/maouni?serverTimezone=Asia/Taipei";
	private String user = "David";
	private String passwd = "123456";

	public List<ShoppingCartItemVO> getCart(String sessionId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShoppingCartItemVO> list = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
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
