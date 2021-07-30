package com.item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.itemphotos.model.ItemPhotosDAO;
import com.itemphotos.model.ItemPhotosVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Item;

public class ItemDAO implements ItemDAO_interface{
	

	
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.ITEM (ITEM_TYPE_ID,ITEM_PET_TYPE,ITEM_NAME,ITEM_CONTENT,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,ITEM_UPDATE) VALUES (?, ?, ?, ?, ?, ?, 1, now())";
	private static final String GET_ALL_STMT = 
		"SELECT ITEM_ID,ITEM_TYPE_ID,ITEM_PET_TYPE,ITEM_NAME,ITEM_CONTENT,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,ITEM_UPDATE FROM MAOUNI.ITEM";
	private static final String GET_ONE_STMT = 
		"SELECT ITEM_ID,ITEM_TYPE_ID,ITEM_PET_TYPE,ITEM_NAME,ITEM_CONTENT,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,ITEM_UPDATE FROM MAOUNI.ITEM WHERE ITEM_ID = ?";	
	private static final String GET_NAME_STMT = 
		"SELECT ITEM_ID,ITEM_TYPE_ID,ITEM_PET_TYPE,ITEM_NAME,ITEM_CONTENT,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,ITEM_UPDATE FROM MAOUNI.ITEM WHERE ITEM_NAME = ?";	//如果複合查詢ok就可以刪掉這行跟下面的方法
	private static final String UPDATE = 
		"UPDATE MAOUNI.ITEM SET ITEM_TYPE_ID=?, ITEM_PET_TYPE=?, ITEM_NAME=?, ITEM_CONTENT=?, ITEM_PRICE=?, ITEM_AMOUNT=?, ITEM_STATUS=?, ITEM_UPDATE=NOW() WHERE ITEM_ID = ?";
	private static final String MULTIPLE =
	"SELECT i.ITEM_ID,i.ITEM_TYPE_ID,t.ITEMT_NAME,i.ITEM_PET_TYPE,i.ITEM_NAME,i.ITEM_CONTENT,i.ITEM_PRICE,i.ITEM_AMOUNT,i.ITEM_STATUS,i.ITEM_UPDATE FROM item i join item_type t on (i.ITEM_TYPE_ID = t.ITEMT_ID)";
	
	
	//新增
	@Override
	public void insert(ItemVO itemVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itemVO.getItemTypeId());
			pstmt.setString(2, itemVO.getItemPetType());
			pstmt.setString(3, itemVO.getItemName());
			pstmt.setString(4, itemVO.getItemContent());
			pstmt.setInt(5, itemVO.getItemPrice());
			pstmt.setDouble(6, itemVO.getItemAmount());
//			pstmt.setInt(6, itemVO.getItemStatus());
			//因為ItemUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明
			
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

	//修改
	@Override
	public void update(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setInt(1, itemVO.getItemTypeId());
			pstmt.setString(2, itemVO.getItemPetType());
			pstmt.setString(3, itemVO.getItemName());
			pstmt.setString(4, itemVO.getItemContent());
			pstmt.setInt(5, itemVO.getItemPrice());
			pstmt.setDouble(6, itemVO.getItemAmount());
			pstmt.setInt(7, itemVO.getItemStatus());
			pstmt.setInt(8, itemVO.getItemId());
			//因為ItemUpdate欄位直接用NOW()寫死，所以不用寫，特別在這註明

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

	//以itemId(商品ID)查詢
	@Override
	public ItemVO findByPrimaryKey(Integer itemId) {
		
		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itemId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();

				itemVO.setItemId(rs.getInt(1));
				itemVO.setItemTypeId(rs.getInt(2));
				itemVO.setItemPetType(rs.getString(3));
				itemVO.setItemName(rs.getString(4));
				itemVO.setItemContent(rs.getString(5));
				itemVO.setItemPrice(rs.getInt(6));
				itemVO.setItemAmount(rs.getInt(7));
				itemVO.setItemStatus(rs.getInt(8));
				itemVO.setItemUpdate(rs.getDate(9));
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
		return itemVO;
	}

	
	@Override
	public ItemVO findByPrimaryKey(String itemName) {
		
		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NAME_STMT);

			pstmt.setString(1, itemName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();

				itemVO.setItemId(rs.getInt(1));
				itemVO.setItemTypeId(rs.getInt(2));
				itemVO.setItemPetType(rs.getString(3));
				itemVO.setItemName(rs.getString(4));
				itemVO.setItemContent(rs.getString(5));
				itemVO.setItemPrice(rs.getInt(6));
				itemVO.setItemAmount(rs.getInt(7));
				itemVO.setItemStatus(rs.getInt(8));
				itemVO.setItemUpdate(rs.getDate(9));
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
		return itemVO;
	}
	
	
	
	//全部列出
	@Override
	public List<ItemVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemVO> list = new ArrayList<ItemVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemVO itemVO = new ItemVO();
				itemVO.setItemId(rs.getInt(1));
				itemVO.setItemTypeId(rs.getInt(2));
				itemVO.setItemPetType(rs.getString(3));
				itemVO.setItemName(rs.getString(4));
				itemVO.setItemContent(rs.getString(5));
				itemVO.setItemPrice(rs.getInt(6));
				itemVO.setItemAmount(rs.getInt(7));
				itemVO.setItemStatus(rs.getInt(8));
				itemVO.setItemUpdate(rs.getDate(9));
				
				ItemPhotosDAO itemPhotosDAO = new ItemPhotosDAO();
				ItemPhotosVO itemPhotosVO = itemPhotosDAO.getItemPhoto(rs.getInt(1)).get(0);
				if(!(itemPhotosVO == null)) {
					itemVO.setItemPhotoFirst(itemPhotosVO.getIpItemBase64());
				} 
//				System.out.println("xxx"+itemPhotosDAO.getItemPhoto(rs.getInt(1)).get(0).getIpItemBase64());
////			    String photostr = Base64.getEncoder().encodeToString(itemPhotosVO.getIpItem());
//			    itemVO.setItemPhotoFirst(itemPhotosVO.getIpItemBase64());
//			   System.out.println(); 		
				
				
				list.add(itemVO); // Store the row in the list
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

	
	//複合查詢
	@Override
	public List<ItemVO> getAll(Map<String, String[]> map) {

	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemVO> list = new ArrayList<ItemVO>();
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = MULTIPLE + jdbcUtil_CompositeQuery_Item.get_WhereCondition(map) + "ORDER BY ITEM_ID";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println(finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {

				ItemVO itemVO = new ItemVO();
				itemVO.setItemId(rs.getInt(1));
				itemVO.setItemTypeId(rs.getInt(2));
				itemVO.setItemTypeName(rs.getString(3));
				itemVO.setItemPetType(rs.getString(4));
				itemVO.setItemName(rs.getString(5));
				itemVO.setItemContent(rs.getString(6));
				itemVO.setItemPrice(rs.getInt(7));
				itemVO.setItemAmount(rs.getInt(8));
				itemVO.setItemStatus(rs.getInt(9));
				itemVO.setItemUpdate(rs.getDate(10));
				list.add(itemVO); 
			}
	
	
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
	};
}

