package driverUtils;
import org.openqa.selenium.WebDriver;
public class CloseBrowser 
{
	/*
	 * Method Name : closeBrowser
	 * Description : Closes the application
	 */
	public static void closeBrowser(WebDriver driver) 
	{
		try
		{
			//Closing the application
			driver.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}