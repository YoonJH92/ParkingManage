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
int s_align1 = Integer.parseInt(request.getParameter("s_align1"));
String s_align2 = request.getParameter("s_align2");
String s_date = request.getParameter("s_date");
String s_startForm = request.getParameter("s_startForm");
String s_endForm = request.getParameter("s_endForm");
int p_startidx = Integer.parseInt(request.getParameter("p_startidx"));


ArrayList<memberManageDTO> dto = dao.SearchMember(s_condition, s_value, s_align1, s_align2, s_date, s_startForm, s_endForm, p_startidx);

JSONObject obj = new JSONObject();
obj.put("TOTAL",dao.SearchMemberSub(s_condition, s_value, s_align1, s_align2, s_date, s_startForm, s_endForm)); 
jarr.add(obj);  

for (memberManageDTO m : dto) {
	obj = new JSONObject();
	obj.put("idx", m.getIdx());
	obj.put("name", m.getName());
	obj.put("regdate", m.getRegDate().toString());
	obj.put("startdate", m.getStartDate().toString());
	obj.put("stopdate", m.getStopDate().toString());
	obj.put("pay", m.getPay());
	obj.put("cnum", m.getCNUM());
	obj.put("email", m.getEmail());
	obj.put("phone", m.getPhone());
	jarr.add(obj);
}
out.println(jarr.toString());
%>