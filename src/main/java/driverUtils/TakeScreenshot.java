package driverUtils;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class TakeScreenshot 
{
	/*
	 * Method Name : takeScreenshot
	 * Description : Takes screenshot of a web element
	 */
	public static void takeScreenshot(WebElement ele,String fName)
	{
		try
		{
			File source=ele.getScreenshotAs(OutputType.FILE);
			File destination=new File("./Screenshots/"+fName);
			FileUtils.copyFile(source,destination);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	/*
	 * Method Name : takeScreenshot
	 * Description : Takes screenshot of the entire screen
	 */
	public static void takeScreenshot(WebDriver driver,String fName)
	{
		try
		{
			TakesScreenshot screenshot=(TakesScreenshot)driver;
			File source=screenshot.getScreenshotAs(OutputType.FILE);
			File destination=new File("./Screenshots/"+fName);
			FileUtils.copyFile(source,destination);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}