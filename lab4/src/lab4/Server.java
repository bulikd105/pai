package lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable 
{
	// Deklaracja stalych wartosci
	private final static String SERVICE_NEW 		= "nowa";
	private final static String SERVICE_RESERVED 	= "zarezerwowana";
	private final static String SERVICE_UNUSED 		= "nieuzywana";
	private final static String SERVICE_WITHDRAWN 	= "wycofana";
	private final static String LOGOUT			 	= "logout";
	
	// Glowne pola serwera dla danego klienta
	private Socket socket;
	private int clientNumber;
	private ArrayList<MyService> serviceList;
		
	// Konstruktor
	public Server(Socket socket, int clientNumber, ArrayList<MyService> serviceList) 
	{
		this.socket = socket;
		this.clientNumber = clientNumber;
		this.serviceList = serviceList;
		System.out.println("Dodano nowego klienta: " + this.clientNumber);
	}

	@Override
	public void run() 
	{
		BufferedReader in = null;
        PrintWriter out = null;
        MyService service = null;
		
		try
		{	
			// Bufery do odbierania i wysylania wiadomosci
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String userInput = "";
            String userTempInput = "";
            String[] tempTable = new String[2];
            boolean flag = true;
            boolean existsFlag = false;
            boolean availableFlag = false;
            boolean clientFlag = false;
            
            String answer;
            int index = 0;
            
            out.println("Witamy w naszym banku\nTw�j numer to: " + this.clientNumber);
           
            // Uruchomienie glownej petli
            //while((userInput = in.readLine()) != null && flag == true)
            while(flag == true)
            {
            	out.println("Wybierz operacje ktora chcesz wykonac:");
            	out.println("1 - Wyswietl liste dostepnych uslug\n2 - Wyswietl liste swoich uslug\n" + 
            					   "3 - Dodaj nowa usluge\n4 - Wycofaj swoja usluge\n5 - Zarezerwuj usluge\n6 - Wyjdz");
            	userInput = in.readLine();
            	System.out.println("Klient: " + this.clientNumber + " powiedzial - " + userInput);
            	answer = "";
            	Thread.sleep(1000);
            	if(userInput != null && userInput.length() > 0)
            	{
            		switch(userInput)
	            	{
	    				case "1" : 	answer = DisplayList(serviceList);
	    							out.println(answer);
	    							break;
	    				case "2" : 	answer = DisplayYourList(serviceList);
	    							out.println(answer);
	    							break;
	    				case "3" : 	//Przeszukaj liste w poszukiwaniu uslug dodanych przez tego usera
	    							index = 0;
	    							for(MyService tempService : serviceList)
				    				{
	    								if(tempService.getOrderOwner() == clientNumber)
	    								{
	    									index = tempService.getOrderIndex();
	    								}
				    				}
;
	    							index += 1;

	    							// Stworz nowy obiekt dla tego klienta
	    							service = new MyService(clientNumber, index, SERVICE_NEW);

	    							// Prosba o podanie danych przez usera
	    							out.flush();
		    						out.println("Podaj po przecinku, nazwe oraz czas wykonania zamowienia");
		    						
		    						// Pobranie danych od usera
		    						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    						userTempInput = in.readLine();
		    						
		    						// Rozdzielenie danych wzietych od usera
		    						tempTable = userTempInput.split(",");
		    						if(tempTable.length > 1 && isNumeric(tempTable[1]))
		    						{
		    							// Dodaj dane otrzymane od usera
		    							service.setOrderName(tempTable[0]);
			    						service.setOrderDate(Integer.parseInt(tempTable[1]));
			    						
			    						// Dodaj dane do listy
			    						serviceList.add(service);
			    						
			    						System.out.println(service.getOrderClient() + " " + service.getOrderDate() + " " + service.getOrderIndex() + " " + service.getOrderName() + " " + service.getOrderOwner() + " " + service.getOrderStatus());
			    						
			    						out.println("Gotowe");
		    						}
		    						else
		    						{
		    							out.println("Blad, popraw dane");
		    						}
		    						
	    							break;
	    				case "4" : 	// Prosba o podanie danych przez usera
			    					out.flush();
			    					out.println("Podaj numer swojej uslugi, ktora chcesz anulowac");

			    					// Pobranie danych od usera
		    						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		    						userTempInput = in.readLine();

		    						if(userTempInput.length() > 0 && isNumeric(userTempInput))
		    						{
		    							existsFlag = false;
		    							//Przeszukaj liste w poszukiwaniu uslug dodanych przez tego usera
				    					index = Integer.parseInt(userTempInput);
										for(MyService tempService : serviceList)
					    				{
											if(tempService.getOrderOwner() == clientNumber && tempService.getOrderIndex() == index)
											{

													// Update na liscie
													tempService.setOrderStatus(SERVICE_WITHDRAWN);
													serviceList.set(serviceList.indexOf(tempService), tempService);
													
													System.out.println(tempService.getOrderClient() + " " + tempService.getOrderDate() + " " 
																	 + tempService.getOrderIndex() + " " + tempService.getOrderName() + " " 
																	 + tempService.getOrderOwner() + " " + tempService.getOrderStatus());
													out.println("Gotowe");
													existsFlag = true;
											}
					    				}
										if(existsFlag == false)
										{
											answer = ("Klient: " + clientNumber + " nie ma pozycji - " + index + "\nBlad, popraw dane");
											out.println(answer);
										}
		    						}
		    						else
		    						{
		    							out.println("Blad, popraw dane");
		    						} 					
	    							break;
	    				case "5" : 	// Prosba o podanie danych przez usera
			    					out.flush();
			    					out.println("Podaj po przecinku, numer klienta, oraz jego usluge ktora chcesz zamowic");
	    					
			    					// Pobranie danych od usera
		    						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    						userTempInput = in.readLine();
		    						
		    						// Rozdzielenie danych wzietych od usera
		    						tempTable = userTempInput.split(",");
		    						if(tempTable.length > 1 && isNumeric(tempTable[0]) && isNumeric(tempTable[1]))
		    						{
		    							existsFlag = false;
		    							availableFlag = false;
		    							clientFlag = false;
		    							//Przeszukaj liste w poszukiwaniu uslug dodanych przez tego usera
										for(MyService tempService : serviceList)
					    				{
											if(clientNumber != Integer.parseInt(tempTable[0]))
											{
												clientFlag = true;
												if(tempService.getOrderOwner() == Integer.parseInt(tempTable[0]) && tempService.getOrderIndex() == Integer.parseInt(tempTable[1]))
												{
													existsFlag = true;
													if(tempService.getOrderStatus().equals(SERVICE_NEW) || tempService.getOrderStatus().equals(SERVICE_UNUSED))
													{
														availableFlag = true;
														// Update na liscie
														tempService.setOrderStatus(SERVICE_RESERVED);
														tempService.setOrderClient(clientNumber);
														serviceList.set(serviceList.indexOf(tempService), tempService);
														
														System.out.println(tempService.getOrderClient() + " " + tempService.getOrderDate() + " " 
																	  	 + tempService.getOrderIndex() + " " + tempService.getOrderName() + " " 
																	  	 + tempService.getOrderOwner() + " " + tempService.getOrderStatus());
														out.println("Gotowe");
													}
												}
											}
					    				}
										if(clientFlag == false)
										{
											answer = ("Klient: " + tempTable[0] + " nie moze zarezerwowac swojej uslug" + "\nBlad, popraw dane");	
											out.println(answer);
										}
										else if(existsFlag == false)
										{
											answer = ("Klient: " + tempTable[0] + " nie istnieje, lub nie ma pozycji - " + tempTable[1] + "\nBlad, popraw dane");	
											out.println(answer);
										}
										else if(availableFlag == false)
										{
											answer = ("Klient: " + tempTable[0] + " pozycja - " + tempTable[1] + " jest zajeta" + "\nBlad, popraw dane");
											out.println(answer);
										}
		    						}
		    						else
		    						{
		    							out.println("Blad, popraw dane");
		    						} 					
	    							break;
	    				case "6" : 	out.println(LOGOUT);
	    							System.out.println("Klient: " + this.clientNumber + " wylogowuje sie");
	    							flag = false;
	    							break;
	    				default  : 	out.println("Nie ma takiej opcji\n");	
	    						   	break;
	            	}           		
            	}
            	else
            	{
            		System.out.println("Klient: " + this.clientNumber + " przeslal null'a, zamykajac tym samym polaczenie");
            		break;
            	}
            }
		}
		catch(IOException | InterruptedException e)
		{
			System.out.println("Klient: " + clientNumber + " zglosil blad: " + e);
		}
		finally // W razie gdyby klient zglosil blad, rozlacz go
		{
        	// Zamkniecie bufferow
        	try 
        	{
				in.close();
				out.close();
				socket.close();
			} 
        	catch (IOException e) 
        	{
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unused")
	public boolean isNumeric(String str)  
	{  
		try  
		{  
			int i = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	
	// Wyswietl liste uslug danego uzytkownika
	private String DisplayYourList(ArrayList<MyService> serviceList) 
	{
		// Tworzymy temp liste dla uslug klienta
		ArrayList<MyService> clientList = new ArrayList<MyService>();
		StringBuilder sb = new StringBuilder();

		sb.append("WYSWIETL LISTE TWOICH USLUG\n");
		
		// Sprawdzamy czy lista wszystkich uslug nie jest pusta
		if(!serviceList.isEmpty())
		{
			// Przeszukujemy liste wszystkich uslug w poszukiwaniu uslug konkretnego klienta
			for(MyService service : serviceList)
			{
				if(service.getOrderOwner() == this.clientNumber)
				{
					clientList.add(service);
				}
			}
			
			// Sprawdzamy czy list uslug tego klienta nie jest pusta
			if(!clientList.isEmpty())
			{
				// Wyswietl wszystkie uslugi tego klienta
				for(MyService service : clientList)
				{
					sb.append("----------------------------------------\n");
					sb.append("Tworca: " + service.getOrderOwner() + "\n");
					sb.append("Usluga nr: " + service.getOrderIndex() + "\n");
					sb.append("Nazwa: " + service.getOrderName() + "\n");
					sb.append("Termin wykonania: " + service.getOrderDate() + "\n");
					sb.append("Status: " + service.getOrderStatus() + "\n");
					sb.append("Wynajete przez: " + service.getOrderClient() + "\n");
				}
			}
			else
			{
				sb.append("Lista Twoich uslug jest pusta");
			}
		}
		else
		{
			sb.append("Lista wszystkich uslug jest pusta");
		}
		return sb.toString();		
	}
	
	// Wyswietl liste wszystkich uslug
	private String DisplayList(ArrayList<MyService> serviceList) 
	{
		StringBuilder sb = new StringBuilder();

		sb.append("WYSWIETL LISTE WSZYSTKICH USLUG\n");
		
		// Sprawdzamy czy lista wszystkich uslug nie jest pusta
		if(!serviceList.isEmpty())
		{
			// Wyswietlamy liste wszystkich uslg
			for(MyService service : serviceList)
			{
				sb.append("----------------------------------------\n");
				sb.append("Tworca: " + service.getOrderOwner() + "\n");
				sb.append("Usluga nr: " + service.getOrderIndex() + "\n");
				sb.append("Nazwa: " + service.getOrderName() + "\n");
				sb.append("Termin wykonania: " + service.getOrderDate() + "\n");
				sb.append("Status: " + service.getOrderStatus() + "\n");
				sb.append("Wynajete przez: " + service.getOrderClient() + "\n");
			}
		}
		else
		{
			sb.append("Lista wszystkich uslug jest pusta");
		}
		return sb.toString();
	}
}
	