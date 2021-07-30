package com.gschedule.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchDAO implements SchDAO_interface {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	private String userid = "David";
	private String passwd = "123456";

	private final String INSERT_STMT = "insert into GSCHEDULE(GROOMERID, SCHDATE, SCHSTATUS) "
			+ "with recursive DATES(V_DATE) as( select curdate() union all select V_DATE + interval 1 day from DATES "
			+ "where V_DATE + interval 1 day <= adddate(curdate(), interval 30 day)) "
			+ "select g.GROOMERID, d.V_DATE, g.SCHTIME from DATES d join GROOMER g "
			+ "left join GSCHEDULE s on (d.V_DATE = s.SCHDATE and g.GROOMERID = s.GROOMERID) where s.SCHDATE is null";

	private final String UPDATE_BYPRESETVACATION_STMT = "update GSCHEDULE set schstatus = repeat('0', 48) where GROOMERID = ? and SCHDATE > CURDATE() ";
	private final String GET_ONELIST_STMT = "select * from GSCHEDULE where GROOMERID = ?";
	private final String UPDATE_STMT = "update GSCHEDULE set SCHSTATUS = insert(SCHSTATUS, ?,?,repeat(?,?)) where GROOMERID = ? AND SCHDATE = ?";
	private final String GET_SCHSTATUS_STMT = "select * from GSCHEDULE where SCHID = ?";
	private final String GET_ONELISTBYMONTH_STMT = "select * from GSCHEDULE where GROOMERID = ? and month(SCHDATE) = ? order by SCHDATE";
	private final String GET_SCHSTATUSBYDATE_STMT = "select * from GSCHEDULE where GROOMERID = ? and SCHDATE = ?";

	@Override
	public void insert() {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 執行遞迴指令，自動新增當日~30天內的班表資料，新增的SCHSTATUS依GROOMER的SCHTIME
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.executeUpdate();

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

	}

	@Override
	public void updateByPresetVacation(Integer groomerId, String schDate) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalUpdateSql = UPDATE_BYPRESETVACATION_STMT;

			int count = 0;
			for (int i = 0; i < schDate.length(); i++) {
				if (schDate.substring(i, (i + 1)).equals("0")) {
					if (count == 0) {
						finalUpdateSql += " and ";
					} else {
						finalUpdateSql += " or ";
					}
					switch (i) {
					case 0:
						finalUpdateSql += " dayname(schdate) = 'Monday' ";
						count++;
						break;
					case 1:
						finalUpdateSql += " dayname(schdate) = 'Tuesday' ";
						count++;
						break;
					case 2:
						finalUpdateSql += " dayname(schdate) = 'Wednesday' ";
						count++;
						break;
					case 3:
						finalUpdateSql += " dayname(schdate) = 'Thursday' ";
						count++;
						break;
					case 4:
						finalUpdateSql += " dayname(schdate) = 'Friday' ";
						count++;
						break;
					case 5:
						finalUpdateSql += " dayname(schdate) = 'Saturday' ";
						count++;
						break;
					case 6:
						finalUpdateSql += " dayname(schdate) = 'Sunday' ";
						count++;
						break;
					}
				}
			}
			pstmt = con.prepareStatement(finalUpdateSql);
			pstmt.setInt(1, groomerId);

			pstmt.executeUpdate();

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
	}

	@Override
	public List<SchVO> getByGroomerId(Integer groomerId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SchVO> list = new ArrayList<SchVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONELIST_STMT);

			pstmt.setInt(1, groomerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SchVO schVO = new SchVO();
				schVO.setSchId(rs.getInt(1));
				schVO.setGroomerId(rs.getInt(2));
				schVO.setSchDate(rs.getDate(3));
				schVO.setSchStatus(rs.getString(4));

				list.add(schVO);
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
	}

	@Override
	public List<SchVO> getByGroomerId(Integer groomerId, Integer month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SchVO> list = new ArrayList<SchVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONELISTBYMONTH_STMT);

			pstmt.setInt(1, groomerId);
			pstmt.setInt(2, month);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SchVO schVO = new SchVO();
				schVO.setSchId(rs.getInt(1));
				schVO.setGroomerId(rs.getInt(2));
				schVO.setSchDate(rs.getDate(3));
				schVO.setSchStatus(rs.getString(4));

				list.add(schVO);
			
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
	}

	@Override
	public void update(Integer groomerId, Date date, Integer status, Integer stime, Integer etime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, (stime + 1)); // mySql 字串index從1開始
			pstmt.setInt(2, (etime - stime + 1));
			pstmt.setInt(3, status);
			pstmt.setInt(4, (etime - stime + 1));
			pstmt.setInt(5, groomerId);
			pstmt.setDate(6, date);

			pstmt.executeUpdate();

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

	}

	@Override
	public void update(Integer groomerId, Date date, Integer status, Integer stime, Integer etime, Connection con) {
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, (stime + 1)); // mySql 字串index從1開始
			pstmt.setInt(2, (etime - stime + 1));
			pstmt.setString(3, status.toString());
			pstmt.setInt(4, (etime - stime + 1));
			pstmt.setInt(5, groomerId);
			pstmt.setDate(6, date);
			pstmt.executeUpdate();

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
		}
	}

	@Override
	public SchVO getOneDaySchdule(Integer schId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SchVO schVO = new SchVO();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SCHSTATUS_STMT);

			pstmt.setInt(1, schId);
			rs = pstmt.executeQuery();

			rs.next();
			schVO.setSchId(rs.getInt("SCHID"));
			schVO.setGroomerId(rs.getInt("GROOMERID"));
			schVO.setSchDate(rs.getDate("SCHDATE"));
			schVO.setSchStatus(rs.getString("SCHSTATUS"));

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
		return schVO;
	}

	@Override
	public SchVO getOneDaySchdule(String apmDate, Integer groomerId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SchVO schVO = new SchVO();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SCHSTATUSBYDATE_STMT);
			
			pstmt.setInt(1, groomerId);
			pstmt.setString(2, apmDate);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			schVO.setSchId(rs.getInt(1));
			schVO.setGroomerId(rs.getInt(2));
			schVO.setSchDate(rs.getDate(3));
			schVO.setSchStatus(rs.getString(4));
			
			
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
		return schVO;
	}

}
