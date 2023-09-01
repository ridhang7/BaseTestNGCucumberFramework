package Hooks;

import java.io.IOException;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import picoContainer.TestContext;

public class ApplicationHooks {
	TestContext testContext = new TestContext();
	@After
	public void check(Scenario scenario) throws IOException
	{
		System.out.println("After Test executed "+scenario.getName());
		testContext.closeDriver();
	}
}
