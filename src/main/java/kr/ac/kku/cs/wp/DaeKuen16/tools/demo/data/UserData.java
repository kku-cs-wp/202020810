package kr.ac.kku.cs.wp.DaeKuen16.tools.demo.data;

import java.util.Map;

import kr.ac.kku.cs.wp.DaeKuen16.user.entity.User;

public class UserData {
	
	private static UserData THIS;
	
	private UserData() {
		this.initData();
	}
	private Map <String, User> users;
	
	private void initData() {
		
	}
	
	public Map<String, User> getData() {
		return users;
	}
	
	public static synchronized UserData getInstance() {
        if (THIS == null) {
        	THIS = new UserData();
        }
        return THIS;
    }

}
