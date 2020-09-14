package com.pms.excel;

import java.util.HashMap;

import com.pms.excel.ExcelCommand;


public class ExcelAction {
private static ExcelAction instance = new ExcelAction();
	
	public static ExcelAction getInstance() {
		return instance;
	}

	private HashMap<String, ExcelCommand> map = new HashMap<String, ExcelCommand>();
	
	private ExcelAction() {
		map.put("/daily", new ExcelDaily());
		map.put("/monthly", new ExcelMonthly());
		map.put("/logexcel", new ExcelLog());
		map.put("/time", new ExcelTime());
	}
	
	public ExcelCommand getAction(String command) {
		ExcelCommand action = null;
		action = map.get(command);
		if(action == null) {
			action = map.get("/empty");
		}
		return action;
	}
}
