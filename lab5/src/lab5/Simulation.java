package lab5;

/* 
 * @Autor 
 * Damian Dworak
 * 187677
 * 
 * @Description
 * Napisz program, ktory zasumuluje nastepujace zjawiska: deadlock, livelock, starvation. 
 * Rodzaj symulacji powinien byc parametrem wywolania programu. 
 * Zrob ograniczenie czasowe na dane zjawisko, tak aby program sie nie zawiesil.
 */

public class Simulation 
{
	public static void main(String[] args) 
	{
		if(args.length == 1)
		{
			long start = 0;
			long end = 0;
			
			switch(args[0])
			{
				case "starvation" 	: 	System.out.println("Wybrales opcje STARVATION");
										starvation(start,end);
										System.out.println("\nZakoñczenie symulacji STARVATION");
										break;
				case "livelock" 	: 	System.out.println("Wybrales opcje LIVELOCK");
										livelock(start,end);
										break;
				case "deadlock" 	:	System.out.println("Wybrales opcje DEADLOCK");
										deadlock(start,end);
										break;
				default 			: 	System.out.println("Zly argument, podaj jeden z tych:\n1 - starvation\n2 - livelock\n3 - deadlock\n");
			}
		}
		else
		{
			System.out.println("Podales niepoprawna liczbe argumentow\n");
		}
	}

	private static void deadlock(long start, long end) 
	{
		// Dwa obiekty ktore beda korzystaly z tych samych zasobow
		String dLock1 = "Deadlock 1";
		String dLock2 = "Deadlock 2";
		
		// Dwa watki dla kazdego zasobu
		Thread t1 = new Thread(new Deadlock(dLock1, dLock2), "t1");
        Thread t2 = new Thread(new Deadlock(dLock2, dLock1), "t2");
        
        // Ustawienie czasu trwania blokowania watkow
        start = System.currentTimeMillis();
        end = start + 20*1000; // czas trwania: 30 sekund
        boolean flag = true;
        
        // Uruchomienie deadlock'a
        while(System.currentTimeMillis() < end)
        {
        	if(flag == true)
        	{
        		t1.start();
                t2.start();
                flag = false;
        	}
        }
        System.out.println("Zakoñczenie symulacji DEADLOCK");
        System.exit(1);
	}

	private static void livelock(long start, long end) 
	{
		// Deklaracja obiektow
		Livelock lock1 = new Livelock("locker1");
		Livelock lock2 = new Livelock("locker2");
		
		// Deklaracja watkow
		Livelock.MyThread thread1 = new Livelock.MyThread();
		Livelock.MyThread thread2 = new Livelock.MyThread();
		
		// Ustawienie wartosci w watkach
		thread1.setLocker1(lock1);
		thread1.setLocker2(lock2);
		thread2.setLocker1(lock2);
		thread2.setLocker2(lock1);
		
        // Ustawienie czasu trwania blokowania watkow
        start = System.currentTimeMillis();
        end = start + 20*1000; // czas trwania: 30 sekund
        boolean flag = true;
        		
        // Uruchomienie watkow
        while(System.currentTimeMillis() < end)
        {
        	if(flag == true)
        	{
        		new Thread(thread1).start();
        		new Thread(thread2).start();
                flag = false;
        	}
        }
        System.out.println("Zakoñczenie symulacji LIVELOCK");
        System.exit(1);
	}

	private static void starvation(long start, long end) 
	{
		// Obiekty dla ktorych beda tworzone watki
		Starvation starver1 = new Starvation("Startvation 1");
		Starvation starver2 = new Starvation("Startvation 2");
		Starvation starver3 = new Starvation("Startvation 3");

		// Przygotowanie watkow
		Thread t1 = new Thread("Duzy priorytet")
		{
			public void run()
			{
				starver1.Counter();
			}
		};
		Thread t2 = new Thread("Normalny priorytet")
		{
			public void run()
			{
				starver2.Counter();
			}
		};
		
		Thread t3 = new Thread("Maly priorytet")
		{
			public void run()
			{
				starver3.Counter();
			}
		};
		
		// Ustawienie priorytetow dla watkow
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MIN_PRIORITY);
		
        // Ustawienie czasu trwania blokowania watkow
        start = System.currentTimeMillis();
        end = start + 20*1000; // czas trwania: 30 sekund
        boolean flag = true;
        		
        // Uruchomienie watkow
        while(System.currentTimeMillis() < end)
        {
        	if(flag == true)
        	{
        		t1.start();
        		t2.start();
        		t3.start();
        		
                flag = false;
        	}
        }
        
		// Wylaczenie watkow
		starver1.setActive(false);
		starver2.setActive(false);
		starver3.setActive(false);
	}
}
