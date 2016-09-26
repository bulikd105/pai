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
	public void main(String[] args)
	{
		// Check for input with filenames
		if (args.length > 0)
		{
			// Create table with proper filenames
			String fileList[] = new String[args.length];
			int i = 0;
			
			// Loop on each file name
			for(String fileName : args)
			{		
				// Check if file is a "*.txt" file
				if(fileName.substring(fileName.length(), 3).equals("txt"))
				{
					fileList[i] = fileName;
					i++;
				}
				else 
				{
					System.out.println("This file: " + fileName + "is not a *.txt file\n");
				}
			}
			
			if(fileList.length > 0)
			{
				// Single thread version
				System.out.println("Single Thread\n");
				SignleThread(fileList);
				System.out.println("----------------------------\n");
				
				// Simultanouse multi thread version
				System.out.println("Multiple Thread Simultanouse\n");
				MultipleThreadSim(fileList);
				System.out.println("----------------------------\n");
				
				// Sequence multi thread version
				System.out.println("Multiple Thread Sequence\n");
				MultipleThreadSeq(fileList);
				System.out.println("----------------------------\n");
			}
			else
			{
				System.out.println("No correct files were given in command line\n");
			}

		}
		else
		{
			System.out.println("No files were given in command line\n");
		}
	}
	
	public void SignleThread(String[] fName)
	{
		
	}
	
	public void MultipleThreadSim(String[] fname)
	{
		
	}
	
	public void MultipleThreadSeq(String[] fname)
	{
		
	}
}
