package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pms.dto.PmsCouponDto;
import com.pms.dto.PmsDiscountDto;
import com.pms.dto.Pms_Coupon_Log_Dto;
import com.pms.dto.memberManageDTO;
import com.pms.util.DBConnectionMgr;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

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

	public ArrayList<PmsCouponDto> SearchCoupon(String condition, String value, int align, int idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PmsCouponDto> arr = new ArrayList<PmsCouponDto>();
		try {
			con = pool.getConnection();
			if (value.isEmpty()) {
				sql = "select rnum, t.* from (select s.*, row_number() over(order by cpnum) as rnum from PMS_COUPON s) t where rnum between "+idx+" AND "+ (idx+align-1);
			} else {
				if (condition.equals("use_date")) {
					value = value.replace("일", "");
				}
				if (condition.equals("discount")) {
					value = value.replace("원", "");
					value = value.replace(",", "");
				}
				sql = "select rnum, t.* from (select s.*, row_number() over(order by cpnum) as rnum from PMS_COUPON s where " + condition + " LIKE " + "'" + value + "%') t where rnum between " + idx + " AND "+ (idx+align-1);
			}
			System.out.println(sql);
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

	public ArrayList<PmsDiscountDto> SearchDiscount(String condition, String value, int align, int idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<PmsDiscountDto> arr = new ArrayList<PmsDiscountDto>();
		try {
			con = pool.getConnection();
			if (value.isEmpty()) {
				sql = "select rnum, t.* from (select s.*, row_number() over(order by com_num) as rnum from PMS_DISCOUNT_MANAGE s) t where rnum between "+idx+" AND "+ (idx+align-1);
			} else {
				if (condition.equals("use_time")) {
					value = value.replace("시간", "");
				}
				sql = "select rnum, t.* from (select s.*, row_number() over(order by com_num) as rnum from PMS_DISCOUNT_MANAGE s where " + condition + " LIKE " + "'" + value + "%') t where rnum between " + idx + " AND "+ (idx+align-1);
			}
			System.out.println(sql);
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

	public void Delete(int num, String c_d) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			if (c_d.equals("coupon")) {
				sql = "delete from PMS_COUPON where CPNUM = " + num;
			} else if (c_d.equals("discount")) {
				sql = "delete from PMS_DISCOUNT_MANAGE where COM_NUM = " + num;
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

	public void modifyCMember(int num, String c_d, String cpname, int date, String c_purpose, int discount) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update pms_coupon set use_date=" + date + ", cpname='" + cpname + "', purpose='" + c_purpose
					+ "', discount=" + discount + " where cpnum=" + num;
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	public void modifyDMember(int num, String c_d, String d_purpose, int time, String company) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "update pms_discount_manage set company='" + company + "', purpose='" + d_purpose + "', use_time="
					+ time + " where com_num=" + num;
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	public ArrayList<Pms_Coupon_Log_Dto> SearchCouponLog(String condition, String value, int align, int idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Pms_Coupon_Log_Dto> arr = new ArrayList<Pms_Coupon_Log_Dto>();
		
		try {
			con = pool.getConnection();
			
			if (value.isEmpty()) {
				sql = "select rnum, t.* from (select s.*, row_number() over(order by idx) as rnum from PMS_COUPON_LOG s) t where rnum between "+idx+" AND "+ (idx+align-1);
			} else if(condition.equals("cnum")){
				sql = "select rnum, t.* from (select s.*, row_number() over(order by idx) as rnum from PMS_COUPON_LOG s where " + condition + " LIKE " + "'" + value + "%') t where rnum between " + idx + " AND "+ (idx+align-1);
			}
			else if(condition.equals("cpnum")) {
				sql = "select rnum, t.* from (select s.*, row_number() over(order by idx) as rnum from PMS_COUPON_LOG s where " + condition + " = " + "'" + value +"') t where rnum between " + idx + " AND " + (idx+align-1);
			}
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Pms_Coupon_Log_Dto dto = new Pms_Coupon_Log_Dto();
				dto.setCNUM(rs.getString("cnum"));
				dto.setCPCODE(rs.getString("cpcode"));
				dto.setCPNUM(rs.getInt("cpnum"));
				dto.setIDX(rs.getInt("idx"));
				dto.setUSED(rs.getString("used"));
				dto.setVALIDITY(rs.getString("validity"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
	public int SearchCouponLogSub(String condition, String value, int align) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int total = 0;
		
		try {
			con = pool.getConnection();
			
			if (value.isEmpty()) {
				sql = "select count(rnum) from (select rownum rnum from PMS_COUPON_LOG ORDER BY rnum ASC )";
			} else if(condition.equals("cnum")){
				sql = "select count(rnum) from (select rownum rnum from PMS_COUPON_LOG where " + condition + " LIKE " + "'" + value + "%' ORDER BY rnum ASC )";
			}
			else if(condition.equals("cpnum")) {
				sql = "select count(rnum) from (select rownum rnum from PMS_COUPON_LOG where " + condition + " = " + "'" + value +"' ORDER BY rnum ASC)";
			}			
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("count(rnum)"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return total;
	}
	
	public int SearchCouponSub(String condition, String value, int align) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int total = 0;
		
		try {
			con = pool.getConnection();
			
			if (value.isEmpty()) {
				sql = "select count(rnum) from (select rownum rnum from PMS_COUPON ORDER BY rnum ASC )";
			} else {
				if (condition.equals("use_date")) {
					value = value.replace("일", "");
				}
				if (condition.equals("discount")) {
					value = value.replace("원", "");
					value = value.replace(",", "");
				}
				sql = "select count(rnum) from (select rownum rnum from PMS_COUPON where " + condition + " LIKE " + "'" + value + "%' ORDER BY rnum ASC )";
			}
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("count(rnum)"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return total;
	}
	
	public int SearchDiscountSub(String condition, String value, int align) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int total = 0;
		
		try {
			con = pool.getConnection();
			
			if (value.isEmpty()) {
				sql = "select count(rnum) from (select rownum rnum from PMS_DISCOUNT_MANAGE ORDER BY rnum ASC )";
			} else {
				if (condition.equals("use_time")) {
					value = value.replace("시간", "");
				}
				sql = "select count(rnum) from (select rownum rnum from PMS_DISCOUNT_MANAGE where " + condition + " LIKE " + "'" + value + "%' ORDER BY rnum ASC )";
			}	
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("count(rnum)"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return total;
	}
	
	public int SearchMemberSub(String condition, String value, int align1, String align2,
			String date, String startForm, String endForm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int total = 0;
		StringBuffer where = new StringBuffer();
		
		try {
			con = pool.getConnection();
			
			if (value.isEmpty() == false || startForm.isEmpty() == false || endForm.isEmpty() == false) {
				where.append("where ");
				if (value.isEmpty() == false) {
					where.append(condition + " LIKE '" + value + "%'");
				}
				if (startForm.isEmpty() == false || endForm.isEmpty() == false) {
					if(value.isEmpty() == false) {
						where.append("AND ");
					}
					where.append(date + " between ");
					if (startForm.isEmpty() == false && endForm.isEmpty() == false) {
						where.append("'" + startForm + "' AND '" + endForm + "' ");
					} else if (startForm.isEmpty() == false) {
						where.append("'" + startForm + "' AND SYSDATE ");
					} else if (endForm.isEmpty() == false) {
						where.append("'1900/01/01' AND '" + endForm + "' ");
					}
				}
			}

			sql = "select count(rnum) from (select row_number() over(order by "+ align2+ ") as rnum from PMS_MONTH_MEMBER s "+ where +") t";
			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("count(rnum)"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return total;
	}

	public ArrayList<memberManageDTO> SearchMember(String condition, String value, int align1, String align2,
			String date, String startForm, String endForm, int idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		StringBuffer where = new StringBuffer();
		ResultSet rs = null;
		ArrayList<memberManageDTO> arr = new ArrayList<memberManageDTO>();

		try {
			con = pool.getConnection();
			if (value.isEmpty() == false || startForm.isEmpty() == false || endForm.isEmpty() == false) {
				where.append("where ");
				if (value.isEmpty() == false) {
					where.append(condition + " LIKE '" + value + "%'");
				}
				if (startForm.isEmpty() == false || endForm.isEmpty() == false) {
					if(value.isEmpty() == false) {
						where.append("AND ");
					}
					where.append(date + " between ");
					if (startForm.isEmpty() == false && endForm.isEmpty() == false) {
						where.append("'" + startForm + "' AND '" + endForm + "' ");
					} else if (startForm.isEmpty() == false) {
						where.append("'" + startForm + "' AND SYSDATE ");
					} else if (endForm.isEmpty() == false) {
						where.append("'1900/01/01' AND '" + endForm + "' ");
					}
				}
			}

			sql = "select rnum, t.* from (select s.*, row_number() over(order by "+ align2+ ") as rnum from PMS_MONTH_MEMBER s "+ where +") t where rnum between "+idx+" AND "+ (idx+align1-1);
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				memberManageDTO mem = new memberManageDTO();
				mem.setIdx(rs.getInt("idx"));
				mem.setCNUM(rs.getString("CARN"));
				mem.setEmail(rs.getString("email"));
				mem.setName(rs.getString("name"));
				mem.setPay(rs.getInt("month_pay"));
				mem.setPhone(rs.getString("phone"));
				mem.setStartDate(rs.getString("sdate"));
				mem.setStopDate(rs.getString("edate"));
				mem.setType(rs.getString("type"));
				mem.setRegDate(rs.getString("jdate"));
				arr.add(mem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
	public void NewCoupon_Log(Pms_Coupon_Log_Dto Log) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = pool.getConnection();
			sql = "insert into PMS_COUPON_LOG(IDX,CPNUM,CPCODE,VALIDITY,USED,CNUM) values(COUPON_LOG_SEQ.nextval,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Log.getCPNUM());
			pstmt.setString(2, Log.getCPCODE());
			pstmt.setString(3, Log.getVALIDITY());
			pstmt.setString(4, Log.isUSED());
			pstmt.setString(5, Log.getCNUM());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

	public void SendSms(String name, String num, String cpname, String date, String discount, String purpose, String cpcode) {
		System.out.println(name+"님 안녕하세요. 반갑습니다.\n"
				+ "PMS 건물에서 쿠폰을 발급해드립니다.\n"
				+ "쿠폰명: "+cpname+"\n"
				+ "발급사유: "+purpose+"\n"
				+ "할인 금액: "+discount+"\n"
				+ "유효 기간: "+date+"까지\n"
				+ "쿠폰 코드: "+cpcode+"\n"
				+"감사합니다.");
		
		//		String api_key = "";
//		String api_secret = "";
//		Message coolsms = new Message(api_key, api_secret);
//		
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("to", num);
//		params.put("from", "");
//		params.put("type", "LMS");
//		params.put("text", name+"님 안녕하세요. 반갑습니다.\n"
//				+ "PMS 건물에서 쿠폰을 발급해드립니다.\n"
//				+ "쿠폰명: "+cpname+"\n"
//				+ "발급사유: "+purpose+"\n"
//				+ "할인 금액: "+discount+"\n"
//				+ "유효 기간: "+date+"까지\n"
//				+ "쿠폰 코드: "+cpcode+"\n"
//				+"감사합니다.");
//		params.put("app_version", "test app 1.2");
//
//		try {
//			JSONObject obj = (JSONObject) coolsms.send(params);
//			System.out.println(obj.toString());
//		} catch (CoolsmsException e) {
//			System.out.println(e.getMessage());
//			System.out.println(e.getCode());
//		}
	}
}
