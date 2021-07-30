package com.article.model;

import java.util.*;

import java.sql.*;

//���Ѥ峹    article
public class ArtJDBCDAO  implements ArtDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	// �s�W
		private static final String INSERT_STMT = 
				"INSERT INTO article ( ART_ARTT_ID, ART_NAME, ART_PIC , ART_CONTENT, ART_UPDATE ) VALUES (?, ? ,?, ?, ?)"; 
	// �d�ߥ���o
		private static final String GET_ALL_STMT = 
				"SELECT ART_ID, ART_ARTT_ID ,ART_NAME,ART_PIC,ART_CONTENT,ART_UPDATE FROM article";
	// �d�ߤ@�����o
		private static final String GET_ONE_STMT = 
				"SELECT ART_ID, ART_ARTT_ID , ART_NAME, ART_PIC, ART_CONTENT, ART_UPDATE FROM article where ART_ID = ?";
	// �R���@�����o
		private static final String DELETE = 
				"DELETE FROM article where ART_ID = ?";
	// �ק�@�����o
		private static final String UPDATE = 
				"UPDATE article set ART_ARTT_ID=?, ART_NAME=?, ART_PIC=? ,ART_CONTENT=? ,ART_UPDATE=?  where ART_ID = ?"; 

		
	//�s�W
		public void insert(ArtVO artVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, artVO.getArtt_id());      //����ID
				pstmt.setString(2, artVO.getName());      //�峹�W��
				pstmt.setBytes(3, artVO.getPic());        //�峹�Ϥ�
				pstmt.setString(4, artVO.getContnt());    //�峹���e
				pstmt.setDate(5, artVO.getUpdate());      //�o�G�ɶ�
				
				pstmt.executeUpdate();
				
				// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		
	//�ק�
		public void update(ArtVO artVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, artVO.getArtt_id());      //����ID
				pstmt.setString(2, artVO.getName());      //�峹�W��
				pstmt.setBytes(3, artVO.getPic());        //�峹�Ϥ�
				pstmt.setString(4, artVO.getContnt());    //�峹���e
				pstmt.setDate(5, artVO.getUpdate());      //�o�G�ɶ�
				pstmt.setInt(6, artVO.getId());           //�峹ID
				pstmt.executeUpdate();
				
				
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		
	//�R��
		public void delete(Integer id) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setInt(1, id);
				
				pstmt.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
			
    //�d�ߤ@�����
		public ArtVO findByPrimaryKey(Integer id) {
			
			ArtVO artVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setInt(1, id);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					artVO = new ArtVO();
					artVO.setId(rs.getInt("art_id"));
					artVO.setArtt_id(rs.getInt("art_artt_id"));
					artVO.setName(rs.getString("art_name"));
					artVO.setPic(rs.getBytes("art_pic"));
					artVO.setContnt(rs.getString("art_content"));
					artVO.setUpdate(rs.getDate("art_update"));
				}
				
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return artVO;
		}
		
	//�d�ߥ���
		public List<ArtVO> getAll(){
			List<ArtVO> list = new ArrayList<ArtVO>();
			ArtVO artVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO �]�٬� Domain objects
					artVO = new ArtVO();
					artVO.setId(rs.getInt("art_id"));
					artVO.setArtt_id(rs.getInt("art_artt_id"));
					artVO.setName(rs.getString("art_name"));
					artVO.setPic(rs.getBytes("art_pic"));
					artVO.setContnt(rs.getString("art_content"));
					artVO.setUpdate(rs.getDate("art_update"));
					list.add(artVO); // Store the row in the list
				}
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
		
		public static void main(String[] args) {
			
			ArtJDBCDAO dao = new ArtJDBCDAO();
			
		// �s�W
			ArtVO artVO1 = new ArtVO();
			artVO1.setArtt_id(1);
			artVO1.setName("�s�W���ժ��Ѥ峹");
			artVO1.setPic(null);
			artVO1.setContnt("test_insert");
			artVO1.setUpdate(java.sql.Date.valueOf("2021-07-01"));
			dao.insert(artVO1);
		
		
		// �ק�
		    ArtVO artVO2 = new ArtVO();
		    artVO2.setId(1);
		    artVO2.setArtt_id(1);
			artVO2.setName("�ק���ժ��Ѥ峹");
			artVO2.setPic(null);
			artVO2.setContnt("test_update");
			artVO2.setUpdate(java.sql.Date.valueOf("2021-07-01"));
		    dao.update(artVO2);

		 // �R��
			dao.delete(3);
			
		// ��@�d��
			ArtVO artVO3 = dao.findByPrimaryKey(1);
		    System.out.print(artVO3.getId()+ ",");
		    System.out.print(artVO3.getArtt_id()+ ",");
		    System.out.print(artVO3.getName()+ ",");
		    System.out.print(artVO3.getPic()+ ",");
		    System.out.print(artVO3.getContnt()+ ",");
		    System.out.println(artVO3.getUpdate()+ ",");
		    System.out.println("=======================================");
		    
		 // �d��
			List<ArtVO> list = dao.getAll();
			for (ArtVO aArt : list) {
			System.out.print(aArt.getId()+ ",");
			System.out.print(aArt.getArtt_id()+ ",");
			System.out.print(aArt.getName()+ ",");
			System.out.print(aArt.getPic()+ ",");
			System.out.print(aArt.getContnt()+ ",");
			System.out.println(aArt.getUpdate()+ ",");
			System.out.println();
			}
       }	
		
}
