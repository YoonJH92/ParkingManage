package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.formula.ptg.Ptg;

import com.pms.dto.PmsCouponDto;
import com.pms.dto.PmsDiscountDto;
import com.pms.dto.SettingDTO;
import com.pms.dto.memberManageDTO;
import com.pms.util.DBConnectionMgr;
import com.pms.util.PMSRandom;

public class RandomInsert {
	private DBConnectionMgr pool;
	Random rd = new Random();

	public RandomInsert() {
		pool = DBConnectionMgr.getInstance();
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

	public void randomLogAdd(String key, String in_time, String out_time, int num, int cnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String month_id = null;
		PmsLogDao dao = PmsLogDao.getInstance();
		int percent = rd.nextInt(100);
		
		ArrayList<PmsDiscountDto> ddto = SearchDiscount();
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
					discount = ddto.get(rd.nextInt(ddto.size()));
					pstmt.setInt(4, cnum);
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
	
	public PmsCouponDto couponNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<PmsCouponDto> arr = new ArrayList<PmsCouponDto>();
		PmsCouponDto dto = null;
		
		try {
			con = pool.getConnection();
			sql = "select * from pms_coupon";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new PmsCouponDto();
				dto.setCPNUM(rs.getInt("cpnum"));
				dto.setCPNAME(rs.getString("cpname"));
				dto.setDISCOUNT(rs.getInt("discount"));
				dto.setPURPOSE(rs.getString("purpose"));
				dto.setUSE_DATE(rs.getInt("use_date"));
				arr.add(dto);
			}
			Collections.shuffle(arr);
			dto = arr.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return dto;
	}
	
	public int randomCoupon_Log(String key) {
		int percent = rd.nextInt(100);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		PmsCouponDto dto = couponNum();
		int num = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.DAY_OF_MONTH, dto.getUSE_DATE());

		
		try {
			con = pool.getConnection();
			sql = "insert into PMS_COUPON_LOG(IDX,CPNUM,CPCODE,VALIDITY,USED,CNUM) values(COUPON_LOG_SEQ.nextval,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCPNUM());
			pstmt.setString(2, PMSRandom.randomCouponCode(15).toString());
			pstmt.setString(3, format.format(cal.getTime()));
			if(percent <= 50) {
				pstmt.setString(4, "0");
			}else {
				pstmt.setString(4, "1");
			}
			pstmt.setString(5, key);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		try {
			con = pool.getConnection();
			sql = "select idx from PMS_COUPON_LOG where cnum ='"+key+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				num = rs.getInt("idx");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return num;
	}
}
