package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client 
{
	public static void main(String[] args) 
	{
		// Parametry serwera
		String serverName = "127.0.0.1";
		int port = 6080;
		
		// Wstepna deklaracja bufferow
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader stdIn = null;
		
		// Polaczenie sie do serwera
		try
		{
			socket = new Socket(serverName, port);	
		}
		catch(IOException e) 
		{
			System.out.println("BLAD: Nie mozna sie polaczyc z tym serwerem: " + serverName);
			System.exit(1);
		}
		
		// Dzialania na serwerze
		try 
		{		
			// Zmienne do przechwytywania odpowiedzi
			String userInput = "Witaj serwerze";
			String serverAnswer = "";
			boolean flag = true;
		
			// Inicjalizacja bufferow
			out = new PrintWriter(socket.getOutputStream(), true);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
		
			// Czytam to co wpisal user
			do
			{	
				// Odpowiadam serwerowi
				out.println(userInput);

				// Czytam odpowiedz serwera
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// Czytam to co odpowiedzial serwer / Sprawdz czy odpowiedz nie jest null i jest dluzsza niz 0 znakow
				while((serverAnswer = in.readLine()) != null && serverAnswer.length() > 0 && flag == true)
				{
					System.out.println("Server: " + serverAnswer);
					
					// Wylogowanie usera
					if(serverAnswer.equals("logout"))
					{
						return;
					}
					
					// Wyjscie z petli po otrzymaniu odpowiedzi
					if(serverAnswer.equals("Gotowe") || serverAnswer.equals("Blad, popraw dane"))
					{
						flag = false;
					}
						
					// Dodanie nowej uslugi
					if(serverAnswer.equals("Podaj po przecinku, nazwe oraz czas wykonania zamowienia"))
					{
						userInput = stdIn.readLine();
						out.println(userInput);
					}
					
					// Anulowanie zamowienia
					if(serverAnswer.equals("Podaj numer swojej uslugi, ktora chcesz anulowac"))
					{
						userInput = stdIn.readLine();
						out.println(userInput);
					}
					
					// Zamowienie uslugi
					if(serverAnswer.equals("Podaj po przecinku, numer klienta, oraz jego usluge ktora chcesz zamowic"))
					{
						userInput = stdIn.readLine();
						out.println(userInput);
					}
				}	
				flag = true;
			}
			while ((userInput = stdIn.readLine()) != null && userInput.length() > 0); 
			
		} 
		catch (IOException e) 
		{
			System.out.println("BLAD: nie mozna pobrac danych z serwera" + e);
		}	
		finally
		{
			// Zamkniecie bufferow
        	try 
        	{
        		System.out.println("Wylogowuje cie z serwera");
				in.close();
				out.close();
				socket.close();
				System.exit(1);
			} 
        	catch (IOException e) 
        	{
				e.printStackTrace();
			}
		}
	}
}
