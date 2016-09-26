package lab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/* 
 * @Autor 
 * Damian Dworak
 * 187677
 * 
 * @Description
 * Napisz program, ktory pobiera liste plikow z linii polecen i wyswietla liczbe wierszy kazdego z nich. 
 * Program powinien utworzyc jeden watek dla kazdego z plikow i uzyc tych watkow do zliczenia liczby wierszy kazdego z plikow rownoczenie. 
 * Utworz wersje programu, ktora odczytuje pliki nie jednoczesnie a sekwencyjnie. 
 * Porownaj wydajnosc wielowatkowego i jednowatkowego programu uzywajac System.currentTimeMillis() do okreslenia czasu wykonania. 
 * Porownania dokonaj dla dwoch, trzech i pieciu plikow.
 * 
 */

public class Lab1 
{
	public static void main(String[] args)
	{
		// Check for input with filenames
		if (args.length > 0)
		{
			//Declare variable to measure time
			long t1, t2, t3;
			long start = 0, end = 0;
			
			// Create list for proper filenames
			ArrayList<String> fileList = new ArrayList<String>();
			
			// Loop on each file name
			System.out.println("Check filenames for incorrect ones\n");
			for(String fileName : args)
			{
				// Check if file is a "*.txt" file
				if(fileName.length() > 3 && fileName.substring(fileName.length()-3, fileName.length()).equals("txt"))
				{
					// Add file to the list
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
				System.out.println("----------------------------");
				
				// Single thread version
				start = System.currentTimeMillis();
				System.out.println("\nSingle Thread started\n");
				
				SingleThread(fileList);
				
				end = System.currentTimeMillis();
				t1 = end - start;
				System.out.println("----------------------------\n");
				
				// Simultanouse multi thread version
				start = System.currentTimeMillis();
				System.out.println("Multiple Thread Simultanouse started\n");
				
				MultipleThreadSim(fileList);
				
				end = System.currentTimeMillis();
				t2 = end - start;
				System.out.println("----------------------------\n");
				
				// Sequence multi thread version
				start = System.currentTimeMillis();
				System.out.println("Multiple Thread Sequence started\n");
				
				MultipleThreadSeq(fileList);
				
				end = System.currentTimeMillis();
				t3 = end - start;
				System.out.println("----------------------------\n");
				
				System.out.println("Time for Single Thread: " + t1);
				System.out.println("Time for Multiple Thread Simultanouse: " + t2);
				System.out.println("Time for Multiple Thread Sequence: " + t3);
				
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
				// Read filepath
				Path path = Paths.get(file);
				
				// Count lines in this file
				long lines = Files.lines(path).count();
				System.out.println("File: " + file + " has " + lines + " line(s)");
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
	
	public static void MultipleThreadSim(ArrayList<String> fName)
	{
		ArrayList<CustomThread> threadList = new ArrayList<CustomThread>();
		String threadName;

		for(String file : fName)
		{
			// Create name for thread
			threadName = ("Thread: " + file);
			
			// Create thread for each file
			CustomThread thread = new CustomThread(threadName,file);
			
			// Add each thread to list
			threadList.add(thread);
		}
		
		System.out.println("\n");
		
		// Start threads
		for(CustomThread thread : threadList)
		{
			thread.ThreadStart();
		}
		
		for(CustomThread thread : threadList)
		{
			thread.ThreadJoin();
		}
	}
	
	public static void MultipleThreadSeq(ArrayList<String> fName)
	{
		ArrayList<CustomThread> threadList = new ArrayList<CustomThread>();
		String threadName;

		for(String file : fName)
		{
			// Create name for thread
			threadName = ("Thread: " + file);
			
			// Create thread for each file
			CustomThread thread = new CustomThread(threadName,file);
			
			// Add each thread to list
			threadList.add(thread);
		}
		
		System.out.println("\n");
		
		for(CustomThread thread : threadList)
		{
			thread.ThreadStart();
			thread.ThreadJoin();
		}
	}
}
