package lab1;

public class Lab1Threads implements Runnable
{
	private Thread t;
	private String threadName;
	
	// Create new thread
	public void Lab1Thread(String name)
	{
		threadName = name;
		System.out.println("Creating " +  threadName );
	}
	
	@Override
	public void run() 
	{

		
	}
	
	public void start()
	{
		
	}

}
