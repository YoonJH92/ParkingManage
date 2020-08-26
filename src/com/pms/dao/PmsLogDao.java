package com.pms.dao;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pms.dto.PmsDto;
import com.pms.util.DBConnectionMgr;

public class PmsLogDao {	
	private static PmsLogDao instance;
	public static PmsLogDao getInstance() {
		if (instance == null) {
			instance = new PmsLogDao();
}
	return instance;		
}
	private DBConnectionMgr pool;	
	private static final String ENCTYPE="utf-8";
	private int sizeLimit =300*200*15;

	public PmsLogDao() {
		pool = DBConnectionMgr.getInstance();
	}
	public ArrayList<PmsDto> viewList(){
    	Connection con = null;
    	Statement st= null;
    	ResultSet rs= null;
    	ArrayList<PmsDto> arr = new ArrayList<PmsDto>();
  
    	try {
			con = pool.getConnection();			
			String sql = " select * from pms_log where out_time is null ";
			st= con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PmsDto dto=new PmsDto();
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
		}finally {
			pool.freeConnection(con, st, rs);
			
		}
    	return arr;
    }	
	public void imgUpdate(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String savepath = req.getServletContext().getRealPath("/img/");
		//실제파일 경로 경로 
		int maxSize= 1024 * 1024 * 50;//파일크기 제한
		String encoding ="utf-8";
		System.out.println(savepath);

		try {
			con = pool.getConnection();
		
			MultipartRequest multi=new MultipartRequest(req, savepath,maxSize,encoding,new DefaultFileRenamePolicy());
			Enumeration fileNames=multi.getFileNames();
			//DefaultFileRenamePolicy() -> 중복파일명을 위한 매개변수
			boolean save=true; //파일 저장 성공
        	String fileInput="";//폼으로 받아온 filename
        	String fileName="";//저장된 파일 이름 
        	String originFileName="";//원본 파일 이름 
        	String type="";//저장된 파일 종류 
        	File fileobj=null;//저장된 파일 객체
        	String fileExtend = ""; //jpg,png,gif 등 확장자
            String fileSize = ""; //저장된 파일 사이즈
            String newFileName="pms_"+System.currentTimeMillis()+fileName;//저장된 파일을  바꿀 이름
            System.out.println("newFileName"+newFileName);
            File Folder = new File(savepath);
            // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
            if (!Folder.exists()) {
            	try{
            		Folder.mkdir(); //폴더 생성합니다.
            		System.out.println("폴더가 생성되었습니다.");
            	} 
            	catch(Exception e){
            		e.getStackTrace();
            	}        
            }else {
            	System.out.println("이미 폴더가 생성되어 있습니다.");
            }   
            while(fileNames.hasMoreElements()){ //있으면
            	fileInput=(String)fileNames.nextElement();//폼에서 받아온 요소 
            	 fileName = multi.getFilesystemName(fileInput);
                 if(fileName != null){
                     type = multi.getContentType(fileInput);
                     fileobj = multi.getFile(fileInput);
                     originFileName = multi.getOriginalFileName(fileInput);
                     fileExtend = fileName.substring(fileName.lastIndexOf(".")+1);//"file1.jpg"라면 jpg 반환
                     fileSize = String.valueOf(fileobj.length());//file도 결국 문자열이므로 length()로 반환
                     String[] splitType = type.split("/");
                     if(!splitType[0].equals("image")){
                         save=false;
                         fileobj.delete(); //저장된 파일 객체로 삭제
                         break;
                     }else{//만약 이미지 파일이면 저장 파일의 이름 바꾼다.
                         newFileName += "."+fileExtend;
                         fileobj.renameTo(new File(savepath+"\\"+newFileName));
                     }
                 }
             }
            if(save) {
			sql=" update pms_log set c_img = ? where idx = ? ";
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
}
	
	
	


		
		
	