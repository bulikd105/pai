package lab4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.stream.Stream;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

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
			String userInput;
			String serverAnswer = "";
			
			// Inicjalizacja bufferow
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			//out.print("Witaj serwerze\n");
			
/*			// Czytam to co odpowiedzial serwer
			while((serverAnswer = in.readLine()) != null)
			{
				System.out.println("Server: " + serverAnswer);
				
				// Czytam to co wpisal user
				while ((userInput = stdIn.readLine()) != null) 
				{
					// Odpowiadam serwerowi
					out.println(userInput);
					out.flush();
				}	
			}*/
			
			// Czytam to co wpisal user
			while ((userInput = stdIn.readLine()) != null) 
			{
				
				// Odpowiadam serwerowi
				out.println(userInput);
				out.flush();
				// Czytam to co odpowiedzial serwer
				while((serverAnswer = in.readLine()) != null)
				{
					// Zamykanie socketu
					if(serverAnswer.equals("logout"))
					{
						System.out.println("Wylogowuje cie z serwera");
					
						out.close();
						in.close();
						stdIn.close();
						socket.close();
						System.exit(1);
						//Logout(socket, out, in, stdIn);
					}
					System.out.println("Server: " + serverAnswer);
				}

				
			}
			
			
			// Zamykanie bufferow
			out.close();
			in.close();
			stdIn.close();
		} 
		catch (IOException e) 
		{
			System.out.println("BLAD: nie mozna pobrac danych z serwera" + e);
			//Logout(socket, out, in, stdIn);
			try 
			{
				System.out.println("Zamykanie polaczenia z serwerem");
				socket.close();
			} 
			catch (IOException e1) 
			{
				System.out.println("BLAD: odlaczanie od serwera nie powiodlo sie");
				System.exit(1);
			}	
		}	
	}

/*	private static void Logout(Socket socket, PrintWriter out, BufferedReader in, BufferedReader stdIn) 
	{
		try 
		{
			// Zamykanie bufferow
			System.out.println("Zamykanie polaczenia z serwerem");		
			out.close();
			in.close();
			stdIn.close();
			socket.close();
		} 
		catch (IOException e) 
		{
			System.out.println("BLAD: odlaczanie od serwera nie powiodlo sie");
			System.exit(1);
		}	
	}*/
}
