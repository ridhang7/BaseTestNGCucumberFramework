package testRunner;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		tags="@letsgoo1",
		features= {"src/test/resources/features"}, 
		glue= {"stepDefs","Hooks"},
		publish= true,
		dryRun= false,
		plugin= {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class CucumberRunner extends AbstractTestNGCucumberTests{
	public static File screenShotFolder;
	
	@BeforeTest
	public void createScreenShotFolder() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY_HH_mm_ss");
		String fileName = dateFormat.format(date).toString();
		screenShotFolder = new File(System.getProperty("user.dir")+"/screenShots/"+fileName);
		System.out.println(screenShotFolder);
	}
	
	
	
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
}


