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
			System.out.println("Error: file " + fileName + " does not exists");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		System.out.println(threadName + " exiting.");
	}
	
	// Method to start thread
	public void startSeq()
	{
		System.out.println("\nStarting " +  threadName );
		
		// Check if there is no started thread for this object
		if(t == null) 
		{
			try 
			{
				// Create new thread
				t = new Thread (this, threadName);
				
				// Start thread
				t.start();
				
				// Join thread with others
				t.join();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void startSim()
	{
		
	}
}