package com.pet.model;

import java.util.*;

import com.variety.model.VarietyVO;

import java.sql.*;

public class PetJDBCDAO implements PetDAO_interface {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "insert into PET(PET_MEM_ID, PET_NAME, PET_SORT, PET_VAR_ID, PET_GENDER, PET_AGE, PET_SURVIVE) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select *from PET";
	private static final String GET_ONE_STMT = "SELECT PET_ID, PET_MEM_ID, PET_NAME, PET_SORT, PET_VAR_ID, PET_GENDER, PET_AGE, PET_SURVIVE FROM PET where PET_ID= ?;";
	private static final String DELETE = "DELETE FROM PET where PET_ID = ?";
	private static final String UPDATE = "UPDATE PET set PET_SORT =?, PET_VAR_ID =?, PET_GENDER =?, PET_AGE =?, PET_SURVIVE =? where PET_ID = ?";
	
	@Override
	public void insert(PetVO petVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println("好想吃可麗露");
			pstmt.setInt(1, petVO.getPetMemId());
			pstmt.setString(2, petVO.getPetName());
			pstmt.setString(3, petVO.getPetSort());
			pstmt.setInt(4, petVO.getPetVarId());
			pstmt.setString(5, petVO.getPetGender());
			pstmt.setInt(6, petVO.getPetAge());
			pstmt.setInt(7, petVO.getPetSurvive());
			
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public void update(PetVO petVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(1, petVO.getPetName());
			pstmt.setString(1, petVO.getPetSort());
			pstmt.setInt(2, petVO.getPetVarId());
			pstmt.setString(3, petVO.getPetGender());
			pstmt.setInt(4, petVO.getPetAge());
			pstmt.setInt(5, petVO.getPetSurvive());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public void delete(Integer PetId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PetId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public PetVO findByPrimaryKey(Integer PetId) {

		PetVO petVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, PetId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

			petVO = new PetVO();
			
			petVO.setPetId(rs.getInt("PET_ID"));
			petVO.setPetMemId(rs.getInt("PET_MEM_ID"));
			petVO.setPetName(rs.getString("PET_NAME"));
			petVO.setPetSort(rs.getString("PET_SORT"));
			petVO.setPetVarId(rs.getInt("PET_VAR_ID"));
			petVO.setPetGender(rs.getString("PET_GENDER"));
			petVO.setPetAge(rs.getInt("PET_AGE"));
			petVO.setPetSurvive(rs.getInt("PET_SURVIVE"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
		
		return petVO;
	}

	@Override
	public List<PetVO> getAll() {
		
		List<PetVO> list = new ArrayList<PetVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				PetVO petVO = new PetVO();
				
				petVO.setPetId(rs.getInt("PET_ID"));
				petVO.setPetMemId(rs.getInt("PET_MEM_ID"));
				petVO.setPetName(rs.getString("PET_NAME"));
				petVO.setPetSort(rs.getString("PET_SORT"));
				petVO.setPetVarId(rs.getInt("PET_VAR_ID"));
				petVO.setPetGender(rs.getString("PET_GENDER"));
				petVO.setPetAge(rs.getInt("PET_AGE"));
				petVO.setPetSurvive(rs.getInt("PET_SURVIVE"));

				list.add(petVO);
			}

			// Handle any driver errors
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		PetJDBCDAO dao =new PetJDBCDAO();
		
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  Class.forName(driver);
		  con = DriverManager.getConnection(url, userid, passwd);
		  pstmt = con.prepareStatement(INSERT_STMT);
		  System.out.println("和尚端湯上塔塔滑湯灑湯燙塔");

		  
		  PetVO petVO = new PetVO();
//		  	List<PetVO> list = dao.getAll();
//			for (PetVO p : list) {
//				System.out.print(p.getPetId() + ",");
//				System.out.print(p.getPetMemId() + ",");
//				System.out.print(p.getPetName() + ",");
//				System.out.print(p.getPetSort() + ",");
//				System.out.print(p.getPetVarId() + ",");
//				System.out.print(p.getPetGender() + ",");
//				System.out.print(p.getPetAge());
//				System.out.print(p.getPetSurvive());
//				System.out.println();
				
//			PetVO petVO3 = dao.findByPrimaryKey(1);
//			System.out.print(petVO3.getPetId() + ",");
//			System.out.print(petVO3.getPetMemId() + ",");
//			System.out.print(petVO3.getPetName() + ",");
//			System.out.print(petVO3.getPetSort() + ",");
//			System.out.print(petVO3.getPetVarId() + ",");
//			System.out.print(petVO3.getPetGender() + ",");
//			System.out.print(petVO3.getPetAge());
//			System.out.print(petVO3.getPetSurvive());
//		  
//			PetVO petVO1 = new PetVO();
//			petVO1.setPetMemId(1);
//			petVO1.setPetName("SQL黑");
//			petVO1.setPetSort("狗");
//			petVO1.setPetVarId(1);
//			petVO1.setPetGender("公");
//			petVO1.setPetAge(10);
//			petVO1.setPetSurvive(1);
//			dao.insert(petVO1);

//			dao.delete(3);
}
}
