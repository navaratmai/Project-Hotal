package M;

import java.util.Date;

public class BookingDetail
{
	public int booking_detail_id;
	public Date check_in;
	public Date check_out;	
	public double total;
	public int room_id;	
	
	public BookingDB booking_id;
	
	public BookingDetail()
	{
		
	}
	
	public BookingDetail(int xbooking_detail_id,Date check_in2,Date check_out2,double xtotal,int xroom_id)
	{
		booking_detail_id = xbooking_detail_id;
		check_in = check_in2;
		check_out = check_out2;
		total = xtotal;
		room_id = xroom_id;
	}
}
