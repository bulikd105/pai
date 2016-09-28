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
			PrintWriter out;
			BufferedReader in;
			BufferedReader stdIn;
			
			
			while(true)
			{
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				String answer = in.readLine();
	 
				
				String userInput;

				System.out.println("Welcome: " + in.readLine());
				System.out.print("input: ");
				while ((userInput = stdIn.readLine()) != null) 
				{
					out.println(userInput);
					System.out.println("echo: " + in.readLine());
					System.out.print("input: ");
				}


			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
