package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable 
{
	private Socket socket;
	private int clientNumber;
	private ArrayList<MyService> services;
	
	
	public Server(Socket socket, int clientNumber, ArrayList<MyService> services) 
	{
		this.socket = socket;
		this.clientNumber = clientNumber;
		this.services = services;
		System.out.println("Dodano nowego klienta: " + this.clientNumber);
	}

	@Override
	public void run() 
	{
		try
		{
			// Bufery do odbierania i wysylania wiadomosci
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String userInput = "";
            
            // Uruchomienie glownej petli
            while((userInput = in.readLine()) != null)
            {
            	
            }
            
        	// Zamkniecie bufferow
        	in.close();
        	out.close();
		}
		catch(IOException e)
		{
			System.out.println("Klient " + clientNumber + " zglosil blad: " + e);
		}
		finally // W razie gdyby klient zglosil blad, rozlacz go
		{
			try
			{
				System.out.println("Odlaczanie klienta nr: " + clientNumber);
				socket.close();
			}
			catch (IOException e) 
			{
				 System.out.println("Odlaczanie klienta nr: " + clientNumber + " nie powiodlo sie");
			}
		}
	}
}
