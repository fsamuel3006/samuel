package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class MessageJDBCDAO implements MessageDAO_interface{
		
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MESSAGE(MS_member,MS_TIME,MS_CONTAIN,MS_STATUS) VALUES (?,now(),?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT MS_ID,MS_member,MS_TIME,MS_CONTAIN,MS_STATUS FROM MESSAGE order by MS_ID";
		private static final String GET_ONE_STMT =
			"SELECT  MS_ID,MS_member,MS_TIME,MS_CONTAIN,MS_STATUS FROM MESSAGE where MS_ID = ?";
		private static final String DELETE = 
			"DELETE FROM MESSAGE where MS_ID = ?";
		private static final String UPDATE = 
			"UPDATE MESSAGE set MS_member = ?, MS_TIME =now(), MS_CONTAIN = ?, MS_STATUS =?  where MS_ID = ?";
		
		
		private static final String get_Member_byMessage = 
				"Select `MEM_ID`,`MEM_NAME`,`MEM_EMAIL`,`MEM_PASSWORD`,`MEM_IDENTITY`,`MEM_GENDER`"
				+ ",`MEM_PH`,`MEM_ADDRES`,`MEM_BIRTHDAT`,`MEM_POSITION`, `MEM_BLACKLIST`,`MEM_RESERVE`,`MEM_SURVIVE`,"
				+ "`MEM_UPDATE` from `MEMBER` where MS_ID = ? order by `MEM_ID`";
		
		private static final String get_COMPLAIN_byMessage=
			"Select COMPLAIN_ID,COMPLAIN_MEM_ID,COMPLAIN_MG_ID,COMPLAIN_O_ID,COMPLAI_QA,COMPLAIN_DATE,COMPLAIN_OUT,COMPLAIN_QAC "
			+ "from CO_COMPLAIN MS_ID =? order by COMPLAIN_MEM_ID";
		
		
		@Override
		public void insert(MessageVO messageVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				
				pstmt=con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, messageVO.getMemId());
				
				pstmt.setString(2, messageVO.getContain());
				pstmt.setInt(3, messageVO.getStatus());

				pstmt.executeUpdate();
				
			}catch(ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver."+e.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("A database error occure."+se.getMessage());
			}finally {
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
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
		}

		@Override
		public void update(MessageVO messageVO)  {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				
				pstmt=con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, messageVO.getId());
				pstmt.setInt(2, messageVO.getMemId());
				
				pstmt.setString(3, messageVO.getContain());
				pstmt.setInt(4, messageVO.getStatus());
				
				pstmt.executeUpdate();
			}catch(ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver."+e.getMessage());
			}catch(SQLException se){
				throw new RuntimeException("A database error occured."+se.getMessage());
			}
			finally {
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
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
		}

		@Override
		public void delete(Integer id) {
			
			
		}

		@Override
		public MessageVO findByPrimaryKey(Integer id) {
			MessageVO messageVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setInt(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					messageVO = new MessageVO();
					messageVO.setId(rs.getInt("MS_ID"));
					messageVO.setMemId(rs.getInt("MS_member"));
					
					messageVO.setContain(rs.getString("MS_CONTAIN"));
					messageVO.setStatus(rs.getInt("MS_STATUS"));
				}
			}catch(ClassNotFoundException e) {
				throw new RuntimeException("Couldn;t load database driver."+e.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured."+se.getMessage());
			}finally {
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
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
				if(rs!=null) {
					try {
						rs.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
			}
			
		}
			
			return messageVO;
		}

		@Override
		public List<MessageVO> getAll() {
			List<MessageVO> list = new ArrayList<MessageVO>();
			MessageVO messageVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					messageVO = new MessageVO();
					messageVO.setId(rs.getInt("MS_ID"));
					messageVO.setMemId(rs.getInt("MS_member"));
					
					messageVO.setContain(rs.getString("MS_CONTAIN"));
					messageVO.setStatus(rs.getInt("MS_STATUS"));
					list.add(messageVO);
				}
				
			}catch(ClassNotFoundException e) {
				throw new RuntimeException("Couldn;t load database driver."+e.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured."+se.getMessage());
			}finally {
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
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
				if(rs!=null) {
					try {
						rs.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
			}
			
		}
			
			return list;
		}

		@Override
		public Set<memberVO> get_Member_byMessage(Integer memId) {
			Set<memberVO> set = new LinkedHashSet<memberVO>();
			memberVO memberVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(get_Member_byMessage);
				pstmt.setInt(1, memId);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					com.message.model.memberVO  member = new memberVO();
					member.setMemId(rs.getInt("id"));
					member.setMemAddres(rs.getString("Addres"));
					member.setMemBirthday(rs.getTimestamp("Birthday"));
					member.setMemEmail(rs.getString("Email"));
					member.setMemIdenity(rs.getString("Idenity"));
					member.setMemGender(rs.getString("Gender"));
					member.setMemPassword(rs.getString("password"));
					member.setMemName(rs.getString("Name"));
					member.setMemPh(rs.getInt("PH"));
					member.setMemPosition(rs.getInt("Position"));
					member.setMemReserve(rs.getInt("Reserve"));					
					member.setMemSurvive(rs.getInt("Survive"));
					member.setMemUpdate(rs.getString("update"));
					set.add(memberVO);
					
				}
			}catch(ClassNotFoundException e) {
				throw new RuntimeException("Couldn;t load database driver."+e.getMessage());
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured."+se.getMessage());
			}finally {
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
					}catch(Exception e) {
						e.printStackTrace(System.err);
					}
				}
				if(rs!=null) {
					try {
						rs.close();
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
			}	
			}
			
			return set;
		}
		
		
		

//			
			public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		MessageJDBCDAO dao = new MessageJDBCDAO();
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt=con.prepareStatement(UPDATE);
		pstmt=con.prepareStatement(GET_ONE_STMT);
		pstmt=con.prepareStatement(GET_ALL_STMT);
		pstmt=con.prepareStatement(INSERT_STMT);
//			
//			//新增
//			MessageVO messageVO1 = new MessageVO();
//		
//		messageVO1.setMemId(3);			
//		
//		messageVO1.setStatus(1);
//		messageVO1.setContain("為什麼逗貓棒一下就壞");
//		dao.insert(messageVO1);
//			
//			//修改
//			MessageVO messageVO2 = new MessageVO();
//			messageVO2.setId(30);
//			messageVO2.setMemId(3);
//		
//			messageVO2.setContain("為什麼逗貓棒一下就壞");
//			messageVO2.setStatus(0);
//			dao.update(messageVO2);
//			
//			//單一查詢
//			MessageVO messageVO3 =dao.findByPrimaryKey(2);
//			System.out.println(messageVO3.getId()+",");
//			System.out.println(messageVO3.getMemId()+",");
//			System.out.println(messageVO3.getTime()+",");
//			System.out.println(messageVO3.getContain()+",");
//			System.out.println(messageVO3.getStatus()+",");
//			System.out.println("-----------------------------------");
//			
//			//查詢訊息所有的資料
//			List<MessageVO> list = dao.getAll();
//			for(MessageVO messageVO : list) {
//				System.out.println(messageVO.getId()+",");
//				System.out.println(messageVO.getMemId()+",");
//				System.out.println(messageVO.getTime()+",");
//				System.out.println(messageVO.getContain()+",");
//				System.out.println(messageVO.getStatus()+",");
//			}
//			//查詢會員與訊息所有資料
//			Set<memberVO> set = dao.get_Member_byMessage(1);
//				for(memberVO memberVO : set) {
//					System.out.println(memberVO.getMemId());
//					System.out.println(memberVO.getMemName());
//					System.out.println(memberVO.getMemPassword());
//					System.out.println(memberVO.getMemSurvive());
//					System.out.println(memberVO.getMemReserve());
//					System.out.println(memberVO.getMemPosition());
//					System.out.println(memberVO.getMemPh());
//					System.out.println(memberVO.getMemBirthday());
//					System.out.println(memberVO.getMemAddres());
//					System.out.println(memberVO.getMemEmail());
//					System.out.println(memberVO.getMemGender());
//					System.out.println(memberVO.getMemIdenity());
//					System.out.println(memberVO.getMemUpdate());
					
//				}
//				Set<COVO> set = dao.get_COMPLAIN_byMessage(MEM_ID);
//				for(COVO cOCVO : set) {
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					System.out.println(cOCVO);
//					
//				}
//		}

//		@Override
//		public Set<COVO> get_COMPLAIN_byMessage(Integer MEM_ID) {
//				Set<COVO> set = new LinkedHashSet<COVO>();
//				COVO COVO = null;
//				
//				Connection con = null;
//				PreparedStatement pstmt = null;
//				ResultSet rs = null;
//				try {
//					Class.forName(driver);
//					con = DriverManager.getConnection(url,userid,passwd);
//					pstmt = con.prepareStatement(get_COMPLAIN_byMessage);
//					pstmt.setInt(1, MEM_ID);
//					rs=pstmt.executeQuery();
//					
//					while(rs.next()){
//						COVO = new COVO();
//						COVO.setComplainId(rs.getInt("id"));
//						COVO.setComplainDate(rs.getTimestamp("date"));
//						COVO.setComplainMemId(rs.getInt("memid"));
//						COVO.setComplainMgId(rs.getInt("mgid"));
//						COVO.setComplainOId(rs.getInt("old"));
//						COVO.setComplainOut(rs.getInt("out"));
//						COVO.setComplainQa(rs.getString("qa"));
//						COVO.setComplainQac(rs.getString("qac"));
//						
//						set.add(COVO);
//						
//					}
//				}catch(ClassNotFoundException e) {
//					throw new RuntimeException("Couldn;t load database driver."+e.getMessage());
//				}catch(SQLException se) {
//					throw new RuntimeException("A database error occured."+se.getMessage());
//				}finally {
//					if(pstmt!=null) {
//						try {
//							pstmt.close();
//						}catch(SQLException se) {
//							se.printStackTrace(System.err);
//						}
//					}
//					if(con!=null) {
//						try {
//							con.close();
//						}catch(Exception e) {
//							e.printStackTrace(System.err);
//						}
//					}
//					if(rs!=null) {
//						try {
//							rs.close();
//						}catch(SQLException se) {
//							se.printStackTrace(System.err);
//						}
//				}	
//				}
//				
//				return set;
//			}
		
		
		
}
		
		}		
		
		
		
