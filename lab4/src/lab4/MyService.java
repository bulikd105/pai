package lab4;

public class MyService 
{
	private int orderOwner;		// Osoba ktora stworzyla usluge
	private int orderClient;	// Osoba ktora rezerwuje usluge
	private int orderIndex;		// Numer uslugi stworzonej przez danego klienta
	private int orderDate;		// Liczba dni na wykonanie tego zamowienia
	private String orderName;	// Nazwa zamowienia
	private String orderStatus; // Status uslugi: nowa, zarezerwowana, niewykorzystana, wycofana
	
	public MyService(int orderOwner, int orderIndex, String orderStatus)
	{
		this.orderOwner = orderOwner;
		this.orderClient = 0;
		this.orderIndex = orderIndex;
		this.orderStatus = orderStatus;
	}

	public void setOrderOwner(int orderOwner) 
	{
		this.orderOwner = orderOwner;
	}

	public void setOrderIndex(int orderIndex) 
	{
		this.orderIndex = orderIndex;
	}

	public void setOrderDate(int orderDate) 
	{
		this.orderDate = orderDate;
	}

	public void setOrderName(String orderName)
	{
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

	public int getOrderOwner() 
	{
		return orderOwner;
	}

	public int getOrderClient() 
	{
		return orderClient;
	}

	public int getOrderIndex() 
	{
		return orderIndex;
	}

	public int getOrderDate() 
	{
		return orderDate;
	}

	public String getOrderName() 
	{
		return orderName;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
	}
}
