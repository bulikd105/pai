package lab4;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/* 
 * @Autor 
 * Damian Dworak
 * 187677
 * 
 * @Description
 * Aplikacja obslugujaca bank czasu. 
 * Klient zglasza (i wycofuje) uslugi jakie moze wykonac i ich terminy, 
 * moze tez zarzadac wyswietlenia wszystkich dostepnych uslug w banku i zarezerwowac sobie wybrana usluge. 
 * Serwer rozsyla komunikaty o nowych, zarezerwowanych, niewykorzystanych i wycofanych uslugach i terminach do wszystkich klientow. 
 * Nalezy zadbac o odpowiednia synchronizacje dostepu do zasobow i ich aktualizacje.
 */

public class Bank 
{
	public static ArrayList<MyService> services = new ArrayList<MyService>(); 
	
	public static void main(String[] args) throws IOException
	{
		int clientNumber = 1;
		
		// Deklaracja serwera
		int port = 6080;
		ServerSocket bankServerSocket = new ServerSocket(port);
		System.out.println("Utworzono server na porcie: " + port);
		
		Fill();

		// Uruchomienie serwera w nieskonczonej petli
		try
		{
			while(true)
			{
				// Utworz nowy watek, dla kazdego kolejnego klienta
				Thread clientThread = new Thread(new Server(bankServerSocket.accept(), clientNumber++, services));
				clientThread.start();
			}
		}
		// W razie problemu, zamknij serwer
		finally
		{
			bankServerSocket.close();
		}
	}
	
	private static void Fill() 
	{
		MyService obj1 = new MyService(1, 1, 10, "obj1");
		MyService obj2 = new MyService(1, 2, 20, "obj2");
		MyService obj3 = new MyService(2, 1, 30, "obj3");
		MyService obj4 = new MyService(3, 1, 40, "obj4");
		
		obj1.setOrderStatus("nowa");
		obj2.setOrderStatus("nowa");
		obj3.setOrderStatus("nowa");
		obj4.setOrderStatus("nowa");
		
		services.add(obj1);
		services.add(obj2);
		services.add(obj3);
		services.add(obj4);
	}
}
