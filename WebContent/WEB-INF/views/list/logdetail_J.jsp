<%@page import="com.pms.dto.PmsPageDto"%>
<%@page import="com.pms.dto.PmsLogDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pms.dao.PmsLogDao"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%	int page_=1;	 
	request.setCharacterEncoding("utf-8");
	PmsLogDao dao=PmsLogDao.getInstance();
	PmsPageDto paging=new PmsPageDto();	//페이징
	JSONArray jarr = new JSONArray();
	String cnum=request.getParameter("cnum"); 
	String LDate=request.getParameter("LDate");	
	int displayRow=20; //기본 값 
	System.out.println("되고있긴하다.");

	String fDate=request.getParameter("FDate");	
	if(fDate==""){
		fDate="-1"; 
	}
	
	System.out.println(fDate);
	System.out.println(LDate);
	System.out.println(cnum);
	

	int count=dao.datailCount(fDate, LDate, cnum); 
	if(request.getParameter("page_")!=null) {
		page_=Integer.parseInt(request.getParameter("page_"));
		}
	 if(request.getParameter("dRs")!=null){		 
			displayRow=Integer.parseInt(request.getParameter("dRs"));
		 }			
	 paging.setPage(page_);
	 paging.setDisplayRow(displayRow);

	paging.setTotalCount(count);	
	System.out.println(count);

	ArrayList<PmsLogDto> arr = dao.viewDetail(paging, fDate, LDate, cnum);
	for (PmsLogDto dto : arr) {
		JSONObject obj = new JSONObject();
		obj.put("idx",dto.getIdx());
		obj.put("cnum", dto.getCnum());
		obj.put("in_time", dto.getInTime());
		obj.put("out_time", dto.getOutTime());
		obj.put("pay",dto.getCpNum());
		obj.put("cp_num",dto.getMonthNum());
		obj.put("sale_num",dto.getSaleNum());
		obj.put("pay", dto.getPay());
		obj.put("total_pay", dto.getTotalPay());
		obj.put("c_img", dto.getcImg());
		jarr.add(obj);
		} 
	out.println(jarr.toString());
	
	request.setAttribute("paging", paging);
	request.setAttribute("cnum", cnum);
	request.setAttribute("FDate", fDate);
	request.setAttribute("LDate", LDate);
	
%>


