package com.pms.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pms.dto.PmsDto;
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
	private static final String SAVEFOLDER="/";
	private static final String ENCTYPE="utf-8";
	private int sizeLimit =300*200*15;

	public PmsLogDao() {
		pool = DBConnectionMgr.getInstance();
	}
//	실시간 현황 조회 
	public ArrayList<PmsDto> viewList(){
    	Connection con = null;
    	Statement st= null;
    	ResultSet rs= null;
    	ArrayList<PmsDto> arr = new ArrayList<PmsDto>();
  
    	try {
			con = pool.getConnection();			
			String sql = " select * from pms_log ";
			st= con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PmsDto dto=new PmsDto();
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
	
//	차량 이미지 수정 
	public int imgUpdate(HttpServletRequest req) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="";
		int result=0;
		try {
			con=pool.getConnection();
			MultipartRequest multi=new MultipartRequest(req, SAVEFOLDER,sizeLimit, ENCTYPE,
					new DefaultFileRenamePolicy());//중복파일명 피하기 위해서 new~
			String filename=null;
			if(multi.getFilesystemName("fileName")!=null){
				filename=multi.getFilesystemName("fileName");
			}
			sql=" update pms_log set pmslog =? where idx = ? ";
			ps=con.prepareStatement(sql);
			if(rs.next()) {
			ps.setString(1, multi.getParameter("fileName"));
			ps.setInt(2, Integer.parseInt(multi.getParameter("idx")));
			ps.executeQuery();
			result=1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.freeConnection(con, ps);
		}
		
		return result;
}
	
//	이미지 출력 
	
	
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

