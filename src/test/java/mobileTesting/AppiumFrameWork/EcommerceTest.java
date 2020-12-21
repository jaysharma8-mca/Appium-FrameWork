package mobileTesting.AppiumFrameWork;

import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;
import pageObject.CheckoutPage;
import pageObject.FormPage;
import pageObject.ShoppingPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;

import static io.appium.java_client.touch.offset.ElementOption.element;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;

import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class EcommerceTest extends BaseClass{

	@SuppressWarnings({ "rawtypes" })
	@Test(dataProvider = "InputData", dataProviderClass = TestDataProvider.class)// got this from TestDataProvider
	public void totalValidation(String input) throws IOException, InterruptedException {
		
		service = startServer();

		AndroidDriver<AndroidElement> driver=capabilities("GeneralStoreApp");
		
		FormPage formPage = new FormPage(driver);
		
		//driver.findElement(By.id("android:id/text1")).click(); //spinner content
	     
		formPage.countrySelectionDropDown().click();
		
		Utilities utilities = new Utilities(driver);
		utilities.scrollToText("Argentina");
		
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+Argentina+"\"));");
          
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
	 
		//formPage.nameField.sendKeys("Neha Singh");
		formPage.getNameField().sendKeys(input);

		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Neha Singh");

		driver.hideKeyboard();

		//driver.findElement(By.xpath("//*[@text='Female']")).click();
     
		formPage.femaleRadioButton().click();

		
     
     
		//formPage.selectSpinnerValue().click();

		//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
     
		formPage.btnLetsShop().click();

     
		ShoppingPage shoppingPage = new ShoppingPage(driver);
     
		//driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
     
		shoppingPage.addToCartButtonClick().get(0).click();
     
		//driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
     
		shoppingPage.addToCartButtonClick().get(0).click();

		//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		shoppingPage.appBarButtonCart().click();

		Thread.sleep(4000);
    
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		//int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
    
		int count = checkoutPage.productList.size();

		double sum=0;

		for(int i=0;i<count;i++)

		{

			//String amount1= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
    	
			String amount1= checkoutPage.productList.get(i).getText();

			double amount=getAmount(amount1);

			sum=sum+amount;

		}

    	System.out.println(sum+ "sum of products");



    	//String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

    	String total=checkoutPage.totalAmount.getText();

    	total= total.substring(1);

    	double totalValue=Double.parseDouble(total);

    	System.out.println(totalValue+"Total value of products");

    	Assert.assertEquals(sum, totalValue, 0);



    	//Mobile Gestures

    	//WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
    
    	WebElement checkbox = checkoutPage.checkBox;

    	TouchAction t=new TouchAction(driver);

    	t.tap(tapOptions().withElement(element(checkbox))).perform();



    	//WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
    
    	WebElement tc = checkoutPage.termsCondition;

    	t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

    	driver.findElement(By.id("android:id/button1")).click();

    	driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    	
    	service.stop();

	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	
	public static double getAmount(String value){

		value= value.substring(1);

		double amount2value=Double.parseDouble(value);

		return amount2value;

	}

}

