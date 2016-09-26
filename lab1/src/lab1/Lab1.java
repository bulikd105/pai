package lab1;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
			ArrayList<String> fileList = new ArrayList<String>();
			
			// Loop on each file name
			System.out.println("Check filenames for incorrect ones");
			for(String fileName : args)
			{
				// Check if file is a "*.txt" file
				if(fileName.length() > 3 && fileName.substring(fileName.length()-3, fileName.length()).equals("txt"))
				{
					fileList.add(fileName);
					System.out.println("Correct filename: " + fileName);
				}
				else 
				{
					System.out.println("Incorrect filename: " + fileName);
				}
			}
			
			if(!fileList.isEmpty())
			{
				// Single thread version
				System.out.println("\nSingle Thread started");
				SingleThread(fileList);
				System.out.println("----------------------------\n");
				
				// Simultanouse multi thread version
				System.out.println("\nMultiple Thread Simultanouse started");
				MultipleThreadSim(fileList);
				System.out.println("----------------------------\n");
				
				// Sequence multi thread version
				System.out.println("\nMultiple Thread Sequence started");
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
	
	public static void SingleThread(ArrayList<String> fName)
	{
		for(String file : fName)
		{
			try
			{
				Path path = Paths.get(file);
				long lines = Files.lines(path).count();
				System.out.println("Lines number: " + lines);
			}
			catch (NoSuchFileException e) 
			{
				System.out.println("Error: file " + file + " does not exists");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void MultipleThreadSim(ArrayList<String> fname)
	{
		
	}
	
	public static void MultipleThreadSeq(ArrayList<String> fname)
	{
		
	}
}
