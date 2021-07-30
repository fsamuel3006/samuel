package com.report_grooming.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appointment_form.model.ApmVO;
import com.groomer.model.GroDAO;
import com.groomer.model.GroService;
import com.groomer.model.GroVO;
import com.util.MailService;

public class GrepDAO implements GrepDAO_interface {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	private String userid = "David";
	private String passwd = "123456";

	private final String INSERT_STMT = "insert into REPORT_GROOMING (APMID, MEMID, GROOMERID, CONTENT, RPTUPDATE) values (?, ?, ?, ?, now())";
	private final String UPDATESTATUS_STMT = "update REPORT_GROOMING set RPTSTATUS = ? where RPTID =?";
	private final String GET_ALL_STMT = "select * from REPORT_GROOMING order by RPTID and RPTSTATUS";
	

	@Override
	public Integer insert(GrepVO grepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer completeNum = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, grepVO.getApmId());
			pstmt.setInt(2, grepVO.getMemId());
			pstmt.setInt(3, grepVO.getGroomerId());
			pstmt.setString(4, grepVO.getContent());

			completeNum = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A databse error occured. " + se.getMessage());
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
	public String update(GrepVO grepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer message = new StringBuffer();
		Map<String, String[]> map = new HashMap<String, String[]>();
		String[] groomerIdStr = { grepVO.getGroomerId().toString() };
		map.put("groomerId", groomerIdStr);
		GroDAO groDao = new GroDAO();
		GroVO groVO = new GroVO();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(UPDATESTATUS_STMT);
			pstmt.setInt(1, grepVO.getRptStatus());
			pstmt.setInt(2, grepVO.getRptId());
			pstmt.executeUpdate();

			if (grepVO.getRptStatus() == 1) { // 檢舉屬實
				groVO.setReped(1); // 給予的值不重要，只是為了讓複合修改在判斷時不會得到null
				groVO.setGroomerId(grepVO.getGroomerId());
				groDao.update(groVO, con); // groomer資料表的REPED要+1
			}

			con.commit();
			con.setAutoCommit(true);
		// ================取回會員資料檢查檢舉屬實次數是否需列為黑名單，並寄出通知信===============
			
			MailService mailSvc = new MailService();
			mailSvc.setTo("project60237@gmail.com");
			mailSvc.setSubject("MaoUni 毛孩有你  美容服務檢舉通知");
			StringBuffer messageText = new StringBuffer();
			
			
			GroVO groVO2 = groDao.getAll(map).get(0);

			if (groVO2.getReped() == 3) {
				// 列為黑名單
				groDao.updateStatus(grepVO.getGroomerId(), 3); 
				
				// 前端頁面顯示訊息
				message.append("已列為黑名單並發送通知信件");	
				
				// 寄出通知信告知美容師
				messageText.append("很抱歉，由於您的檢舉記點已達 3 次，我們將取消您的美容服務資格。") ;
				mailSvc.setMessageText(messageText.toString());
				mailSvc.start();

		// =======================使用執行續處理信件寄送曼導致畫面卡卡問題=======================

			} else {
				// 前端頁面顯示訊息
				message.append("已累計檢舉屬實次數： " + groVO2.getReped() + "，已發送通知信件");
				
				// 寄出通知信告知美容師
				messageText.append("您的檢舉記點已累計 " + groVO2.getReped() + " 次，達 3 次您將被取消美容師服務資格。") ;
				mailSvc.setMessageText(messageText.toString());
				mailSvc.start();
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		System.out.println(message);
		return message.toString();

	}

//	@Override
//	public List<GrepVO> findByGroomer(Integer memId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<GrepVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GrepVO> list = new ArrayList();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GrepVO grepVO = new GrepVO();
				grepVO.setRptId(rs.getInt("RPTID"));
				grepVO.setApmId(rs.getInt("APMID"));
				grepVO.setContent(rs.getString("CONTENT"));
				grepVO.setRptStatus(rs.getInt("RPTSTATUS"));
				grepVO.setGroomerId(rs.getInt("GROOMERID"));
//				grepVO.setRptUpdate(rs.getTimestamp(5));

				list.add(grepVO);
			}
			
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
		return list;
	}

}
