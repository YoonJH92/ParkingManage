package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pms.dto.StatisticsDTO;
import com.pms.dto.memberManageDTO;
import com.pms.util.DBConnectionMgr;

public class StatDailyDAD implements Statistics{
	private DBConnectionMgr pool;
	
	public StatDailyDAD() {
		pool = DBConnectionMgr.getInstance();
	}
	
	private static StatDailyDAD instance;
	public static StatDailyDAD getInstance() {
		if (instance == null) {
			instance = new StatDailyDAD();
		}
		return instance;
	}
	
	@Override
	public ArrayList<StatisticsDTO> ListView() {
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = null;
		ResultSet rs = null;
		ArrayList<StatisticsDTO> arr = new ArrayList<>();
		
		try {
			con = pool.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT A.TIME, A.IN_MONTH,A.IN_NOMAL,B.OUT_MONTH,B.OUT_NOMAL,B.PAY,B.TOTAL_PAY,C.COUNT,C.MONTH_PAY FROM ");
			sql.append(" (SELECT TO_CHAR(IN_TIME,'yyyy-mm-dd') TIME, ");
			sql.append(" COUNT(CASE WHEN MONTH_NUM != 0 THEN 1 END) IN_MONTH, ");
			sql.append(" COUNT(CASE WHEN MONTH_NUM = 0 THEN 1 END) IN_NOMAL ");
			sql.append(" FROM PMS_LOG GROUP BY TO_CHAR(IN_TIME,'yyyy-mm-dd') ");
			sql.append(" ) A LEFT JOIN") ;
			sql.append(" (SELECT TO_CHAR(OUT_TIME,'yyyy-mm-dd') TIME, ");
			sql.append(" COUNT(CASE WHEN MONTH_NUM != 0 THEN 1 END) OUT_MONTH, ");
			sql.append(" COUNT(CASE WHEN MONTH_NUM = 0 THEN 1 END) OUT_NOMAL, ");
			sql.append(" SUM(PAY) PAY, SUM(TOTAL_PAY) TOTAL_PAY ");
			sql.append(" FROM PMS_LOG GROUP BY TO_CHAR(OUT_TIME,'yyyy-mm-dd') ");
			sql.append(" ) B ON A.TIME = B.TIME LEFT JOIN ");
			sql.append(" (SELECT TO_CHAR(SDATE,'yyyy-mm-dd') TIME, COUNT(*) COUNT, SUM(MONTH_PAY) MONTH_PAY ");
			sql.append(" FROM PMS_MONTH_MEMBER GROUP BY TO_CHAR(SDATE,'yyyy-mm-dd') ");
			sql.append(" ) C ON A.TIME = C.TIME");
			sql.append(" WHERE SUBSTR(A.TIME,0,7)='2020-08' ORDER BY A.TIME");
			
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StatisticsDTO list = new StatisticsDTO();
				list.setTime(rs.getString(1));
				list.setInMonth(rs.getInt(2));
				list.setInNomal(rs.getInt(3));
				list.setOutMonth(rs.getInt(4));
				list.setOutNomal(rs.getInt(5));
				list.setPay(rs.getInt(6));
				list.setTotalPay(rs.getInt(7));
				list.setMonthCount(rs.getInt(8));
				list.setMonthPay(rs.getInt(9));
				arr.add(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
}
