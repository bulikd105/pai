package lab5;

public class Starvation 
{
	private boolean isActive;
	private String name;
	private int count;
	
	public Starvation(String name)
	{
		this.name = name;
		this.isActive = true;
		this.count = 0;
	}
	
	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}

	public void Counter()
	{
		while(isActive)
		{
			count += 1;
			System.out.println(name + " numer: " + count);
		}
		System.out.println("STOP. Watek: " + name + " majacy "  + Thread.currentThread().getName() + " osiagnal wartosc: " + count);
	}
}
