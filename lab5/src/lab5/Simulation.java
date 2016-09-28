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
		String dLock1 = "Deadlock 1";
		String dLock2 = "Deadlock 2";
		
		Thread t1 = new Thread(new Deadlock(dLock1, dLock2), "t1");
        Thread t2 = new Thread(new Deadlock(dLock2, dLock1), "t2");
        
        start = System.currentTimeMillis();
        end = start + 10*1000;

        t1.start();
        t2.start();

             
        System.out.println("Zakoñczenie symulacji DEADLOCK");
	}

	private static void livelock(long start, long end) 
	{
		
	}

	private static void starvation(long start, long end) 
	{
		
	}
}
