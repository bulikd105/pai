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
	private final static String LOGOUT			 	= "logout";
	
	// Glowne pola serwera dla danego klienta
	private Socket socket;
	private int clientNumber;
	private ArrayList<MyService> serviceList;
	
	// Konstruktor
	public Server(Socket socket, int clientNumber, ArrayList<MyService> serviceList) 
	{
		this.socket = socket;
		this.clientNumber = clientNumber;
		this.serviceList = serviceList;
		System.out.println("Dodano nowego klienta: " + this.clientNumber);
	}

	@Override
	public void run() 
	{
		BufferedReader in = null;
        PrintWriter out = null;
		
		try
		{
			Fill();
			
			// Bufery do odbierania i wysylania wiadomosci
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String userInput = "";
            boolean flag = true;
            
            out.println("Witamy w naszym banku\nTw�j numer to: " + this.clientNumber);
           
            // Uruchomienie glownej petli
            //while((userInput = in.readLine()) != null && flag == true)
            while(flag == true)
            {
            	out.println("Wybierz operacje ktora chcesz wykonac:");
            	out.println("1 - Wyswietl liste dostepnych uslug\n2 - Wyswietl liste swoich uslug\n" + 
            					   "3 - Dodaj nowa usluge\n4 - Wycofaj swoja usluge\n5 - Zarezerwuj usluge\n6 - Wyjdz");
            	userInput = in.readLine();
            	System.out.println("Klient: " + this.clientNumber + " powiedzial - " + userInput);
            	if(userInput != null && userInput.length() > 0)
            	{
            		switch(userInput)
	            	{
	    				case "1" : 	DisplayList(serviceList,out);
	    							break;
	    				case "2" : 	DisplayYourList(serviceList, out);
	    							break;
	    				case "3" : 	ServiceAdd(serviceList);
	    							break;
	    				case "4" : 	ServiceRemove(serviceList);
	    							break;
	    				case "5" : 	ServiceReserve(serviceList);
	    							break;
	    				case "6" : 	out.println(LOGOUT);
	    							System.out.println("Klient: " + this.clientNumber + " wylogowuje sie");
	    							flag = false;
	    							break;
	    				default  : 	out.println("Nie ma takiej opcji\n");	
	    						   	break;
	            	}           		
            	}
            	else
            	{
            		System.out.println("Klient: " + this.clientNumber + " przeslal null'a, zamykajac tym samym polaczenie");
            		break;
            	}
            }
		}
		catch(IOException e)
		{
			System.out.println("Klient " + clientNumber + " zglosil blad: " + e);
		}
		finally // W razie gdyby klient zglosil blad, rozlacz go
		{
        	// Zamkniecie bufferow
        	try 
        	{
				in.close();
				out.close();
				socket.close();
			} 
        	catch (IOException e) 
        	{
				e.printStackTrace();
			}
		}
	}
	
	private void Fill() 
	{
		MyService obj1 = new MyService(1, 1, 10, "obj1");
		MyService obj2 = new MyService(1, 2, 20, "obj2");
		MyService obj3 = new MyService(2, 1, 30, "obj3");
		MyService obj4 = new MyService(3, 1, 40, "obj4");
		
		obj1.setOrderStatus(SERVICE_NEW);
		obj2.setOrderStatus(SERVICE_NEW);
		obj3.setOrderStatus(SERVICE_NEW);
		obj4.setOrderStatus(SERVICE_NEW);
		
		serviceList.add(obj1);
		serviceList.add(obj2);
		serviceList.add(obj3);
		serviceList.add(obj4);
	}

	// Zarezerwuj usluge
	private MyService ServiceReserve(ArrayList<MyService> serviceList) 
	{
		MyService service = null;
		
		return service;
		
	}

	// Usun usluge
	private MyService ServiceRemove(ArrayList<MyService> serviceList) 
	{
		MyService service = null;
		
		return service;
		
	}

	// Dodaj usluge
	private MyService ServiceAdd(ArrayList<MyService> serviceList) 
	{
		MyService service = null;
		
		return service;
		
	}

	// Wyswietl liste uslug danego uzytkownika
	private void DisplayYourList(ArrayList<MyService> serviceList, PrintWriter out) 
	{
		// Tworzymy temp liste dla uslug klienta
		ArrayList<MyService> clientList = new ArrayList<MyService>();
		
		out.println("WYSWIETL LISTE WYSTAWIONYCH PRZEZ CIEBIE USLUG\n");
		// Sprawdzamy czy lista uslug nie jest pusta
		if(!serviceList.isEmpty())
		{
			// Przeszukujemy liste wszystkich uslug w poszukiwaniu uslug konkretnego klienta
			for(MyService service : serviceList)
			{
				if(service.getOrderOwner() == this.clientNumber)
				{
					clientList.add(service);
				}
			}
		}
		else
		{
			out.println("Lista wszystkich uslug jest pusta");
		}
		
		// Sprawdzamy czy list uslug tego klienta nie jest pusta
		if(!clientList.isEmpty())
		{
			// Wyswietl wszystkie uslugi tego klienta
			for(MyService service : clientList)
			{
				out.println("Tworca: " + service.getOrderOwner());
				out.println("Usluga nr: " + service.getOrderIndex());
				out.println("Nazwa: " + service.getOrderName());
				out.println("Termin wykonania: " + service.getOrderDate());
				out.println("Status: " + service.getOrderStatus());
			}
		}
		else
		{
			out.println("Lista Twoich uslug jest pusta");
		}
	}
	
	// Wyswietl liste wszystkich uslug
	private void DisplayList(ArrayList<MyService> serviceList, PrintWriter out) 
	{
		out.println("WYSWIETL LISTE WSZYSTKICH USLUG\n");
		// Sprawdzamy czy lista wszystkich uslug nie jest pusta
		if(!serviceList.isEmpty())
		{
			// Wyswietlamy liste wszystkich uslg
			for(MyService service : serviceList)
			{
				out.println("Tworca: " + service.getOrderOwner());
				out.println("Usluga nr: " + service.getOrderIndex());
				out.println("Nazwa: " + service.getOrderName());
				out.println("Termin wykonania: " + service.getOrderDate());
				out.println("Status: " + service.getOrderStatus());
			}
		}
		else
		{
			out.println("Lista wszystkich uslug jest pusta");
		}
	}
}
	