package com.pms.excel;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;

public class ExcelMember implements ExcelCommand {

	@Override
	public HSSFWorkbook execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/**파라미터 값 받아옴 **/
		String startForm = request.getParameter("startForm") == "" ? "" : request.getParameter("startForm");
		String endForm = request.getParameter("endForm") == "" ? "" : request.getParameter("endForm"); 
		String dateSearch = request.getParameter("dateSearch") == "" ? "" : request.getParameter("dateSearch"); 
		String search = request.getParameter("search") == "" ? "" : request.getParameter("search"); 
		String searchForm = request.getParameter("endForm") == "" ? "" : request.getParameter("searchForm"); 
		
		MemberManageDAO dao = MemberManageDAO.getInstance();
		ArrayList<memberManageDTO> arr = null;

		
		if((startForm !="" && startForm != null) || (endForm !="" && endForm !=null) || (searchForm !="" && searchForm !=null)) {
			/**검색 결과가 있을시 **/
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("startForm",startForm);
			map.put("endForm",endForm);
			map.put("dateSearch",dateSearch);
			map.put("search",search);
			map.put("searchForm",searchForm);
			arr = dao.ListMember(map); // 리스트 데이터 가져옴		
		}else {
			/**검색 결과가 없을시 **/
			arr = dao.ListMember();
		}
		
		/** 엑셀 시트 생성 **/
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = null;
        
        HSSFCell cell = null;
        
        row = sheet.createRow(0);
        /**엑셀 상단 제목 생성 **/
        String[] headerKey = {"회원 이름", "차량 번호", "시작 시간", "종료 시간","사용 금액","이메일","phone","구분"};
        
        for(int i=0; i<headerKey.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
        }
        
        /**엑셀 데이터 입력 **/
        for(int i=0; i<arr.size(); i++) {
            row = sheet.createRow(i + 1);
            memberManageDTO vo = arr.get(i);
            
            cell = row.createCell(0);
            cell.setCellValue(vo.getName());
            cell = row.createCell(1);
            cell.setCellValue(vo.getCNUM());
            cell = row.createCell(2);
            cell.setCellValue(vo.getStartDate());
            cell = row.createCell(3);
            cell.setCellValue(vo.getStopDate());
            cell = row.createCell(4);
            cell.setCellValue(vo.getPay());
            cell = row.createCell(5);
            cell.setCellValue(vo.getEmail());
            cell = row.createCell(6);
            cell.setCellValue(vo.getPhone());
            cell = row.createCell(7);
            cell.setCellValue(vo.getType());

            
        }
		return workbook;
	}

}
