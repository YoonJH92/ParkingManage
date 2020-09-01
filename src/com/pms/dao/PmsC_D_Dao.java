package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pms.dto.PmsCouponDto;
import com.pms.dto.PmsDiscountDto;
import com.pms.util.DBConnectionMgr;

public class PmsC_D_Dao {
//---------------------------------------------------------
	private static PmsC_D_Dao instance = null;

	public static PmsC_D_Dao getInstance() {
		if (instance == null) {
			instance = new PmsC_D_Dao();
		}
		return instance;
	}

	private DBConnectionMgr pool;

	private PmsC_D_Dao() {
		pool = DBConnectionMgr.getInstance();
	}
//---------------------------------------------------------

	public void NewCoupon(PmsCouponDto coupon) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = pool.getConnection();
			sql = "insert into PMS_COUPON(CPNUM,USE_DATE,CPNAME,PURPOSE,DISCOUNT) values(COUPON_SEQ.nextval,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, coupon.getUSE_DATE());
			pstmt.setString(2, coupon.getCPNAME());
			pstmt.setString(3, coupon.getPURPOSE());
			pstmt.setInt(4, coupon.getDISCOUNT());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	public void NewDiscount(PmsDiscountDto discount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = pool.getConnection();
			sql = "insert into PMS_DISCOUNT_MANAGE(COM_NUM,COMPANY,PURPOSE,USE_TIME) values(DISCOUNT_MANAGE_SEQ.nextval,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, discount.getCOMPANY());
			pstmt.setString(2, discount.getPURPOSE());
			pstmt.setInt(3, discount.getUSE_TIME());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	public ArrayList<PmsCouponDto> SearchCoupon(String condition, String value, int align) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PmsCouponDto> arr = new ArrayList<PmsCouponDto>();
		System.out.println("check");
		try {
			con = pool.getConnection();
			if(value.isEmpty()) {
				sql= "select * from PMS_COUPON where rownum <="+align+" ORDER BY CPNUM ASC";
			}
			else {
				sql = "select * from PMS_COUPON where "+condition+" = "+"'"+value+"' ORDER BY CPNUM ASC";
			}
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs =pstmt.executeQuery();
			while(rs.next()) {
				PmsCouponDto dto = new PmsCouponDto(); 
				dto.setCPNUM(rs.getInt("cpnum"));
				dto.setCPNAME(rs.getString("cpname"));
				dto.setUSE_DATE(rs.getInt("use_date"));
				dto.setPURPOSE(rs.getString("purpose"));
				dto.setDISCOUNT(rs.getInt("discount"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
	public ArrayList<PmsDiscountDto> SearchDiscount(String condition, String value, int align) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PmsDiscountDto> arr = new ArrayList<PmsDiscountDto>();
		System.out.println("check");

		try {
			con = pool.getConnection();
			if(value.isEmpty()) {
				sql= "select * from PMS_Discount_manage where rownum <="+align+" ORDER BY COM_NUM ASC";
			}
			else {
				sql = "select * from PMS_Discount_manage where "+condition+" = "+"'"+value+"' ORDER BY COM_NUM ASC";
			}
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs =pstmt.executeQuery();
			while(rs.next()) {
				PmsDiscountDto dto = new PmsDiscountDto(); 
				dto.setCOM_NUM(rs.getInt("COM_NUM"));
				dto.setCOMPANY(rs.getString("COMPANY"));
				dto.setUSE_TIME(rs.getInt("use_time"));
				dto.setPURPOSE(rs.getString("purpose"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
	public void Delete(int num, String c_d) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			if(c_d.equals("쿠폰")) {
				sql = "delete from PMS_COUPON where CPNUM = "+num;
			}else if(c_d.equals("할인권")) {
				sql = "delete from PMS_DISCOUNT_MANAGE where COM_NUM = "+num;
			}
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
}
