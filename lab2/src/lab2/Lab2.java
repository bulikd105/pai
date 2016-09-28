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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				siteUrlConnection.setConnectTimeout(50);
				siteUrlConnection.connect();
				
				StringBuilder contentPage = new StringBuilder();
				String contentLine = "";
				
				// Pobieranie parametr�w po��czenia
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
				
				while ((contentLine = br.readLine()) != null) 
			    {
					 contentPage.append(contentLine);
					 //contentPage.append(System.lineSeparator());
			    }
				br.close();
			
				WriteFile(contentPage);
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
	
	public static void WriteFile(StringBuilder contentPage)
	{
		try 
		{
			// Deklaracj� zmiennych dla wzorca
			String header = "";
			String[] headerSplitted;
			String headerPattern = "<head>(.*?)</head>";
			Matcher regexMatcher = Pattern.compile(headerPattern).matcher(contentPage);
			
			// Wyszukiwanie wzorca i zapisanie do go zmiennej
			if(regexMatcher.find()) 
			{
				if(regexMatcher.group().length() != 0) 
				{
					header = regexMatcher.group(1).trim();
				}              
			}
			
			// Podzielenie nag��wka na linie
			headerSplitted = header.split("  +");
			
			// Utworzenie pliku do zapisu danych
			File file = new File("Dane.txt");
			if (!file.exists()) 
			{
				file.createNewFile();
			}
			
			// Przygotowanie zmiennych do zapisu plik�w
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			// Zapisanie paramatrow po��czenia do pliku
			
			
			
			// Zapisanie nag��wka do pliku
			for(String line : headerSplitted)
			{
				bw.write(line+"\n");
			}
			
		    bw.close();
		    
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
