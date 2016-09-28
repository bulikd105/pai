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
		System.out.println("Utworzono nowy watek: " + i);
	}

	@Override
	public void run() 
	{	
		try 
		{
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            while(true)
            {
            	// Czekaj na dane i zaczytuj wszystkie linie
            	String userInput;
            	while ((userInput = stdIn.readLine()) != null) 
            	{
            	    out.println(userInput);
            	    System.out.println("Watek: " + threadNum + " - echo: " + in.readLine());
            	}
            }
        } 
		catch (IOException e) 
		{
           System.out.println("Watek: " + threadNum + " wyrzucil blad: " + e);
        } 
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e) 
			{
				 System.out.println("Watek: " + threadNum + " nie chce sie zamknac");
			}
		}
	}
}
