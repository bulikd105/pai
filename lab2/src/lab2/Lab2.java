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
import java.util.ArrayList;
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
				// Deklaracja i utworzenie polaczenia ze stron¹
				URL siteUrl = new URL(args[0]);
				URLConnection siteUrlConnection = siteUrl.openConnection();
				siteUrlConnection.setConnectTimeout(50);
				siteUrlConnection.connect();
				
				StringBuilder contentPage = new StringBuilder();
				String contentLine = "";
				
				// Pobieranie parametrów po³¹czenia
				String host = siteUrl.getHost();
				InetAddress address = InetAddress.getByName(host);
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
			    }
				br.close();
				
				//Uruchomienie metod do czytania zawartosci strony
				ReadContent(contentPage);
				WriteFile(contentPage,connParameters);
				
				
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
	
	public static void WriteFile(StringBuilder contentPage, Map<String, String> connParameters)
	{
		try 
		{
			// Deklaracjê zmiennych dla wzorca
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
			
			// Podzielenie nag³ówka na linie
			headerSplitted = header.split("  +");
			
			// Utworzenie pliku do zapisu danych
			File file = new File("Dane.txt");
			if (!file.exists()) 
			{
				file.createNewFile();
			}
			
			// Przygotowanie zmiennych do zapisu plików
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			// Zapisanie paramatrow po³¹czenia do pliku
			bw.write("PARAMETRY STRONY: \n");
			for(Map.Entry<String, String> entry : connParameters.entrySet())
			{
				bw.write(entry.getKey() + entry.getValue() + "\n");
			}
			
			// Zapisanie nag³ówka do pliku
			bw.write("\nNAG£ÓWEK STRONY: \n");
			for(String line : headerSplitted)
			{
				bw.write(line+"\n");
			}
			
		    bw.close();
		    
			System.out.println("\nPlik zapisany poprawnie");
		} 
		catch (IOException e) 
		{
			System.out.println("Blad podczas tworzenia pliku");
		}
	}
	
	public static void ReadContent(StringBuilder contentPage)
	{
		ArrayList<String> links = new ArrayList<String>();
		String linkPattern =  "(?i)<a href=\"(.*?)\">.*?</a>";
		
		ArrayList<String> emails = new ArrayList<String>();
		String emailPattern =  "[A-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
		
		Matcher regexMatcher; 
		int i = 1, j=1;
		
		regexMatcher = Pattern.compile(linkPattern).matcher(contentPage);
		
		// Wyszukiwanie wzorca i zapisanie do go zmiennej
		while(regexMatcher.find())
		{
		    if(regexMatcher.group().length() != 0) 
		    {
	            links.add(regexMatcher.group(1).trim());
		    }
		}
		
		System.out.println("LINKI NA STRONIE:\n");
		for(String link : links)
		{
			System.out.println(i + " - " + link);
			i++;
		}

		regexMatcher = Pattern.compile(emailPattern).matcher(contentPage);
		while(regexMatcher.find())
		{
		    if(regexMatcher.group().length() != 0) 
		    {
		    	emails.add(regexMatcher.group(1).trim());
		    }
		}
		
		System.out.println("\nE-MAIL'E NA STRONIE:\n");
		for(String email : emails)
		{
			System.out.println(j + " - " + email);
			j++;
		}
	}
}
