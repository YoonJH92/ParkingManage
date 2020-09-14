package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.poi.ss.formula.ptg.Ptg;

import com.pms.dto.PmsCouponDto;
import com.pms.dto.PmsDiscountDto;
import com.pms.dto.SettingDTO;
import com.pms.dto.memberManageDTO;
import com.pms.util.DBConnectionMgr;
import com.pms.util.PMSRandom2;

public class RandomInsert2 {
	private DBConnectionMgr pool;

	public RandomInsert2() {
		pool = DBConnectionMgr.getInstance();
	}

	public ArrayList<PmsCouponDto> SearchCoupon() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PmsCouponDto> arr = new ArrayList<PmsCouponDto>();
		try {
			con = pool.getConnection();
			sql = "select * from PMS_COUPON ORDER BY CPNUM ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	
	public ArrayList<PmsDiscountDto> SearchDiscount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PmsDiscountDto> arr = new ArrayList<PmsDiscountDto>();
		try {
			con = pool.getConnection();
			sql = "select * from PMS_Discount_manage ORDER BY COM_NUM ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public void randomLogAdd(String key, String in_time, String out_time, int num) {
		Random rd = new Random();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String month_id = null;
		PmsLogDao dao = PmsLogDao.getInstance();
		
//		ArrayList<PmsCouponDto> cdto = SearchCoupon();
		ArrayList<PmsDiscountDto> ddto = SearchDiscount();
		int percent = rd.nextInt(100);
//		PmsCouponDto coupon;
		PmsDiscountDto discount;
			
		
		try {
			con = pool.getConnection();
			if (out_time != null) {
				sql = "insert into PMS_LOG(IDX,CNUM,IN_TIME,OUT_TIME,CP_NUM,SALE_NUM,MONTH_NUM,pay,total_pay) values(LOG_SEQ.nextval,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), ? , ? , ? , ? , ? )";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setString(2, in_time);
				pstmt.setString(3, out_time);
				if(percent <= 50) {
//					coupon = cdto.get(rd.nextInt(cdto.size()));
					discount = ddto.get(rd.nextInt(ddto.size()));
//					pstmt.setInt(4, coupon.getCPNUM());
					pstmt.setNull(4, Types.INTEGER);
					pstmt.setInt(5, discount.getCOM_NUM());
				
				}else {
					pstmt.setNull(4, Types.INTEGER);
					pstmt.setNull(5, Types.INTEGER);	
				}
				pstmt.setInt(6, num);
				if (num == 0) {
					pstmt.setInt(7, dao.fare2(in_time, out_time));
					pstmt.setInt(8, dao.fare2(in_time, out_time));
				} else {
					pstmt.setInt(7, 0);
					pstmt.setInt(8, 0);
				}

			} else {
				discount = ddto.get(rd.nextInt(ddto.size()));

				sql = "insert into PMS_LOG(IDX,CNUM,IN_TIME,CP_NUM,SALE_NUM,MONTH_NUM) values(LOG_SEQ.nextval,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);
				pstmt.setString(2, in_time);
				pstmt.setNull(3, Types.INTEGER);
				pstmt.setInt(4, discount.getCOM_NUM());
				pstmt.setInt(5, num);

			}
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	public void randomMemberAdd(String key, String toDate, String stopDate, String name, String addr, String phone,
			String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			int pay = 0;
			// 주차장 세팅
			SettingDAO setDao = SettingDAO.getInstance();
			SettingDTO set = setDao.settItem();
			// 객체 생성 후 값 저장

			if (type.equals("일반"))
				pay = set.getMonth_fare();
			sql = "insert into PMS_MONTH_MEMBER(IDX,JDATE,SDATE,EDATE,CARN,NAME,EMAIL,PHONE,TYPE,MONTH_PAY) values(MONTH_MEMBER_SEQ.nextval,SYSDATE,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, toDate);
			pstmt.setString(2, stopDate);
			pstmt.setString(3, key);
			pstmt.setString(4, name);
			pstmt.setString(5, addr);
			pstmt.setString(6, phone);
			pstmt.setString(7, type);
			pstmt.setInt(8, pay);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	public int monthNum(String key, String in_time, String out_time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT * FROM PMS_MONTH_MEMBER WHERE SDATE <= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND EDATE >= TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND CARN = ?";
			String sql1 = "SELECT * FROM PMS_MONTH_MEMBER WHERE SDATE <= TO_DATE('" + in_time
					+ "','YYYY-MM-DD HH24:MI:SS') AND EDATE >= TO_DATE('" + out_time
					+ "','YYYY-MM-DD HH24:MI:SS') AND CARN = '" + key + "'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, in_time);
			pstmt.setString(2, out_time);
			pstmt.setString(3, key);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("idx");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return num;
	}
}
