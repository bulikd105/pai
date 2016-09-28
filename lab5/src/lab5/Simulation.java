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
			switch(args[0])
			{
				case "starvation" 	: 	System.out.println("Wybrales opcje STARVATION");
										starvation();
										break;
				case "livelock" 	: 	System.out.println("Wybrales opcje LIVELOCK");
										livelock();
										break;
				case "deadlock" 	:	System.out.println("Wybrales opcje DEADLOCK");
										deadlock();
										break;
				default 			: 	System.out.println("Zly argument, podaj jeden z tych:\n1 - starvation\n2 - livelock\n3 - deadlock\n");
			}
		}
	}

	private static void deadlock() 
	{
		
	}

	private static void livelock() 
	{
		
	}

	private static void starvation() 
	{
		
	}
}
