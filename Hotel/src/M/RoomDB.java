package M;

public class RoomDB
{
	public int id;
	public String room_detail;
	public int rate_id;
	
	public RoomDB()
	{
		
	}
	
	public RoomDB(int xid,String xroom_detail,int xrate_id)
	{
		id = xid;
		room_detail = xroom_detail;
		rate_id = xrate_id;
	}

}
