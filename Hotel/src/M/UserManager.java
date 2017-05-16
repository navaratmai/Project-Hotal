package M;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.GlobalData;

public class UserManager
{
	public static ArrayList<UserDB> getAllUser()
	{
		ArrayList<UserDB> list = new ArrayList<UserDB>();

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
			String query = "SELECT * FROM users";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{

				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String usertype = rs.getString("usertype");
				
				UserDB cc = new UserDB(id,username,password,usertype);
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
	public static void saveNewUser(UserDB x)
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
		
			
			String query = "INSERT INTO users VALUES (0, "
					+ "'"+x.username+"'"
					+ ", '"+SHAEncrypt(x.password)+"', "
					+ "'"+x.usertype+"')";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
			
			System.out.println(query);
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	public static void editUser(UserDB x)
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
		
			
			String query = "UPDATE users SET username = '"+x.username
					+"', password = '"+SHAEncrypt(x.password)
					+"', usertype   = '"+x.usertype
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
	public static void deleteUser(UserDB x)
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
		
			
			String query = "DELETE FROM users WHERE id  = " + x.id + "";
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	public static boolean checkLogin(String username ,String password)
	{
		try
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setString(1, username);
			st.setString(2, SHAEncrypt(password));
			
			ResultSet rs = st.executeQuery();
			while (rs.next())
			{
				GlobalData.CurrentUser_userID = rs.getInt(1);
				GlobalData.CurrentUser_username = rs.getString(2);
				GlobalData.CurrentUser_usertype = rs.getString(4);
				return true;
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
			return false;
	}

	public static String SHAEncrypt(String input)
	{
		String password = input;

		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++)
			{
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			System.out.println("Hex format : " + sb.toString());
			return sb.toString();
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
