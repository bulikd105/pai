package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
		
		try
		{
			socket = new Socket(serverName, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
		}
		catch (UnknownHostException e)
		{
			System.out.println("Nie rozpoznaje tego adresu: " + serverName);
		} 
		catch (IOException e) 
		{
			System.out.println("Blad przy pobieraniu danych dla tego adresu: " + serverName);
		}
		
		try 
		{
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(in.readLine());
			System.out.print("Klient: ");
		
			while ((userInput = stdIn.readLine()) != null) 
			{
				out.println(userInput);
				System.out.println("Server: " + in.readLine());
				System.out.print("Klient: ");
			}
		} 
		catch (IOException e) 
		{
			System.out.println("Klient, ma problem przy pobieraniu/wysylaniu danych");
		}	
	
		try 
		{
			out.close();
			in.close();
			stdIn.close();
			socket.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Klient, maproblem przy zamykaniu bufferow");
		}	
	}
}
