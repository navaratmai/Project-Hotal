package M;

public class CustomerDB
{
	public int id;
	public String name;
	public String surname;
	public String phone;
	public String email;
	
	public CustomerDB()
	{
		
	}

	public CustomerDB(int xid,String xname,String xsurname,String xphone,String xemail)
	{
		id = xid;
		name = xname;
		surname = xsurname;
		phone = xphone;
		email = xemail;
	}
}
