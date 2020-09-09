package com.pms.command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pms.dao.PmsC_D_Dao;
import com.pms.dto.PmsCouponDto;

public class Search_Log_Command implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    return "coupon/search_log_Proc";
	}

}
