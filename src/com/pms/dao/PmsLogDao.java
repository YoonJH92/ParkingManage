package com.pms.dao;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.pms.dto.PmsDto;
import com.pms.dto.PmsPageDto;
import com.pms.dto.SettingDTO;
import com.pms.util.DBConnectionMgr;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class PmsLogDao {
	private static PmsLogDao instance;

	public static PmsLogDao getInstance() {
		if (instance == null) {
			instance = new PmsLogDao();
		}
		return instance;
	}

	private DBConnectionMgr pool;
	private static final String ENCTYPE = "utf-8";
	private int sizeLimit = 300 * 200 * 15;

	public PmsLogDao() {
		pool = DBConnectionMgr.getInstance();
	}
	//실시간 조회
	public ArrayList<PmsDto> viewList(PmsPageDto page) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<PmsDto> arr = new ArrayList<PmsDto>();
		int startNum = page.getStartNum();
		int endNum=page.getEndNum();
		
		try {
			con = pool.getConnection();
			   String sql = "SELECT * FROM (" + 
			   		"         SELECT * FROM (" + 
			   		" SELECT ROWNUM row_num, pms_log.* FROM pms_log " + 
			   		"               ) WHERE row_num >= ? " + 
			   		"               ) WHERE row_num <= ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startNum);
			ps.setInt(2, endNum);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				PmsDto dto = new PmsDto();
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
	//
	public int getlistCount() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		int count=0;
		try {
			con=pool.getConnection();
			sql="select count(*) as count from pms_log";
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				count=rs.getInt("count");
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
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
				fileName = multi.getFilesystemName(fileInput);
				if (fileName != null) {
					type = multi.getContentType(fileInput);
					fileobj = multi.getFile(fileInput);
					//originFileName = multi.getOriginalFileName(fileInput);
					fileExtend = fileName.substring(fileName.lastIndexOf(".") + 1);// "file1.jpg"라면 jpg 반환
					//fileSize = String.valueOf(fileobj.length());// file도 결국 문자열이므로 length()로 반환
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
			sql = " select count(cnum), count(case when month_num > 0 then 1 end),count(case when month_num = 0 then 1 end) from pms_log where out_time is Null ";
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
	
	public int fare2 (String intime,String Outtime) {
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
			if(minuteDiff<0) {
				fare=0;
		}
		
		if(minuteDiff>0) {
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
		}catch (ParseException e) {
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
					+ " pms_log where ( out_time is Not Null) and ( month_num = 0 ) and ( pay = 0 ) ";
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
		
			 if(idxArr.size()!=0) {
				sql = " update pms_log set  pay =? ,total_pay = ? where (out_time is NOT NULL) and ( idx= ?) and (month_num = 0 )";
				ps = con.prepareStatement(sql);
				for (int i = 0; i < idxArr.size(); i++) {
					ps.setInt(1, fare2(iTimeArr.get(i),OtimeArr.get(i) ));
					ps.setInt(2, fare2(iTimeArr.get(i),OtimeArr.get(i)));
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

	// 쿠폰 적용
	@SuppressWarnings("resource")
	public void totalfare() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		// 요금
		ArrayList<Integer> payarr = new ArrayList<Integer>();
		ArrayList<Integer> discountarr = new ArrayList<Integer>();
		ArrayList<Integer> idxarr = new ArrayList<Integer>();
		ArrayList<Integer> cpnumarr = new ArrayList<Integer>();
		ArrayList<Integer> clIdxarr=new ArrayList<Integer>();
		try {
			con = pool.getConnection();
			sql = " SELECT l.idx, l.pay ,cp.discount ,cl.cpnum ,cl.idx as clidx from pms_log l Join pms_coupon cp "
					+ " on l.cp_num=cp.cpnum  Join pms_coupon_log cl  on cl.cpnum=cp.cpname "
					+ " where cl.used= 1 ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				int idx = rs.getInt("idx");
				int pay = rs.getInt("pay");
				int cpnum = rs.getInt("cpnum");
				int discount = rs.getInt("discount");
				int clidx=rs.getInt("clidx");
				payarr.add(pay);
				idxarr.add(idx);
				cpnumarr.add(cpnum);
				clIdxarr.add(clidx);
				discountarr.add(discount);
			}

			if(clIdxarr.size()!=0) {
				sql = "update pms_coupon_log set used= ? where idx = ? ";
				ps = con.prepareStatement(sql);
				for (int i = 0; i <cpnumarr.size(); i++) {
					ps.setInt(1, 2);
					ps.setInt(2, clIdxarr.get(i));
					ps.executeUpdate();
					ps.clearParameters();
				}
			}
			
			if (discountarr.size()!=0) {
				sql = "update pms_log set total_pay = ? where idx = ?  and cp_num = ?";
				ps = con.prepareStatement(sql);
				for (int i = 0; i <= discountarr.size(); i++) {
					int discountfare=payarr.get(i) - discountarr.get(i);
					ps.setInt(1, discountfare);
					ps.setInt(2, idxarr.get(i));
					ps.setInt(3, cpnumarr.get(i));
					ps.executeUpdate();
					ps.clearParameters();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
	}
	// 할인권 적용
	
	@SuppressWarnings("resource")
	public void discountfare() {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		SettingDAO settingDao = SettingDAO.getInstance();
		SettingDTO setingDto = settingDao.settItem();
		// 기본 시간
		final int dtime = setingDto != null ? setingDto.getDtime() : 1;
		// 기본 요금
		final int settingfare = setingDto != null ? setingDto.getFare() : 0;
		// 오버시 시간
		final int otime = setingDto != null ? setingDto.getOtime() : 1;
		// 오버시 요금
		final int ofare = setingDto != null ? setingDto.getOfare() : 0;
		 long fare = 0;
		ArrayList<Integer> useTimearr = new ArrayList<Integer>();
		ArrayList<Integer> idxarr = new ArrayList<Integer>();
		ArrayList<String> intimearr = new ArrayList<String>();
		ArrayList<String> Otimearr = new ArrayList<String>();
		ArrayList<Integer> totalarr = new ArrayList<Integer>();
		try {
			con = pool.getConnection();
			sql = "select dm.use_time , l.idx , dm.use_time,to_char( l.in_time, 'YYYY-MM-DD HH24:MI:SS' ) as in_time, to_char( l.out_time, 'YYYY-MM-DD HH24:MI:SS' ) as out_time "
				+ " from pms_log l join pms_discount_manage dm on l.sale_num = dm.com_num "
				+ " where l.sale_num is not null ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String iTimes = rs.getString("in_time");
				String Otimes = rs.getString("out_time");
				int useTime = rs.getInt("use_time");
				useTimearr.add(useTime);
				idxarr.add(idx);
				intimearr.add(iTimes);
				Otimearr.add(Otimes);
			 }
			if (useTimearr.size()!=0) {
				SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
				for (int i = 0; i < useTimearr.size(); i++) {
					long disCTimes = useTimearr.get(i) * (60 * 60 * 1000);// 시간
					String inTimevalues = intimearr.get(i);
					String oTimevalues = Otimearr.get(i);
					long inTimeStamp = todaySdf.parse(inTimevalues).getTime();
					long OTimeStamp = todaySdf.parse(oTimevalues).getTime();
					long diff = OTimeStamp - inTimeStamp - disCTimes;
					long x = diff / dtime;
					long y = diff % dtime;				 
						if(diff<0) {
							fare=0;
						}							
						else if (x < 1) {
							fare = ofare;
							if (diff > otime) {
								fare = settingfare;
							}
						}
						else if (x >= 1) {
							fare = settingfare * x;
							if (y > 0) {
								fare += ofare;
							}
							if (y > otime) {
								fare += settingfare;
							}
						}
						totalarr.add((int) fare);
					}
											   
			}
				if (totalarr.size()!=0) {
					sql = " update pms_log set total_pay = ? where (out_time is NOT NULL) and (idx= ?) ";
					ps = con.prepareStatement(sql);
					for (int i = 0; i < totalarr.size(); i++) {
						ps.setInt(1, totalarr.get(i));
						ps.setInt(2, idxarr.get(i));
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
		DecimalFormat Commas = new DecimalFormat("#,###"); //단위 콤마 

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
		
			for(int i=0;i<arr.size();i++) {				
			int Cfare=	fare2(arr.get(i),todayDate);	
			fareArr.add((String)Commas.format(Cfare));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, ps, rs);
		}
		return fareArr;
	}
	//차량조회 
	public ArrayList<PmsDto> viewDetail(String FDate, String LDate, String cnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<PmsDto> arr = new ArrayList<PmsDto>();
		String sql = "";
		try {
			con = pool.getConnection();
			//값 없을때
			
			if ((FDate.equals("-1"))) {		
			sql = "select * from pms_log where (out_time is not null) and to_date (in_time,'YYYY-MM-DD') = TO_DATE(SYSDATE-1,'YYYY-MM-DD') order by in_time ";
							
			}						
				
			 else if (cnum.equals("")) {
					sql = "select * from pms_log  WHERE in_time BETWEEN TO_DATE('" + FDate+ "', 'YYYY-MM-DD HH24:MI:SS') AND "
							+ "TO_DATE('" + LDate + "','YYYY-MM-DD HH24:MI:SS') order by in_time ";
				} else if (FDate.equals("")) {
					sql = "select * from pms_log  WHERE (cnum='"+cnum+"') and (out_time is not null) order by in_time ";
				} else {
					sql = "select * from pms_log  WHERE in_time BETWEEN TO_DATE('" + FDate+ "', 'YYYY-MM-DD HH24:MI:SS') AND "
							+ "TO_DATE('" + LDate + "','YYYY-MM-DD HH24:MI:SS') "
							+ "and (out_time is Not null)and (cnum='" + cnum + "') order by in_time ";
				}			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				PmsDto dto = new PmsDto();
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
			pool.freeConnection(con, st, rs);

		}
		return arr;
	}

	// 실시간 엑셀
	public void writeLogExcel(ArrayList<PmsDto> arr) throws FileNotFoundException { // 데이터 담을 리스트
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
			PmsDto dto = arr.get(i);
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

	public void writeLogDetailExcel(ArrayList<PmsDto> arr) throws FileNotFoundException { // 데이터 담을 리스트

		String path = "C://Download/";
		// 파일경로
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");

		String today = format1.format(System.currentTimeMillis());

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

		File file = new File(path + "Detaillog.xls");

		FileOutputStream fos = new FileOutputStream(file);

		HSSFWorkbook workbook = new HSSFWorkbook();// 새 엑셀 생성
		HSSFSheet sheet = workbook.createSheet("차량조회");// 새 시트 생성
		
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
		cellstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// 
		CellStyle bodycellstyle = workbook.createCellStyle();
		bodycellstyle.setAlignment(HorizontalAlignment.CENTER);
		bodycellstyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		bodycellstyle.setBorderLeft(BorderStyle.THIN);
		bodycellstyle.setBorderTop(BorderStyle.THIN);
		bodycellstyle.setBorderRight(BorderStyle.THIN);
		bodycellstyle.setBorderBottom(BorderStyle.THIN);
		
		// 요금 형식 
		CellStyle moneyCellstyle = workbook.createCellStyle();
		moneyCellstyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
		moneyCellstyle.setAlignment(HorizontalAlignment.CENTER);
		moneyCellstyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		moneyCellstyle.setBorderLeft(BorderStyle.THIN);
		moneyCellstyle.setBorderTop(BorderStyle.THIN);
		moneyCellstyle.setBorderRight(BorderStyle.THIN);
		moneyCellstyle.setBorderBottom(BorderStyle.THIN);
		
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
		row = sheet.createRow(0);
		// 첫 번째출 cell 설정
		cell = row.createCell(0);
		cell.setCellValue("No.");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(1);
		cell.setCellValue("차량 번호");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(2);
		cell.setCellValue("입 차 시간");
		cell.setCellStyle(cellstyle);

		cell = row.createCell(3);
		cell.setCellValue("출 차 시간");
		cell.setCellStyle(cellstyle);

		
		cell = row.createCell(4);
		cell.setCellValue("사용 금액");
		cell.setCellStyle(cellstyle);


		cell = row.createCell(5);
		cell.setCellValue("쿠폰 사용 여부");
		cell.setCellStyle(cellstyle);

		
		cell = row.createCell(6);
		cell.setCellValue("월 정액 여부");
		cell.setCellStyle(cellstyle);
		
		cell = row.createCell(7);
		cell.setCellValue("할인 여부");
		cell.setCellStyle(cellstyle);
		
		
		cell = row.createCell(8);
		cell.setCellValue("최종 금액 ");
		cell.setCellStyle(cellstyle);

		
		for (int i = 0; i < arr.size(); i++) {
			PmsDto dto = arr.get(i);
			row = sheet.createRow(i + 1);

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
			cell.setCellValue(String.valueOf(dto.getOutTime()));
			cell.setCellStyle(bodycellstyle);

			cell = row.createCell(4);
			cell.setCellValue(dto.getPay());
			cell.setCellStyle(moneyCellstyle);

			cell = row.createCell(5);
			cell.setCellValue(dto.getCpNum());
			cell.setCellStyle(bodycellstyle);

			cell = row.createCell(6);
			cell.setCellValue(dto.getMonthNum());
			cell.setCellStyle(bodycellstyle);

			cell=row.createCell(7);
			cell.setCellValue(dto.getSaleNum());
			cell.setCellStyle(moneyCellstyle);
			
			cell=row.createCell(8);
			cell.setCellValue(dto.getTotalPay());
			cell.setCellStyle(moneyCellstyle);
			
		}

		try {
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ExcelDownload(HttpServletRequest request, HttpServletResponse response) {

		// 파일이 업로드 된 경로
		String path = "C://Download/";
		SimpleDateFormat format1 = new SimpleDateFormat("yyMMddHHmmss");
		String today = format1.format(System.currentTimeMillis());
		String savepath = path;
		// 서버에 저장된 파일명
		String filename = "log.xls";
		// 실제 내보낼 파일명
		String orgfilename = today + "log.xls";
		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";

		try {
			// 파일을 읽어 스트림에 담기
			try {
				file = new File(savepath, filename);
				in = new FileInputStream(file);
			} catch (FileNotFoundException fe) {
				skip = true;
			}
			client = request.getHeader("User-Agent"); // 유저의 시스템 정보
			// 파일 다운로드 헤더 지정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");

			if (!skip) {

				// IE
				if (client.indexOf("MSIE") != -1) {
					response.setHeader("Content-Disposition",
							"attachment; filename=" + new String(orgfilename.getBytes("KSC5601"), "ISO8859_1"));
			
				} else {
					// 한글 파일명 처리
					orgfilename = new String(orgfilename.getBytes("utf-8"), "iso-8859-1");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				}
				response.setHeader("Content-Length", "" + file.length());
				os = response.getOutputStream();
				byte b[] = new byte[(int) file.length()];
				int leng = 0;

				while ((leng = in.read(b)) > 0) {
					os.write(b, 0, leng);
				}
			} else {

				System.out.println("X");
			}
			in.close();
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ExcelDetaillogDown(HttpServletRequest request, HttpServletResponse response) {
		// 파일이 업로드 된 경로
		String path = "C://Download/";
		SimpleDateFormat format1 = new SimpleDateFormat("yyMMddHHmmss");
		String today = format1.format(System.currentTimeMillis());
		String savepath = path;
		// 서버에 저장된 파일명
		String filename = "Detaillog.xls";
		// 실제 내보낼 파일명
		String orgfilename = today + "Detaillog.xls";
		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";

		try {
			// 파일을 읽어 스트림에 담기
			try {
				file = new File(savepath, filename);
				in = new FileInputStream(file);
			} catch (FileNotFoundException fe) {
				skip = true;
			}
			client = request.getHeader("User-Agent"); // 유저의 시스템 정보
			// 파일 다운로드 헤더 지정
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");

			if (!skip) {

				// IE
				if (client.indexOf("MSIE") != -1) {
					response.setHeader("Content-Disposition",
							"attachment; filename=" + new String(orgfilename.getBytes("KSC5601"), "ISO8859_1"));

				} else {
					// 한글 파일명 처리
					orgfilename = new String(orgfilename.getBytes("utf-8"), "iso-8859-1");

					response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				}
				response.setHeader("Content-Length", "" + file.length());
				os = response.getOutputStream();
				byte b[] = new byte[(int) file.length()];
				int leng = 0;

				while ((leng = in.read(b)) > 0) {
					os.write(b, 0, leng);
				}
			} else {

				System.out.println("X");
			}
			in.close();
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
