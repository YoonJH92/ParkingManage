<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.pms.dto.PmsCouponDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pms.dao.PmsC_D_Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
String condition = request.getParameter("condition");
String value = request.getParameter("value");
int align = Integer.parseInt(request.getParameter("align"));
String c_d = request.getParameter("c_d");
System.out.println(c_d);
PmsC_D_Dao dao = PmsC_D_Dao.getInstance();

ArrayList<PmsCouponDto> dto = dao.SearchCoupon(condition, value, align);
JSONArray jarr = new JSONArray();
for (PmsCouponDto d : dto) {
	JSONObject obj = new JSONObject();
	obj.put("CPNUM", d.getCPNUM());
	obj.put("CPNAME", d.getCPNAME());
	obj.put("USE_DATE", d.getUSE_DATE());
	obj.put("PURPOSE", d.getPURPOSE());
	obj.put("DISCOUNT", d.getDISCOUNT());
	jarr.add(obj);
}
out.println(jarr.toString());
%>