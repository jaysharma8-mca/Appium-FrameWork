package mobileTesting.AppiumFrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	  public static AndroidDriver<AndroidElement> driver;
	
	  public static AppiumDriverLocalService service;
	  
	  public AppiumDriverLocalService startServer() throws IOException { 
		  
		  boolean serverRunningFlag = checkIfServerIsRunnning(4723);
	  
		  if(!serverRunningFlag) { 
			  //service.stop(); 
			  System.out.println("Server not running... Server Started");
			  service = AppiumDriverLocalService.buildDefaultService(); 
			  service.start(); 
		  }
	  
//		  else { 
//			  System.out.println("Server is running... Killed & Restarted");
//			  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\killAppium.bat"); 
//			  service = AppiumDriverLocalService.buildDefaultService();
//			  service.start(); 
//		  }
	  
		  return service;
		  
	  }
	  
	  public static boolean checkIfServerIsRunnning(int port) {
	  
		  boolean isServerRunning = false; 
		  ServerSocket serverSocket; 
		  try {
			  	serverSocket = new ServerSocket(port); serverSocket.close(); 
			  } 
		  catch(IOException e) { 
			  //If control comes here, then it means that the port is in use
			  isServerRunning = true; 
		  	} 
		  finally { serverSocket = null; } 
		  return isServerRunning; 
	  }
	 

	
	  public static void startEmulator() throws IOException, InterruptedException {
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat"); 
		  Thread.sleep(6000); 
	  }
	 

	  public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {

		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\global.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);

		

		File appDir = new File("src");
		File app = new File(appDir, (String) properties.get(appName));
		DesiredCapabilities dc = new DesiredCapabilities();

		//String device = (String) properties.get("device");
		String device= System.getProperty("deviceName");
		if(device.contains("Emulator")) {
			startEmulator();
		}

		dc.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}
	  
	  public static void getScreenshot(String s) throws IOException {
		  File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  //FileUtils.copyFile(screenShotFile, new File("C:\\Users\\jaysh\\Downloads\\AppiumFramework\\src\\main\\java\\failureScreenShot\\testFailure.png"));
		  FileUtils.copyFile(screenShotFile, new File(System.getProperty("user.dir") + "\\src\\main\\java\\failureScreenShot\\"+s+".png"));
	  }

}
