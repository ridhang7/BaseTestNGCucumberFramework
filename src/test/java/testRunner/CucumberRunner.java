package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags="@letsgoo",
		features= {"src/test/resources/features"}, 
		glue= {"stepDefs","Hooks"},
		dryRun= false,
		plugin= {})
public class CucumberRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios(){
		return super.scenarios();
	}
}


