package lab3;

import java.net.ServerSocket;

/* 
 * @Autor 
 * Damian Dworak
 * 187677
 * 
 * @Description
 * Echo klient i echo serwer wielowatkowy (dla ulatwienia zacznij od jednowatkowego)
 */

public class Lab3 
{
	public static void main(String[] args) throws Exception
	{
		// Deklaracja portu
		int port = 6080;
		int threadNum = 1;
		
		// Utworzenie server socket
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Utworzono server na porcie: " + port);
		
		// Uruchomienie nieskonczonej petli dla serwera
		try
		{
			while(true)
			{
				// Utworz nowy watek, dla kazdego kolejnego klienta
				Thread serverThread = new Thread(new Server(serverSocket.accept(), threadNum++));
				serverThread.start();
			}
		}
		finally
		{
			// W razie problemu zamknij socket
			serverSocket.close();
		}
	}
}
