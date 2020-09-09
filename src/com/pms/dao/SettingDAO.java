package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pms.dto.SettingDTO;
import com.pms.util.DBConnectionMgr;
import com.sun.istack.internal.Pool;

public class SettingDAO {

	private static SettingDAO instance;

	public static SettingDAO getInstance() {
		if (instance == null) {
			instance = new SettingDAO();
		}
		return instance;
	}
	
	private DBConnectionMgr pool;
	
	public SettingDAO () {
		pool = DBConnectionMgr.getInstance();
	}
	
	public void updateSetting(SettingDTO sdto) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = pool.getConnection();
			String sql = "update pms_setting set count=?,dtime=?,fare=?,otime=?,ofare=?,month_fare=? ";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, sdto.getCount());
			pstmt.setInt(2, sdto.getDtime());
			pstmt.setInt(3, sdto.getFare());
			pstmt.setInt(4, sdto.getOtime());
			pstmt.setInt(5, sdto.getOfare());
			pstmt.setInt(6, sdto.getMonth_fare());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt);
		}

	}

	public SettingDTO settItem() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SettingDTO temp = null;
		try {
			con = pool.getConnection();
			String sql = "select * from pms_setting";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				temp = new SettingDTO();
				temp.setIdx(rs.getInt("idx"));
				temp.setCount(rs.getInt("count"));
				temp.setDtime(rs.getInt("dtime"));
				temp.setFare(rs.getInt("fare"));
				temp.setOtime(rs.getInt("otime"));
				temp.setOfare(rs.getInt("ofare"));
				temp.setMonth_fare(rs.getInt("month_fare"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return temp;

	}

}
