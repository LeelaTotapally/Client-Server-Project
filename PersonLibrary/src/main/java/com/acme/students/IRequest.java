package com.acme.students;

public interface IRequest {
	//public String getInputStream();

	public String getObject(String key);

	public void addParam(String key, String value);

}
