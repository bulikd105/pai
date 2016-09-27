package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
 * @Autor 
 * Damian Dworak
 * 187677
 * 
 * @Description
 * Napisz program laczacy sie ze strona podana jako argument wywolania programu 
 * i wypisujacy wszystkie znalezione na niej linki i adresy email wykorzystujac 
 * w tym celu wyrazenia regularne (pakiet java.util.regex). 
 * Oprocz tego program ma zapisac do pliku wszystkie parametry polaczenia, 
 * adres IP komputera na ktorym znajduje sie strona oraz naglowek strony (zawartosc sekcji <head>).
 * 
 */

public class Lab2 
{
	public static void main(String[] args)
	{
		if(args.length == 1)
		{
			try 
			{	
				// Deklaracja i utworzenie polaczenia ze stron¹
				URL siteUrl = new URL(args[0]);
				URLConnection siteUrlConnection = siteUrl.openConnection();
				siteUrlConnection.setConnectTimeout(20);
				siteUrlConnection.connect();
				
				// Pobieranie parametrów po³¹czenia
				String host = siteUrl.getHost();
				InetAddress address = InetAddress.getByName(host);
				Map<String, List<String>> map = siteUrlConnection.getHeaderFields();
				Map<String, String> connParameters = new HashMap<String,String>();	
				
				connParameters.put("URL - ",siteUrl.toString());
				connParameters.put("Protocol - ",siteUrl.getProtocol());
				connParameters.put("Authority - ",siteUrl.getAuthority());
				connParameters.put("Host - ",siteUrl.getHost());
				connParameters.put("Default port - ", ""+siteUrl.getDefaultPort());
				connParameters.put("Query - ",  siteUrl.getQuery());
				connParameters.put("Ref - ",  siteUrl.getRef());
				connParameters.put("IP address - ",  address.getHostAddress());
				connParameters.put("Address - ", address.getHostName());
				
				// Wyswietlanie parametrow polaczenia
				System.out.println("Parametry po³¹czenia:");
				
				for(Map.Entry<String, String> entry : connParameters.entrySet())
				{
					try
					{
						if(!entry.getValue().equals(null) || !entry.getValue().equals(""))
						{
							System.out.println(entry.getKey() + "" + entry.getValue());
						}
					}
					catch(NullPointerException e)
					{
						System.out.println(entry.getKey() + "empty");
					}
				}
				
				
				
				for (Map.Entry<String, List<String>> entry : map.entrySet()) 
				{
					System.out.println(entry.getKey() + " - " + entry.getValue());
				}

				
				// Pobierania naglowka strony
				
				
				
				
				
				// Zczytanie calej zawartosci strony
				BufferedReader br = new BufferedReader(new InputStreamReader(siteUrlConnection.getInputStream()));

				// Wyswietlenie calej zawartosci strony
/*			    String inputLine;
			    while ((inputLine = br.readLine()) != null) 
			    {
			    	System.out.println(inputLine);
			    }
			    br.close();*/
				
				
			} 
			catch (MalformedURLException e ) 
			{
				System.out.println("Error: Validate URL address\n");
			}
			catch (SocketTimeoutException e)
			{
				System.out.println("Error: Connection timed out\n");
			}
			catch (IOException e)
			{
				System.out.println("Error: Validate URL address\n");
			}
		}
		else
		{
			System.out.println("Wrong number of arguments.\nPlease verify\n");
		}
	}
}
