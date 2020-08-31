package com.pms.command;

import java.util.HashMap;

public class Action {
	private static Action instance = new Action();
	
	public static Action getInstance() {
		return instance;
	}

	private HashMap<String, Command> map = new HashMap<String, Command>();
	
	private Action() {
		map.put("/daily", new StatDailyCommand());
		map.put("/empty", new EmptyCommand());
      	map.put("/stat/test",new TestCommand());	
      	map.put("/loglist",new LogListCommand());
      	map.put("/imgupdate",new LoglmgModifyAction());
      	map.put("/index",new IndexCommand());
      	map.put("/logdetail",new LogDetailCommand());
      	map.put("/member",new MemberManageCommand());
      	map.put("/memberInsert",new MemberInsertCommand());
      	map.put("/loglist",new LogListCommand());
      	map.put("/login/login",new LoginCommand());
      	map.put("/login/join",new JoinCommand());
      	map.put("/login/searchId",new SearchIdCommand());
      	map.put("/login/searchPass",new SearchPassCommand());
      	map.put("/login/setting",new SettingCommand());
      	map.put("/login/change",new ChangeCommand());
     	map.put("/memberSearch",new memberSearchCommand());
      	map.put("/new_cp_dc",new CDCommand());
      	map.put("/addc_d",new Add_C_DCommand());
      	map.put("/search_cp_dc",new CDCommand2());  	
      	map.put("/search_C_D",new Search_C_DCommand());  	  	
      	map.put("/delete_C_D",new Delete_C_DCommand());  	  	
      	map.put("/memberUpdate",new MemberUpdateCommand());  	  	
//      	map.put("/login/login",new LoginCommand());
//      	map.put("/login/join",new JoinCommand());
//      	map.put("/login/searchId",new SearchIdCommand());
//      	map.put("/login/searchPass",new SearchPassCommand());
//      	map.put("/login/setting",new SettingCommand());
      	
	}
	
	public Command getAction(String command) {
		Command action = null;
		action = map.get(command);
		if(action == null) {
			action = map.get("/empty");
		}
		return action;
	}

}
