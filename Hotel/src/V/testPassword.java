package V;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class testPassword
{

	public static void main(String[] args)
	{
		String password = "1234";

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

		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
