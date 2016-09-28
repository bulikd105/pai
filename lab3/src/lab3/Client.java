package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
	public static void main(String[] args) 
	{
		String serverName = "127.0.0.1";
		int port = 6080;
		
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		BufferedReader stdIn = null;
		
		String userInput;
		
		// Polaczenie sie do serwera
		try
		{
			socket = new Socket(serverName, port);	
		}
		catch (IOException e) 
		{
			System.out.println("Blad przy laczeniu sie do tego adresu: " + serverName);
		}
		
		try 
		{	
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(in.readLine());
			System.out.print("Klient: ");
			
			String serverAnswer;
			// Pobieramy wiadomosc od usera
			while ((userInput = stdIn.readLine()) != null) 
			{
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// Wysylamy na serwer
				out.println(userInput);
				out.flush();
				
				serverAnswer = in.readLine();
				
				if(serverAnswer.equals("disconnect"))
				{
					System.out.println("Klient: zamykanie polaczenia");
					socket.close();
					break;
				}

				// Wyswietlamy odpowiedz servera
				System.out.println("Server: " + serverAnswer);
				
				System.out.print("Klient: ");
			}
			
			out.close();
			in.close();
			stdIn.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Klient, ma problem przy pobieraniu/wysylaniu danych" + e);
			
			try 
			{
				System.out.println("Klient, zamykanie socketu");
				socket.close();
			} 
			catch (IOException e1) 
			{
				System.out.println("Klient, maproblem przy zamykaniu socketu" + e1);
			}	
		}	
	}
}
