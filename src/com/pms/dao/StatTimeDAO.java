package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.pms.dto.StatisticsDTO;
import com.pms.util.DBConnectionMgr;

public class StatTimeDAO {
	
	private static StatTimeDAO instance;

	public static StatTimeDAO getInstance() {
		if (instance == null) {
			instance = new StatTimeDAO();
		}
		return instance;
	}
	
	private DBConnectionMgr pool;
	
	public StatTimeDAO() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public ArrayList<StatisticsDTO> timeList(String startForm){
		
		ArrayList<StatisticsDTO> arr = new ArrayList<StatisticsDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = null;
		
		try {
			
			con = pool.getConnection();
			sql = new StringBuffer();
			
			sql.append("SELECT A.TIME, A.IN_MONTH,A.IN_NOMAL,B.OUT_MONTH,B.OUT_NOMAL,b.total FROM ") ;
			sql.append("  (SELECT TO_CHAR(IN_TIME,'yyyy-mm-dd hh24') TIME,") ;
			sql.append("        COUNT(CASE WHEN MONTH_NUM != 0 THEN 1 END) IN_MONTH,") ;
			sql.append("        COUNT(CASE WHEN MONTH_NUM = 0 THEN 1 END) IN_NOMAL") ;
			sql.append("        FROM PMS_LOG GROUP BY TO_CHAR(IN_TIME,'yyyy-mm-dd hh24') ") ;
			sql.append("    ) A LEFT JOIN ") ;
			sql.append("    (SELECT TO_CHAR(OUT_TIME,'yyyy-mm-dd hh24') TIME,") ;
			sql.append("     COUNT(CASE WHEN MONTH_NUM != 0 THEN 1 END) OUT_MONTH,") ;
			sql.append("     COUNT(CASE WHEN MONTH_NUM = 0 THEN 1 END) OUT_NOMAL,") ;
			sql.append("        sum(pms_log.total_pay) total ") ;
			sql.append("        FROM PMS_LOG GROUP BY TO_CHAR(OUT_TIME,'yyyy-mm-dd hh24') ") ;

			sql.append("    ) B ON A.TIME = B.TIME WHERE SUBSTR(A.TIME,0,10)='"+startForm+"' ORDER BY A.TIME") ;
			

			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StatisticsDTO list = new StatisticsDTO();
				list.setTime(rs.getString(1));
				
				list.setInMonth(rs.getInt(2));
				list.setInNomal(rs.getInt(3));
				list.setOutMonth(rs.getInt(4));
				list.setOutNomal(rs.getInt(5));
				list.setTotalPay(rs.getInt(6));
				arr.add(list);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			pool.freeConnection(con,pstmt,rs);
		}
		
		return arr;

	}

}
