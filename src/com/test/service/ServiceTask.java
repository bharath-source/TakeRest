package com.test.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.json.JsonObject;
import com.google.gson.Gson;

import com.test.db.DatabaseAccess;

@Path("/api")
public class ServiceTask
{
	@POST
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addTask(JsonObject ip)
	{
		String status = "{\"status\": 0}";
		try
		{
			
			String taskName = ip.getString("taskName").toString();
			int isDone = ip.getInt("isDone");
			int createdAt = ip.getInt("createdAt");
			int doneAt = ip.getInt("doneAt");
			int s = new DatabaseAccess().addTask(taskName, isDone, createdAt, doneAt);
			if(s == 1)
				status = "{\"status\": 1}";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	@POST
	@Path("tasks/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getTask(JsonObject ip)
	{
		String row = "";
		try
		{
			int taskId = ip.getInt("taskId");
			row = new Gson().toJson(new DatabaseAccess().getTask(taskId));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return row;
	}
	
	@POST
	@Path("/tasks/edit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editTask(JsonObject ip)
	{
		String status = "{\"status\": 0}";
		try
		{
			int taskId = ip.getInt("taskId");
			String taskName = ip.getString("taskName").toString();
			int isDone = ip.getInt("isDone");
			int createdAt = ip.getInt("createdAt");
			int doneAt = ip.getInt("doneAt");
			int s = new DatabaseAccess().editTask(taskId, taskName, isDone, createdAt, doneAt);
			if(s == 1)
				status = "{\"status\": 1}";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	@GET
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllTasks()
	{
		return new Gson().toJson(new DatabaseAccess().readAllTasks());
	}
}