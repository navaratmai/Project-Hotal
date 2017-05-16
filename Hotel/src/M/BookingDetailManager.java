package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import common.GlobalData;

public class BookingDetailManager
{
	public static ArrayList<BookingDetail> getAllBooking()
	{
		ArrayList<BookingDetail> list = new ArrayList<BookingDetail>();

		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM booking_detail";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int booking_detail_id = rs.getInt("booking_detail_id");
				Date check_in = rs.getDate("check_in");
				Date check_out = rs.getDate("check_out");
				double total = rs.getDouble("total");
				int room_id = rs.getInt("room_id");
				
				
				BookingDetail cc = new BookingDetail(booking_detail_id,check_in,check_out,total,room_id);
				list.add(cc);
				
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}
}
