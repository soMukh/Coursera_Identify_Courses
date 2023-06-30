package coursera;
import org.openqa.selenium.By;
public class Locators 
{
	//Locators of all the web elements required for automation
	By search=By.className("react-autosuggest__input");
	By beginner=By.xpath("//span[text()='Beginner']/ancestor::label//input");
	By courses=By.xpath("//span[text()='Courses']/ancestor::label//input");
	By showMoreLanguage=By.xpath("//button[@aria-label='Show more Language options']");
	By english=By.xpath("//span[text()='English']/ancestor::label//input");
	By apply=By.xpath("//span[text()='Apply']");
	By beginnerFilterClear=By.xpath("//button[@aria-label='Clear Beginner filter']/span[text()='Beginner']");
	By course1=By.xpath("//a[contains(@aria-label,'Introduction to Web Development with HTML')]");
	By course2=By.xpath("//a[contains(@aria-label,'Introduction to Front-End Development')]");
	By cName=By.tagName("h1");
	By rating=By.xpath("//span[@data-test='number-star-rating']");
	By duration=By.xpath("//div[@class='ProductGlance']//span[contains(text(),'Approx.')]");
	By explore=By.xpath("//span[text()='Explore']");
	By languageLearning=By.xpath("//span[text()='Language Learning']//parent::button");
	By freeCourses=By.xpath("//a[contains(@href,'Language') and text()='Free courses']");
	By language=By.xpath("//div[@id='checkbox-group']/div");
	By begin=By.xpath("//span[text()='Beginner']");
	By intermediate=By.xpath("//span[text()='Intermediate']");
	By advanced=By.xpath("//span[text()='Advanced']");
	By mixed=By.xpath("//span[text()='Mixed']");
	By business=By.xpath("//span[text()=' Businesses']");
	By enterprise=By.id("enterprise-link");
	By solutions=By.xpath("//a[normalize-space()='Solutions']");
	By contact=By.xpath("//span[text()='Contact Sales']/parent::a");
	By fName=By.id("FirstName");
	By lName=By.id("LastName");
	By email=By.id("Email");
	By companyName=By.id("db_company_name__c");
	By jobRole=By.id("jobRole");
	By jobFunction=By.id("C4C_Job_Title__c");
	By phone=By.id("Phone");
	By eNumber=By.id("Employee_Range__c");
	By tNumber=By.id("Self_reported_employees_to_buy_for__c");
	By country=By.id("Country");
	By submit=By.xpath("//button[@type='submit']");
	By errMessage=By.id("ValidMsgEmail");
}