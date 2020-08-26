package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.pms.dto.PMSDto;
import com.pms.util.DBConnectionMgr;

public class PmsLogDao {
	
	private static PmsLogDao instance;
	public static PmsLogDao getInstance() {
		if (instance == null) {
			instance = new PmsLogDao();
	}
	return instance;
	}
	private DBConnectionMgr pool;
	
	public PmsLogDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	
	public ArrayList<PMSDto> viewList(){
    	Connection con = null;
    	Statement st= null;
    	ResultSet rs= null;
    	ArrayList<PMSDto> arr = new ArrayList<PMSDto>();
  
    	try {
			con = pool.getConnection();			
			String sql = "select * from pms_log ";
			st= con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PMSDto dto = new PMSDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getDate("in_time"));
				dto.setOutTime(rs.getDate("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
				arr.add(dto);
					}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			pool.freeConnection(con, st, rs);
			
		}
    	return arr;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
