package com.acme.students;

public class Response {
	public String status = "Done";
	
	public Response(String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		return status;
	}
}
