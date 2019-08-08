package com.atmecs.testscripts;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.PageActionClick;
import com.atmecs.constants.PageActionGetText;
import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.pages.ClickHelper;
import com.atmecs.pages.GetTextHelper;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.CommanUtils;
import com.atmecs.utils.PropertiesUtil;

/**
 * 
 * The AddToCart program implements the functionality
 * for the Adding product to cart.
 *  
 * @author Sampada.Joshi
 *
 */
public class AddToCart extends TestBase
{
	String imageList = objectrepoProps.getProperty("imageClick");
	String buyNowbtn = objectrepoProps.getProperty("BuyNowbtn");
	String notification = objectrepoProps.getProperty("notification");
	String navigateToClearancePage = objectrepoProps.getProperty("navigateToClearancePage");
	String getItemCount = objectrepoProps.getProperty("getItemCount");
	String BuyNowbtn1 = objectrepoProps.getProperty("BuyNowbtn1");
	String addToCart = objectrepoProps.getProperty("addToCart");
	
	/**
	 * This method is invoke browser
	 */
	@BeforeTest
	public void preSetup()
	{
		this.driver = invokeBrowser();
		String baseUrl = configProps.getProperty("applicationurl");
		driver.get(baseUrl);
		this.driver = windowOperations();
	}

	/**
	 * The method is regarding to adding the product 
	 * to the cart
	 * 
	 */
	@Test
	public void addToCart() 
	{
		//Verifying whether user is navigated to clearance page by using title
		String Url = "http://10.10.10.232:8080/clearance";
		String expectedTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Clearance";
		String actualTitle = "";
		driver.get(Url);
		actualTitle = driver.getTitle();
		asert.verifyAssertion(actualTitle, expectedTitle, "Navigation");

		//Verifying whether the particular image is opening after clicking or not by PageTitle
		ClickHelper.clickElement(driver, imageList);
		String expectedImgTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Green Ghost";
		String actualImgTitle = "";
		actualImgTitle = driver.getTitle();
		asert.verifyAssertion(actualImgTitle, expectedImgTitle, "Navigation");

		//Verifying whether the notification message is displaying after clicking on Buy Now option
		ClickHelper.clickElement(driver, buyNowbtn);

		//Applied wait 
		CommanUtils.waitForElement(driver, objectrepoProps.getProperty("notification"));
		
		//Taking the notification in message and passing into actualMessage
		GetTextHelper.getElement(driver, notification);
		System.out.println(PageActionGetText.text);
		String expectedMessage = "GREEN GHOST HAS BEEN ADDED TO THE CART!";
		asert.verifyAssertion(PageActionGetText.text, expectedMessage, "Notification");

		//To navigate to the Clearance Page
		ClickHelper.clickElement(driver, navigateToClearancePage);
		
		//Verify whether the product is added to cart
		GetTextHelper.getElement(driver, getItemCount);
		GetTextHelper.getElement(driver, BuyNowbtn1);
		ClickHelper.clickElement(driver, addToCart);
		GetTextHelper.getElement(driver, getItemCount);
		asert.verifyAssertion(PageActionGetText.text, PageActionGetText.text, "Products in cart");
	}

	/**
	 * This method is to close the browser
	 */
	@AfterTest
	public void tearDown()
	{
		driver.close(); 
	}
}





