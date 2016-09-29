package lab5;

public class Livelock 
{
	private String gender;
	private String word = "!";
	
	public Livelock(String gender)
	{
		this.gender = gender;
	}
	
	public void Hello(Livelock lock)
	{
		while(!lock.word.equals("Witaj"))
		{
			
		}
		this.word = "Witaj";
	}
	
	public class Thread implements Runnable
	{
		// Obiekty ktore klasy nadrzednej
		Livelock lock1;
		Livelock lock2;
		
		// Ustawianie wartosci dla obiektow
		void setLocker1(Livelock lock)
		{
			this.lock1 = lock;
		}
		
		void setLocker2(Livelock lock)
		{
			this.lock2 = lock;
		}
		
		// Wywowalnie metody dla obiektu 
		@Override
		public void run() 
		{
			lock1.Hello(lock2);
		}
	}	
}
