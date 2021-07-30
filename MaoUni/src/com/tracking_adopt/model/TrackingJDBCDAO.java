package com.tracking_adopt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.adopt_imf.model.ImfVO;

public class TrackingJDBCDAO implements TrackingDAO_interface {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO TRACKING_ADOPT(ATRA_MEMID,ATRA_AI_ID) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT ATRA_MEMID,ATRA_AI_ID FROM TRACKING_ADOPT order by ATRA_MEMID";
	private static final String GET_ONE_STMT = "SELECT ATRA_MEMID,ATRA_AI_ID FROM TRACKING_ADOPT where ATRA_MEMID = ?";
	private static final String DELETE = "DELETE FROM TRACKING_ADOPT where ATRA_MEMID = ?";
	private static final String UPDATE = "UPDATE TRACKING_ADOPT set ATRA_AI_ID=?  where ATRA_MEMID";

	private static final String getMemberByid = "select `MEM_ID`,`MEM_NAME`,`MEM_EMAIL`,`MEM_PASSWORD`,`MEM_IDENTITY`,`MEM_GENDER`,`MEM_PH`,`MEM_ADDRES`,`MEM_BIRTHDAT`,`MEM_POSITION`,`MEM_BLACKLIST`,`MEM_RESERVE`,`MEM_SURVIVE`,`MEM_UPDATE` from `MEMBER` where ATRA_AI_ID=?order by `MEM_ID`";
	private static final String getImfVObyid = "SELECT AI_ID,AI_ADOPT,AI_MECH,AI_NAME,AI_YEAR,AI_SIT FROM ADOPT_IMF WHERE ATRA_MEMID = ? ORDER BY AI_ID";

	@Override
	public Integer insert(TrackingVO trackingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer completeNum = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, trackingVO.getId());
			pstmt.setInt(2, trackingVO.getDet());

			completeNum = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return completeNum;
	}

	@Override
	public void update(TrackingVO trackingVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trackingVO.getDet());
			pstmt.setInt(2, trackingVO.getId());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(Integer Id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, Id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public TrackingVO findByPrimaryKey(Integer Id) {
		TrackingVO trackingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, Id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trackingVO = new TrackingVO();
				trackingVO.setId(rs.getInt("ATRA_MEMID"));
				trackingVO.setDet(rs.getInt("ATRA_AI_ID"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return trackingVO;
	}

	@Override
	public List<TrackingVO> getAll() {
		List<TrackingVO> list = new ArrayList<TrackingVO>();
		TrackingVO trackingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trackingVO = new TrackingVO();
				trackingVO.setId(rs.getInt("ATRA_MEMID"));
				trackingVO.setDet(rs.getInt("ATRA_AI_ID"));

				list.add(trackingVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		TrackingJDBCDAO dao = new TrackingJDBCDAO();
		Connection con = null;
		PreparedStatement pstmt = null;
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);
		pstmt = con.prepareStatement(GET_ONE_STMT);
		pstmt = con.prepareStatement(GET_ALL_STMT);
//		
		// �s�W
//		DetailVO d1=new DetailVO();
//		d1.setDet(1);
//		d1.setId(1);
//		dao.insert(d1);
//		
//		//�ק�
//		DetailVO d2=new DetailVO();
//		d2.setDet(1);
//		d2.setId(1);
//		dao.update(d2);
//		
//		//�R��
//		dao.delete(10);
//		
//		//�d��
//		DetailVO detailVO=dao.findByPrimaryKey(1);
//		System.out.println(TrackingVO.getId()+",");
//		System.out.println(TrackingVO.getDet()+",");

//				
//		//�d��
//		List<TrackingVO> list = dao.getAll();
//		for(TrackingVO detailVO : list) {
//			System.out.println(detailVO.getId()+",");
//			System.out.println(detailVO.getDet()+",");
//			
//		}	
//		}

	}

	@Override
	public Set<MemberVO> getMemberByid(Integer id) {
		Set<MemberVO> set = new LinkedHashSet<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(getMemberByid);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemId(rs.getInt("`MEM_ID`"));
				memberVO.setMemName(rs.getString(" `MEM_NAME`"));
				memberVO.setMemEmail(rs.getString(" `MEM_NAME`"));
				memberVO.setMemPassword(rs.getString("`MEM_PASSWORD`"));
				memberVO.setMemIdenity(rs.getString("`MEM_IDENTITY`"));
				memberVO.setMemGender(rs.getString("`MEM_GENDER`"));
				memberVO.setMemPh(rs.getInt(" `MEM_PH` "));
				memberVO.setMemAddres(rs.getString("`MEM_ADDRES`"));
				memberVO.setMemBirthday(rs.getTimestamp("`MEM_BIRTHDAT`"));
				memberVO.setMemPosition(rs.getInt("`MEM_POSITION`"));
				memberVO.setMemReserve(rs.getInt("`MEM_RESERVE`"));
				memberVO.setMemSurvive(rs.getInt("`MEM_SURVIVE`"));
				memberVO.setMemUpdate(rs.getTimestamp("`MEM_UPDATE`"));
				set.add(memberVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
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

	@Override
	public Set<ImfVO> getImfVObyid(Integer id) {
		Set<ImfVO> set = new LinkedHashSet<ImfVO>();
		ImfVO imfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(getImfVObyid);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				imfVO = new ImfVO();
				imfVO.setId(rs.getInt("AI_ID"));
				imfVO.setAdopt(rs.getInt("AI_ADOPT"));
				imfVO.setMech(rs.getInt("AI_MECH"));
				imfVO.setName(rs.getString("AI_NAME"));
				imfVO.setYear(rs.getInt("AI_YEAR"));
				imfVO.setSit(rs.getString("AI_SIT"));
				imfVO.setPhoto(rs.getBytes("AI_PHOTO"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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

		return set;
	}
}
