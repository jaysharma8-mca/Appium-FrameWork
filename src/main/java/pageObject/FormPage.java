package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


//Login Page Validation
public class FormPage {
	
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement nameField;
	
	
	@AndroidFindBy(xpath = "//*[@text='Female']")
	public WebElement femaleRadioButton;
	
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement countrySelectionDropDown;
	
	
	@AndroidFindBy(xpath = "//*[@text='Argentina']")
	public WebElement selectSpinnerValue;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	
	
	public WebElement getNameField() {
		System.out.println("Trying to find name field...");
		return nameField;
	}
	
	public WebElement femaleRadioButton() {
		System.out.println("Checked the radio button");
		return femaleRadioButton;
	}
	
	public WebElement countrySelectionDropDown() {
		System.out.println("Scroll to dropdown value");
		return countrySelectionDropDown;
	}
	
	public WebElement selectSpinnerValue() {
		System.out.println("Select spinner value");
		return selectSpinnerValue;
	}
	
	public WebElement btnLetsShop() {
		System.out.println("Click on Let's shop button");
		return btnLetsShop;
	}

}
