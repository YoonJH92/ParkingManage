package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	//월정액 회원 추가
	public void insertMember(memberManageDTO mem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = null;
		try {
			con = pool.getConnection();
			sql = new StringBuffer();
			sql.append("insert into PMS_MONTH_MEMBER values");
			sql.append("(MONTH_MEMBER_SEQ.nextval,?,sysdate,TO_DATE(?,'YYYY-MM-DD HH24:MI'),TO_DATE(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getStartDate());
			pstmt.setString(3, mem.getStopDate());
			pstmt.setString(4, mem.getCNUM());
			pstmt.setString(5, mem.getPhone());
			pstmt.setString(6, mem.getEmail());
			pstmt.setInt(7, mem.getPay());
			pstmt.setString(8, mem.getType());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	
	//리스트 추가
	public ArrayList<memberManageDTO> ListMember() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		ArrayList<memberManageDTO> arr = new ArrayList<memberManageDTO>();
		
		try {
			con = pool.getConnection();
			sql = "select * from PMS_MONTH_MEMBER order by JDATE desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				memberManageDTO mem = new memberManageDTO();
				mem.setCNUM(rs.getString("CARN"));
				mem.setEmail(rs.getString("email"));
				mem.setName(rs.getString("name"));
				mem.setPay(rs.getInt("month_pay"));
				mem.setPhone(rs.getString("phone"));
				mem.setStartDate(rs.getString("sdate"));
				mem.setStopDate(rs.getString("edate"));
				mem.setType(rs.getString("type"));
				mem.setRegDate(rs.getString("jdate"));
				arr.add(mem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
}
