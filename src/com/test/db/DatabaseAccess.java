package com.test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.test.model.ModelTask;

public class DatabaseAccess
{
	private Connection connection;
	private PreparedStatement stmt;
	
	public DatabaseAccess()
	{
		this.connection = new DatabaseConnection().getConnection();
	}
	
	public int addTask(String taskName, int isDone, int createdAt, int doneAt)
	{
		int status = 0;
		try
		{
			this.stmt = this.connection.prepareStatement("INSERT INTO tasks(taskName, isDone, createdAt, doneAt) VALUES(?, ?, ?, ?)");
			this.stmt.setString(1, taskName);
			this.stmt.setInt(2, isDone);
			this.stmt.setInt(3, createdAt);
			this.stmt.setInt(4, doneAt);
			status = this.stmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				this.stmt.close();
				this.connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public ArrayList<ModelTask> getTask(int taskId)
	{
		ArrayList<ModelTask> singleTask = new ArrayList<>();
		ModelTask modelTask = new ModelTask();
		try
		{
			this.stmt = this.connection.prepareStatement("SELECT * FROM tasks WHERE taskId = ?");
			stmt.setInt(1, taskId);
			ResultSet rset = this.stmt.executeQuery();
			
			while(rset.next())
			{
				modelTask.setTaskId(rset.getInt("taskId"));
				modelTask.setTaskName(rset.getString("taskName"));
				modelTask.setIsDone(rset.getInt("isDone"));
				modelTask.setCreatedAt(rset.getInt("createdAt"));
				modelTask.setDoneAt(rset.getInt("doneAt"));
				singleTask.add(modelTask);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				this.stmt.close();
				this.connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return singleTask;
	}
	
	public int editTask(int taskId, String taskName, int isDone, int createdAt, int doneAt)
	{
		int status = 0;
		
		try
		{
			this.stmt = this.connection.prepareStatement("UPDATE tasks SET taskName = ?, isDone = ?, createdAt = ?, doneAt = ? WHERE taskId = ?");
			stmt.setString(1, taskName);
			stmt.setInt(2, isDone);
			stmt.setInt(3, createdAt);
			stmt.setInt(4, doneAt);
			stmt.setInt(5, taskId);
			status = stmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				this.stmt.close();
				this.connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public ArrayList<ModelTask> readAllTasks()
	{
		ArrayList<ModelTask> allTask = new ArrayList<>();
		
		try
		{
			this.stmt = this.connection.prepareStatement("SELECT * FROM tasks");
			ResultSet rset = this.stmt.executeQuery();
			
			while(rset.next())
			{
				ModelTask modelTask = new ModelTask();
				modelTask.setTaskId(rset.getInt("taskId"));
				modelTask.setTaskName(rset.getString("taskName"));
				modelTask.setIsDone(rset.getInt("isDone"));
				modelTask.setCreatedAt(rset.getInt("createdAt"));
				modelTask.setDoneAt(rset.getInt("doneAt"));
				allTask.add(modelTask);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				this.stmt.close();
				this.connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return allTask;
	}
}