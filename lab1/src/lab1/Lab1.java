package lab1;

/* 
 * Napisz program, ktory pobiera liste plikow z linii polecen i wyswietla liczbe wierszy kazdego z nich. 
 * Program powinien utworzyc jeden watek dla kazdego z plikow i uzyc tych watkow do zliczenia liczby wierszy kazdego z plikow rownoczenie. 
 * Utworz wersje programu, ktora odczytuje pliki nie jednoczesnie a sekwencyjnie. 
 * Porownaj wydajnosc wielowatkowego i jednowatkowego programu uzywajac System.currentTimeMillis() do okreslenia czasu wykonania. 
 * Porownania dokonaj dla dwoch, trzech i pieciu plikow.
 */

public class Lab1 
{
	public static void main(String[] args)
	{
		// Check for input with filenames
		if (args.length > 0)
		{
			// Loop on each file name
			for(String fileName : args)
			{		
				// Check if file is a "*.txt" file
				if(fileName.substring(fileName.length(), 3).equals("txt"))
				{
					
				}
				else 
				{
					System.out.println("This file: " + fileName + "is not a *.txt file\n");
				}
			}
		}
		else
		{
			System.out.println("No files was given in command line\n");
		}
	}
}
