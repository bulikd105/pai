package lab4;

public class MyService 
{
	private int orderOwner;		// Osoba ktora stworzyla usluge
	private int orderClient;	// Osoba ktora rezerwuje usluge
	private int orderIndex;		// Numer uslugi stworzonej przez danego klienta
	private int orderDate;		// Liczba dni na wykonanie tego zamowienia
	private String orderName;	// Nazwa zamowienia
	private String orderStatus; // Status uslugi: nowa, zarezerwowana, niewykorzystana, wycofana
	
	public MyService(int orderOwner, int orderIndex, int orderDate, String orderName)
	{
		this.orderOwner = orderOwner;
		this.orderIndex = orderIndex;
		this.orderDate = orderDate;
		this.orderName = orderName;
	}

	// Setter dla klienta ktory bierze zamowienie
	public void setOrderClient(int orderClient) 	
	{
		this.orderClient = orderClient;
	}

	// Setter dla statusu zamowienia
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}
	
}
