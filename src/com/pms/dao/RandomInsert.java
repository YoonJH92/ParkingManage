package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.pms.util.DBConnectionMgr;
import com.pms.util.PMSRandom;

public class RandomInsert {
	private DBConnectionMgr pool;
		
	public RandomInsert() {
		pool = DBConnectionMgr.getInstance();
	}

	public void randomLogAdd(String key, String in_time, String out_time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			con = pool.getConnection();
			if(out_time != null) {
				sql = "insert into PMS_LOG(IDX,CNUM,IN_TIME,OUT_TIME) values(LOG_SEQ.nextval,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'))";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setString(2, in_time);
				pstmt.setString(3, out_time);
				System.out.println(key);
				System.out.println(in_time);
				System.out.println(out_time);
			}else {
				sql = "insert into PMS_LOG(IDX,CNUM,IN_TIME) values(LOG_SEQ.nextval,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'))";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setString(2, in_time);
			}

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
}

