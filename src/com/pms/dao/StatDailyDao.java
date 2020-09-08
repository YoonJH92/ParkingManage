package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pms.dto.PmsDto;
import com.pms.util.DBConnectionMgr;

public class StatDailyDao {
	
	private static SettingDAO instance;

	public static SettingDAO getInstance() {
		if (instance == null) {
			instance = new SettingDAO();
		}
		return instance;
	}
	
	private DBConnectionMgr pool;
	
	public StatDailyDao () {
		pool = DBConnectionMgr.getInstance();
	}
	
	public ArrayList<PmsDto> timeList(String startForm){
		
		ArrayList<PmsDto> list = new ArrayList<PmsDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "SELECT A.TIME, A.IN_MONTH,A.IN_NOMAL,B.OUT_MONTH,B.OUT_NOMAL,b.total_pay,b.total  FROM \r\n" + 
					"    (SELECT TO_CHAR(IN_TIME,'yyyy-mm-dd hh24') TIME,\r\n" + 
					"        COUNT(CASE WHEN MONTH_NUM != 0 THEN 1 END) IN_MONTH,\r\n" + 
					"        COUNT(CASE WHEN MONTH_NUM = 0 THEN 1 END) IN_NOMAL \r\n" + 
					"        FROM PMS_LOG GROUP BY TO_CHAR(IN_TIME,'yyyy-mm-dd hh24') \r\n" + 
					"    ) A LEFT JOIN \r\n" + 
					"    (SELECT TO_CHAR(OUT_TIME,'yyyy-mm-dd hh24') TIME,\r\n" + 
					"        COUNT(CASE WHEN MONTH_NUM != 0 THEN 1 END) OUT_MONTH,\r\n" + 
					"        COUNT(CASE WHEN MONTH_NUM = 0 THEN 1 END) OUT_NOMAL ,\r\n" + 
					"        sum(pms_log.total_pay) total\r\n" + 
					"        \r\n" + 
					"        FROM PMS_LOG GROUP BY TO_CHAR(OUT_TIME,'yyyy-mm-dd hh24') \r\n" + 
					"    ) B ON A.TIME = B.TIME WHERE SUBSTR(A.TIME,0,10)='"+startForm+"' ORDER BY A.TIME;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PmsDto dto = new PmsDto();
				
			}
		} catch (Exception e) {
			
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return null;

	}
	
}
