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
            
            System.out.println("Witamy w naszym banku\n");
            
            // Uruchomienie glownej petli
            while((userInput = in.readLine()) != null)
            {
            	System.out.println("Wybierz operacje ktora chcesz wykonac:");
            	System.out.println("1 - Wyswietl liste dostepnych uslug\n2 - Wyswietl liste swoich uslug\n" + 
            					   "3 - Dodaj nowa usluge\n4 - Wycofaj swoja usluge\n5 - Zarezerwuj usluge\n6 - Wyjdz");
            	switch(userInput)
            	{
    				case "1" : 	DisplayList();
    							break;
    				case "2" : 	DisplayYourList();
    							break;
    				case "3" : 	ServiceAdd();
    							break;
    				case "4" : 	ServiceRemove();
    							break;
    				case "5" : 	ServiceReserve();
    							break;
    				case "6" : 	Logout();
    							break;
    				default  : 	System.out.println("Nie ma takiej opcji\n");	
    						   	break;
            	}
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

	private void Logout() 
	{
		// TODO Auto-generated method stub
		
	}

	private void ServiceReserve() 
	{
		// TODO Auto-generated method stub
		
	}

	private void ServiceRemove() 
	{
		// TODO Auto-generated method stub
		
	}

	private void ServiceAdd() 
	{
		// TODO Auto-generated method stub
		
	}

	private void DisplayYourList() 
	{
		// TODO Auto-generated method stub
		
	}

	private void DisplayList() 
	{
		// TODO Auto-generated method stub
		
	}
}
