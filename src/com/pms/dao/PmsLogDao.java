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
				dto.setIn_time(rs.getTimestamp("in_time"));
				dto.setOut_time(rs.getTimestamp("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCp_num(rs.getInt("cp_num"));
				dto.setSale_num(rs.getInt("sale_num"));
				dto.setTotal_pay(rs.getInt("total_pay"));
				dto.setMonth_num(rs.getInt("month_num"));
				dto.setC_img(rs.getString("c_img"));
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
