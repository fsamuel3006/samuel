package com.appointment_form.model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.addressGeo.model.GeocodingService;
import com.appointment_form_details.model.ApmdDAO;
import com.appointment_form_details.model.ApmdVO;
import com.groomer.model.GroDAO;
import com.groomer.model.GroVO;
import com.gschedule.model.SchDAO;
import com.util.GroomerApm;

public class ApmDAO implements ApmDAO_interface {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	private String userid = "David";
	private String passwd = "123456";

	private final String INSERT_STMT = "insert into APPOINTMENT_FORM (MEMID, GROOMERID, PID, APMDATE, STIME, ETIME, TOTAL)values(?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE_STMT = "update APPOINTMENT_FORM set ";
	private final String GET_ONELIST_STMT = "SELECT a.APMID, a.MEMID, a.GROOMERID, a.PID, a.APMDATE, a.STIME, a.ETIME, a.TOTAL, a.APMSTATUS, a.STAR, a.APMCOMMENT, a.PHOTO, m.MEM_ADDRES from APPOINTMENT_FORM a join MEMBER m on (a.MEMID = m.MEM_ID)";
	private final String GET_MONTH_STMT = "select APMID, MEMID, GROOMERID, PID, APMDATE, dayofmonth(APMDATE), weekday(APMDATE)+1, STIME, ETIME, TOTAL, APMSTATUS  from APPOINTMENT_FORM where GROOMERID = ? AND month(APMDATE) = ? order by APMDATE";

	@Override
	public void insert(ApmVO apmVO, List<ApmdVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false); // 關閉自動交易

			String[] cols = { "APMID" }; // 取得主AI PK的欄位
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, apmVO.getMemId());
			pstmt.setInt(2, apmVO.getGroomerId());
			pstmt.setInt(3, apmVO.getPid());
			pstmt.setDate(4, apmVO.getApmDate()); 
			pstmt.setInt(5, apmVO.getStime());
			pstmt.setInt(6, apmVO.getEtime());
			pstmt.setInt(7, apmVO.getTotal()); // 由前端頁面記算好總金額
			pstmt.executeUpdate();

			// Retrieves any auto-generated keys created as a result of
			// executing this Statement object. If this Statement object
			// did not generate any keys, an empty ResultSet object is returned.
			rs = pstmt.getGeneratedKeys(); // 取得AI PK
			rs.next();

			ApmdDAO apmdDao = new ApmdDAO();
			for (ApmdVO apmdVO : list) {
				apmdVO.setApmId(rs.getInt(1)); // 取回來的值為String
				apmdDao.insert(apmdVO, con); // 綁定AI PK
			}

			rs.close();

			con.commit();
			con.setAutoCommit(true); // 開回自動交易

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new RuntimeException("rollback error occured. " + e.getMessage());
				}
			}
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	// 可修改APMSTATUS、APMDATE、STIME、ETIME、STAR、APMCOMMENT、PHOTO、TOTAL
	@Override
	public Integer update(ApmVO apmVO, Date changeDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer completeNum = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 判斷需修改的項目，查看ampVO裡面有什麼
			Class clazz = apmVO.getClass();
			Field[] fields = clazz.getDeclaredFields(); // 取得類宣告的變數

			StringBuffer condition = new StringBuffer();
			int count = 0;
			SchDAO schDao = new SchDAO();
			GroDAO groDao = new GroDAO();
			
			con.setAutoCommit(false);

			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true); // 若成員變數為private，則需設定accessible為true
//				System.out.println(field.getName());
//				System.out.println(field.get(apmVO)); // 會取得變數的值，若未賦值會得到null
				String colName = fields[i].getName();

				if (fields[i].get(apmVO) != null) {
					if (count != 0) {
						condition.append(", ");
					}

					if ("apmComment".equals(colName)) {
						condition.append(colName + " = " + "'" + fields[i].get(apmVO).toString() + "' ");
						count++;
					}

					if ("apmDate".equals(colName)) {
						condition.append(colName + " = " + "'" + changeDate + "' ");
						count++;
					}

					if ("stime".equals(colName) || "etime".equals(colName) || "total".equals(colName)
							|| "apmStatus".equals(colName) || "star".equals(colName) || "total".equals(colName)) {
						condition.append(colName + " = " + fields[i].get(apmVO).toString() + " ");
						count++;
					}

					if ("photo".equals(colName)) {
						condition.append(colName + " = ? ");
					}

					// 若修正項目為apmSatus，需更動GSCHDULE狀態並更正班表
					if ("apmStatus".equals(colName)) { // 無論如何前端都得傳入此參數，這樣無論預約時間有無更動，都必須更新					
						// 取消預約，當日該時段行程改為可預約
						if (apmVO.getApmStatus() == 4) {
							schDao.update(apmVO.getGroomerId(), apmVO.getApmDate(), 1, apmVO.getStime(), apmVO.getEtime(), con);
						} else if (apmVO.getApmStatus() == 2 || apmVO.getApmStatus() == 3) {
							if (changeDate.equals(apmVO.getApmDate())) { // 沒有更改日期
								schDao.update(apmVO.getGroomerId(), apmVO.getApmDate(), apmVO.getApmStatus(),
										apmVO.getStime(), apmVO.getEtime(), con);
							} else { // 有更改日期
								//	修正原預約日期
								schDao.update(apmVO.getGroomerId(), apmVO.getApmDate(), 1, apmVO.getStime(),
										apmVO.getEtime(), con);
								//	修正欲更改的日期
								schDao.update(apmVO.getGroomerId(), changeDate, apmVO.getApmStatus(), apmVO.getStime(),
										apmVO.getEtime(), con);
							}

							// 若預約單狀態修改為完成，需更動GROOMER的預約完成數
							if (apmVO.getApmStatus() == 3) {
								GroVO groVO1 = new GroVO();
								groVO1.setReserve(1); // 給予的值不重要，只是為了讓複合修改在判斷時不會得到null
								groVO1.setGroomerId(apmVO.getGroomerId());
								groDao.update(groVO1, con);
							}
						}
					}

					// 若為修正評論分數，需更動GROOMER的COM || GOMG
					if ("star".equals(colName)) {
						GroVO groVO2 = new GroVO();
						groVO2.setCom(1); // 給予的值不重要，只是為了讓複合修改在判斷時不會得到null
						groVO2.setComg(apmVO.getStar()); // 將分數存入以便在Grodao增加comg分數
						groVO2.setGroomerId(apmVO.getGroomerId());
						groDao.update(groVO2, con);
					}

				}
			}
			String finalsql = UPDATE_STMT + condition.toString() + " where APMID = " + apmVO.getApmId();

			pstmt = con.prepareStatement(finalsql);

			if (apmVO.getPhoto() != null) {
				pstmt.setBytes(1, apmVO.getPhoto());
			}
			completeNum = pstmt.executeUpdate();
System.out.println("finalsql: " + finalsql);
			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					throw new RuntimeException("rollback error occured. " + e.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace(System.err);
		} catch (IllegalAccessException e) {
			e.printStackTrace(System.err);
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
	public List<ApmVO> getAll(Map<String, String[]> map) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApmVO> list = new ArrayList<ApmVO>();
		StringBuffer finalsql = new StringBuffer(GET_ONELIST_STMT);
		StringBuffer condition = new StringBuffer();
		int count = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			Set<String> keys = map.keySet();
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					if (count == 1) 
						condition.append(" where ");
					else 
						condition.append(" and ");
					
					if ("month".equals(key)) 
						condition.append("month(apmdate)" + " = " + value.trim());
					
					if ("apmDate".equals(key)) 
						condition.append(key + " = " + "'" + value.trim() + "'");
					
					if ("apmId".equals(key) || "memId".equals(key) || "groomerId".equals(key) || "pid".equals(key) || "apmStatus".equals(key)) 
						condition.append(key + " = " + value.trim());
				}
			}

			finalsql.append(condition).append(" order by APMDATE desc, APMSTATUS");
			pstmt = con.prepareStatement(finalsql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ApmVO apmVO = new ApmVO();
				apmVO.setApmId(rs.getInt(1));
				apmVO.setMemId(rs.getInt(2));
				apmVO.setGroomerId(rs.getInt(3));
				apmVO.setPid(rs.getInt(4));
				apmVO.setApmDate(rs.getDate(5));
				apmVO.setStime(rs.getInt(6));
				apmVO.setEtime(rs.getInt(7));

				GroomerApm groApm = new GroomerApm();
				String stimeFormated = groApm.timeFormate(apmVO.getStime());
				String etimeFormated = groApm.timeFormate(apmVO.getEtime());
				apmVO.setStimeFormated(stimeFormated);
				apmVO.setEtimeFormated(etimeFormated);

				apmVO.setTotal(rs.getInt(8));
				apmVO.setApmStatus(rs.getInt(9));
				apmVO.setStar(rs.getInt(10));
				apmVO.setApmComment(rs.getString(11));
				apmVO.setPhoto(rs.getBytes(12));
				
				if(rs.getBytes(12) != null) {
					String photoBase64 = Base64.getEncoder().encodeToString(rs.getBytes(12));
					apmVO.setPhotoBase64(photoBase64);
				}
				
				apmVO.setAddress(rs.getString(13));
				
				
				// getGeo from Redis
				GeocodingService geocodingSvc = new GeocodingService();
				Integer memId = new Integer(rs.getInt("memId"));

				if(geocodingSvc.getGeocode(memId).get(0) != null) {
					String geocode = geocodingSvc.getGeocode(memId).get(0).toString();
//				retrun fromat(121.234134,23.23514)
					String lng = geocode.substring(1, geocode.indexOf(","));
					String lat = geocode.substring(geocode.indexOf(",") + 1, geocode.length() -1);
					
					apmVO.setLng(lng);
					apmVO.setLat(lat);
					System.out.println(memId + ": " + geocode);
				}
				
				list.add(apmVO);
			}

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
		return list;
	}

//	public List<ApmVO> getMonth(Integer month, Integer groomerId){
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<ApmVO> list = new ArrayList<ApmVO>();
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_MONTH_STMT);
//			
//			pstmt.setInt(1, groomerId);
//			pstmt.setInt(2, month);
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				ApmVO apmVO = new ApmVO();
//				apmVO.setApmId(rs.getInt(1));
//				apmVO.setMemId(rs.getInt(2));
//				apmVO.setGroomerId(rs.getInt(3));
//				apmVO.setPid(rs.getInt(4));
//				apmVO.setApmDate(rs.getDate(5));
//				apmVO.setDateOfMonth(rs.getInt(6));
//				apmVO.setDateOfWeek(rs.getInt(7));
//				apmVO.setStime(rs.getInt(8));
//				apmVO.setEtime(rs.getInt(9));
//				apmVO.setTotal(rs.getInt(10));
//				apmVO.setApmStatus(rs.getInt(11));
//
//				// 將取得的時間index格式化
//				GroomerApm groApm = new GroomerApm();
//				String stimeFormated = groApm.timeFormate(apmVO.getStime());
//				String etimeFormated = groApm.timeFormate(apmVO.getEtime());
//				apmVO.setStimeFormated(stimeFormated);
//				apmVO.setEtimeFormated(etimeFormated);
//				
//				list.add(apmVO);
//			}	
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		}finally {
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	};
	
}
