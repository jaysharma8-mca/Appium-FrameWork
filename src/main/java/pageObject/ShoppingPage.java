package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ShoppingPage {
	
	public ShoppingPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
	public List<WebElement> addToCartButtonClick;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement appBarButtonCart;
	
	
	public List<WebElement> addToCartButtonClick() {
		System.out.println("Clicked on Add to cart button");
		return addToCartButtonClick;
	}
	
	public WebElement appBarButtonCart() {
		System.out.println("Clicked on App Bar Button");
		return appBarButtonCart;
	}
}
