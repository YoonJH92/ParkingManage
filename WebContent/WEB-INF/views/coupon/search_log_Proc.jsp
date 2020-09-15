<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.pms.dao.PmsC_D_Dao"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.pms.dto.Pms_Coupon_Log_Dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
JSONArray jarr = new JSONArray();

String l_condition = request.getParameter("l_condition");
String l_value = request.getParameter("l_value");
int l_align = Integer.parseInt(request.getParameter("l_align"));
int startidx = Integer.parseInt(request.getParameter("startidx"));

ArrayList<Pms_Coupon_Log_Dto> dto = dao.SearchCouponLog(l_condition, l_value, l_align, startidx);
startidx = dto.get(0).getIDX();

JSONObject obj = new JSONObject();
obj.put("STARTIDX", startidx);
obj.put("TOTAL",dao.SearchCouponLogSub().size());
jarr.add(obj); 

for (Pms_Coupon_Log_Dto l : dto) {
	obj = new JSONObject();
	obj.put("CPNUM", l.getCPNUM());
	obj.put("CNUM", l.getCNUM());
	obj.put("CPCODE", l.getCPCODE());
	obj.put("IDX", l.getIDX());
	obj.put("USED", l.isUSED());
	obj.put("VALIDITY", l.getVALIDITY());
	jarr.add(obj);
}
System.out.println(startidx);
out.println(jarr.toString());
%>