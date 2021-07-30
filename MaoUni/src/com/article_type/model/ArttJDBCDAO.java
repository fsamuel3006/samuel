package com.article_type.model;

import java.util.*;

import com.article.model.ArtVO;

import java.sql.*;

//ArttVO  �峹����
public class ArttJDBCDAO implements ArttDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MaoUni?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	//�s�Wo
	private static final String INSERT_STMT =
			"INSERT INTO ARTICLE_TYPE (ARTT_NAME) VALUES (?)";
	//�d��o
	private static final String GET_ALL_STMT = 
			"SELECT ARTT_ID, ARTT_NAME FROM ARTICLE_TYPE order by ARTT_ID";
	//�d�ߤ@�����o
	private static final String GET_ONE_STMT = 
			"SELECT ARTT_ID, ARTT_NAME FROM ARTICLE_TYPE where ARTT_ID = ?";
	
	private static final String GET_Art_ByArtt_STMT =
			"SELECT ART_ID, ART_ARTT_ID ,ART_NAME,ART_PIC,ART_CONTENT,ART_UPDATE FROM article where ART_ARTT_ID = ? order by ART_ID";
	
	//�R���@�����o
	private static final String DELETE = 
			"DELETE FROM ARTICLE_TYPE where ARTT_ID = ?";
	//�ק�@�����o
	private static final String UPDATE = 
			"UPDATE ARTICLE_TYPE set ARTT_NAME= ? where ARTT_ID = ?";
	
	@Override
	//�s�W
	public void insert(ArttVO arttVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, arttVO.getName());
			
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
	@Override
	//�ק�
	public void update(ArttVO arttVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, arttVO.getName());
			pstmt.setInt(2, arttVO.getId());

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
	@Override
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
	@Override
	//�d�ߤ@�����
	public ArttVO findByPrimaryKey(Integer id) {

		ArttVO arttVO = null;
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
				// empVo �]�٬� Domain objects
				arttVO = new ArttVO();
				arttVO.setId(rs.getInt("artt_id"));           //SQL ���W
				arttVO.setName(rs.getString("artt_name"));    //SQL ���W
			
			}

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
		return arttVO;
	}
	
	@Override
	//�d�ߥ���
	public List<ArttVO> getAll() {
		List<ArttVO> list = new ArrayList<ArttVO>();
		ArttVO arttVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				arttVO = new ArttVO();
				arttVO.setId(rs.getInt( "artt_id"));
				arttVO.setName(rs.getString("artt_name"));
				list.add(arttVO); 
			}

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
	
//�d�߬Y�峹���������Ѥ峹(�@��h)
	public Set<ArtVO> getArtByArtt(Integer artt_id){
		Set<ArtVO> set = new LinkedHashSet<ArtVO>();
		ArtVO artVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Art_ByArtt_STMT);
			pstmt.setInt(1, artt_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setId(rs.getInt("art_id"));
				artVO.setArtt_id(rs.getInt("art_artt_id"));
				artVO.setName(rs.getString("art_name"));
				artVO.setPic(rs.getBytes("art_pic"));
				artVO.setContnt(rs.getString("art_content"));
				artVO.setUpdate(rs.getDate("art_update"));
				set.add(artVO); 
			}
			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return set;
	}
	
	
	
	
	public static void main(String[] args) {
		
		ArttJDBCDAO dao = new ArttJDBCDAO();
		
	// �s�W
//		ArttVO arttVO1 = new ArttVO();
//		arttVO1.setName("�ߤ��");
//			
//		dao.insert(arttVO1);
		
	// �ק�
//		ArttVO arttVO2 = new ArttVO();
//		arttVO2.setId(9);
//		arttVO2.setName("�ק�");
//		dao.update(arttVO2);
		
	// �R��
//		dao.delete(3);	
		
	// �d��
		ArttVO arttVO3 = dao.findByPrimaryKey( 1 );	
		System.out.print(arttVO3.getId() + ",");
		System.out.println(arttVO3.getName() + ",");
		System.out.println("--------------------------------------");
		
	// �d��
		List<ArttVO> list = dao.getAll();
		for (ArttVO aArtt : list) {
			System.out.print(aArtt.getId() + ",");
			System.out.print(aArtt.getName());
			System.out.println();
			
		}
			
	// �d�߬Y�峹�������峹
			Set<ArtVO> set = dao.getArtByArtt(1);	
			for (ArtVO aArt : set) {
				System.out.print(aArt.getId() + ",");
				System.out.print(aArt.getArtt_id() + ",");
				System.out.print(aArt.getName() + ",");
				System.out.print(aArt.getPic() + ",");
				System.out.print(aArt.getContnt() + ",");
				System.out.print(aArt.getUpdate());
				System.out.println();
			}
		}
		
	}
	
	

