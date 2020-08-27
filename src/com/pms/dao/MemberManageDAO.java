package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pms.dto.memberManageDTO;
import com.pms.util.DBConnectionMgr;

public class MemberManageDAO {
	
	private DBConnectionMgr pool;
	
	public MemberManageDAO() {
		pool = DBConnectionMgr.getInstance();
	}
	
	private static MemberManageDAO instance;
	public static MemberManageDAO getInstance() {
		if (instance == null) {
			instance = new MemberManageDAO();
		}
		return instance;
	}
	
	//회원 가입
	public boolean insertMember(memberManageDTO mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert tblMember(id,pwd,name,gender,"
					+ "birthday,email,zipcode,address,hobby,job)"
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	
		
}
