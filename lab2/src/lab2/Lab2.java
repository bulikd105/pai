package lab2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
				siteUrlConnection.setConnectTimeout(50);
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
				

				// Zczytanie calej zawartosci strony
				BufferedReader br = new BufferedReader(new InputStreamReader(siteUrlConnection.getInputStream()));
				
				WriteFile(br);
				ReadContent(br);
				
				
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
	
	public static void WriteFile(BufferedReader br)
	{

		try 
		{
			// Utworzenie pliku do zapisu danych
			File file = new File("Dane.txt");
			if (!file.exists()) 
			{
					file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			// Zapisanie parametrow polaczenia
			bw.write("Parametry po³¹czenia:\n");
			
/*			for(Map.Entry<String, String> entry : connParameters.entrySet())
			{
				try
				{
					if(!entry.getValue().equals(null) || !entry.getValue().equals(""))
					{
						bw.write(entry.getKey() + "" + entry.getValue() + "\n");
					}
				}
				catch(NullPointerException e)
				{
					bw.write(entry.getKey() + "empty\n");
				}
			}
							
			for (Map.Entry<String, List<String>> entry : map.entrySet()) 
			{
				bw.write(entry.getKey() + " - " + entry.getValue() + "\n");
			}*/
			
			// Zapisanie naglowka strony
			boolean flag = false;
		    String inputLine;
		    
		    bw.write("Naglowek strony: \n");
		    
		    while ((inputLine = br.readLine()) != null) 
		    {
		    	// Szukamy poczatku sekcji header
		    	if(inputLine.contains("<head>"))
		    	{
		    		flag = true;
		    	}
		    	
		    	// Zapisujemy wszystko co sie znajduje w sekcji header
		    	if(flag == true)
		    	{
		    		bw.write(inputLine + "\n");
		    	}
		    	
		    	// Szukamy konca sekcji header
		    	if(inputLine.contains("</head>") && flag == true)
		    	{
		    		flag = false;
		    		break;
		    	}
		    }
		    
		    bw.close();
			br.close();

			System.out.println("Plik zapisany poprawnie");
			
		} 
		catch (IOException e) 
		{
			System.out.println("Blad podczas tworzenia pliku");
		}
	}
	
	public static void ReadContent(BufferedReader br)
	{
		
	}
}
