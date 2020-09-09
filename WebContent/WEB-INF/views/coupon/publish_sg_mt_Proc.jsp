<%@page import="com.pms.dto.memberManageDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.pms.dao.PmsC_D_Dao"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
JSONArray jarr = new JSONArray();

String s_condition = request.getParameter("s_condition");
String s_value = request.getParameter("s_value");
int s_align = Integer.parseInt(request.getParameter("s_align"));

ArrayList<memberManageDTO> dto = dao.SearchMember(s_condition, s_value, s_align);

for (memberManageDTO l : dto) {
	JSONObject obj = new JSONObject();
	obj.put("CPNUM", l.getCPNUM());
	obj.put("CNUM", l.getCNUM());
	obj.put("CPCODE", l.getCPCODE());
	obj.put("IDX", l.getIDX());
	obj.put("USED", l.isUSED());
	obj.put("VALIDITY", l.getVALIDITY().toString());
	jarr.add(obj);
}
System.out.println(jarr.toString());
out.println(jarr.toString());
%>