package com.adopt_imf.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.adopt_mechanism.model.model.MechanismVO;
import com.variety.model.VarietyVO;

public class ImfJDBCDAO implements ImfDAO_interface {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/MAOUNI?serverTimezone=Asia/Taipei";
	static String userid = "David";
	static String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO adopt_imf(AI_ID,AI_ADOPT,AI_MECH,AI_NAME,AI_YEAR,AI_SIT,AI_PHOTO) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select i.AI_ID, i.AI_ADOPT, i.AI_MECH, m.ADOPT_NAME, i.AI_NAME, i.AI_YEAR, i.AI_SIT, i.AI_PHOTO   \r\n" + 
			"			from ADOPT_MECHANISM m  join ADOPT_IMF i  on  m.ADOPT_ID = i.AI_MECH";
	private static final String GET_ONE_STMT = "SELECT AI_ID,AI_ADOPT,AI_MECH,AI_NAME,AI_YEAR,AI_SIT,AI_PHOTO FROM adopt_imf where AI_ID = ?";
	private static final String DELETE = "DELETE FROM adopt_imf where AI_ID = ?";
	private static final String UPDATE = "UPDATE adopt_imf set AI_ADOPT = ?, AI_MECH = ?, AI_NAME = ?, AI_YEAR =?,AI_SIT =?,AI_PHOTO=? where  AI_ID = ?";

	private static final String getMechanismsByid = "select* from ADOPT_MECHANISM m  join ADOPT_IMF i  on  m.ADOPT_ID=i.AI_MECH";
	private static final String getVaritybyid = "SELECT `VAR_ID`,`VAR_KIND`,`VAR_KIND` FROM VARIETY WHERE `VAR_ID`=? ";
	
	private static final String getmechanisandimf=
	"select* from ADOPT_MECHANISM m  join ADOPT_IMF i  on  m.ADOPT_ID=i.AI_MECH";
	
	
	@Override
	public void insert(ImfVO imfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, imfVO.getId());
			pstmt.setInt(2, imfVO.getAdopt());
			pstmt.setInt(3, imfVO.getMech());
			pstmt.setString(4, imfVO.getName());
			pstmt.setInt(5, imfVO.getYear());
			pstmt.setString(6, imfVO.getSit());
			pstmt.setBytes(7, imfVO.getPhoto());
	
			pstmt.executeUpdate();

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

	public byte[] getPictureByteArray(String path) {

		byte[] buffer = null;

		try {
			FileInputStream fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);

			fis.close();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return buffer;

	}

	@Override
	public void update(ImfVO imfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, imfVO.getAdopt());
			pstmt.setInt(2, imfVO.getMech());
			pstmt.setString(3, imfVO.getName());
			pstmt.setInt(4, imfVO.getYear());
			pstmt.setString(5, imfVO.getSit());
			pstmt.setInt(6, imfVO.getId());
			pstmt.setBytes(7, imfVO.getPhoto());

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
	public ImfVO findByPrimaryKey(Integer id) {
		ImfVO imfVO = null;
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
				imfVO = new ImfVO();
				imfVO.setId(rs.getInt("AI_ID"));
				imfVO.setAdopt(rs.getInt("AI_ADOPT"));
				imfVO.setMech(rs.getInt("AI_MECH"));
				imfVO.setName(rs.getString("AI_NAME"));
				imfVO.setSit(rs.getString("AI_SIT"));
				imfVO.setYear(rs.getInt("AI_YEAR"));
				imfVO.setPhoto(rs.getBytes("AI_photo"));
		
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
		return imfVO;
	}

	@Override
	public List<ImfVO> getAll() {
		List<ImfVO> list = new ArrayList<ImfVO>();
		ImfVO imfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				imfVO = new ImfVO();
				imfVO.setId(rs.getInt("AI_ID"));
				imfVO.setAdopt(rs.getInt("AI_ADOPT"));
				imfVO.setMech(rs.getInt("AI_MECH"));
				imfVO.setMech2(rs.getString("ADOPT_NAME"));
				imfVO.setName(rs.getString("AI_NAME"));
				imfVO.setSit(rs.getString("AI_SIT"));
				imfVO.setYear(rs.getInt("AI_YEAR"));
				imfVO.setPhoto(rs.getBytes("AI_PHOTO"));
				list.add(imfVO);
				
				

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


////	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		ImfJDBCDAO dao = new ImfJDBCDAO();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		pstmt = con.prepareStatement(UPDATE);
//		pstmt = con.prepareStatement(GET_ONE_STMT);
//		pstmt = con.prepareStatement(GET_ALL_STMT);
//		pstmt = con.prepareStatement(INSERT_STMT);

//	System.out.println("fuck");
////		//嚙編嚙磕
//		ImfVO imfVO1=new ImfVO();
//		imfVO1.setAdopt(6);
//		imfVO1.setId(8);
//		imfVO1.setMech(20);
//		imfVO1.setName("撠");
//		imfVO1.setYear(2);
//		imfVO1.setSit("��瘜�帘");
//		imfVO1.setPhoto("test/1.jpg");
//		dao.insert(imfVO1);
//		
//		//嚙論改蕭
//		ImfVO imfVO2 = new ImfVO();
//		imfVO2.setAdopt(2);
//		imfVO2.setId(2);	
//		imfVO2.setMech(18);
//		imfVO2.setName("Super");
//		imfVO2.setSit("狀況良好");
//		imfVO2.setYear(2);
//		imfVO2.setPhoto("test/1.jpg");

//		dao.update(imfVO2);
//		
//		//嚙磋嚙踝蕭
//		dao.delete(10);
//		
//		//嚙範嚙踝蕭
//		ImfVO imfVO3=dao.findByPrimaryKey(1);
//		System.out.println(imfVO3.getName()+",");
//		System.out.println(imfVO3.getSit()+",");
//		System.out.println(imfVO3.getAdopt()+",");
//		System.out.println(imfVO3.getId()+",");
//		System.out.println(imfVO3.getMech()+",");
//		System.out.println(imfVO3.getYear()+",");
//		System.out.println(imfVO3.getClass()+",");
//		
//		//嚙範嚙踝蕭
//		List<ImfVO> list = dao.getAll();
//		for(ImfVO imfVO : list) {
//			System.out.println(imfVO.getName()+",");
//			System.out.println(imfVO.getSit()+",");
//			System.out.println(imfVO.getAdopt()+",");
//			System.out.println(imfVO.getId()+",");
//			System.out.println(imfVO.getMech()+",");
//			System.out.println(imfVO.getYear()+",");
//			System.out.println(imfVO.getPhoto()+",");
//			System.out.println();
		

	
//
	@Override
	public Set<MechanismVO> getMechanismsByid(Integer id) {
		Set<MechanismVO> set = new LinkedHashSet<MechanismVO>();
		MechanismVO mechanismVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(getMechanismsByid);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mechanismVO = new MechanismVO();
				mechanismVO.setId(rs.getInt("ADOPT_ID"));
				mechanismVO.setName(rs.getString("ADOPT_NAME"));
				mechanismVO.setAddress(rs.getString("ADOPT_ADDRESS"));
				mechanismVO.setTel(rs.getString("ADOPT_TEL"));
				mechanismVO.setUrl(rs.getString("ADOPT_URL"));
				set.add(mechanismVO);
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." +e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A datbase error occured. " +se.getMessage());
		}finally {
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
	public Set<VarietyVO> getVaritybyid(Integer id) {
		Set<VarietyVO> set = new LinkedHashSet<VarietyVO>();
		VarietyVO varietyVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(getVaritybyid);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				varietyVO = new VarietyVO();
				varietyVO.setVarId(rs.getInt("`VAR_ID`"));
				varietyVO.setVarName(rs.getString("`VAR_NAME`"));
				varietyVO.setVarKind(rs.getString("`VAR_KIND`"));
				set.add(varietyVO);
			}
	}catch(ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver." +e.getMessage());
	}catch(SQLException se) {
		throw new RuntimeException("A datbase error occured. " +se.getMessage());
	}finally {
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







}








