package lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
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
				// Deklaracja i utworzenie polaczenia ze stron�
				URL siteUrl = new URL(args[0]);
				URLConnection siteUrlConnection = siteUrl.openConnection();
				siteUrlConnection.connect();
				
				// Pobieranie parametr�w po��czenia
				String host = siteUrl.getHost();
				InetAddress address = InetAddress.getByName(host);
				String ipAddress = address.getHostAddress();

				
				
				

				Map<String,String> connParameters = new HashMap<String,String>();
				connParameters.put("URL is ", siteUrl.toString());
				connParameters.put("Protocol is ", siteUrl.getProtocol());
				connParameters.put("Authority is ", siteUrl.getAuthority());
				connParameters.put("File name is ", siteUrl.getFile());
				connParameters.put("Host is ", siteUrl.getHost());
				connParameters.put("Path is ", siteUrl.getPath());
				connParameters.put("Port is ", ""+siteUrl.getPort());
				connParameters.put("Default port is ", ""+siteUrl.getDefaultPort());
				connParameters.put("Query is ", siteUrl.getQuery());
				connParameters.put("Ref is ", siteUrl.getRef());
				
				for(Map.Entry<String, String> entry : connParameters.entrySet())
				{
					System.out.println(entry.getKey() + "" + entry.getValue());
				}
				
				
				
				
				
				// Zczytanie calej zawartosci strony
				BufferedReader br = new BufferedReader(new InputStreamReader(siteUrlConnection.getInputStream()));

				// Wyswietlenie calej zawartosci strony
			    String inputLine;
			    while ((inputLine = br.readLine()) != null) 
			    {
			    	System.out.println(inputLine);
			    }
			    br.close();
				
				
			} 
			catch (MalformedURLException e ) 
			{
				System.out.println("Error: Validate URL address\n");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error: Validate URL address\n");
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Wrong number of arguments.\nPlease verify\n");
		}
	}
}