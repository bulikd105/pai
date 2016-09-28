package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server implements Runnable
{
	private Socket socket;
	private int threadNum;
	
	public Server(Socket accept, int i) 
	{
		this.socket = accept;
		this.threadNum = i;
		System.out.println("Dodano nowego klienta: " + i);
	}

	@Override
	public void run() 
	{	
		try 
		{
			// Bufery do odbierania i wysylania wiadomosci
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
        	out.println("Witaj na serwerze, uzytkowniku: " + threadNum + "\n");
        	String userInput = "";
        	
        	// Wyswietlanie wiadomosci od klientow na serwerze
        	while ((userInput = in.readLine()) != null) 
        	{
        	    System.out.println("Klient: " + threadNum + " - powiedzial: " + userInput);
        	    out.println(userInput);
        	    out.flush();
        	}
        	
        	// Zamkniecie bufferow
        	in.close();
        	out.close();
        } 
		catch (IOException e) 
		{
           System.out.println("Klient: " + threadNum + " wyrzucil blad: " + e);
        } 
		finally // W razie problemu, zamknij socket
		{
			try
			{
				socket.close();
			}
			catch (IOException e) 
			{
				 System.out.println("Klient: " + threadNum + " nie chce sie zamknac");
			}
		}
	}
}
