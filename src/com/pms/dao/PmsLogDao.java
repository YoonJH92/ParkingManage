package com.pms.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pms.dto.PmsDto;
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

	public ArrayList<PmsDto> viewList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		ArrayList<PmsDto> arr = new ArrayList<PmsDto>();
		
		
		try {
			con = pool.getConnection();
			String sql = " select * from pms_log where out_time is null ";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			ArrayList<Integer>fare=Curentfare();
			while (rs.next()) {
				PmsDto dto = new PmsDto();
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getDate("in_time"));
				dto.setOutTime(rs.getDate("out_time"));
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
	//실시간 차량 사진
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
			sql = " select count(cnum), count(month_num),count(case when month_num IS NULL then 1 end) from pms_log where out_time is Null ";
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
	
		//최종요금 
		@SuppressWarnings("resource")
		public void fare() throws ParseException{
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;	
			String sql="";
			SettingDAO settingDao=SettingDAO.getInstance();			
			SettingDTO setingDto=settingDao.settItem();	
			
			HashMap<String,String>map=new HashMap<String,String>();
			//실시간 요금
			long fare=0;
			//기본 시간
			 int dtime=setingDto!=null?setingDto.getDtime():1;
			//기본 요금 
			 int settingfare=setingDto!=null?setingDto.getFare():0;
			//오버시 시간
			 int otime=setingDto!=null?setingDto.getOtime():1;
			//오버시 요금 
			final int ofare=setingDto!=null?setingDto.getOfare():0;
			ArrayList<Integer> totalFareArr=new ArrayList<Integer>();
			ArrayList<Integer>idxArr=new ArrayList<Integer>();
			
		  try {
			  con=pool.getConnection();
			  sql="select idx, to_char( in_time, 'YYYY/MM/DD HH24:MI:SS' ) as in_time, to_char( out_time, 'YYYY/MM/DD HH24:MI:SS' ) as out_time from pms_log where out_time is Not Null";  
			  ps=con.prepareStatement(sql);			
			  rs=ps.executeQuery(sql);
			
			  while(rs.next()) {
					String inTime=rs.getString("in_time");
					String outTime=rs.getString("out_time");	
					int idx=rs.getInt("idx");
					map.put("Itime", inTime);
					map.put("Otime",outTime);
					idxArr.add(idx);
			  }			  			  			
				SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA);
				todaySdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

				for(int i=0;i<map.size();i++) {
				
				String itTime=map.get("Itime");
				String OTime=map.get("Otime");
				
				long inTimestamp = todaySdf.parse(itTime).getTime();
				long OutTimestamp=todaySdf.parse(OTime).getTime();
				
				long difference=(OutTimestamp-inTimestamp);
		        long minuteDiff=difference/(60*1000);
		        long x=minuteDiff/dtime; 
		        long y=minuteDiff%dtime;
		        
				System.out.println("입차시간"+ itTime);
				System.out.println("출차시간"+ OTime);
				System.out.println(inTimestamp);
				System.out.println(OutTimestamp);
				
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
			        totalFareArr.add((int)fare);
				}
				  
				try {
									    
					sql=" update pms_log set pay = ? where out_time is NOT NULL and idx=?";
					
					ps= con.prepareStatement(sql);
					rs=ps.executeQuery(sql);
					
					if(rs.next()) {
						
				 for(int i=0;i<totalFareArr.size();i++) {
		  			ps.setInt(1, totalFareArr.get(i));
		  			ps.setInt(2, idxArr.get(i));
		  			ps.executeUpdate();			
				}

					}	
				}catch (Exception e) {
					
				}
				
					
				
			} catch (Exception e) {
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
			ArrayList<Integer> fareArr=new ArrayList<Integer>();
			
			try {
				con=pool.getConnection();
				
				sql="select to_char( in_time, 'YYYY/MM/DD HH24:MI:SS' ) as in_time from pms_log where out_time is null ";			
				ps=con.prepareStatement(sql);			
				rs=ps.executeQuery(sql);
				while (rs.next()) {		
					String difftimes=rs.getString("in_time");
					arr.add(difftimes);
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
					System.out.println("현재 시간:"+date2);
					System.out.println("입차 시간:"+diffTime);
					System.out.println(todayTimestamp);
					System.out.println(diffTimestamp);
			        System.out.println("시간차=> "  +  difference/ (60*60*1000));
			        System.out.println("분차=>+"+difference/(60 * 1000));
			        
			        long result=difference/ (60*60*1000);//시간차이
			        long minuteDiff=difference/(60*1000);
 
			        long x=minuteDiff/dtime; //
			        long y=minuteDiff%dtime;//
			 
			        			      		        			        
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
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, ps, rs);
			}
			return fareArr ;
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
		
			if ((FDate.equals("")&&cnum.equals("")&&LDate.equals(""))){			
				sql="select * from pms_log where idx = 0";			
			}
			
			
			else if (cnum.equals("")) {
				sql = "select * from pms_log  WHERE in_time BETWEEN TO_DATE('" + FDate+ "', 'YYYY/MM/DD HH24:MI:SS') AND "
						+ "TO_DATE('" + LDate + "','YYYY/MM/DD HH24:MI:SS') and (out_time is not null) ";
			} else if (FDate.equals("")) {
				sql = "select * from pms_log  WHERE (cnum='"+cnum+"') and (out_time is not null)";			
			}					
			else {
				sql = "select * from pms_log  WHERE in_time BETWEEN TO_DATE('" + FDate+ "', 'YYYY/MM/DD HH24:MI:SS') AND "
						+ "TO_DATE('" + LDate + "','YYYY/MM/DD HH24:MI:SS')"
						+ "and (out_time is Not null)and (cnum='" + cnum + "')";

			}
			st = con.createStatement();
			rs = st.executeQuery(sql);	
			while (rs.next()) {
				PmsDto dto = new PmsDto();	
				dto.setIdx(rs.getInt("idx"));
				dto.setCnum(rs.getString("cnum"));
				dto.setInTime(rs.getDate("in_time"));
				dto.setOutTime(rs.getDate("out_time"));
				dto.setPay(rs.getInt("pay"));
				dto.setCpNum(rs.getInt("cp_num"));
				dto.setSaleNum(rs.getInt("sale_num"));
				dto.setTotalPay(rs.getInt("total_pay"));
				dto.setMonthNum(rs.getInt("month_num"));
				dto.setcImg(rs.getString("c_img"));
				
				arr.add(dto);
			}
			
		
				
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, st, rs);

		}
		return arr;
	}
		
	
	//실시간 엑셀
	
	public void writeLogExcel(ArrayList<PmsDto>arr) throws FileNotFoundException{ //데이터 담을 리스트 
		String path="C://Download/";
		//파일경로 
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
				
		String today = format1.format (System.currentTimeMillis());
		
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
			
		   File file = new File(path+today+"log.xls");
        
           FileOutputStream fos = new FileOutputStream(file); 
           

		HSSFWorkbook workbook=new HSSFWorkbook();//새 엑셀 생성
		HSSFSheet sheet =workbook.createSheet("실시간");//새 시트 생성
		
		HSSFRow row=null;//행
		HSSFCell cell=null;//셀
		
		//첫번째 줄
		row=sheet.createRow(0);
		//첫 번째출 cell 설정
		cell=row.createCell(0);
		cell.setCellValue("No.");
		
		cell=row.createCell(1);
		cell.setCellValue("차량번호");
		
		cell=row.createCell(2);
		cell.setCellValue("입차시간");
		
		cell=row.createCell(3);
		cell.setCellValue("사용금액");
		
		cell=row.createCell(4);
		cell.setCellValue("월정액 여부");
		
		for(int i=0;i<arr.size();i++) {
			PmsDto dto=arr.get(i);
			row=sheet.createRow(i+1);

			cell=row.createCell(0);
			cell.setCellValue(dto.getIdx());
			
			cell=row.createCell(1);
			cell.setCellValue(dto.getCnum());
			
			cell=row.createCell(2);
			cell.setCellValue(dto.getInTime());
			
			cell=row.createCell(3);
			cell.setCellValue(dto.getPay());
			
			cell=row.createCell(4);
			cell.setCellValue(dto.getMonthNum());
			
		}
		
		try {
			workbook.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
			
		}
		
		
	public void writeLogDetailExcel(ArrayList<PmsDto>arr) throws FileNotFoundException{ //데이터 담을 리스트 
		String path="C://Download/";
		//파일경로 	
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
				
		String today = format1.format (System.currentTimeMillis());
		
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
			
		   File file = new File(path+today+"Detaillog.xls");
        
           FileOutputStream fos = new FileOutputStream(file); 
           

		HSSFWorkbook workbook=new HSSFWorkbook();//새 엑셀 생성
		HSSFSheet sheet =workbook.createSheet("차량조회");//새 시트 생성
		
		HSSFRow row=null;//행
		HSSFCell cell=null;//셀
		
		//첫번째 줄
		row=sheet.createRow(0);
		//첫 번째출 cell 설정
		cell=row.createCell(0);
		cell.setCellValue("No.");
		
		cell=row.createCell(1);
		cell.setCellValue("차량번호");
		
		cell=row.createCell(2);
		cell.setCellValue("입차시간");
		
		cell=row.createCell(3);
		cell.setCellValue("출차시간");;
		
		cell=row.createCell(4);
		cell.setCellValue("사용금액");
		
		cell=row.createCell(5);
		cell.setCellValue("쿠폰사용 여부");
		cell=row.createCell(6);
		cell.setCellValue("월정액 여부");
		
		for(int i=0;i<arr.size();i++) {
			PmsDto dto=arr.get(i);
			row=sheet.createRow(i+1);

			cell=row.createCell(0);
			cell.setCellValue(dto.getIdx());
			
			cell=row.createCell(1);
			cell.setCellValue(dto.getCnum());
			
			cell=row.createCell(2);
			cell.setCellValue(dto.getInTime());
			
			cell=row.createCell(3);
			cell.setCellValue(dto.getOutTime());
			
			
			cell=row.createCell(4);
			cell.setCellValue(dto.getPay());
		
			cell=row.createCell(5);
			cell.setCellValue(dto.getCnum());
			cell=row.createCell(6);
			cell.setCellValue(dto.getMonthNum());
			
		}
		
		try {
			workbook.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
			
		}
		
		
		}
		
		
		
	  




