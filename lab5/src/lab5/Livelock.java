package lab5;

public class Livelock 
{
	String person1;
	String person2;
	
	public Livelock(String person1, String person2)
	{
		this.person1 = person1;
		this.person2 = person2;
	}
	
	public void Hello(Livelock lock2)
	{
		
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
