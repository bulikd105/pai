package lab3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client 
{
	public static void main(String[] args)
	{
		String serverName = "127.0.0.1";
		int port = 6080;
		
		try
		{
			Socket socket = new Socket(serverName, port);
			
			while(true)
			{
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String answer = input.readLine();
	 
				System.out.println(answer);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
