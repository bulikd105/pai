package lab4;

import java.io.BufferedReader;
import java.io.DataInputStream;
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

		DataInputStream dIS = null;
		
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
			String userInput = "";
			String serverAnswer = "";
			boolean flag = true;
		
			// Inicjalizacja bufferow
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			dIS = new DataInputStream(socket.getInputStream());
			
			out.print("Witaj serwerze\n");
			

			// Czytam to co wpisal user
			while ((userInput = stdIn.readLine()) != null && userInput.length() > 0 && flag == true) 
			{	
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// Odpowiadam serwerowi
				out.println(userInput);
				out.flush();

				// Czytam to co odpowiedzial serwer / Sprawdz czy odpowiedz nie jest null i jest dluzsza niz 0 znakow
				while((serverAnswer = in.readLine()) != null && serverAnswer.length() > 0 && flag == true)
				{
					System.out.println("Server: " + serverAnswer);
					// Zamykanie socketu
					if(serverAnswer.equals("logout"))
					{
						flag = false;
					}
				}	
			}
			
			

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
