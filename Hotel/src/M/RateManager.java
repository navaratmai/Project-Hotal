package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.util.ArrayList;

import common.GlobalData;

public class RateManager
{
	public static ArrayList<RateDB> getAllRate()
	{
		ArrayList<RateDB> list = new ArrayList<RateDB>();

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
			String query = "SELECT * FROM rate";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("id");
				String type_room = rs.getString("type_room");
				double special_day_price = rs.getDouble("special_day_price");
				double normal_price = rs.getDouble("normal_price");
				
				RateDB cc = new RateDB(id,type_room,special_day_price,normal_price);
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
	public static void saveNewRate(RateDB x)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
		
			
			String query = "INSERT INTO rate VALUES (0, '"+x.type_room+"'"
					+ ", '"+x.special_day_price+"', '"+x.normal_price+"')";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	public static void editRate(RateDB x)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
		
			
			String query = "UPDATE rate SET type_room = '"+x.type_room
					+"', special_day_price = '"+x.special_day_price
					+"', normal_price   = '"+x.normal_price
					+"'WHERE id  = " + x.id + "";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	public static void deleteRate(RateDB x)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
		
			
			String query = "DELETE FROM rate WHERE id  = " + x.id + "";
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
