package com.itemtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.item.model.ItemVO;

public class ItemTypeDAO implements ItemTypeDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO MAOUNI.ITEM_TYPE (ITEMT_NAME) VALUES (?)";
	private static final String GET_ALL_STMT = 
		"SELECT ITEMT_ID, ITEMT_NAME FROM MAOUNI.ITEM_TYPE";
	private static final String GET_ONE_STMT = 
		"SELECT ITEMT_ID,ITEMT_NAME FROM MAOUNI.ITEM_TYPE where ITEMT_ID = ?";	
	private static final String DELETE = 
		"DELETE FROM MAOUNI.ITEM_TYPE WHERE ITEMT_ID = ?";
	private static final String UPDATE = 
		"UPDATE MAOUNI.ITEM_TYPE SET ITEMT_NAME=? WHERE ITEMT_ID = ?";
	private static final String GET_Items_ByItemtId_STMT = 
		"SELECT ITEM_ID,ITEM_TYPE_ID,ITEM_PET_TYPE,ITEM_NAME,ITEM_CONTENT,ITEM_PRICE,ITEM_AMOUNT,ITEM_STATUS,ITEM_UPDATE FROM MAOUNI.ITEM WHERE ITEM_TYPE_ID = ?";
		
	
	//新增
	@Override
	public void insert(ItemTypeVO itemTypeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itemTypeVO.getItemtName());

			pstmt.executeUpdate("set auto_increment_offset=1;");
			pstmt.executeUpdate("set auto_increment_increment=1;");
			
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
	public void update(ItemTypeVO itemTypeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, itemTypeVO.getItemtName());
			pstmt.setInt(2, itemTypeVO.getItemtId());


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
	public void delete(Integer itemtId) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, itemtId);

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

	//以itemtId(商品類別ID)查詢
	@Override
	public ItemTypeVO findByPrimaryKey(Integer itemtId) {
		
		ItemTypeVO itemTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, itemtId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemTypeVO = new ItemTypeVO();
				itemTypeVO.setItemtId(rs.getInt("ITEMT_ID"));
				itemTypeVO.setItemtName(rs.getString("ITEMT_NAME"));


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
		return itemTypeVO;
	}

	//全部列出
	@Override
	public List<ItemTypeVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemTypeVO> list = new ArrayList<ItemTypeVO>();

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemTypeVO itemTypeVO = new ItemTypeVO();
				itemTypeVO.setItemtId(rs.getInt(1));
				itemTypeVO.setItemtName(rs.getString(2));
				list.add(itemTypeVO); // Store the row in the list
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

	
	//以ItemtId(商品類別ID)取得商品
	public Set<ItemVO> getItemsByItemtId(Integer itemtId) {
		Set<ItemVO> set = new LinkedHashSet<ItemVO>();
		ItemVO itemVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_Items_ByItemtId_STMT);
		pstmt.setInt(1, itemtId);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			itemVO = new ItemVO();
			itemVO.setItemId(rs.getInt("item_Id"));
			itemVO.setItemTypeId(rs.getInt("item_Type_Id"));
			itemVO.setItemPetType(rs.getString("item_PET_Type"));
			itemVO.setItemName(rs.getString("item_Name"));
			itemVO.setItemContent(rs.getString("item_Content"));
			itemVO.setItemPrice(rs.getInt("item_Price"));
			itemVO.setItemAmount(rs.getInt("item_Amount"));
			itemVO.setItemStatus(rs.getInt("item_Status"));
			itemVO.setItemUpdate(rs.getDate("item_Update"));			
			set.add(itemVO); // Store the row in the vector
		}

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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
	return set;
	}

}