<%@page import="com.pms.dto.PmsDiscountDto"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.pms.dto.PmsCouponDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pms.dao.PmsC_D_Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String c_d = request.getParameter("c_d");
PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
JSONArray jarr = new JSONArray();

if(c_d.equals("쿠폰"))
{
	String c_condition = request.getParameter("c_condition");
	String c_value = request.getParameter("c_value");
	int c_align = Integer.parseInt(request.getParameter("c_align"));
	
	ArrayList<PmsCouponDto> dto = dao.SearchCoupon(c_condition, c_value, c_align);
	for (PmsCouponDto d : dto) {
		JSONObject obj = new JSONObject();
		obj.put("CPNUM", d.getCPNUM());
		obj.put("CPNAME", d.getCPNAME());
		obj.put("USE_DATE", d.getUSE_DATE());
		obj.put("PURPOSE", d.getPURPOSE());
		obj.put("DISCOUNT", d.getDISCOUNT());
		jarr.add(obj);
	}
}else if(c_d.equals("할인권"))
{
	String d_condition = request.getParameter("d_condition");
	String d_value = request.getParameter("d_value");
	int d_align = Integer.parseInt(request.getParameter("d_align"));
	
	ArrayList<PmsDiscountDto> dto = dao.SearchDiscount(d_condition, d_value, d_align);
	for (PmsDiscountDto d : dto) {
		JSONObject obj = new JSONObject();
		obj.put("COM_NUM", d.getCOM_NUM());
		obj.put("COMPANY", d.getCOMPANY());
		obj.put("USE_TIME", d.getUSE_TIME());
		obj.put("PURPOSE", d.getPURPOSE());
		jarr.add(obj);
	}
}
out.println(jarr.toString());
%>