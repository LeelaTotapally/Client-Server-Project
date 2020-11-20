package com.acme.students;

import java.util.HashMap;
import java.util.Map;

public class Request implements IRequest {
	Map<String, String> map;
    public Request()
    {
	 map = new HashMap<String, String>();
    }

	@Override
	public String getObject(String key) {
		String value = map.get(key);
		return value;
	}
	
	public  void addParam(String key, String value)
	{
		map.put(key, value);
	}

}
