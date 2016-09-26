package lab1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
			// Create table with proper filenames
			String fileList[] = new String[args.length];
			int i = 0;
			
			// Loop on each file name
			for(String fileName : args)
			{
				// Check if file is a "*.txt" file
				if(fileName.substring(fileName.length()-3, fileName.length()).equals("txt"))
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
				System.out.println("Single Thread");
				SingleThread(fileList);
				System.out.println("----------------------------\n");
				
				// Simultanouse multi thread version
				System.out.println("Multiple Thread Simultanouse");
				MultipleThreadSim(fileList);
				System.out.println("----------------------------\n");
				
				// Sequence multi thread version
				System.out.println("Multiple Thread Sequence");
				MultipleThreadSeq(fileList);
				System.out.println("----------------------------");
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
	
	public static void SingleThread(String[] fName)
	{
		for(String file : fName)
		{
			try
			{
				Path path = Paths.get(file);
				long lines = Files.lines(path).count();
				System.out.println("Lines number: " + lines);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void MultipleThreadSim(String[] fname)
	{
		
	}
	
	public static void MultipleThreadSeq(String[] fname)
	{
		
	}
}
