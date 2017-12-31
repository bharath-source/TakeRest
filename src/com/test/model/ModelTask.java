package com.test.model;

// Complete model of the Task Information

public class ModelTask
{
	private int taskId;
	private String taskName;
	private int isDone, createdAt, doneAt;
	
	public int getTaskId()
	{
		return this.taskId;
	}
	
	public void setTaskId(int i)
	{
		this.taskId = i;
	}
	
	public String getTaskName()
	{
		return this.taskName;
	}
	
	public void setTaskName(String s)
	{
		this.taskName = s;
	}
	
	public int getIsDone()
	{
		return this.isDone;
	}
	
	public void setIsDone(int i)
	{
		this.isDone = i;
	}
	
	public int getCreatedAt()
	{
		return this.createdAt;
	}
	
	public void setCreatedAt(int i)
	{
		this.createdAt = i;
	}
	
	public int getDoneAt()
	{
		return this.doneAt;
	}
	
	public void setDoneAt(int i)
	{
		this.doneAt = i;
	}
}