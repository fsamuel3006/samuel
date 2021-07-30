package com.adopt_mechanism.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adopt_imf.model.ImfJDBCDAO;
import com.adopt_imf.model.ImfVO;



public class MechanismJDBCDAO implements MechanismDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO adopt_mechanism(ADOPT_ID,ADOPT_NAME,ADOPT_ADDRESS,ADOPT_TEL,ADOPT_URL) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT ADOPT_ID,ADOPT_NAME,ADOPT_ADDRESS,ADOPT_TEL,ADOPT_URL FROM adopt_mechanism order by ADOPT_ID";
	private static final String GET_ONE_STMT =
		"SELECT ADOPT_ID,ADOPT_NAME,ADOPT_ADDRESS,ADOPT_TEL,ADOPT_URL FROM adopt_mechanism where ADOPT_ID = ?";
	private static final String DELETE = 
		"DELETE FROM adopt_mechanism where ADOPT_ID = ?";
	private static final String UPDATE = 
		"UPDATE adopt_mechanism set ADOPT_NAME = ?, ADOPT_ADDRESS = ?, ADOPT_TEL = ?, ADOPT_URL =? where= ADOPT_ID";
	
	
	@Override
	public void insert(MechanismVO mechanismVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, mechanismVO.getId());
			pstmt.setString(2, mechanismVO.getName());
			pstmt.setString(3, mechanismVO.getAddress());
			pstmt.setString(4, mechanismVO.getTel());
			pstmt.setString(5, mechanismVO.getUrl());
			
			
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
	public void update( MechanismVO  mechanismVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(2, mechanismVO.getName());
			pstmt.setString(3, mechanismVO.getAddress());
			pstmt.setString(4, mechanismVO.getTel());
			pstmt.setString(5, mechanismVO.getUrl());
			pstmt.setInt(1, mechanismVO.getId());
			
			
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
			pstmt=con.prepareStatement(DELETE);
			
			pstmt.setInt(1, id);
			
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
	public MechanismVO findByPrimaryKey(Integer id) {
		MechanismVO mechanismVO=null;
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
				mechanismVO = new MechanismVO();
				mechanismVO.setId(rs.getInt("ADOPT_ID"));
				mechanismVO.setName(rs.getString("ADOPT_NAME"));
				mechanismVO.setAddress(rs.getString("ADOPT_ADDRESS"));
				mechanismVO.setTel(rs.getString("ADOPT_TEL"));
				mechanismVO.setUrl(rs.getString("ADOPT_URL"));
				
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
				if(con != null) {
					try {
						con.close();
					}catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
	
		return mechanismVO;
	}

		
	
	@Override
	public List<MechanismVO> getAll() {
		List<MechanismVO> list = new ArrayList<MechanismVO>();
		MechanismVO mechanismVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				mechanismVO =new MechanismVO();
				mechanismVO.setId(rs.getInt("ADOPT_ID"));
				mechanismVO.setName(rs.getString("ADOPT_NAME"));
				mechanismVO.setAddress(rs.getString("ADOPT_ADDRESS"));
				mechanismVO.setTel(rs.getString("ADOPT_TEL"));
				mechanismVO.setUrl(rs.getString("ADOPT_URL"));
				list.add(mechanismVO);
				
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
	
//	public static void main(String [] args) throws ClassNotFoundException, SQLException{
//		MechanismJDBCDAO dao = new MechanismJDBCDAO();
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		pstmt=con.prepareStatement(UPDATE);
//		pstmt=con.prepareStatement(GET_ONE_STMT);
//		pstmt=con.prepareStatement(GET_ALL_STMT);
//		
//		//�s�W
//		MechanismVO mechanismVO1=new MechanismVO();
//		mechanismVO1.setADDRESS("�s�_��");
//		mechanismVO1.setID(1);
//		mechanismVO1.setNAME("�y�����a");
//		mechanismVO1.setTEL(02-84758302);
//		mechanismVO1.setURL("www.google.com");
//		dao.insert(mechanismVO1);
//		
//		//�ק�
//		MechanismVO mechanismVO2=new MechanismVO();
//		mechanismVO2.setId(3);
//		mechanismVO2.setAddress("臺東縣臺東市中華路4段999巷600號");
//		mechanismVO2.setName("臺東縣動物收容中心");
//		mechanismVO2.setTel("089-362011");
//		mechanismVO2.setUrl("http://asms.coa.gov.tw/amlapp/App/AnnounceMent.aspx?PageType=Adopt&Unit=NAAAG");
//		dao.update(mechanismVO2);
//		
//		//�R��
//		dao.delete(20);
//		
//		//�d��
//		MechanismVO mechanismVO3=dao.findByPrimaryKey(1);
//		System.out.println(mechanismVO3.getAddress()+",");
//		System.out.println(mechanismVO3.getName()+",");
//		System.out.println(mechanismVO3.getUrl()+",");
//		System.out.println(mechanismVO3.getId()+",");
//		System.out.println(mechanismVO3.getTel()+",");
//		
//		
////		//�d��
//		List<MechanismVO> list = dao.getAll();
//		for(MechanismVO mechanismVO : list) {
//			System.out.println(mechanismVO.getAddress()+",");
//			System.out.println(mechanismVO.getName()+",");
//			System.out.println(mechanismVO.getUrl()+",");
//			System.out.println(mechanismVO.getId()+",");
//			System.out.println(mechanismVO.getTel()+",");
//			
//			System.out.println();
//		}
//		
//		
//	}

	
}
	
	


