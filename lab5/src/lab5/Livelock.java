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
		// Sprawdzaj czy drugi obiekt powiedzial "Witaj", jesli tak, odpowiedz mu
		while(!lock.word.equals("Witaj"))
		{
			try
			{
				System.out.println("Watek: " + this.gender + " przechodzi w stan uspienia, poniewaz " + lock.gender + " nie powiedzial 'Witaj'");
				Thread.sleep(4000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}	
		this.word = "Witaj";
	}
	
	public static class MyThread implements Runnable
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
