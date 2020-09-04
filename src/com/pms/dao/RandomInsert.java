package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.formula.ptg.Ptg;

import com.pms.dto.SettingDTO;
import com.pms.dto.memberManageDTO;
import com.pms.util.DBConnectionMgr;
import com.pms.util.PMSRandom;

public class RandomInsert {
	private DBConnectionMgr pool;
		
	public RandomInsert() {
		pool = DBConnectionMgr.getInstance();
	}


	public void randomLogAdd(String key, String in_time, String out_time, int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String month_id = null;
		PmsLogDao dao =PmsLogDao.getInstance();
		
		try {
			con = pool.getConnection();

			if(out_time != null) {
				int pay= dao.fare2(in_time, out_time);
				sql = "insert into PMS_LOG(IDX,CNUM,IN_TIME,OUT_TIME,MONTH_NUM, pay) values(LOG_SEQ.nextval,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), ? , ? )";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setString(2, in_time);
				pstmt.setString(3, out_time);
				pstmt.setInt(4, num);
				pstmt.setInt(5, pay );
			}else {
				sql = "insert into PMS_LOG(IDX,CNUM,IN_TIME,MONTH_NUM) values(LOG_SEQ.nextval,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setString(2, in_time);
				pstmt.setInt(3, num);
			}
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	public void randomMemberAdd(String key, String toDate, String stopDate, String name, String addr, String phone, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			con = pool.getConnection();
			int pay = 0;
			//주차장 세팅 
			SettingDAO setDao = SettingDAO.getInstance();
			SettingDTO set = setDao.settItem();
			
			// 객체 생성 후 값 저장
			if(type.equals("일반")) pay = set.getMonth_fare(); 
			sql = "insert into PMS_MONTH_MEMBER(IDX,JDATE,SDATE,EDATE,CARN,NAME,EMAIL,PHONE,TYPE,MONTH_PAY) values(MONTH_MEMBER_SEQ.nextval,SYSDATE,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, toDate);
			pstmt.setString(2, stopDate);
			pstmt.setString(3, key);
			pstmt.setString(4, name);
			pstmt.setString(5, addr);
			pstmt.setString(6, phone);
			pstmt.setString(7, type);
			pstmt.setInt(8, pay);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}		
	}
	
	public int monthNum(String key, String in_time, String out_time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM PMS_MONTH_MEMBER WHERE SDATE <= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND EDATE >= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND CARN = ?";
			String sql1 = "SELECT * FROM PMS_MONTH_MEMBER WHERE SDATE <= TO_DATE('"+in_time+"','YYYY-MM-DD HH24:MI:SS') AND EDATE >= TO_DATE('"+out_time+"','YYYY-MM-DD HH24:MI:SS') AND CARN = '"+key+"'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, in_time);
			pstmt.setString(2, out_time);
			pstmt.setString(3, key);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			   num = rs.getInt("idx");
			   break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}		
		return num;
	}
}

