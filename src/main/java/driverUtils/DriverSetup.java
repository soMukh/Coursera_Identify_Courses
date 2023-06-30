package driverUtils;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ReadProperties;
public class DriverSetup 
{
	static WebDriver driver=null;
	/*
	 * Method Name : getDriver
	 * Description : Gets the driver for the desired browser
	 */
	public static WebDriver getDriver() 
	{	
		try
		{
			String bname=ReadProperties.readProperties("browser");
			if(bname.equalsIgnoreCase("chrome"))
			{
				//Driver for Chrome browser
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(bname.equalsIgnoreCase("edge"))
			{
				//Driver for Edge browser
				driver=new EdgeDriver();
			}
			String url=ReadProperties.readProperties("url");
			//Maximizing the window
			driver.manage().window().maximize();
			//Opening the application
			driver.get(url);
			//Applying implicit wait for synchronization
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		catch(Exception e)
		{
			CloseBrowser.closeBrowser(driver);
			System.out.println("Application failed to open....."+"\n"+e.getMessage());
		}
		return driver;
	}
}