package M;

public class RateDB
{
	public int id;
	public String type_room;
	public double special_day_price;
	public double normal_price;
	
	public RateDB()
	{
		
	}
	
	public RateDB(int xid,String xtype_room,double xspecial_day_price,double xnormal_price)
	{
		id = xid;
		type_room = xtype_room;
		special_day_price = xspecial_day_price;
		normal_price = xnormal_price;
	}
}
