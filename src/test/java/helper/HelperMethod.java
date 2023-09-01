package helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import testRunner.CucumberRunner;

public class HelperMethod {
	
	public HelperMethod(){
		
	}
	
	public static void captureScreenShot(WebDriver driver) throws IOException {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_HH_mm_ss");
		String screenShotName = dateFormat.format(date).toString();
		Shutterbug.shootPage(driver, Capture.FULL_SCROLL).withName(screenShotName).save(CucumberRunner.screenShotFolder.toString());
		File screenShotFilePath = new File (CucumberRunner.screenShotFolder+"/"+screenShotName+".png");
		ExtentCucumberAdapter.addTestStepLog("<a href =\""+screenShotFilePath.toString()+"\"><i>ScreenShot</i></a>");
	}
	
	public static void captureFailedScreenShot(WebDriver driver) throws IOException {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_HH_mm_ss");
		String screenShotName = dateFormat.format(date).toString();
		Shutterbug.shootPage(driver, Capture.FULL_SCROLL).withName(screenShotName).save(CucumberRunner.screenShotFolder.toString());
		File screenShotFilePath = new File (CucumberRunner.screenShotFolder+"/"+screenShotName+".png");
		ExtentCucumberAdapter.addTestStepLog("<a href =\""+screenShotFilePath.toString()+"\" style=\"color:red;\"><i>Failed Step ScreenShot</i></a>");
	}
	
	public static void captureElementScreenShot(WebDriver driver, WebElement ele) throws IOException {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_HH_mm_ss");
		String screenShotName = dateFormat.format(date).toString();
		Shutterbug.shootElement(driver, ele).withName(screenShotName).save(CucumberRunner.screenShotFolder.toString());
		File screenShotFilePath = new File (CucumberRunner.screenShotFolder+"/"+screenShotName+".png");
		ExtentCucumberAdapter.addTestStepLog("<a href =\""+screenShotFilePath.toString()+"\" ><i>Element ScreenShot</i></a>");
	}
	
	public static void clickOnElementWithXpath(String xpath, WebDriver driver) {
		System.out.println("Searching element: "+xpath);
		WebElement ele = driver.findElement(By.xpath(xpath));
		if (ele.isDisplayed()) {
			System.out.println("element with xpath: "+xpath+ " found!");
			ele.click();
		}
		else {
			Assert.fail("Failed to find element with xpath: "+xpath);
		}
		
	}
	
	public static void clickOnWebElement(WebElement element) {
		if (element.isDisplayed()) {
			element.click();
		}
		else {
			Assert.fail("Failed to find WebElement: " + element);
		}
	}
	
	public static void validateExactLabelOnUI (WebElement element, String exptectedString, WebDriver driver) {
		try {
			element.isDisplayed();
			if (element.getText().equals(exptectedString)) {
//				ExtentCucumberAdapter.addTestStepLog("Label on UI: " +exptectedString+ " is displayed");
			}
			else {
				Assert.fail("Label on UI: "+element.getText()+" doesnt match with expected Label i.e. "+exptectedString);
			}
		} catch (Exception e) {
			Assert.fail(element+"is not displayed");
		}
	}
	
	public static void validatePartialLabelOnUI (WebElement element, String exptectedString, WebDriver driver) {
		try {
			element.isDisplayed();
			if (element.getText().contains(exptectedString)) {
//				ExtentCucumberAdapter.addTestStepLog("Label on UI: " +exptectedString+ " is displayed");
			}
			else {
				Assert.fail("Label on UI: "+element.getText()+" doesnt match with expected Label i.e. "+exptectedString);
			}
		} catch (Exception e) {
			Assert.fail(element+"is not displayed");
		}
	}
}
