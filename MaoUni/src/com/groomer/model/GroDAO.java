package com.groomer.model;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.appointment_form.model.ApmVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Groomer;
import oracle.net.aso.e;

public class GroDAO implements GroDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	private final String INSERT_STMT = "insert into GROOMER (MEMID, GNAME, CENTER, GRANGE, LICENSE, PCRC, FID, BID) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String GET_ALL_STMT = "select GROOMERID, MEMID, GNAME, CENTER, GRANGE, SCHDATE, SCHTIME, GSTATUS, RESERVE, COM, COMG, REPED, AVATAR, INTRO from GROOMER";
	private final String GET_ONEPHOTOS_STMT = "select LICENSE, PCRC, FID, BID from GROOMER where GROOMERID = ?";
	private final String UPDATE_STATUS_STMT = "update GROOMER set GSTATUS = ? where GROOMERID = ?";
	private final String UPDATE_INFO_STMT = "update GROOMER set GNAME = ?, SCHDATE = ?, SCHTIME = ?, AVATAR = ?, INTRO = ? where GROOMERID = ?";
	private final String GET_ONT_STMT = "select * from GROOMER where GROOMERID = ?";

//  以下為舊方法，已被上方取代	
//	private final String UPDATE_REPED_STMT = "update GROOMER set REPED = REPED + 1 where GROOMERID = ?";
//	private final String GET_LISTBYSTATUS_STMT = "select * from GROOMER where GSTATUS = ?";
	
	
	@Override
	public void insert(GroVO groVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groVO.getMemId());
			pstmt.setString(2, groVO.getGname());
			pstmt.setString(3, groVO.getCenter());
			pstmt.setInt(4, groVO.getGrange());
			pstmt.setBytes(5, groVO.getLicense());
			pstmt.setBytes(6, groVO.getPcrc());
			pstmt.setBytes(7, groVO.getFid());
			pstmt.setBytes(8, groVO.getBid());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't loa Database driver. " + e.getMessage());
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void updateStatus(Integer groomerId, Integer gstatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setInt(1, gstatus);
			pstmt.setInt(2, groomerId);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());  
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
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

	}

	// get someone's photos in base64 String
	@Override
	public Map<String, String> findPhotosBygmId(Integer groomerId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> photos = new HashMap<String, String>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONEPHOTOS_STMT);
			pstmt.setInt(1, groomerId);
			rs = pstmt.executeQuery();

			// if no file, can not execute base64Change();
			while (rs.next()) {
				if (rs.getBytes(1) != null) {
					photos.put("license", base64Change(rs.getBytes(1)));
				}

				if (rs.getBytes(2) != null) {
					photos.put("pcrc", base64Change(rs.getBytes(2)));
				}

				if (rs.getBytes(3) != null) {
					photos.put("fid", base64Change(rs.getBytes(3)));
				}

				if (rs.getBytes(4) != null) {
					photos.put("bid", base64Change(rs.getBytes(4)));
				}
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't loa database driver. " + e.getMessage());
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

		return photos;
	}

	@Override
	public List<GroVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GroVO> list = new ArrayList();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GroVO groVO = new GroVO();
				groVO.setGroomerId(rs.getInt(1));
				groVO.setMemId(rs.getInt(2));
				groVO.setGname(rs.getString(3));
				groVO.setCenter(rs.getString(4));
				groVO.setGrange(rs.getInt(5));
				groVO.setSchDate(rs.getString(6)); 
				groVO.setSchTime(rs.getString(7)); 
				groVO.setGstatus(rs.getInt(8));
				groVO.setReserve(rs.getInt(9));
				groVO.setCom(rs.getInt(10));
				groVO.setComg(rs.getInt(11));
				groVO.setReped(rs.getInt(12));
				
				if(rs.getBytes(13) != null) {
					groVO.setAvatarBase64(Base64.getEncoder().encodeToString(rs.getBytes(13)).toString());
				}
				list.add(groVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't loa database driver. " + e.getMessage());	// getMessage 得到字串，可以顯示在網頁上
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);	// printStackTrace 顯示在console上
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

	
	@Override
	public List<GroVO> getAll(Map<String, String[]> map) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GroVO> list = new ArrayList();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSql = GET_ALL_STMT + jdbcUtil_CompositeQuery_Groomer.get_WhereCondition(map) + " order by groomerId";
			pstmt = con.prepareStatement(finalSql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GroVO groVO = new GroVO();
				groVO.setGroomerId(rs.getInt(1));
				groVO.setMemId(rs.getInt(2));
				groVO.setGname(rs.getString(3));
				groVO.setCenter(rs.getString(4));
				groVO.setGrange(rs.getInt(5));
				groVO.setSchDate(rs.getString(6)); 
				groVO.setSchTime(rs.getString(7)); 
				groVO.setGstatus(rs.getInt(8));
				groVO.setReserve(rs.getInt(9));
				groVO.setCom(rs.getInt(10));
				groVO.setComg(rs.getInt(11));
				groVO.setReped(rs.getInt(12));
				groVO.setAvatar(rs.getBytes(13));
				groVO.setIntro(rs.getString(14));
				
				list.add(groVO);
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


	public Integer updateInfo(GroVO groVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer completeNum = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_INFO_STMT);

			pstmt.setString(1, groVO.getGname());
			pstmt.setString(2, groVO.getSchDate());
			pstmt.setString(3, groVO.getSchTime());
			pstmt.setBytes(4, groVO.getAvatar());
			pstmt.setString(5, groVO.getIntro());
			pstmt.setInt(6, groVO.getGroomerId());

			completeNum = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return completeNum;
	}


	@Override
	public void update(GroVO groVO, Connection con) {
		
		PreparedStatement pstmt = null;
		StringBuffer finalsql = new StringBuffer("update GROOMER set ");
		int count = 0;
		StringBuffer condition = new StringBuffer();
		
		try {
			Class clazz = groVO.getClass();
			Field[] fields = clazz.getDeclaredFields(); 
			
			for(int i = 0; i < fields.length; i++) {
				
				fields[i].setAccessible(true);
				String colName = fields[i].getName();
				
				if(fields[i].get(groVO) != null) {
					if(count != 0) {
						condition.append(", ");
					}
					
					if("reped".equals(colName) || "reserve".equals(colName) || "com".equals(colName)) {
						condition.append(colName + " = " + colName + " + 1 ");
						count++;
					}
					if("comg".equals(colName)) {
						condition.append(colName + " = " + colName + " + " + groVO.getComg());
						count++;
					}
				}
			}
			
			finalsql.append(condition + " where GROOMERID = " + groVO.getGroomerId());
			pstmt = con.prepareStatement(finalsql.toString());
			pstmt.executeUpdate();
			
		} catch (SecurityException e) {
			throw new RuntimeException(e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace(System.err);
		} catch (IllegalAccessException e) {
			e.printStackTrace(System.err);
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	// 將圖片轉成base64字串
	public String base64Change(byte[] b) {
		String pic = Base64.getEncoder().encodeToString(b);
		return pic;
	}
	
	
	
//	@Override
//	public void updateReped(Integer groomerId, Connection con) {
//		PreparedStatement pstmt = null;
//
//		try {
//			pstmt = con.prepareStatement(UPDATE_REPED_STMT);
//			pstmt.setInt(1, groomerId);
//			pstmt.executeUpdate(); // 增加檢舉屬實次數
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			} // 由GrepDAO.update 綁定交易、共用連線，因此不用con.close()
//		}
//	}	
	
	

	
	@Override
	public GroVO findByPrimaryKey(Integer groomerId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GroVO groVO = new GroVO();
		
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONT_STMT);
			pstmt.setInt(1, groomerId);
			
			rs = pstmt.executeQuery();
			rs.next();
			groVO.setGroomerId(rs.getInt("GROOMERID"));
			groVO.setMemId(rs.getInt("MEMID"));
			groVO.setGname(rs.getString("GNAME"));
			groVO.setCenter(rs.getString("CENTER"));
			groVO.setGrange(rs.getInt("GRANGE"));
			groVO.setSchDate(rs.getString("SCHDATE")); 
			groVO.setSchTime(rs.getString("SCHTIME")); 
			groVO.setGstatus(rs.getInt("GSTATUS"));
			groVO.setReserve(rs.getInt("RESERVE"));
			groVO.setCom(rs.getInt("COM"));
			groVO.setComg(rs.getInt("COMG"));
			groVO.setReped(rs.getInt("REPED"));
			
			byte[] b = rs.getBytes("AVATAR");
			groVO.setAvatar(b);
			if(b != null){
				groVO.setAvatarBase64(Base64.getEncoder().encodeToString(b));
			}
			groVO.setIntro(rs.getString("INTRO"));
			

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
		return groVO;
	}

	
}
