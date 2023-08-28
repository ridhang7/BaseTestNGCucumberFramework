package stepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.And;
import managerClass.CreateDriverManager;
import pageObjs.HomePageObject;
import picoContainer.TestContext;

public class HomePageStepDef {
	WebDriver driver;
	HomePageObject _homePage;
	
	public HomePageStepDef(TestContext testContext) {
		_homePage = testContext.getPageObjectManager().getHomePage();
//		driver = testContext.getWebDriver();
	}
	
	@And("^I login valid using credentials")
	public void i_login_valid_using_credentials() throws Throwable{
		CreateDriverManager.getInstance().getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
		Thread.sleep(5000);
	}
	
	@And("^I Click on service")
	public void i_click_on_service() throws Throwable{
		CreateDriverManager.getInstance().getDriver().findElement(By.xpath("//a[text()='Services']")).click();
		Thread.sleep(4000);
		if (CreateDriverManager.getInstance().getDriver().findElement(By.xpath("//span[text()='Available Bookstore SOAP services:']")).getText() == "Available Bookstore SOAP services:")
			{
				Assert.fail("Test case failed for service");
			}
	}
	
	@And("^I Click on Products")
	public void i_click_on_products() throws Throwable{
		CreateDriverManager.getInstance().getDriver().findElement(By.xpath("//a[text()='Products']")).click();
		Thread.sleep(4000);
		if (CreateDriverManager.getInstance().getDriver().findElement(By.xpath("//h1")).getText() == "Innovative and Intelligent Software Testing Platform")
			{
				Assert.fail("Test case failed for product");
			}
	}
}
