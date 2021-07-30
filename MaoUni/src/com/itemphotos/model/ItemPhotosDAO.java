package com.itemphotos.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class ItemPhotosDAO implements ItemPhotosDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.ITEM_PHOTOS (IP_ITEM_ID,IP_ITEM,IP_UPDATE) VALUES (?,?,now())";
	private static final String GET_ALL_STMT = 
		"SELECT IP_ID,IP_ITEM_ID,IP_ITEM,IP_UPDATE FROM MAOUNI.ITEM_PHOTOS";
	private static final String GET_ONE_STMT = 
		"SELECT IP_ID,IP_ITEM_ID,IP_ITEM,IP_UPDATE FROM MAOUNI.ITEM_PHOTOS where IP_ID = ?";	
	private static final String DELETE = 
		"DELETE FROM MAOUNI.ITEM_PHOTOS WHERE IP_ID = ?";
	private static final String UPDATE = 
		"UPDATE MAOUNI.ITEM_PHOTOS SET IP_ITEM_ID=?, IP_ITEM=?, IP_UPDATE=now() WHERE IP_ID = ?";
	private static final String GET_PIC_STMT = 
		"SELECT IP_ID,IP_ITEM_ID,IP_ITEM,IP_UPDATE FROM MAOUNI.ITEM_PHOTOS WHERE IP_ITEM_ID = ?";
	private static final String GET_ONE_FOR_IPID = 
		"SELECT * FROM MAOUNI.ITEM_PHOTOS WHERE IP_ITEM_ID = ?";
		
	
	//新增
	@Override
	public void insert(ItemPhotosVO itemPhotosVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itemPhotosVO.getIpItemId());
			pstmt.setBytes(2, itemPhotosVO.getIpItem());
//			pstmt.setDate(3, itemPhotosVO.getIpUpdate());

			pstmt.executeUpdate("set auto_increment_offset=1;");
			pstmt.executeUpdate("set auto_increment_increment=1;");
			
			int insertCount = pstmt.executeUpdate();
			System.out.println("�s�W" + insertCount + "�����");
			
			
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

	//修改
	@Override
	public void update(ItemPhotosVO itemPhotosVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, itemPhotosVO.getIpItemId());
			pstmt.setBytes(2, itemPhotosVO.getIpItem());
//			pstmt.setDate(3, itemPhotosVO.getIpUpdate());
			pstmt.setInt(3, itemPhotosVO.getIpId());

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

	//刪除
	@Override
	public void delete(Integer ipId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, ipId);

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

	//單一查詢
	@Override
	public ItemPhotosVO findByPrimaryKey(Integer ipId) {
		
		ItemPhotosVO itemPhotosVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ipId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemPhotosVO = new ItemPhotosVO();
				itemPhotosVO.setIpId(rs.getInt("ipId"));
				itemPhotosVO.setIpItemId(rs.getInt("ipItemId"));
				itemPhotosVO.setIpItem(rs.getBytes("ipItem"));
				itemPhotosVO.setIpUpdate(rs.getDate("ipUpdate"));
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
		return itemPhotosVO;
	}

	//全部列出
	@Override
	public List<ItemPhotosVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemPhotosVO> list = new ArrayList<ItemPhotosVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
				itemPhotosVO.setIpId(rs.getInt(1));
				itemPhotosVO.setIpItemId(rs.getInt(2));
				itemPhotosVO.setIpItem(rs.getBytes(3));
				itemPhotosVO.setIpUpdate(rs.getDate(4));
				list.add(itemPhotosVO); // Store the row in the list
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

	//取得照片
	@Override
	public List<ItemPhotosVO> getItemPhoto(Integer ipItemId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemPhotosVO> list = new ArrayList<ItemPhotosVO>();
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PIC_STMT);
			
			pstmt.setInt(1, ipItemId);
			System.out.println(ipItemId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
				itemPhotosVO.setIpId(rs.getInt(1));
				itemPhotosVO.setIpItemId(rs.getInt(2));
				if(rs.getBytes("IP_ITEM") != null) {
					itemPhotosVO.setIpItemBase64(base64Change(rs.getBytes("IP_ITEM")));
					System.out.println("zzz");
				}
				itemPhotosVO.setIpUpdate(rs.getDate(4));
				list.add(itemPhotosVO); // Store the row in the list
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
		return list;
		
		}
		
		public String base64Change(byte[] b) {
			String pic = Base64.getEncoder().encodeToString(b);
			return pic;
	}
	
	
	
	
	//插入照片
	@Override
	public byte[] insertItemPhoto(String path) {
		
		byte[]iip = null;
		
		try {
			FileInputStream fis = new FileInputStream(path);
			iip = new byte[fis.available()];
			fis.read(iip);
			fis.close();
		} catch (Exception e) {

		}
		return iip;
	}

	@Override
	public byte[] updateItemPhoto(String path) {
		
		byte[]iia = null;
		
		try {
			FileInputStream fis = new FileInputStream(path);
			iia = new byte[fis.available()];
			fis.read(iia);
			fis.close();
		} catch (Exception e) {

		}
		return iia;
	}

	@Override
	public byte[] getOnePic(Integer ipId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] img =null;
		System.out.println(ipId);
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FOR_IPID);
			pstmt.setInt(1, ipId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				img = rs.getBytes("IP_ITEM");
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
				}
			}
		}
		return img;
		
		}

	

		
	}
	
	
	
	
	
	
	
	
	
	
	
	



