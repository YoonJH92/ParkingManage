package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
			
			sql.append("SELECT A.TIME, A.IN_MONTH,A.IN_NOMAL,B.OUT_MONTH,B.OUT_NOMAL,b.total,C.COUNT,C.MONTH_PAY  FROM ") ;
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
			sql.append(" ) B ON A.TIME = B.TIME LEFT JOIN") ;
			sql.append("(SELECT TO_CHAR(SDATE,'yyyy-mm-dd hh24') TIME, COUNT(*) COUNT, SUM(MONTH_PAY) MONTH_PAY") ;
			sql.append("	FROM PMS_MONTH_MEMBER GROUP BY TO_CHAR(SDATE,'yyyy-mm-dd hh24')") ;
			sql.append(" ) C ON A.TIME = C.TIME") ;
			sql.append("     WHERE SUBSTR(A.TIME,0,10)='"+startForm+"' ORDER BY A.TIME") ;
			System.out.println(sql.toString());

			
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
				list.setMonthCount(rs.getInt(7));
				list.setMonthPay(rs.getInt(8));
				arr.add(list);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			pool.freeConnection(con,pstmt,rs);
		}
		
		return arr;

	}
	
	public ArrayList<StatisticsDTO> timeList(){
		
		ArrayList<StatisticsDTO> arr = new ArrayList<StatisticsDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = null;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ();
		String startForm = mSimpleDateFormat.format ( currentTime );
		
		try {
			
			con = pool.getConnection();
			sql = new StringBuffer();
			
			sql.append("SELECT A.TIME, A.IN_MONTH,A.IN_NOMAL,B.OUT_MONTH,B.OUT_NOMAL,b.total,C.COUNT,C.MONTH_PAY  FROM ") ;
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
			sql.append(" ) B ON A.TIME = B.TIME LEFT JOIN") ;
			sql.append("(SELECT TO_CHAR(SDATE,'yyyy-mm-dd hh24') TIME, COUNT(*) COUNT, SUM(MONTH_PAY) MONTH_PAY") ;
			sql.append("	FROM PMS_MONTH_MEMBER GROUP BY TO_CHAR(SDATE,'yyyy-mm-dd hh24')") ;
			sql.append(" ) C ON A.TIME = C.TIME") ;
			sql.append("     WHERE SUBSTR(A.TIME,0,10)='"+startForm+"' ORDER BY A.TIME") ;
			System.out.println(sql.toString());

			
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
				list.setMonthCount(rs.getInt(7));
				list.setMonthPay(rs.getInt(8));
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
