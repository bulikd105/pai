package lab4;

import java.net.Socket;

public class Server implements Runnable 
{
	private Socket socket;
	private int clientNumber;
	
	public Server(Socket socket, int clientNumber) 
	{
		this.socket = socket;
		this.clientNumber = clientNumber;
		System.out.println("Dodano nowego klienta: " + this.clientNumber);
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}

}
