package helper;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.browserstack.local.Local;

public class BrowerStackConfig {
	public static WebDriver driver;
    private static JSONObject config;
    private static Local l;
    private static Map<String, Object> commonCapabilities;
	
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@BeforeSuite(alwaysRun=true)
    @Parameters(value = { "config" })
    public void beforeSuite(String config_file) throws Exception {
        JSONParser parser = new JSONParser();
        config = (JSONObject) parser.parse(new FileReader("C:\\Users\\ridha\\eclipse-workspace\\TestNGCucumberFramework\\configData\\" + config_file));
        commonCapabilities = (Map<String, Object>) config.get("capabilities");
        @SuppressWarnings("unused")
		HashMap bstackOptionsMap = (HashMap) commonCapabilities.get("bstack:options");
	
    }
    
//  @BeforeMethod(alwaysRun = true)
//  @org.testng.annotations.Parameters(value = { "config", "environment" })
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static WebDriver setUp(String jsonFile, String browser) throws Exception {
      JSONParser parser = new JSONParser();
      config = (JSONObject) parser.parse(new FileReader("C:\\Users\\ridha\\eclipse-workspace\\TestNGCucumberFramework\\configData\\" + jsonFile));
      JSONObject envs = (JSONObject) config.get("environments");

      MutableCapabilities capabilities = new MutableCapabilities();

      Map<String, Object> envCapabilities = (Map<String, Object>) envs.get(browser);
      Iterator it = envCapabilities.entrySet().iterator();
      while (it.hasNext()) {
          Map.Entry pair = (Map.Entry) it.next();
          capabilities.setCapability(pair.getKey().toString(), pair.getValue());
      }

      commonCapabilities = (Map<String, Object>) config.get("capabilities");
      it = commonCapabilities.entrySet().iterator();
      while (it.hasNext()) {
          Map.Entry pair = (Map.Entry) it.next();
          if (capabilities.getCapability(pair.getKey().toString()) == null) {
              capabilities.setCapability(pair.getKey().toString(), pair.getValue());
          } else if (pair.getKey().toString().equalsIgnoreCase("bstack:options")) {
              HashMap bstackOptionsMap = (HashMap) pair.getValue();
              bstackOptionsMap.putAll((HashMap) capabilities.getCapability("bstack:options"));
              capabilities.setCapability(pair.getKey().toString(), bstackOptionsMap);
          }
      }
      
      if (capabilities.getCapability("bstack:options") != null) {
          HashMap bstackOptionsMap = (HashMap) capabilities.getCapability("bstack:options");
          if ((bstackOptionsMap.get("local") != null &&
                  bstackOptionsMap.get("local").toString().equalsIgnoreCase("true") && (l == null || !l.isRunning()))) {
              l = new Local();
              Map<String, String> options = new HashMap<String, String>();
              options.put("key", config.get("key").toString());
              l.start(options);
          }
          bstackOptionsMap.put("source", "testng:sample-selenium-4:v1.2");
      }
      System.out.println("https://"+config.get("user")+":"+config.get("key")+"@"+config.get("server")+"/wd/hub");
      driver = RemoteWebDriver.builder()
              .address(new URL("https://"+config.get("user")+":"+config.get("key")+"@"+config.get("server")+"/wd/hub"))
              .oneOf(capabilities)
              .build();
      
      return driver;
  }

}
