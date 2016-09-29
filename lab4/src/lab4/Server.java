package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable 
{
	// Deklaracja stalych wartosci
	private final static String SERVICE_NEW 		= "nowa";
	private final static String SERVICE_RESERVED 	= "zarezerwowana";
	private final static String SERVICE_UNUSED 		= "nieuzywana";
	private final static String SERVICE_WITHDRAWN 	= "wycofana";
	
	// Glowne pola serwera dla danego klienta
	private Socket socket;
	private int clientNumber;
	private ArrayList<MyService> services;
	
	// Konstruktor
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
    				case "1" : 	DisplayList(services);
    							break;
    				case "2" : 	DisplayYourList(services);
    							break;
    				case "3" : 	ServiceAdd(services);
    							break;
    				case "4" : 	ServiceRemove(services);
    							break;
    				case "5" : 	ServiceReserve(services);
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
			Logout();
		}
	}

	private void Logout() 
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

	// Zarezerwuj usluge
	private void ServiceReserve() 
	{
		// TODO Auto-generated method stub
		
	}

	// Usun usluge
	private void ServiceRemove() 
	{
		// TODO Auto-generated method stub
		
	}

	// Dodaj usluge
	private void ServiceAdd() 
	{
		// TODO Auto-generated method stub
		
	}

	// Wyswietl liste uslug danego uzytkownika
	private void DisplayYourList(ArrayList<MyService> services) 
	{
		// TODO Auto-generated method stub
		
	}
	
	// Wyswietl liste wszystkich uslug
	private void DisplayList(ArrayList<MyService> services) 
	{
		// TODO Auto-generated method stub
		
	}
}
