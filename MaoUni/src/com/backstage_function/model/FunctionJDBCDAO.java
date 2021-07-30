package com.backstage_function.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.backstage_authority.model.AuthorityJDBCAO;
import com.backstage_authority.model.AuthorityVO;
import com.backstage_staff.model.StaffJDBCDAO;

public class FunctionJDBCDAO implements FunctionDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO BACKSTAGE_FUNCTION(BF_ID,BF_NAME) VALUES (?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT BF_ID,BF_NAME FROM BACKSTAGE_FUNCTION order by BF_ID";
		private static final String GET_ONE_STMT =
			"SELECT  BF_ID,BF_NAME FROM BACKSTAGE_FUNCTION where BF_ID = ?";
		
		private static final String GET_AI_BYFUNCTION_STMT=
			"SELECT BA_ID,BA_FUN FROM BACKSTAGE_AUTHORITY where BF_ID ? order by BA_ID=";
		
		
//		private static final String DELETE_BACKSTAGE_staff = 
//			"DELETE FROM BACKSTAGE_staff where MG_ID = ?";	
//		private static final String DELETE_BACKSTAGE_FUNCTION = 
//			"DELETE FROM BACKSTAGE_FUNCTION where BF_ID = ?";
		
		private static final String UPDATE = 
			"UPDATE BACKSTAGE_FUNCTION set BF_NAME = ? where BF_ID=?";
	
	@Override
	public void insert(FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, functionVO.getId());
			pstmt.setString(2, functionVO.getName());
			
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	
	@Override
	public void update (FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, functionVO.getName());
			pstmt.setInt(2, functionVO.getId());
		
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
		
	
	@Override
	public void delete(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
//			pstmt=con.prepareStatement(DELETE_BACKSTAGE_FUNCTION);
			
			pstmt.setInt(1,  id);
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
		
	
	@Override
	public FunctionVO findByPrimaryKey(Integer id) {
		FunctionVO functionVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				functionVO = new FunctionVO();
				functionVO.setId(rs.getInt("BF_ID"));
				functionVO.setName(rs.getString("BF_NAME"));				
			}
		}
			catch(ClassNotFoundException e){
				throw new RuntimeException("Couldn't load database driver."+e.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured."+se.getMessage());
			}finally {
				if(pstmt !=null) {
					try {
						pstmt.close();
					}catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if(rs != null) {
					try {
						con.close();
					}catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
				if(con != null) {
					try {
						con.close();
					}catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		return functionVO;
		}
		
	
	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO  functionVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				functionVO = new FunctionVO();
				functionVO.setId(rs.getInt("BF_ID"));
				functionVO.setName(rs.getString("BF_NAME"));
				list.add(functionVO);
				
			}
		}
			catch(ClassNotFoundException e){
				throw new RuntimeException("Couldn't load database driver."+e.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured."+se.getMessage());
			}finally {
				if(rs !=null) {
					try {
						rs.close();
					}catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					}catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
				if(con!=null) {
					try {
						con.close();
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		
		return list;
	}
	@Override
	public Set<AuthorityVO> getAuthorityBYFunction(Integer BF_ID) {
		Set<AuthorityVO> set = new LinkedHashSet<AuthorityVO>();
		AuthorityVO authorityVO = null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		Class.forName(driver);
		con=DriverManager.getConnection(url,userid,passwd);
		pstmt=con.prepareStatement(GET_AI_BYFUNCTION_STMT);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			authorityVO = new AuthorityVO();
			authorityVO.setId(rs.getInt("BA_ID"));
			authorityVO.setFun(rs.getInt("BA_FUN"));
			set.add(authorityVO);
		}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error ouucre."+se.getMessage());
		}finally {
			if(rs!=null){
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}

	
	public static void main(String [] args) throws ClassNotFoundException, SQLException {
		FunctionJDBCDAO dao = new FunctionJDBCDAO();
//
//	
		Connection con = null;
		PreparedStatement pstmt = null;
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);
		pstmt=con.prepareStatement(GET_ONE_STMT);
		pstmt=con.prepareStatement(GET_ALL_STMT);
		//pstmt=con.prepareStatement(DELETE_BACKSTAGE_FUNCTION);
		pstmt=con.prepareStatement(UPDATE);
		pstmt=con.prepareStatement(GET_AI_BYFUNCTION_STMT);
//		
		
		
		//�s�W
//		FunctionVO functionVO1=new FunctionVO();
//		functionVO1.setId(8);
//		functionVO1.setName("great");
//		dao.insert(functionVO1);
		
//		//�ק�
//		FunctionVO functionVO2=new FunctionVO();
//		functionVO2.setId(1);
//		functionVO2.setName("NO");
//		dao.update(functionVO2);
////		
//		//�R��
//		dao.delete(5);
//		
//		//單一查詢
		FunctionVO functionVO3=dao.findByPrimaryKey(1);
		System.out.println(functionVO3.getName()+",");
		System.out.println(functionVO3.getId()+",");
//		
//
//       全部查詢
//		List<FunctionVO> list = dao.getAll();
//		for(FunctionVO functionVO: list) {
//			System.out.println(functionVO.getName()+",");
//			System.out.println(functionVO.getId()+",");
//			
		//同時查詢權限與功能
		Set<AuthorityVO> set=dao.getAuthorityBYFunction(1);
		for(AuthorityVO authorityVO : set) {
			System.out.print(authorityVO.getId()+",");
			System.out.print(authorityVO.getFun()+",");
		}
		
		
//	
//	}			

		
	}



	
}