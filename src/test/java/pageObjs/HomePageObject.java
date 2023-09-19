package pageObjs;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import helper.BrowerStackConfig;
import helper.HelperMethod;

@SuppressWarnings("static-access")
public class HomePageObject extends BrowerStackConfig{
	public HomePageObject(WebDriver driver)  {
		PageFactory.initElements(driver,this);
	}
	
	HelperMethod _helper = new HelperMethod();
	
	@FindBy(how = How.XPATH, using = "//a[text()='Services']") 
	WebElement serviceSubMenu;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Products']") 
	WebElement productSubMenu;

	@FindBy(how = How.XPATH, using = "//div[@id='rightPanel']//link") 
	WebElement serviceTitle;
	
	@FindBy(how = How.XPATH, using = "//h1") 
	WebElement productTitle;
	
	public void loginWithCreds(WebDriver driver) throws IOException {
		_helper.captureScreenShot(driver);
	}
	
	public void clickOnService(WebDriver driver) throws IOException {
		_helper.clickOnWebElement(serviceSubMenu);
		_helper.captureScreenShot(driver);
		
	}
	
	public void clickOnProductwithXpath(WebDriver driver) throws IOException {
		_helper.clickOnWebElement(productSubMenu);
		_helper.captureScreenShot(driver);
	}
	
	public void verifyServicePageTitle(WebDriver driver) throws IOException {
		String attributeValue = serviceTitle.getAttribute("href");
		if (attributeValue.contains("services/")) {
			ExtentCucumberAdapter.addTestStepLog("landed on services page");
		}
		else {
			Assert.fail("Failed to land on services page");
		}
//		_helper.validateExactLabelOnUI(serviceTitle, "Available Bookstore SOAP services:", driver);
	}
	
	public void verifyProductPageTitle(WebDriver driver) throws IOException {
		_helper.validateExactLabelOnUI(productTitle, "Innovative and Intelligent Software Testing Platform", driver);
	}
	
	
}
