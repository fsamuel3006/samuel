package com.obuy.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.commodity_details.model.CdJDBCDAO;
import com.commodity_details.model.CdVO;


public class ObuyJDBCDAO implements ObuyDAO_interface {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/maouni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	private static final String INSERT_STMT = "insert into OBUY (O_MEM_ID, O_MONEY, O_DATE, O_PAYING, O_SEND,O_OTHER) values (?, ?, now(), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM OBUY";
	private static final String GET_ONE_STMT = "SELECT O_ID, O_MEM_ID, O_MONEY, O_DATE, O_PAYING, O_SEND, O_SURVIVE, O_OTHER FROM OBUY where O_ID = ?";
	private static final String DELETE = "DELETE FROM OBUY where O_ID = ?";
	private static final String UPDATE = "UPDATE OBUY  set O_MEM_ID =?, O_MONEY=?, O_DATE=?, O_PAYING=?, O_SEND=?, O_SURVIVE=?, O_OTHER=? WHERE O_ID =?";
	
	@Override
	public void insert(ObuyVO obuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, obuyVO.getoMemId());
			pstmt.setInt(2, obuyVO.getoMoney());
			pstmt.setInt(3, obuyVO.getoPaying());
			pstmt.setInt(4, obuyVO.getoSend());
//			pstmt.setInt(5, obuyVO.getoSurvive());
			pstmt.setString(5, obuyVO.getObuyOther());

			pstmt.executeUpdate();

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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public void update(ObuyVO obuyVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, obuyVO.getoMemId());
			pstmt.setInt(2, obuyVO.getoMoney());
			pstmt.setTimestamp(3, obuyVO.getoDate());
			pstmt.setInt(4, obuyVO.getoPaying());
			pstmt.setInt(5, obuyVO.getoSend());
			pstmt.setInt(6, obuyVO.getoSurvive());
			pstmt.setString(7, obuyVO.getObuyOther());

			pstmt.executeUpdate();

			// Handle any driver errors
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	
	@Override
	public void delete(Integer obuyId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, obuyId);

			pstmt.executeUpdate();

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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	
	public ObuyVO findByPrimaryKey(Integer obuyId) {
		ObuyVO obuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, obuyId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				obuyVO = new ObuyVO();
				obuyVO.setObuyId(rs.getInt("O_BUY_ID"));
				obuyVO.setoMemId(rs.getInt("O_MEM_ID"));
				obuyVO.setoMoney(rs.getInt("O_MONEY"));
				obuyVO.setoDate(rs.getTimestamp("O_DATE"));
				obuyVO.setoPaying(rs.getInt("O_PAYING"));
				obuyVO.setoSend(rs.getInt("O_SEND"));
				obuyVO.setoSurvive(rs.getInt("O_SURVIVE"));
				obuyVO.setObuyOther(rs.getString("O_OTHER"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return obuyVO;

	}
	@Override
	public List<ObuyVO> getAll() {
		List<ObuyVO> list = new ArrayList<ObuyVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

        try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ObuyVO obuyVO = new ObuyVO();
				obuyVO.setObuyId(rs.getInt("O_ID"));
				obuyVO.setoMemId(rs.getInt("O_MEM_ID"));
				obuyVO.setoMoney(rs.getInt("O_MONEY"));
				obuyVO.setoDate(rs.getTimestamp("O_DATE"));
				obuyVO.setoPaying(rs.getInt("O_PAYING"));
				obuyVO.setoSend(rs.getInt("O_SEND"));
				obuyVO.setoSurvive(rs.getInt("O_SURVIVE"));
				obuyVO.setObuyOther(rs.getString("O_OTHER"));

				list.add(obuyVO);
			}

			// Handle any driver errors
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	

@Override
public void insertWithco(ObuyVO obuyVO , List<CdVO> list) {

	System.out.println("========================= dao");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"O_ID"}; //再備註下面的是甚麼
			pstmt = con.prepareStatement(INSERT_STMT, cols);	
			pstmt.setInt(1, obuyVO.getoMemId());//
			pstmt.setInt(2, obuyVO.getoMoney());//
			pstmt.setInt(3, obuyVO.getoPaying());
			pstmt.setInt(4, obuyVO.getoSend());
			pstmt.setString(5, obuyVO.getObuyOther());

			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_obuyId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_obuyId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_obuyId +"(剛新增成功");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增明細
			CdJDBCDAO dao = new CdJDBCDAO();
			for (CdVO cdVO : list) {
				cdVO.setCdoId(new Integer(next_obuyId)) ;
				dao.insert2(cdVO,con); //呼叫使用明細的新增，名字記得一樣還要有連線池
				
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("增加中");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-obuy");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
}
