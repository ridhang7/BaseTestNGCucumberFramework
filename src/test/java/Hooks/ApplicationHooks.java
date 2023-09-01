package Hooks;

import java.io.IOException;
import helper.HelperMethod;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import managerClass.CreateDriverManager;
import picoContainer.TestContext;

@SuppressWarnings("static-access")
public class ApplicationHooks {
	TestContext testContext = new TestContext();
	HelperMethod helper = new HelperMethod();
	
	@After
	public void testScenarioCompleted(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			helper.captureFailedScreenShot(CreateDriverManager.getInstance().getDriver());
		}
		else {
			helper.captureScreenShot(CreateDriverManager.getInstance().getDriver());
		}
		testContext.closeDriver();
	}
}
