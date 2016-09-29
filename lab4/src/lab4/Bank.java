package lab4;

import java.io.IOException;
import java.net.ServerSocket;

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
	public static void main(String[] args) throws IOException
	{
		int clientNumber = 1;
		
		// Deklaracja serwera
		int port = 6080;
		ServerSocket bankServerSocket = new ServerSocket(port);
		System.out.println("Utworzono server na porcie: " + port);

		// Uruchomienie serwera w nieskonczonej petli
		try
		{
			while(true)
			{
				// Utworz nowy watek, dla kazdego kolejnego klienta
				Thread clientThread = new Thread(new Server(bankServerSocket.accept(), clientNumber++));
				clientThread.start();
			}
		}
		// W razie problemu, zamknij serwer
		finally
		{
			bankServerSocket.close();
		}
	}
}
