package mobileTesting.AppiumFrameWork;

import java.io.IOException;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.HomePage;

public class ApiDemoTest extends BaseClass{
	
	@Test
	public void ApiDemo() throws IOException, InterruptedException {
		
		//service = startServer();
		
		AndroidDriver<AndroidElement> driver=capabilities("ApiDemosApp");
		
		HomePage h = new HomePage(driver);
		
		h.Preferences.click();
		
		//service.stop();
	}
}
