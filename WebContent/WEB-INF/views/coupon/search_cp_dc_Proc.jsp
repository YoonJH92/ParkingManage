<%@page import="com.pms.dto.PmsDiscountDto"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.pms.dto.PmsCouponDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pms.dao.PmsC_D_Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String condition = request.getParameter("condition");
String value = request.getParameter("value");
int align = Integer.parseInt(request.getParameter("align"));
String c_d = request.getParameter("c_d");
PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
JSONArray jarr = new JSONArray();
if(c_d.equals("쿠폰"))
{
	ArrayList<PmsCouponDto> dto = dao.SearchCoupon(condition, value, align);
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
	ArrayList<PmsDiscountDto> dto = dao.SearchDiscount(condition, value, align);
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