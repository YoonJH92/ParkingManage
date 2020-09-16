package com.pms.excel;

import java.util.HashMap;

import com.pms.excel.ExcelCommand;


public class ExcelAction {
private static ExcelAction instance = new ExcelAction();
	
	public static ExcelAction getInstance() {
		return instance;
	}

	private HashMap<String, ExcelCommand> map = new HashMap<String, ExcelCommand>();
	
	/**엑셀 ACTION 위치설정**/
	private ExcelAction() {
		map.put("/daily", new ExcelDaily());
		map.put("/monthly", new ExcelMonthly());
		map.put("/logexcel", new ExcelLog());
		map.put("/time", new ExcelTime());
		map.put("/logDetailExcel", new ExcelDatailLog());
		map.put("/member", new ExcelMember());
	}
	
	/** 객체 반환 **/
	public ExcelCommand getAction(String command) {
		ExcelCommand action = null;
		action = map.get(command);
		if(action == null) {
			action = map.get("/empty");
		}
		return action;
	}
}
