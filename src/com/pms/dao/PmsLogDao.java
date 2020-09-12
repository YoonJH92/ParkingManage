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
	public ArrayList<PmsLogDto> viewList(PmsPageDto page) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PmsLogDto> arr = new ArrayList<PmsLogDto>();
		int startNum = page.getStartNum();
		int endNum = page.getEndNum();

		try {
			con = pool.getConnection();
			String sql = "SELECT * FROM (" + "  SELECT * FROM ("
					+ " SELECT ROWNUM row_num, pms_log.* FROM pms_log  where out_time is null ) WHERE row_num >= ?  "
					+ " ) WHERE row_num <= ?  ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startNum);
			ps.setInt(2, endNum);
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

	// 실시간 차량 사진
		public void imgUpdate(HttpServletRequest req) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			String savepath = req.getServletContext().getRealPath("/img/");
			// 실제파일 경로 경로
			int maxSize = 1024 * 1024 * 50;// 파일크기 제한
			String encoding = "utf-8";
			System.out.println(savepath);
			File Folder = new File(savepath);
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
				MultipartRequest multi = new MultipartRequest(req, savepath, maxSize, encoding, new DefaultFileRenamePolicy());
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
			}
			catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
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

	
	// 실시간 요금
	public ArrayList<String> Curentfare() throws ParseException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 현재시간
		String sql = "";
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> fareArr = new ArrayList<String>();
		DecimalFormat Commas = new DecimalFormat("#,###"); // 단위 콤마

		try {
			con = pool.getConnection();
			sql = "select to_char( in_time, 'YYYY-MM-DD HH24:MI:SS' ) as in_time from pms_log where out_time is null order by in_time ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				String difftimes = rs.getString("in_time");
				arr.add(difftimes);
			}
			SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			// 한국기준 날짜
			Calendar calendar = Calendar.getInstance();// 시스템 현재 날짜 가져오기
			Date date = new Date(calendar.getTimeInMillis());
			todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
			String todayDate = todaySdf.format(date);

			for (int i = 0; i < arr.size(); i++) {
				int Cfare = fare2(arr.get(i), todayDate);
				fareArr.add((String) Commas.format(Cfare));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return fareArr;
	}

	// 차량조회
	public ArrayList<PmsLogDto> viewDetail(PmsPageDto page, String FDate, String LDate, String cnum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PmsLogDto> arr = new ArrayList<PmsLogDto>();
		String sql = "";
		int startNum=page.getStartNum();
		int endNum=page.getEndNum();		
		try {
			con = pool.getConnection();
			// 값 없을때
			if ((FDate.equals("-1"))) {				
				sql= "SELECT * FROM (" + "  SELECT * FROM ("
						+ " SELECT ROWNUM row_num, pms_log.* FROM pms_log  where out_time is not null and to_date (in_time,'YYYY-MM-DD') = TO_DATE(SYSDATE-20,'YYYY-MM-DD') ) WHERE row_num >= ? ) "
						+ "WHERE row_num <= ? ";				
			}

			else if (cnum.equals("")) {	
				
				sql= "SELECT * FROM (" + "  SELECT * FROM ("
						+ " SELECT ROWNUM row_num, pms_log.* FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('" + FDate + 
						"', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate + "','YYYY-MM-DD HH24:MI:SS') ) WHERE row_num >= ? ) "
						+ "WHERE row_num <= ? ";
			} else if (FDate.equals("")) {
				sql= "SELECT * FROM (" + "  SELECT * FROM ("
						+ " SELECT ROWNUM row_num, pms_log.* FROM pms_log  where out_time is not null  and (cnum='"+ cnum + "' ) ) WHERE row_num >= ? ) "
						+ "WHERE row_num <= ? ";
			} else {
				sql= "SELECT * FROM (" + "  SELECT * FROM ("
						+ " SELECT ROWNUM row_num, pms_log.* FROM pms_log  where out_time is not null  and in_time BETWEEN TO_DATE ('" + FDate + 
						"', 'YYYY-MM-DD HH24:MI:SS') AND " + "TO_DATE('" + LDate + "','YYYY-MM-DD HH24:MI:SS')  and (cnum='"+ cnum + "' ) ) WHERE row_num >= ? ) "
						+ "WHERE row_num <= ? ";				
			}		
			ps = con.prepareStatement(sql);
			ps.setInt(1, startNum);
			ps.setInt(2, endNum);
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
			pool.freeConnection(con, ps , rs);
		}
		return arr;
	}

	public int datailCount(String FDate, String LDate, String cnum)  {
	    Connection con=null;
	    PreparedStatement ps=null;
	    ResultSet rs=null;
	    String sql="";
	    int Dcount=0;
	   	   try {	
	   	    con=pool.getConnection();
	      if(FDate.equals("-1")) {
	    	  sql = "select count(*) as count from pms_log where (out_time is not null) and to_date (in_time,'YYYY-MM-DD') = TO_DATE(SYSDATE-20,'YYYY-MM-DD') order by in_time ";}
	    		else if(cnum.equals("")){
	    	  sql = "select count(*) as count from pms_log  WHERE in_time BETWEEN TO_DATE('" + FDate	
				+ "', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('" + LDate+ "','YYYY-MM-DD HH24:MI:SS')  ";
				}
	    	else if(LDate.equals("")) {
	    	  sql="select count(*) as count from pms_log where cnum='"+cnum+"' and out_time is null";
				}
	    	else {
	    	  sql = "select count(*) as count from pms_log  WHERE in_time BETWEEN TO_DATE('" + FDate
	    				+ "', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE('" + LDate+ "','YYYY-MM-DD HH24:MI:SS') and (cnum ='"+cnum+"') order by in_time ";
	    		}
	    
	    		    
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				Dcount = rs.getInt("count");
			}	else {
				Dcount=0;
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
	
	
	// 실시간 엑셀
	public void writeLogExcel(ArrayList<PmsLogDto> arr) throws FileNotFoundException { // 데이터 담을 리스트
		String path = "C://Download/";
		// 파일경로
		File Folder = new File(path);
		// 해당 디렉토리가 없을경우
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

		File file = new File(path + "log.xls");

		FileOutputStream fos = new FileOutputStream(file);

		HSSFWorkbook workbook = new HSSFWorkbook();// 새 엑셀 생성
		HSSFSheet sheet = workbook.createSheet("실시간");// 새 시트 생성

		// 스타일 설정

		sheet.setDefaultColumnWidth((short) 20);
		sheet.setDefaultRowHeight((short) 300);

		CellStyle cellstyle = workbook.createCellStyle();
		// 가운데 정렬
		cellstyle.setAlignment(HorizontalAlignment.CENTER);
		// 세로 정렬
		cellstyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		cellstyle.setBorderLeft(BorderStyle.THIN);
		cellstyle.setBorderTop(BorderStyle.THIN);
		cellstyle.setBorderRight(BorderStyle.THIN);
		cellstyle.setBorderBottom(BorderStyle.THIN);
		cellstyle.setFillForegroundColor(HSSFColor.CORAL.index);
		cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		CellStyle bodycellstyle = workbook.createCellStyle();
		bodycellstyle.setAlignment(HorizontalAlignment.CENTER);
		bodycellstyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		bodycellstyle.setBorderLeft(BorderStyle.THIN);
		bodycellstyle.setBorderTop(BorderStyle.THIN);
		bodycellstyle.setBorderRight(BorderStyle.THIN);
		bodycellstyle.setBorderBottom(BorderStyle.THIN);
		// 폰트 설정 적용

		// Title
		HSSFFont Headerfont = workbook.createFont();
		Headerfont.setFontName("맑은 고딕");
		Headerfont.setBold(true);

		// body
		HSSFFont font = workbook.createFont();
		Headerfont.setFontName("맑은 고딕");

		bodycellstyle.setFont(font);
		cellstyle.setFont(Headerfont); // cellstyle 적용

		HSSFRow row = null;// 행
		HSSFCell cell = null;// 셀

		// 첫번째 줄
		row = sheet.createRow(1);
		// 첫 번째출 cell 설정
		cell = row.createCell(0);
		cell.setCellValue("No.");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(1);
		cell.setCellValue("차량번호");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(2);
		cell.setCellValue("입차시간");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(3);
		cell.setCellValue("할인 적용 여부 ");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(4);
		cell.setCellValue("월정액 여부");
		cell.setCellStyle(cellstyle);

		for (int i = 0; i < arr.size(); i++) {
			PmsLogDto dto = arr.get(i);
			row = sheet.createRow(i + 2);

			cell = row.createCell(0);
			cell.setCellValue(dto.getIdx());
			cell.setCellStyle(bodycellstyle);

			cell = row.createCell(1);
			cell.setCellValue(dto.getCnum());
			cell.setCellStyle(bodycellstyle);

			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(dto.getInTime()));
			cell.setCellStyle(bodycellstyle);

			cell = row.createCell(3);
			cell.setCellValue(dto.getCpNum());
			cell.setCellStyle(bodycellstyle);

			cell = row.createCell(4);
			cell.setCellValue(dto.getMonthNum());
			cell.setCellStyle(bodycellstyle);

		}

		try {
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public void datailImgUpdate(HttpServletRequest request,HttpServletResponse response) throws FileUploadException, UnsupportedEncodingException  {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		int maxSize = 1024 * 1024 * 100;
		String path = request.getServletContext().getRealPath("/img/"); //저장경로	
		
		boolean isrequest =ServletFileUpload.isMultipartContent(request);//request 확인
		
		if(isrequest) {
			DiskFileItemFactory diskFacktory =new DiskFileItemFactory();
			diskFacktory.setSizeThreshold(5000); //업로드시 사용할 임시 메모리 		
			ServletFileUpload upload =new ServletFileUpload(diskFacktory);
			upload.setSizeMax(maxSize);//업로드 크기 제한 
			
			List<FileItem> items =upload.parseRequest(request);
			String FieldName=null;
			String FileName=null;
			String value=null;
			String idx=null;
			Iterator<FileItem> iter=items.iterator();
			while(iter.hasNext()) {
				FileItem item =iter.next();				
				if(item.isFormField()) {
					FieldName=item.getFieldName();					
					value=item.getString("utf-8");
					System.out.println("form"+FieldName+","+value);
					
					 idx = value.substring(0,value.lastIndexOf(""));
					System.out.println("idx:"+idx);

				}else {
					FieldName=item.getFieldName();
					FileName=item.getName();
					boolean isInMemory=item.isInMemory();
					long sizeInBytes=item.getSize();
					System.out.println("File"+FieldName +","+sizeInBytes+","+FileName+","+value);
						if(!FileName.isEmpty()) {
							File uploadFile=null;							
							UUID id =UUID.randomUUID();
							
						try {
							item.write(new File(path+"/"+id+FileName));	
							
							if (!idx.equals(null)) {
								con=pool.getConnection();
								sql = " update pms_log set c_img = ? where idx = ? ";
								ps = con.prepareStatement(sql);
								ps.setString(1, id+FileName);
								ps.setString(2, idx);
								ps.executeUpdate();
							}	
							
						}catch (Exception e) {
							e.printStackTrace();
							
						}finally {
						pool.freeConnection(con, ps);
					}				
				}	
		
			}
		}
	}
	}
}

	
	
		
		
		
		
		
		
		
		
		
		
		
	

	
	
				


