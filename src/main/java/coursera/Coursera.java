package coursera;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import driverUtils.TakeScreenshot;
import utils.ExcelUtils;
import utils.ReadProperties;
import utils.ReadXML;
import utils.WriteFile;
public class Coursera 
{
	WebDriver driver;
	Actions action;
	Locators locator=new Locators();
	public Coursera(WebDriver driver)
	{
		this.driver=driver;
	}
	/*
	 * Method Name : setSearch
	 * Description : Searches for something in the search bar
	 */
	public int setSearch()
	{
		int flagSearch=0;
		try
		{
			String input=ReadXML.readXML("./src/main/resources/input.xml");
			//Locating and sending value to the search bar for searching
			driver.findElement(locator.search).sendKeys(input);
			driver.findElement(locator.search).sendKeys(Keys.ENTER);
			flagSearch=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetSearchFailure.png");
			System.out.println("Error Ocuured..."+e.getMessage());
		}
		return flagSearch;
	}
	/*
	 * Method Name : setConditions
	 * Description : Set filters for getting some desired result
	 */
	public int[] setConditions()
	{
		int flagCondition[]=new int[3];
		try
		{
			driver.findElement(locator.beginner).click();
			flagCondition[0]=1;
			driver.findElement(locator.courses).click();
			flagCondition[1]=1;
			driver.findElement(locator.english).click();
			flagCondition[2]=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetConditionsFailure.png");
			System.out.println("Error Ocuured..."+e.getMessage());
		}
		return flagCondition;
	}
	/*
	 * Method Name : getCourses
	 * Description : Retrieves courses based on some specific conditions
	 */
	public int[] getCourses()
	{
		int flagCourse[]=new int[4];
		try
		{
			action=new Actions(driver);
			String courseRating="";
			String courseDuration="";
			//Takes screenshot of courses
			action.moveToElement(driver.findElement(locator.beginnerFilterClear)).build().perform();
			TakeScreenshot.takeScreenshot(driver,"Courses.png");
			//Retrieves the details of first course displayed
			String hrefCourse1=driver.findElement(locator.course1).getAttribute("href");
			driver.navigate().to(hrefCourse1);
			TakeScreenshot.takeScreenshot(driver,"Course1.png");
			String courseName=driver.findElement(locator.cName).getText();
			try
			{
				courseRating=driver.findElement(locator.rating).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				courseRating=driver.findElement(locator.rating2).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				courseDuration=driver.findElement(locator.duration).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				courseDuration=driver.findElement(locator.duration2).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			//Writing the details of course1 into excel sheet
			ExcelUtils.writeExcel("./src/test/resources/Courses.xlsx","course_details",1,0,courseName);
			ExcelUtils.writeExcel("./src/test/resources/Courses.xlsx","course_details",1,1,courseRating);
			ExcelUtils.writeExcel("./src/test/resources/Courses.xlsx","course_details",1,2,courseDuration);
			flagCourse[0]=1;
			driver.navigate().back();
			flagCourse[1]=1;
			//Retrieves the details of second course displayed
			String hrefCourse2=driver.findElement(locator.course2).getAttribute("href");
			driver.navigate().to(hrefCourse2);
			TakeScreenshot.takeScreenshot(driver,"Course2.png");
			courseName=driver.findElement(locator.cName).getText();
			try
			{
				courseRating=driver.findElement(locator.rating).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				courseRating=driver.findElement(locator.rating2).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				courseDuration=driver.findElement(locator.duration).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			try
			{
				courseDuration=driver.findElement(locator.duration2).getText();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			//Writing the details of course2 into excel sheet
			ExcelUtils.writeExcel("./src/test/resources/Courses.xlsx","course_details",2,0,courseName);
			ExcelUtils.writeExcel("./src/test/resources/Courses.xlsx","course_details",2,1,courseRating);
			ExcelUtils.writeExcel("./src/test/resources/Courses.xlsx","course_details",2,2,courseDuration);
			flagCourse[2]=1;
			driver.navigate().back();
			flagCourse[3]=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/GetCourses.png");
			System.out.println("Error Ocuured..."+e.getMessage());
		}
		return flagCourse;
	}
	/*
	 * Method Name : getLanguagesLevels
	 * Description : Retrieves all the languages and levels in Language Learning
	 */
	public int[] getLanguagesLevels()
	{
		int flagLanguageLevel[]=new int[2];
		try
		{
			action=new Actions(driver);
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
			driver.navigate().refresh();
			//Locates and selects the Explore option
			action.moveToElement(driver.findElement(locator.explore)).build().perform();
			//Locates and selects the Language Learning option
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator.languageLearning));
			action.moveToElement(driver.findElement(locator.languageLearning)).click().build().perform();
			//Locates and selects the Free Courses option
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator.freeCourses));
			String freeCour=driver.findElement(locator.freeCourses).getAttribute("href");
			driver.navigate().to(freeCour);
			TakeScreenshot.takeScreenshot(driver,"LanguageLearning.png");
			//Retrieves all the languages in the section
			driver.findElement(locator.showMoreLanguage).click();
			TakeScreenshot.takeScreenshot(driver,"Languages.png");
			List<WebElement> lang=driver.findElements(locator.language);
			String languages[]=new String[lang.size()];
			for(int i=0;i<lang.size();i++)
			{
				languages[i]=lang.get(i).getAttribute("data-testid");
				String split[]=new String[2];
				split=languages[i].split("-");
				languages[i]=split[0];
			}
			//Writing the count of all languages and their names into file
			WriteFile.writeFile("\r\n");
			WriteFile.writeFile("The number of languages present are : ");
			WriteFile.writeFile(String.valueOf(languages.length));
			WriteFile.writeFile("\r\n");
			WriteFile.writeFile("\r\n");
			WriteFile.writeFile("The languages are : ");
			WriteFile.writeFile("\r\n");
			for(int i=0;i<languages.length;i++)
			{
				WriteFile.writeFile("\r\n");
				WriteFile.writeFile(languages[i]);
			}
			flagLanguageLevel[0]=1;
			driver.findElement(locator.apply).click();
			//Retrieves all the levels in the section
			String level[]=new String[4];
			action.moveToElement(driver.findElement(locator.mixed)).build().perform();
			TakeScreenshot.takeScreenshot(driver,"Levels.png");
			level[0]=driver.findElement(locator.begin).getText();
			level[1]=driver.findElement(locator.intermediate).getText();
			level[2]=driver.findElement(locator.advanced).getText();
			level[3]=driver.findElement(locator.mixed).getText();
			//Writing the count of all levels and their names into file
			WriteFile.writeFile("\r\n");
			WriteFile.writeFile("The number of levels present are : ");
			WriteFile.writeFile(String.valueOf(level.length));
			WriteFile.writeFile("\r\n");
			WriteFile.writeFile("\r\n");
			WriteFile.writeFile("The levels are : ");
			WriteFile.writeFile("\r\n");
			for(int i=0;i<level.length;i++)
			{
				WriteFile.writeFile("\r\n");
				WriteFile.writeFile(level[i]);
			}
			flagLanguageLevel[1]=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/GetLanguagesLevels.png");
			System.out.println("Error Ocuured..."+e.getMessage());
		}
		return flagLanguageLevel;
	}
	/*
	 * Method Name : goToContactUs
	 * Description : Navigates to Contact Sales option in For Businesses/For Enterprises option
	 */
	public int[] goToContactUs() 
	{
		int flagContactUs[]=new int[4];
		try
		{
			//Navigates back to Home page
			driver.get(ReadProperties.readProperties("url"));
			flagContactUs[0]=1;
			try
			{
				//Locates and selects For Businesses
				driver.findElement(locator.business).click();
				flagContactUs[1]=1;
			}
			finally
			{
				try
				{
					//Locates and selects For Enterprises
					driver.findElement(locator.enterprise).click();
					flagContactUs[1]=1;
				}
				finally
				{
					//Locates and selects Solutions 
					driver.findElement(locator.solutions).click();
					//Takes screenshot of the products
					TakeScreenshot.takeScreenshot(driver,"Products.png");
					flagContactUs[2]=1;
					//Locates and selects Contact Sales
					String contactSales=driver.findElement(locator.contact).getAttribute("href");
					driver.navigate().to(contactSales);
					flagContactUs[3]=1;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return flagContactUs;
	}
	/*
	 * Method Name : setFirstName
	 * Description : Sets the first name in the Contact Sales form
	 */
	public int setFirstName(String firstName)
	{
		int flagFName=0;
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator.fName));
			driver.findElement(locator.fName).sendKeys(firstName);
			flagFName=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetFirstName.png");
			System.out.println(e.getMessage());
		}
		return flagFName;
	}
	/*
	 * Method Name : setLastName
	 * Description : Sets the last name in the Contact Sales form
	 */
	public int setLastName(String lastName)
	{
		int flagLName=0;
		try
		{
			driver.findElement(locator.lName).sendKeys(lastName);
			flagLName=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetLastName.png");
			System.out.println(e.getMessage());
		}
		return flagLName;
	}
	/*
	 * Method Name : setEmail
	 * Description : Sets the email in the Contact Sales form
	 */
	public int setEmail(String email)
	{
		int flagEmail=0;
		try
		{
			driver.findElement(locator.email).sendKeys(email);
			flagEmail=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetEmail.png");
			System.out.println(e.getMessage());
		}
		return flagEmail;
	}
	/*
	 * Method Name : setCompanyName
	 * Description : Sets the company name in the Contact Sales form
	 */
	public int setCompanyName(String company)
	{
		int flagCName=0;
		try
		{
			driver.findElement(locator.companyName).clear();
			driver.findElement(locator.companyName).sendKeys(company);
			flagCName=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetCompanyName.png");
			System.out.println(e.getMessage());
		}
		return flagCName;
	}
	/*
	 * Method Name : setJobRole
	 * Description : Sets the job role in the Contact Sales form
	 */
	public int setJobRole(String jRole)
	{
		int flagJRole=0;
		try
		{
			Select jobRole=new Select(driver.findElement(locator.jobRole));
			jobRole.selectByVisibleText(jRole);
			flagJRole=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetJobRole.png");
			e.getMessage();
		}
		return flagJRole;
	}
	/*
	 * Method Name : setJobFunction
	 * Description : Sets the job function in the Contact Sales form
	 */
	public int setJobFunction(String jFunction)
	{
		int flagJFunction=0;
		try
		{
			Select jobFunction=new Select(driver.findElement(locator.jobFunction));
			jobFunction.selectByVisibleText(jFunction);
			flagJFunction=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetJobFunction.png");
			System.out.println(e.getMessage());
		}
		return flagJFunction;
	}
	/*
	 * Method Name : setPhone
	 * Description : Sets the phone number in the Contact Sales form
	 */
	public int setPhone(String phone)
	{
		int flagPhone=0;
		try
		{
			driver.findElement(locator.phone).sendKeys(phone);
			flagPhone=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetPhone.png");
			System.out.println(e.getMessage());
		}
		return flagPhone;
	}
	/*
	 * Method Name : setCompanySize
	 * Description : Sets the company size in the Contact Sales form
	 */
	public int setCompanySize(String cSize)
	{
		int flagCSize=0;
		try
		{
			Select companySize=new Select(driver.findElement(locator.eNumber));
			companySize.selectByVisibleText(cSize);
			flagCSize=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetCompanySize.png");
			System.out.println(e.getMessage());
		}
		return flagCSize;
	}
	/*
	 * Method Name : setLearners
	 * Description : Sets the learners number in the Contact Sales form
	 */
	public int setLearners(String learners)
	{
		int flagLearners=0;
		try
		{
			Select learner=new Select(driver.findElement(locator.tNumber));
			learner.selectByVisibleText(learners);
			flagLearners=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetLearners.png");
			System.out.println(e.getMessage());
		}
		return flagLearners;
	}
	/*
	 * Method Name : setCountry
	 * Description : Sets the country name in the Contact Sales form
	 */
	public int setCountry(String country)
	{
		int flagCountry=0;
		try
		{
			Select countryDropDown=new Select(driver.findElement(locator.country));
			countryDropDown.selectByVisibleText(country);
			flagCountry=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetCountry.png");
			System.out.println(e.getMessage());
		}
		return flagCountry;
	}
	/*
	 * Method Name : submit
	 * Description : Submits the Contact Sales form
	 */
	public int submit()
	{
		int flagSubmit=0;
		try
		{
			driver.findElement(locator.submit).click();
			flagSubmit=1;
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/SetSubmit.png");
			System.out.println(e.getMessage());
		}
		return flagSubmit;
	}
	/*
	 * Method Name : getMessage
	 * Description : gets the error message and writes it on the Excel file 
	 */
	public String getMessage()
	{
		String message="";
		try
		{
			message=driver.findElement(locator.errMessage).getText();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			TakeScreenshot.takeScreenshot(driver,"ErrorMessage.png");
			ExcelUtils.writeExcel("./src/main/resources/FormInput.xlsx","form_invalid",1,10,message);
		}
		catch(Exception e)
		{
			TakeScreenshot.takeScreenshot(driver,"Failures/getMessage.png");
			System.out.println(e.getMessage());
		}
		return message;
	}
}