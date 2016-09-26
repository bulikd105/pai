package lab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomThread implements Runnable
{
	private Thread t;
	private String threadName;
	private String fileName;
	
	// Create new thread
	public CustomThread(String threadName, String fileName)
	{
		this.threadName = threadName;
		this.fileName = fileName;
		System.out.println("Creating " +  threadName );
	}
	
	@Override
	public void run() 
	{
		System.out.println("Running " +  threadName );
		
		try 
		{
			// Read filepath
			Path path = Paths.get(fileName);
			
			// Count lines in this file
			long lines = Files.lines(path).count();
			System.out.println("File: " + fileName + " has " + lines + " line(s)");
			
		}
		catch (NoSuchFileException e) 
		{
			System.out.println("Error: file " + fileName + " does not exists\n");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		System.out.println(threadName + " exiting.\n");
	}
	
	// Start thread
	public void ThreadStart()
	{
		System.out.println("Starting " +  threadName);
		
		// Check if there is no started thread for this object
		if(t == null) 
		{
			// Create new thread
			t = new Thread (this, threadName);
			
			// Start thread
			t.start();
		}
	}
	
	// Join thread
	public void ThreadJoin()
	{
		try 
		{
			t.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}