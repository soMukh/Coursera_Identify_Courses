package utils;
import java.io.FileInputStream;
import java.util.Properties;
public class ReadProperties 
{
	/*
	 * Method Name : readProperties
	 * Description : Reads the properties file
	 */
	public static String readProperties(String prop)
	{
		String readProp=null;
		try
		{
			Properties property=new Properties();
			FileInputStream fis=new FileInputStream("./src/main/resources/config.properties");
			property.load(fis);
			readProp=property.getProperty(prop);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return readProp;
	}
}