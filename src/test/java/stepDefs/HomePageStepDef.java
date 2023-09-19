package stepDefs;

import helper.HelperMethod;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import managerClass.CreateDriverManager;
import pageObjs.HomePageObject;
import picoContainer.TestContext;

public class HomePageStepDef extends HelperMethod {
	HomePageObject _homePage;
	
	public HomePageStepDef(TestContext testContext) {
		_homePage = testContext.getPageObjectManager().getHomePage();
	}
	
	@And("^I login valid using credentials")
	public void i_login_valid_using_credentials() throws Throwable{
		CreateDriverManager.getInstance().getDriver().get("https://parabank.parasoft.com/parabank/index.htm");
		CreateDriverManager.getInstance().getDriver().manage().window().maximize();
		_homePage.loginWithCreds(CreateDriverManager.getInstance().getDriver());
	}
	
	@And("^I Click on service")
	public void i_click_on_service() throws Throwable{
		_homePage.clickOnService(CreateDriverManager.getInstance().getDriver());
	}
	
	@Then("Verify Service page is displayed")
	public void verify_service_page_is_displayed() throws Throwable
	{
		_homePage.verifyServicePageTitle(CreateDriverManager.getInstance().getDriver());
	}
	
	@Then("Verify Product page is displayed")
	public void verify_product_page_is_displayed() throws Throwable{
		_homePage.verifyProductPageTitle(CreateDriverManager.getInstance().getDriver());
	}
	
	@And("^I Click on Products")
	public void i_click_on_products() throws Throwable{
		_homePage.clickOnProductwithXpath(CreateDriverManager.getInstance().getDriver());
		}
			
}
