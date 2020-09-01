package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.directory.SearchControls;

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
	
	
	//월정액 리스트 
	public ArrayList<memberManageDTO> ListMember() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		ArrayList<memberManageDTO> arr = new ArrayList<memberManageDTO>();
		
		try {
			con = pool.getConnection();
			sql = "select * from PMS_MONTH_MEMBER order by SDATE desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				memberManageDTO mem = new memberManageDTO();
				mem.setIdx(rs.getInt("idx"));
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
	
	//월정액 리스트 
		public ArrayList<memberManageDTO> ListMember(Map<String, String> map) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			ArrayList<memberManageDTO> arr = new ArrayList<memberManageDTO>();
			String where = " 1=1 ";
			
			try {
				con = pool.getConnection();
				if(map.get("searchForm") != "") where += "AND "+map.get("search")+" LIKE ?";
				if(map.get("startForm")  != "" && map.get("endForm")  != "") where += "AND "+map.get("dateSearch")+" BETWEEN TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') ";
				else if(map.get("startForm")  != "") where += "AND "+map.get("dateSearch")+" >= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')";
				else if(map.get("endForm")  != "") where += "AND "+map.get("dateSearch")+" <= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')";
				
				sql = "select * from PMS_MONTH_MEMBER where "+ where +" order by SDATE desc";
				pstmt = con.prepareStatement(sql);
				if(map.get("searchForm")  != "") {
					pstmt.setString(1, "%"+map.get("searchForm")+"%");
					if(map.get("startForm")  != "" && map.get("endForm")  != "") {
						pstmt.setString(2, map.get("startForm"));
						pstmt.setString(3, map.get("endForm"));
					}else if(map.get("startForm")  != "") {
						pstmt.setString(2, map.get("startForm"));
					}else if(map.get("endForm")  != "") {
						pstmt.setString(2, map.get("endForm"));
					}
				}else {
					if(map.get("startForm")  != "" && map.get("endForm")  != "") {
						pstmt.setString(1, map.get("startForm"));
						pstmt.setString(2, map.get("endForm"));
					}else if(map.get("startForm")  != "") {
						pstmt.setString(1, map.get("startForm"));
					}else if(map.get("endForm")  != "") {
						pstmt.setString(1, map.get("endForm"));
					}
				}
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					memberManageDTO mem = new memberManageDTO();
					mem.setIdx(rs.getInt("idx"));
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

		public void updateMember(memberManageDTO mem) {
			Connection con = null;
			PreparedStatement pstmt = null;
			StringBuffer sql = null;
			try {
				con = pool.getConnection();
				sql = new StringBuffer();
				sql.append("UPDATE PMS_MONTH_MEMBER SET ");
				sql.append("NAME=?,CARN=?,SDATE=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),EDATE=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS')");
				sql.append(",MONTH_PAY=?,EMAIL=?,PHONE=?,TYPE=? WHERE IDX=?");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, mem.getName());
				pstmt.setString(2, mem.getCNUM());
				pstmt.setString(3, mem.getStartDate());
				pstmt.setString(4, mem.getStopDate());
				pstmt.setInt(5, mem.getPay());
				pstmt.setString(6, mem.getEmail());
				pstmt.setString(7, mem.getPhone());
				pstmt.setString(8, mem.getType());
				pstmt.setInt(9, mem.getIdx());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}

		public void deleteMember(int idx) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "DELETE FROM PMS_MONTH_MEMBER WHERE IDX = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}			
		}
		
}
