package com.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pms.dto.ManagerBean;
import com.pms.dto.SettingDTO;
import com.pms.util.DBConnectionMgr;

public class ManagerDAO {

	private static ManagerDAO instance;

	public static ManagerDAO getInstance() {
		if (instance == null) {
			instance = new ManagerDAO();
		}
		return instance;
	}

	DBConnectionMgr pool = DBConnectionMgr.getInstance();

	public void insertManager(ManagerBean mbean) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = pool.getConnection();

			String sql = "insert into pms_admin values(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPass());
			pstmt.setString(3, mbean.getName());
			pstmt.setString(4, mbean.getEmail());
			pstmt.setString(5, mbean.getTel());

			pstmt.executeUpdate();

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

	}

	public int loginManager(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select admin_id,pwd from pms_admin where admin_id=?";
		String db_pwd;
		int re = -1;
		try {
			con = pool.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				db_pwd = rs.getString("PWD");
				if (db_pwd.equals(pwd)) {
					re = 1;
				} else {
					re = 0;
				}
			} else {
				re = -1;
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return re;

	}

	public String MdSearchId(ManagerBean idbean) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = "";
		String sql = "select admin_id from pms_admin where name=? and email=? and phone=?";

		try {
			con = pool.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idbean.getName());
			pstmt.setString(2, idbean.getEmail());
			pstmt.setString(3, idbean.getTel());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getString(1);
			} else {
				id = "없음";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		System.out.println(id);
		return id;

	}

	public String MdSearchPass(ManagerBean passbean) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pass = "";
		String sql = "select pwd from pms_admin where name=? and admin_id=? and email=? and phone=?";

		try {
			con = pool.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, passbean.getName());
			pstmt.setString(2, passbean.getId());
			pstmt.setString(3, passbean.getEmail());
			pstmt.setString(4, passbean.getTel());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pass = rs.getString(1);
			} else {
				pass = "없음";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return pass;

	}

	public void updateM(ManagerBean cbean) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = pool.getConnection();
			String sql = "update pms_admin set name=?, email=?,phone=?,pwd=? where admin_id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, cbean.getName());
			pstmt.setString(2, cbean.getEmail());
			pstmt.setString(3, cbean.getTel());
			pstmt.setString(4, cbean.getPass());
			pstmt.setString(5, cbean.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

	}

	public ManagerBean searchM(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ManagerBean mbean = new ManagerBean();
		try {
			con = pool.getConnection();
			String sql = "select * from pms_admin where admin_id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mbean.setName(rs.getString("name"));
				mbean.setEmail(rs.getString("email"));
				mbean.setTel(rs.getString("phone"));
				mbean.setPass(rs.getString("pwd"));
				mbean.setId(rs.getString("admin_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return mbean;

	}

}
