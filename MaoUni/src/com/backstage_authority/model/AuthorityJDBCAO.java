package com.backstage_authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.adopt_mechanism.model.MechanismJDBCDAO;
import com.adopt_mechanism.model.MechanismVO;
import com.backstage_function.model.FunctionVO;
import com.backstage_staff.model.StaffVO;


public class AuthorityJDBCAO implements AuthorityDAO_interface{
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO backstage_authority(BA_ID,BA_FUN) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT BA_ID,BA_FUN FROM backstage_authority order by BA_ID";
	
	private static final String GET_ONE_STMT =
		"SELECT BA_ID,BA_FUN FROM backstage_authority where BA_ID = ?";
	
	private static final String get_StaffS_byAI_STMT=
		"SELECT MG_ID,MG_STATUS,MG_NAME,MG_AGE,MG_EDU,MG_ADD,MG_TEL,MG_CONT FROM BACKSTAGE_STAFF WHERE BA_ID = ? ORDER BY MG_ID";
	private static final String get_Functions_byId_STMT=
			"SELECT BF_ID,BF_FUN FROM BACKSTAGE_FUNCTION WHERE BF_ID=? ORDER BY BF_ID";
	
	private static final String getStaff_Authoriy_join=
			"select* from BACKSTAGE_staff s  join   BACKSTAGE_FUNCTION f  on  s.MG_ID=f.BF_ID";
	
	
	
	private static final String UPDATE = 
		"UPDATE backstage_authority set BA_FUN = ? where BA_ID = ?";
	
	
	@Override
	public void insert(AuthorityVO authorityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, authorityVO.getId());
			pstmt.setInt(2, authorityVO.getFun());
			
			
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
	public void update (AuthorityVO authorityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(UPDATE);		
			
			pstmt.setInt(1, authorityVO.getFun());
			pstmt.setInt(2, authorityVO.getId());
			
			
			
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
	public AuthorityVO findByPrimaryKey(Integer id) {
		AuthorityVO authorityVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,  id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setId(rs.getInt("BA_ID"));
				authorityVO.setFun(rs.getInt("BA_FUN"));
				
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
				if(rs !=null) {
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
		return authorityVO;
		}
		
	
	@Override
	public List< AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList< AuthorityVO>();
		 AuthorityVO  authorityVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setId(rs.getInt("BA_ID"));
				authorityVO.setFun(rs.getInt("BA_FUN"));
				list.add(authorityVO);
				
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
	public List<AuthorityJoinVO> getAllbyJoin() {
		List<AuthorityJoinVO> list = new ArrayList<AuthorityJoinVO>();
		StaffVO staffVO=null;
		AuthorityJoinVO authorityJoinVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(getStaff_Authoriy_join);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				authorityJoinVO=new AuthorityJoinVO(); 
				staffVO=new StaffVO();
				
				staffVO.setId(rs.getInt("MG_ID"));
				staffVO.setStatus(rs.getInt("MG_STATUS"));
				staffVO.setName(rs.getString("MG_NAME"));
				staffVO.setAge(rs.getInt("MG_AGE"));
				staffVO.setEdu(rs.getString("MG_EDU"));
				staffVO.setAdd(rs.getString("MG_ADD"));
				staffVO.setTel(rs.getString("MG_TEL"));
				staffVO.setCont(rs.getString("MG_CONT"));
				authorityJoinVO.setId(rs.getInt("BF_ID"));
				
		
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver."+e.getMessage());
			
		}catch (SQLException se){
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException se) {
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
		}
		if(con!=null) {
			try {
				con.close();
			}catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		return list; 
	}



	@Override
	public Set<StaffVO> get_StaffS_byAI_STMT(Integer id) {
		Set<StaffVO> set = new LinkedHashSet<StaffVO>();
		StaffVO staffVO = null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(get_StaffS_byAI_STMT);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				staffVO = new StaffVO();
				staffVO.setId(rs.getInt("MG_ID"));
				staffVO.setStatus(rs.getInt("MG_STATUS"));
				staffVO.setName(rs.getString("MG_NAME"));
				staffVO.setAge(rs.getInt("MG_AGE"));
				staffVO.setEdu(rs.getString("MG_EDU"));
				staffVO.setAdd(rs.getString("MG_ADD"));
				staffVO.setTel(rs.getString("MG_TEL"));
				staffVO.setCont(rs.getString("MG_CONT"));
				set.add(staffVO);
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver."+e.getMessage());
			
		}catch (SQLException se){
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException se) {
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
		}if(con!=null) {
			try {
				con.close();
			}catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		
		return set;
	}



	@Override
	public Set<FunctionVO> get_Functions_byId_STMT(Integer id) {
		Set<FunctionVO> set = new LinkedHashSet<FunctionVO>();
		FunctionVO functionVO =null;
		
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(get_Functions_byId_STMT);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				functionVO = new FunctionVO();
				functionVO.setId(rs.getInt("BF_ID"));
				functionVO.setName(rs.getString("BF_NAME"));
				set.add(functionVO);
				
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver."+e.getMessage());
			
		}catch (SQLException se){
			throw new RuntimeException("A database error occured."+se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException se) {
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
		}if(con!=null) {
			try {
				con.close();
			}catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		
			
		return set;
	}


		//������甈��镼選��閬���������閬���銝�ㄐ
//	@Override
//	public Set<FunctionVO> getFunctionsByid(Integer id) {
//		Set<FunctionVO> set =new HashSet<FunctionVO>();
//		FunctionVO functionVO=null;
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		try {
//			Class.forName(driver);
//			con=DriverManager.getConnection(url,userid,passwd);
//			pstmt=con.prepareStatement(get_Functions_byId_STMT);
//			pstmt.setInt(1, id);
//			rs=pstmt.executeQuery();
//			while(rs.next()) {
//				functionVO=new FunctionVO();
//				functionVO.setId(rs.getInt("BF_id"));
//				functionVO.setName(rs.getString("BF_name"));
//				
//			}
//		}catch(ClassNotFoundException e) {
//			throw new RuntimeException ("Couldn't load database driver."+e.getMessage());
//			
//		}catch (SQLException se){
//			throw new RuntimeException("A database error occured."+se.getMessage());
//		}finally {
//			if(rs!=null) {
//				try {
//					rs.close();
//				}catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(pstmt!=null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//		if(con!=null) {
//			try {
//				con.close();
//			}catch (Exception e) {
//				e.printStackTrace(System.err);
//			}
//		}
//		return set; 
//	}
//	
//
//	public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		AuthorityJDBCAO dao = new AuthorityJDBCAO();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		pstmt = con.prepareStatement(INSERT_STMT);
//		pstmt=con.prepareStatement(UPDATE);
//		pstmt=con.prepareStatement(GET_ONE_STMT);
//		pstmt=con.prepareStatement(GET_ALL_STMT);
//		pstmt=con.prepareStatement(get_Functions_byId_STMT);
//		pstmt=con.prepareStatement(get_StaffS_byAI_STMT);
//		
		
		
		
		
		//�憓�
//		AuthorityVO authorityVO1=new AuthorityVO();
//		authorityVO1.setFun(2);
//		authorityVO1.setId(14);
//		dao.insert(authorityVO1);
//		
//		��
//		AuthorityVO authorityVO2=new AuthorityVO();
//		authorityVO2.setFun(2);
//		authorityVO2.setId(1);
//		dao.update(authorityVO2);
//		
//		��
//		dao.delete(6);
//		
//		�銝��閰�
//		AuthorityVO authorityVO3=dao.findByPrimaryKey(1);
//		System.out.println(authorityVO3.getFun()+",");
//		System.out.println(authorityVO3.getId()+",");
////		
////		
////		�蝝閰Ｗ�甈��
//		List<AuthorityVO> list = dao.getAll();
//		for(AuthorityVO authorityVO: list) {
//			System.out.println(authorityVO.getFun()+",");
//			System.out.println(authorityVO.getId()+",");

		
		//��撌乩誑�����閰�
//		Set<StaffVO> set = dao.getAllbyJoin();
//		for(StaffVO staffVO : set) {
//			System.out.println(staffVO.getId()+",");
//			System.out.println(staffVO.getName()+",");
//			System.out.println(staffVO.getStatus()+",");
//			System.out.println(staffVO.getAge()+",");
//			System.out.println(staffVO.getEdu()+",");
//			System.out.println(staffVO.getAdd()+",");
//			System.out.println(staffVO.getTel()+",");
//			System.out.println(staffVO.getCont()+",");
//			System.out.println();
		



	
}
	
	
	
	
	
	
	

