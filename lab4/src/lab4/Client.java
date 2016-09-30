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
			boolean flagGlobal = true;
			boolean flagClient = false;
			boolean flagServer = true;
			
			
			// Inicjalizacja bufferow
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			dIS = new DataInputStream(socket.getInputStream());
			
/*			out.print("Witaj serwerze\n");
			
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
				
				// Czytam to co wpisal user
				while ((userInput = stdIn.readLine()) != null) 
				{
					// Odpowiadam serwerowi
					out.println(userInput);
					out.flush();
				}	
			}
			*/
			
/*			out.print("Witaj serwerze\n");
			while(flag == true)
			{
				if((userInput = stdIn.readLine()) != null)
				{
					// Odpowiadam serwerowi
					out.println(userInput);
					out.flush();
				}
				
				if((serverAnswer = in.readLine()) != null)
				{
					// Zamykanie socketu
					if(serverAnswer.equals("logout"))
					{
						System.out.println("Wylogowuje cie z serwera");
						flag = false;
					}
					System.out.println("Server: " + serverAnswer);
				}
			}*/
			
			//out.println("Witaj serwerze\n");
			while(flagGlobal == true)
			{

				while(flagServer == true)
				{
					serverAnswer = in.readLine();
					System.out.println("Server: " + serverAnswer);
					// Zamykanie socketu
					if(serverAnswer.equals("logout"))
					{
						flagGlobal = false;
					}
					
					if(in.readLine() == null)
					{
						flagClient = true;
						flagServer = false;
					}
				}
				
				while(flagClient == true) 
				{
					userInput = stdIn.readLine();
					out.println(userInput);
					out.flush();
					
					if(stdIn.readLine() == null)
					{
						flagClient = false;
						flagServer = true;
					}
				}
			}
			
			
			
			
			
			
			/*// Czytam to co wpisal user
			while ((userInput = stdIn.readLine()) != null && flag == true) 
			{	
				// Odpowiadam serwerowi
				out.println(userInput);
				out.flush();
				// Czytam to co odpowiedzial serwer
				while((serverAnswer = in.readLine()) != null && flag == true)
				{
					System.out.println("Server: " + serverAnswer);
					// Zamykanie socketu
					if(serverAnswer.equals("logout"))
					{
						flag = false;
					}
				}	
			}*/
			
			
			
			
			
			/*out.print("Witaj serwerze\n");
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
			*/
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
