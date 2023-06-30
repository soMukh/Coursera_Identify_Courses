package test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import coursera.Coursera;
import driverUtils.CloseBrowser;
import driverUtils.DriverSetup;
import utils.ExcelUtils;
import utils.ReadProperties;
public class Main 
{
	static WebDriver driver;
	static String[] data=null;
	String errMessage="Must be valid email."+"\n"+"example@yourdomain.com";
	static ExtentSparkReporter htmlReporter;
	static ExtentTest test1,test2,test3;
	static ExtentReports report=new ExtentReports();
	Coursera coursera;
	@BeforeTest(alwaysRun=true)
	public void setUp()
	{
		//Sets up the driver and retrieves value from excel sheet
		driver=DriverSetup.getDriver();
		coursera=new Coursera(driver);
	}
	@BeforeClass(alwaysRun=true)
	public void startTest()
	{
		htmlReporter=new ExtentSparkReporter("./TestResult/TestReport.html");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Coursera Flow Check");
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a'('zzz')'");
		htmlReporter.config().setTheme(Theme.DARK);
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Operating System",System.getProperty("os.name"));
		report.setSystemInfo("Java Version",System.getProperty("java.version"));
		report.setSystemInfo("Browser Name",ReadProperties.readProperties("browser"));
		report.setSystemInfo("Website",ReadProperties.readProperties("url"));
		data=ExcelUtils.readExcel("./src/main/resources/FormInput.xlsx","form_invalid",1,10);
	}
	@Test(groups="Smoke Suite",priority=0,retryAnalyzer=Retry.class)
	public void search()
	{
		test1=report.createTest("Retrieving Web Development Courses For Beginners In English");
		//Sends search value and logs the status
		int flagSearch=coursera.setSearch();
		if(flagSearch==1)
		{
			test1.log(Status.PASS,"Search bar is visible");
			test1.log(Status.PASS,"Value can be entered in search bar.");
		}
		else
		{
			test1.log(Status.FAIL,"Search bar isn't visible.");
			test1.log(Status.FAIL,"Value can't be entered in search bar.");
			test1.log(Status.SKIP,"Setting conditions and retrieving courses are skipped.");
		}
		//Comparing the actual and expected result
		Assert.assertEquals(flagSearch,1);
	}
	@Test(groups="Smoke Suite",dependsOnMethods="search",priority=1)
	public void retrieveCourse()
	{
		//Sets the conditions, retrieves the first two courses and logs the status
		int flagCondition[]=coursera.setConditions();
		int flagCourse[]=coursera.getCourses();
		int conditionExpected[]= {1,1,1};
		int courseExpected[]= {1,1,1,1};
		if(flagCondition[0]==1)
		{
			test1.log(Status.PASS,"Beginner is visible to select.");
			test1.log(Status.PASS,"Beginner is selected.");
		}
		else
		{
			test1.log(Status.FAIL,"Beginner isn't visible to select.");
			test1.log(Status.FAIL,"Beginner isn't selected.");
		}
		if(flagCondition[1]==1)
		{
			test1.log(Status.PASS,"Course is visible to select.");
			test1.log(Status.PASS,"Course is selected.");
		}
		else
		{
			test1.log(Status.FAIL,"Course isn't visible to select.");
			test1.log(Status.FAIL,"Course isn't selected.");
		}
		if(flagCondition[2]==1)
		{
			test1.log(Status.PASS,"English is visible to select.");
			test1.log(Status.PASS,"English is selected.");
		}
		else
		{
			test1.log(Status.FAIL,"English isn't visible to select.");
			test1.log(Status.FAIL,"English isn't selected.");
		}
		if(flagCourse[0]==1)
		{
			test1.log(Status.PASS,"First course details is extracted.");
		}
		else
		{
			test1.log(Status.FAIL,"First course details isn't extracted.");
		}
		if(flagCourse[1]==1)
		{
			test1.log(Status.PASS,"Navigated back to searched page.");
		}
		else
		{
			test1.log(Status.FAIL,"Didn't navigate back to searched page.");
		}
		if(flagCourse[2]==1)
		{
			test1.log(Status.PASS,"Second course details is extracted.");
		}
		else
		{
			test1.log(Status.FAIL,"Second course details isn't extracted.");
		}
		if(flagCourse[3]==1)
		{
			test1.log(Status.PASS,"Navigated back to searched page.");
		}
		else
		{
			test1.log(Status.FAIL,"Didn't navigate back to searched page.");
		}	
		//Comparing the actual and expected results
		Assert.assertEquals(flagCondition,conditionExpected);
		Assert.assertEquals(flagCourse,courseExpected);
	}
	@Test(groups="Regression Suite",priority=2,retryAnalyzer=Retry.class)
	public void retrieveLanguageLevel()
	{
		test2=report.createTest("Retrieving All Languages and Levels In Language Learning");
		//retrieves the languages and levels in Language Learning and logs the status
		int flagLanguageLevel[]=coursera.getLanguagesLevels();
		int languageLevelExpected[]= {1,1};
		if(flagLanguageLevel[0]==1)
		{
			test2.log(Status.PASS,"Traversed to Language Learning and all the languages are extracted.");
		}
		else
		{
			test2.log(Status.FAIL,"Didn't traverse to Language Learning and the languages aren't extracted.");
			test2.log(Status.SKIP,"Retrieval of the levels is skipped.");
		}
		if(flagLanguageLevel[0]==1 && flagLanguageLevel[1]==1)
		{	
				test2.log(Status.PASS,"All the levels are extracted.");
		}
		else
		{
			test2.log(Status.FAIL,"All the levels aren't extracted.");
		}
		//Comparing the actual and expected result
		Assert.assertEquals(flagLanguageLevel,languageLevelExpected);
	}
	@Test(groups="Regression Suite",priority=3,retryAnalyzer=Retry.class)
	public void retrieveErrorMessage()
	{
		test3=report.createTest("Retrieving Error Message After Entering An Invalid Data In A Mandatory Field And Submitting The Form");
		//sets the Contact Sales form values, submits it, retrieves the error message on entering invalid input and logs the status
		int flagContactUs[]=coursera.goToContactUs();
		int flagFName=coursera.setFirstName(data[0]);
		int flagLName=coursera.setLastName(data[1]);
		int flagEmail=coursera.setEmail(data[2]);
		int flagCName=coursera.setCompanyName(data[3]);
		int flagJRole=coursera.setJobRole(data[4]);
		int flagJFunction=coursera.setJobFunction(data[5]);
		int flagPhone=coursera.setPhone(data[6]);
		int flagCSize=coursera.setCompanySize(data[7]);
		int flagLearner=coursera.setLearners(data[8]);
		int flagCountry=coursera.setCountry(data[9]);
		int flagSubmit=coursera.submit();
		String message=coursera.getMessage();
		int contactUsExpected[]= {1,1,1,1};
		if(flagContactUs[0]==1)
		{
			test3.log(Status.PASS,"Navigated to Home page.");
			if(flagContactUs[0]==1 && flagContactUs[1]==1)
			{
				test3.log(Status.PASS,"For Businesses/For Enterprises visible to select.");
				test3.log(Status.PASS,"For Businesses/For Enterprises selected.");
				if(flagContactUs[0]==1 && flagContactUs[1]==1 && flagContactUs[2]==1)
				{
					test3.log(Status.PASS,"Solutions visible to select.");
					test3.log(Status.PASS,"Solutions selected.");
					test3.log(Status.PASS,"Products displayed.");
				}
				else
				{
					test3.log(Status.FAIL,"Solutions not visible to select.");
					test3.log(Status.FAIL,"Solutions not selected.");
					test3.log(Status.FAIL,"Products not displayed.");
				}
				if(flagContactUs[0]==1 && flagContactUs[1]==1 && flagContactUs[3]==1)
				{
					test3.log(Status.PASS,"Contact Sales visible.");
					test3.log(Status.PASS,"Contact Sales selected.");
					if(flagFName==1)
					{
						test3.log(Status.PASS,"First name textbox visible");
						test3.log(Status.PASS,"First name entered in textbox");
					}
					else
					{
						test3.log(Status.FAIL,"First name textbox not visible");
						test3.log(Status.FAIL,"First name not entered in textbox");
					}
					if(flagLName==1)
					{
						test3.log(Status.PASS,"Last name textbox visible");
						test3.log(Status.PASS,"Last name entered in textbox");
					}
					else
					{
						test3.log(Status.FAIL,"Last name textbox not visible");
						test3.log(Status.FAIL,"Last name not entered in textbox");
					}
					if(flagEmail==1)
					{
						test3.log(Status.PASS,"Email textbox visible");
						test3.log(Status.PASS,"Email entered in textbox");
					}
					else
					{
						test3.log(Status.FAIL,"Email textbox not visible");
						test3.log(Status.FAIL,"Email not entered in textbox");
					}
					if(flagCName==1)
					{
						test3.log(Status.PASS,"Company name textbox visible");
						test3.log(Status.PASS,"Company name entered in textbox");
					}
					else
					{
						test3.log(Status.FAIL,"Company name textbox not visible");
						test3.log(Status.FAIL,"Company name not entered in textbox");
					}
					if(flagJRole==1)
					{
						test3.log(Status.PASS,"Job role dropdown visible");
						test3.log(Status.PASS,"Job role selected");
					}
					else
					{
						test3.log(Status.FAIL,"Job role dropdown not visible");
						test3.log(Status.FAIL,"Job role not selected");
					}
					if(flagJFunction==1)
					{
						test3.log(Status.PASS,"Job function dropdown visible");
						test3.log(Status.PASS,"Job function selected");
					}
					else
					{
						test3.log(Status.FAIL,"Job function dropdown not visible");
						test3.log(Status.FAIL,"Job function not selected");
					}
					if(flagPhone==1)
					{
						test3.log(Status.PASS,"Phone number textbox visible");
						test3.log(Status.PASS,"Phone number entered in textbox");
					}
					else
					{
						test3.log(Status.FAIL,"Phone number textbox not visible");
						test3.log(Status.FAIL,"Phone number not entered in textbox");
					}
					if(flagCSize==1)
					{
						test3.log(Status.PASS,"Company size dropdown visible");
						test3.log(Status.PASS,"Company size selected");
					}
					else
					{
						test3.log(Status.FAIL,"Company size dropdown not visible");
						test3.log(Status.FAIL,"Company size not selected");
					}
					if(flagLearner==1)
					{
						test3.log(Status.PASS,"Number of learners dropdown visible");
						test3.log(Status.PASS,"Number of learners selected");
					}
					else
					{
						test3.log(Status.FAIL,"Number of learners dropdown not visible");
						test3.log(Status.FAIL,"Number of learners not selected");
					}
					if(flagCountry==1)
					{
						test3.log(Status.PASS,"Country dropdown visible");
						test3.log(Status.PASS,"Country selected");
					}
					else
					{
						test3.log(Status.FAIL,"Country dropdown not visible");
						test3.log(Status.FAIL,"Country not selected");
					}
					if(flagSubmit==1)
					{
						test3.log(Status.PASS,"Submit button visible");
						test3.log(Status.PASS,"Submit button clicked");
					}
					else
					{
						test3.log(Status.FAIL,"Submit button not visible");
						test3.log(Status.FAIL,"Submit button not clicked");
					}
					if(flagFName==1 && flagLName==1 && flagEmail==1 && flagCName==1 && flagJRole==1 && flagJFunction==1 && flagPhone==1 && flagCSize==1 && flagLearner==1 && flagSubmit==1)
					{
						if(message.equals(errMessage))
						{
							test3.log(Status.PASS,"Correct error message displayed on entering invalid input(email).");
						}
						else
							test3.log(Status.FAIL,"Incorrect error message displayed on entering invalid input(email).");
					}
					else
						test3.log(Status.SKIP,"Retrieval of error message is skipped.");
				}
				else
				{
					test3.log(Status.FAIL,"Contact Sales not visible.");
					test3.log(Status.FAIL,"Contact Sales not selected.");
					test3.log(Status.SKIP,"Submission of Contact Sales form is skipped.");
				}
			}
			else
			{
				test3.log(Status.FAIL,"For Businesses/For Enterprises not visible to select.");
				test3.log(Status.FAIL,"For Businesses/For Enterprises not selected.");
				test3.log(Status.SKIP,"Selection of Solutions and displaying of products are skipped.");
				test3.log(Status.SKIP,"Selection of Contact Sales is skipped.");
				test3.log(Status.SKIP,"Submission of Contact Sales form is skipped.");
			}
		}
		else
		{
			test3.log(Status.FAIL,"Didn't navigate to Home page.");
			test3.log(Status.SKIP,"Selection of For Businesses/For Enterprises is skipped.");
			test3.log(Status.SKIP,"Selection of Solutions and displaying of products are skipped.");
			test3.log(Status.SKIP,"Selection of Contact Sales is skipped.");
			test3.log(Status.SKIP,"Submission of Contact Sales form is skipped.");
		}
		//Comparing the actual and expected results
		Assert.assertEquals(flagContactUs,contactUsExpected);
		Assert.assertEquals(flagFName,1);
		Assert.assertEquals(flagLName,1);
		Assert.assertEquals(flagEmail,1);
		Assert.assertEquals(flagCName,1);
		Assert.assertEquals(flagJRole,1);
		Assert.assertEquals(flagJFunction,1);
		Assert.assertEquals(flagPhone,1);
		Assert.assertEquals(flagCSize,1);
		Assert.assertEquals(flagLearner,1);
		Assert.assertEquals(flagCountry,1);
		Assert.assertEquals(flagSubmit,1);
	}
	@AfterClass(alwaysRun=true)
	public void endTest()
	{
		report.flush();
	}
	@AfterTest(alwaysRun=true)
	public void close()
	{
		//Closes the application
		CloseBrowser.closeBrowser(driver);
	}
}