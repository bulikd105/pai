package lab5;

public class Deadlock implements Runnable
{
	private String name1;
	private String name2;
	
	public Deadlock(String name1, String name2)
	{
		this.name1 = name1;
		this.name2 = name2;
	}
	
	@Override
	public void run() 
	{
		String name = Thread.currentThread().getName();
		
        System.out.println("locking on " + name1);
        synchronized (name1) 
        {
			System.out.println("locked on " + name1);
			Method();
			System.out.println("locking on " + name2);
			synchronized (name2) 
			{
				System.out.println("locked on " + name2);
				Method();
			}
			System.out.println(name + "unlocked on " + name2);
		}
		System.out.println(name + "unlocked on " + name1);
		
		System.out.println(name + "finished execution.");		
	}

	private void Method() 
	{
		try 
		{
			Thread.sleep(30000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}
}
