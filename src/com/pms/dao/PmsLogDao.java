package com.pms.dao;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.Vector;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;
import com.pms.dto.SettingDTO;
import com.pms.paging.Pagination;
import com.pms.paging.Pagination2;
import com.pms.util.DBConnectionMgr;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class PmsLogDao {
	private static PmsLogDao instance;

	public static PmsLogDao getInstance() {
		if (instance == null) {
			instance = new PmsLogDao();
		}
		return instance;
	}
	private DBConnectionMgr pool;
	public PmsLogDao() {
		pool = DBConnectionMgr.getInstance();
	}
	// 실시간 조회
	public ArrayList<PmsLogDto> viewList(Pagination2 p) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql=null;
		ArrayList<PmsLogDto> arr = new ArrayList<PmsLogDto>();	
		try {
			con = pool.getConnection();
			sql=new StringBuffer();
			sql.append(" SELECT * FROM (" );
			sql.append(" SELECT A.*, ROWNUM AS RNUM FROM  ");
			sql.append(" (select * from PMS_log where out_time is null order by in_time desc ) A ");
			sql.append(" WHERE ROWNUM <= ? ");
			sql.append(" ) WHERE RNUM > ? ");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, p.getCurPage() * p.getPageSize()); 
			ps.setInt(2, (p.getCurPage()-1) * p.getPageSize());		
			rs = ps.executeQuery();
			while (rs.next()) {
				PmsLogDto dto = new PmsLogDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getString("in_time"));
				dto.setOutTime(rs.getString("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
				arr.add(dto);
			}
				
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return arr;
	}
	public int getlistCount() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) as count from pms_log where out_time is null ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			pool.freeConnection(con, ps, rs);
		}
		return count;
	}
	public ArrayList<PmsLogDto> viewList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PmsLogDto> arr = new ArrayList<PmsLogDto>();
		try {
			con = pool.getConnection();
			String sql = " SELECT * FROM pms_log where out_time is null order by in_time desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				PmsLogDto dto = new PmsLogDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getString("in_time"));
				dto.setOutTime(rs.getString("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return arr;
	}
	public PmsLogDto view(int idx) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PmsLogDto dto = new PmsLogDto();
		try {
			con = pool.getConnection();
			String sql = " SELECT * FROM pms_log where out_time is null and idx = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getString("in_time"));
				dto.setOutTime(rs.getString("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return dto;
	}
	// 실시간 차량 사진
		public ArrayList<String> imgDetailUpdate(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String savepath = req.getServletContext().getRealPath("/img/");
		// 실제파일 경로 경로
		int maxSize = 1024 * 1024 * 50;// 파일크기 제한
		String encoding = "utf-8";
		System.out.println(savepath);
		File Folder = new File(savepath);
		
		String url="";
		ArrayList<String>arr=new ArrayList<String>();
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdirs(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		try {
			con = pool.getConnection();
			MultipartRequest multi = new MultipartRequest(req, savepath, maxSize, encoding,
					new DefaultFileRenamePolicy());
			Enumeration fileNames = multi.getFileNames();
			// DefaultFileRenamePolicy() -> 중복파일명을 위한 매개변수
			boolean save = true; // 파일 저장 성공
			String fileInput = "";// 폼으로 받아온 filename
			String fileName = "";// 저장된 파일 이름
			String originFileName = "";// 원본 파일 이름
			String type = "";// 저장된 파일 종류
			File fileobj = null;// 저장된 파일 객체
			String fileExtend = ""; // jpg,png,gif 등 확장자
			String fileSize = ""; // 저장된 파일 사이즈
			String newFileName = "pms_" + System.currentTimeMillis() + fileName;// 저장된 파일을 바꿀 이름
			System.out.println("newFileName" + newFileName);
			while (fileNames.hasMoreElements()) { // 있으면
				fileInput = (String) fileNames.nextElement();// 폼에서 받아온 요소
				
				String cnum=multi.getParameter("cnum")==null?"":multi.getParameter("cnum").trim();
				String FDATE=multi.getParameter("FDate")==null?"":multi.getParameter("FDate").trim();
				String LDATE=multi.getParameter("LDate")==null?"":multi.getParameter("LDate").trim();
				String Ipage=multi.getParameter("ipage")==null?"1":multi.getParameter("ipage").trim();
				String idrw=multi.getParameter("idrw")==null?"20":multi.getParameter("idrw").trim();
				System.out.println("cnum"+cnum);		
				arr.add("FDate="+FDATE);
				arr.add("&LDate="+LDATE+"&cnum=");
				arr.add(cnum);
				arr.add("&dRs="+idrw+"&p="+Ipage);
				
				
				fileName = multi.getFilesystemName(fileInput);
				if (fileName != null) {
					type = multi.getContentType(fileInput);
					fileobj = multi.getFile(fileInput);
					originFileName = multi.getOriginalFileName(fileInput);
					fileExtend = fileName.substring(fileName.lastIndexOf(".") + 1);// "file1.jpg"라면 jpg 반환
					fileSize = String.valueOf(fileobj.length());// file도 결국 문자열이므로 length()로 반환
					String[] splitType = type.split("/");
					if (!splitType[0].equals("image")) {
						save = false;
						fileobj.delete(); // 저장된 파일 객체로 삭제
						break;
					} else {// 만약 이미지 파일이면 저장 파일의 이름 바꾼다.
						newFileName += "." + fileExtend;
						fileobj.renameTo(new File(savepath + "\\" + newFileName));
					}
				}
			}
			if (save) {
				sql = " update pms_log set c_img = ? where idx = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newFileName);
				pstmt.setString(2, multi.getParameter("idx"));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	public HashMap<String, Integer> logTotalResult() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		try {
			con = pool.getConnection();
			sql = " select count(*), count(case when month_num > 0 then 1 end),count(case when month_num = 0 then 1 end) from pms_log where out_time is Null ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				result.put("allCum", rs.getInt(1));
				result.put("mNum", rs.getInt(2));
				result.put("gNum", rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	public int fare2(String intime, String Outtime) {
		SettingDAO settingDao = SettingDAO.getInstance();
		SettingDTO setingDto = settingDao.settItem();
		long fare = 0;
		// 기본 시간
		int dtime = setingDto != null ? setingDto.getDtime() : 1;
		// 기본 요금
		int settingfare = setingDto != null ? setingDto.getFare() : 0;
		// 오버시 시간
		int otime = setingDto != null ? setingDto.getOtime() : 1;
		final int ofare = setingDto != null ? setingDto.getOfare() : 0;
		SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		try {
			long inTimestamp = todaySdf.parse(intime).getTime();
			long OutTimestamp = todaySdf.parse(Outtime).getTime();
			long difference = (OutTimestamp - inTimestamp);
			long minuteDiff = difference / (60 * 1000);
			long x = minuteDiff / dtime;
			long y = minuteDiff % dtime;

			if (minuteDiff < 0) {
				fare = 0;
			}
			if (minuteDiff > 0) {
				if (x < 1) {
					fare = ofare;
					if (minuteDiff > otime) {
						fare = settingfare;
					}
				}
				if (x >= 1) {
					fare = settingfare * x;
					if (y > 0) {
						fare += ofare;
					}
					if (y > otime) {
						fare += settingfare;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) fare;
	}
	@SuppressWarnings("resource")
	public void fare() throws ParseException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<Integer> idxArr = new ArrayList<Integer>();
		ArrayList<String> iTimeArr = new ArrayList<String>();
		ArrayList<String> OtimeArr = new ArrayList<String>();
		try {
			con = pool.getConnection();
			sql = "select idx, to_char( in_time, 'YYYY-MM-DD HH24:MI:SS' ) as in_time, to_char( out_time, 'YYYY-MM-DD HH24:MI:SS' ) as out_time from "
					+ " pms_log where ( out_time is Not Null) and ( month_num = 0 ) and ( pay is null) ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String inTime = rs.getString("in_time");
				String outTime = rs.getString("out_time");
				int idx = rs.getInt("idx");
				iTimeArr.add(inTime);
				OtimeArr.add(outTime);
				idxArr.add(idx);
			}
			if (idxArr.size() != 0) {
				sql = " update pms_log set  pay =? ,total_pay = ? where (out_time is NOT NULL) and ( idx= ?) and ( month_num = 0 )";
				ps = con.prepareStatement(sql);
				for (int i = 0; i < idxArr.size(); i++) {
					ps.setInt(1, fare2(iTimeArr.get(i), OtimeArr.get(i)));
					ps.setInt(2, fare2(iTimeArr.get(i), OtimeArr.get(i)));
					ps.setInt(3, idxArr.get(i));
					ps.executeUpdate();
					ps.clearParameters();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
	}
	//실시간 요금
	public ArrayList<Integer> Curentfare() throws ParseException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SettingDAO settingDao=SettingDAO.getInstance();			
		SettingDTO setingDto=settingDao.settItem();
		//실시간 요금
		long fare=0;
		//기본 시간
		final int dtime=setingDto!=null?setingDto.getDtime():1;
		//기본 요금 
		final int settingfare=setingDto!=null?setingDto.getFare():0;
		//오버시 시간
		final int otime=setingDto!=null?setingDto.getOtime():1;
		//오버시 요금 
		final int ofare=setingDto!=null?setingDto.getOfare():0;
		//현재시간
		String sql="";
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<Integer> fareArr = new ArrayList<Integer>();
		ArrayList<Integer> idxArr = new ArrayList<Integer>();
		try {
			con=pool.getConnection();
			sql="select idx,to_char( in_time, 'YYYY/MM/DD HH24:MI:SS' ) as in_time from pms_log where out_time is null order by in_time desc ";			
			ps=con.prepareStatement(sql);			
			rs=ps.executeQuery(sql);
			while (rs.next()) {		
				String difftimes=rs.getString("in_time");
				int idx=rs.getInt("idx");
				arr.add(difftimes);
				idxArr.add(idx);
			}		
			SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA);
			//한국기준 날짜
			Calendar calendar = Calendar.getInstance();//시스템 현재 날짜 가져오기 
			Date date = new Date(calendar.getTimeInMillis());
			todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
			String todayDate = todaySdf.format(date);
			long todayTimestamp = todaySdf.parse(todayDate).getTime();
			Date date2 = new Date(todayTimestamp);
			String todayDate2 = todaySdf.format(date2);				
				for(int i=0;i<arr.size();i++) {
				String diffTime=arr.get(i);				
				long diffTimestamp=todaySdf.parse(diffTime).getTime();					
				long difference=(todayTimestamp-diffTimestamp);
				//현재시간-입차시간		        
		        long result=difference/ (60*60*1000);//시간차이
		        long daysDiff=difference/ (24*60*60*1000);//시간차이
		        
		        long minuteDiff=difference/(60*1000);
		        long x=minuteDiff/dtime; 
		        long y=minuteDiff%dtime;
		        if (minuteDiff < 0) {
					fare = 0;
				}       			      		        			        
		        if(x<1) {
		        	fare=ofare;
		        	if(minuteDiff>otime) {
		        		fare=settingfare;
		        	}     	
		        }			        
		        if(x>=1) {	        	
		        	fare=settingfare*x;
		        	
		        	if(y>0) {
		        		fare+=ofare;
		        	}	
		        	if(y>otime) {
		        		fare+=settingfare;
		        	}		     
		        }	
		        fareArr.add((int) fare);
		        }
				if(idxArr.size()!=0) {
				sql= " update pms_log set pay = ? where idx = ? ";
				ps = con.prepareStatement(sql);
				for(int i=0;i<fareArr.size();i++) {
					ps.setInt(1, fareArr.get(i));
					ps.setInt(2, idxArr.get(i));
					ps.executeUpdate();
					ps.clearParameters();		
				}
			}
											
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return fareArr ;
	}	
	// 차량조회
	public ArrayList<PmsLogDto> viewDetail(Pagination2 p, String FDate, String LDate, String cnum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PmsLogDto> arr = new ArrayList<PmsLogDto>();
		String sql = "";
		String yesterday=Yesterday();
		try {			
			con = pool.getConnection();
			if ((FDate.equals(yesterday))) {
				sql = "SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
						+ "( select * from PMS_log where out_time is not null and to_date (in_time,'YYYY-MM-DD') = TO_DATE(SYSDATE-1,'YYYY-MM-DD') order by in_time ) A WHERE ROWNUM <= ?  "
						+ " ) WHERE RNUM > ? ";
			} else if (FDate.equals("")) {
				if (cnum.equals("")) {
					sql = "SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
							+ " ( SELECT * FROM pms_log  where out_time is not null  and ( in_time < TO_DATE('"
							+ LDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time ) A WHERE ROWNUM <= ?  "
							+ " ) WHERE RNUM > ? ";

				} else if (LDate.equals("")) {
					sql = "SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
							+ " (SELECT * from pms_log  where out_time is not null  and (cnum='"
							+ cnum.trim() + "' )  order by in_time ) WHERE row_num >= ? )  A WHERE ROWNUM <= ?  ) WHERE RNUM > ? ";
				}				
				else {									
					sql = " SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
							+ "  ( SELECT * FROM pms_log  where out_time is not null  and (cnum='" + cnum.trim() + "')"
							+ " and ( in_time <= TO_DATE('"+ LDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time )  A WHERE ROWNUM <= ?  ) WHERE RNUM > ?  ";
				}		
			}
			
			else if (cnum.equals("")) {
				if (LDate.equals("")) {
					sql = " SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
							+ " ( SELECT * FROM pms_log  where out_time is not null  and ( in_time >= TO_DATE('"
							+ FDate.trim() + "','YYYY-MM-DD HH24:MI:SS')) order by in_time )  A WHERE ROWNUM <= ?  ) WHERE RNUM > ?  ";							
									
				} else {
					sql = " SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
							+ " ( SELECT  * FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('"
							+ FDate.trim() + "', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate.trim()
							+ "','YYYY-MM-DD HH24:MI:SS') order by in_time ) A WHERE ROWNUM <= ? ) WHERE RNUM > ?  ";
				}
			}			
			else if(LDate.equals("")) {
				sql = " SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
						+ " ( SELECT  * FROM pms_log  where out_time is not null  and (cnum='" + cnum.trim() + "')"
						+ " and ( in_time <= TO_DATE('"+ FDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time ) " +
						"A WHERE ROWNUM <= ? ) WHERE RNUM > ?"; 
			}					
		 else {
				sql = " SELECT * FROM ( SELECT A.*, ROWNUM AS RNUM FROM "
						+ " ( SELECT  * FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('"
						+ FDate.trim() + "', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate.trim()
						+ "','YYYY-MM-DD HH24:MI:SS')  and (cnum='" + cnum.trim() + "' )  order by in_time ) "
						+"A WHERE ROWNUM <= ? ) WHERE RNUM > ?"; 
			}
				
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getCurPage() * p.getPageSize()); 
			ps.setInt(2, (p.getCurPage()-1) * p.getPageSize());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				PmsLogDto dto = new PmsLogDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getString("in_time"));
				dto.setOutTime(rs.getString("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return arr;
	}

	public int datailCount(String FDate, String LDate, String cnum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		int Dcount = 0;
 		String Yesterday =Yesterday();		
		try {		
			con = pool.getConnection();
			if ((FDate.equals(Yesterday))) {
				sql = "SELECT count(*) as count FROM pms_log  where out_time is not null and to_date (in_time,'YYYY-MM-DD') = TO_DATE(SYSDATE-1,'YYYY-MM-DD') order by in_time";
			} else if (FDate.equals("")) {
				if (cnum.equals("")) {
					sql = " SELECT count(*) as count FROM pms_log  where out_time is not null  and ( in_time <= TO_DATE('"
							+ LDate.trim() + "','YYYY-MM-DD HH24:MI:SS')) order by in_time ";					
				} else if (LDate.equals("")) {
					sql = "SELECT count(*) as count FROM pms_log  where out_time is not null  and (cnum='" + cnum.trim() + "' )  order by in_time ";
				}else {
						   sql = " SELECT count(*) as count FROM pms_log  where out_time is not null and ( cnum='" + cnum.trim() + "')"
							+ " and ( in_time <= TO_DATE('"+ LDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time  ";					
					}					
			} else if (cnum.equals("")) {
				if (LDate.equals("")) {
					sql = "SELECT count(*) as count FROM pms_log  where out_time is not null  and ( in_time >= TO_DATE('" + FDate.trim()
							+ "','YYYY-MM-DD HH24:MI:SS')) order by in_time ";
				} else {
					sql = "SELECT count(*) as count FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('"
							+ FDate.trim() + "', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate.trim()
							+ "','YYYY-MM-DD HH24:MI:SS') order by in_time ";
					} 
			}	else if(LDate.equals("")) {		
						
				  sql = " SELECT count(*) as count FROM pms_log  where out_time is not null and ( cnum='" + cnum.trim() + "')"
							+ " and ( in_time <= TO_DATE('"+ FDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time  ";			
			}	
			
			else {
				sql = "SELECT count(*) as count FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('" + FDate.trim()
						+ "', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate.trim()
						+ "','YYYY-MM-DD HH24:MI:SS')  and (cnum='" + cnum.trim() + "' ) order by in_time ";
				}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				Dcount = rs.getInt("count");
			} else {
				Dcount = 0;
				System.out.println("0");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return Dcount;
	}
	//엑셀용 조회
	public ArrayList<PmsLogDto> viewDetail(String FDate, String LDate, String cnum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PmsLogDto> arr = new ArrayList<PmsLogDto>();
		String sql = "";
		try {
			con = pool.getConnection();
			if ((FDate.equals(""))) {
				sql = "SELECT * FROM pms_log  where out_time is not null and to_date (in_time,'YYYY-MM-DD') = TO_DATE(SYSDATE-1,'YYYY-MM-DD') order by in_time";
			} else if (FDate.equals("")) {
				if (cnum.equals("")) {
					sql = " SELECT * FROM pms_log  where out_time is not null  and ( in_time <= TO_DATE('"
							+ LDate.trim() + "','YYYY-MM-DD HH24:MI:SS')) order by in_time ";					
				} else if (LDate.equals("")) {
					sql = "SELECT * FROM pms_log  where out_time is not null  and (cnum='" + cnum.trim() + "' )  order by in_time ";
				}else {
						   sql = " SELECT * FROM pms_log  where out_time is not null and ( cnum='" + cnum.trim() + "')"
							+ " and ( in_time <= TO_DATE('"+ LDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time  ";					
					}					
			} else if (cnum.equals("")) {
				if (LDate.equals("")) {
					sql = "SELECT * FROM pms_log  where out_time is not null  and ( in_time >= TO_DATE('" + FDate.trim()
							+ "','YYYY-MM-DD HH24:MI:SS')) order by in_time ";
				} else {
					sql = "SELECT * FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('"
							+ FDate.trim() + "', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate.trim()
							+ "','YYYY-MM-DD HH24:MI:SS') order by in_time ";
					} 
			}	else if(LDate.equals("")) {		
						
				  sql = " SELECT * FROM pms_log  where out_time is not null and ( cnum='" + cnum.trim() + "')"
							+ " and ( in_time <= TO_DATE('"+ FDate.trim() + "','YYYY-MM-DD HH24:MI:SS' )) order by in_time  ";			
			}	
			
			else {
				sql = "SELECT * FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('" + FDate.trim()
						+ "', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate.trim()
						+ "','YYYY-MM-DD HH24:MI:SS')  and (cnum='" + cnum.trim() + "' ) order by in_time ";
				}
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
			while (rs.next()) {
				PmsLogDto dto = new PmsLogDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getString("in_time"));
				dto.setOutTime(rs.getString("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return arr;
	}
	
	public ArrayList<String> imgUpdate(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String savepath = req.getServletContext().getRealPath("/img/");
		// 실제파일 경로 경로
		int maxSize = 1024 * 1024 * 50;// 파일크기 제한
		String encoding = "utf-8";
		System.out.println(savepath);
		File Folder = new File(savepath);
		
		String url="";
		ArrayList<String>arr=new ArrayList<String>();
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try {
				Folder.mkdirs(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		try {
			con = pool.getConnection();
			MultipartRequest multi = new MultipartRequest(req, savepath, maxSize, encoding,
					new DefaultFileRenamePolicy());
			Enumeration fileNames = multi.getFileNames();
			// DefaultFileRenamePolicy() -> 중복파일명을 위한 매개변수
			boolean save = true; // 파일 저장 성공
			String fileInput = "";// 폼으로 받아온 filename
			String fileName = "";// 저장된 파일 이름
			String originFileName = "";// 원본 파일 이름
			String type = "";// 저장된 파일 종류
			File fileobj = null;// 저장된 파일 객체
			String fileExtend = ""; // jpg,png,gif 등 확장자
			String fileSize = ""; // 저장된 파일 사이즈
			String newFileName = "pms_" + System.currentTimeMillis() + fileName;// 저장된 파일을 바꿀 이름
			System.out.println("newFileName" + newFileName);
			while (fileNames.hasMoreElements()) { // 있으면
				fileInput = (String) fileNames.nextElement();// 폼에서 받아온 요소			
				String Ipage=multi.getParameter("ipage")==null?"1":multi.getParameter("ipage").trim();
				String idrw=multi.getParameter("idrw")==null?"20":multi.getParameter("idrw").trim();				
				arr.add("p="+Ipage+"&dRs="+idrw);
				fileName = multi.getFilesystemName(fileInput);
				if (fileName != null) {
					type = multi.getContentType(fileInput);
					fileobj = multi.getFile(fileInput);
					originFileName = multi.getOriginalFileName(fileInput);
					fileExtend = fileName.substring(fileName.lastIndexOf(".") + 1);// "file1.jpg"라면 jpg 반환
					fileSize = String.valueOf(fileobj.length());// file도 결국 문자열이므로 length()로 반환
					String[] splitType = type.split("/");
					if (!splitType[0].equals("image")) {
						save = false;
						fileobj.delete(); // 저장된 파일 객체로 삭제
						break;
					} else {// 만약 이미지 파일이면 저장 파일의 이름 바꾼다.
						newFileName += "." + fileExtend;
						fileobj.renameTo(new File(savepath + "\\" + newFileName));
					}
				}
			}
			if (save) {
				sql = " update pms_log set c_img = ? where idx = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newFileName);
				pstmt.setString(2, multi.getParameter("idx"));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return arr;
	}
	
	
		public String Yesterday() {
			SimpleDateFormat todaySdf = new SimpleDateFormat("YYYY-MM-dd", Locale.KOREA);
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -1);
	        String timedate = todaySdf.format(cal.getTime());
	        return timedate;
	    }
	
	
	

	public void datailImgUpdate(HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, UnsupportedEncodingException {
			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;
			int maxSize = 1024 * 1024 * 100;
			String path = request.getServletContext().getRealPath("/img/"); // 저장경로
			boolean isrequest = ServletFileUpload.isMultipartContent(request);// request 확인
		if (isrequest) {
			DiskFileItemFactory diskFacktory = new DiskFileItemFactory(); // 업로드 파일 보관
			ServletFileUpload upload = new ServletFileUpload(diskFacktory);
			upload.setSizeMax(maxSize);// 업로드 크기 제한
			List<FileItem> items = upload.parseRequest(request);
			String FieldName = null;
			String FileName = null;
			String value = null;
			String idx = null;
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) {
					FieldName = item.getFieldName();
					value = item.getString("utf-8");
					System.out.println("form" + FieldName + "," + value);
					System.out.println("idx:" + value);
					idx = value;
					if (value.contains(".")) {
						idx = "*";
					}
				} else {
					FieldName = item.getFieldName();
					FileName = item.getName();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
					System.out.println("File" + FieldName + "," + sizeInBytes + "," + FileName + "," + value);
					if (!FileName.isEmpty()) {
						UUID id = UUID.randomUUID();
						// SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHH:mm:ss");
						// Date time = new Date();
						// String day = format1.format(time);
						try {
							item.write(new File(path + "/" + id + FileName));
							System.out.println(isInMemory);
							if (idx.contains("*")) {
								System.out.println("실패");
							} else {
								con = pool.getConnection();
								sql = " update pms_log set c_img = ? where idx = ? ";
								ps = con.prepareStatement(sql);
								ps.setString(1, id + FileName);
								ps.setString(2, idx);
								ps.executeUpdate();
							}
						} catch (Exception e) {
							e.printStackTrace();

						} finally {
							pool.freeConnection(con, ps);
						}
					}
				}
			}
		}
	}
}
