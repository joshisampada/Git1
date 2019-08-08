package com.atmecs.testscripts;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.pages.ClickHelper;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.CommanUtils;
import com.atmecs.utils.PropertiesUtil;

/**
 * 
 * The CheckoutTest program implements the functionality
 * of updating the no of items and payment regarding. 
 * 
 * @author Sampada.Joshi
 *
 */
public class CheckoutTest extends TestBase
{
	String navigateToClearancePage = objectrepoProps.getProperty("navigateToClearancePage");
	String BuyNowbtn = objectrepoProps.getProperty("BuyNowbtn");
	String clickCart = objectrepoProps.getProperty("clickCart");
	String Quantity = objectrepoProps.getProperty("Quantity");
	String updateClick = objectrepoProps.getProperty("updateClick");
	String checkoutBtn = objectrepoProps.getProperty("checkoutBtn");
	String productInfoLink = objectrepoProps.getProperty("productInfoLink");
	String clickOnEdit = objectrepoProps.getProperty("clickOnEdit");
	String Quantity1 = objectrepoProps.getProperty("Quantity1");
	
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
	 * The method is regarding to checkout process
	 * after buying the product
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	public void checkout() throws InterruptedException 
	{
		//To navigate to the Clearance Page
		ClickHelper.clickElement(driver, navigateToClearancePage);

		//To click on Buy Now option
		ClickHelper.clickElement(driver, BuyNowbtn);

		//To click on cart
		ClickHelper.clickElement(driver, clickCart);
		
		//To clear the quantity
		ClickHelper.clearElement(driver, Quantity);

		//To send the value 
		driver.findElement(By.xpath(objectrepoProps.getProperty("Quantity"))).sendKeys("5");
		String beforeUpdateText = driver.findElement(By.id("subtotal")).getText();
		
		//driver.findElement(By.xpath(objectrepoProps.getProperty("updateClick"))).click();
		ClickHelper.clickElement(driver, updateClick);
		String afterUpdateText = driver.findElement(By.id("subtotal")).getText();

		Assert.assertTrue(beforeUpdateText.equals(afterUpdateText));
		System.out.println("update success");
		list.waitForElement(driver, "checkoutBtn");
		
		//Click on checkout button
		ClickHelper.clickElement(driver, checkoutBtn);

		//Verifying whether user is navigated to checkout page by using title after clicking on checkout btn
		String Url = "http://10.10.10.232:8080/checkout";
		String expectedTitle = "Broadleaf Commerce Demo Store - Heat Clinic - Checkout";
		String actualTitle = "";
		driver.get(Url);
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);

		//Click on link to open product info
		ClickHelper.clickElement(driver, productInfoLink);

		//Verifying whether user is navigated to particular product page by using title 
		String Url1 = "http://10.10.10.232:8080/hot-sauces/green_ghost";
		String expectedTitle1 = "Broadleaf Commerce Demo Store - Heat Clinic - Green Ghost";
		String actualTitle1 = "";
		driver.get(Url1);
		actualTitle1 = driver.getTitle();
		System.out.println(actualTitle1);
		Assert.assertEquals(actualTitle1, expectedTitle1);
		
		list.waitForElement(driver, "clickCart");

		//Verifying whether the Edit link is properly working or not
		//To click on Edit link
		ClickHelper.clickElement(driver, clickCart);
		//driver.findElement(By.xpath(objectrepoProps.getProperty("checkoutBtn"))).click();
		ClickHelper.clickElement(driver, checkoutBtn);
		//driver.findElement(By.xpath(objectrepoProps.getProperty("clickOnEdit"))).click();
		ClickHelper.clickElement(driver, clickOnEdit);
		
		//To clear the quantity
		ClickHelper.clearElement(driver, Quantity1);

		//To give the input to quantity
		driver.findElement(By.xpath(objectrepoProps.getProperty("Quantity1"))).sendKeys("3");
	
		String bforeUpdateText = driver.findElement(By.id("subtotal")).getText();
		
		//driver.findElement(By.xpath(objectrepoProps.getProperty("updateClick"))).click();
		ClickHelper.clickElement(driver, updateClick);
		
		String aftrUpdateText = driver.findElement(By.id("subtotal")).getText();
		
		//verify update
		Assert.assertTrue(bforeUpdateText.equals(aftrUpdateText));
		System.out.println("update success");
		
		list.waitForElement(driver, "checkoutBtn");

		//Verifying whether as a guest user should able to buy product
		//driver.findElement(By.xpath(objectrepoProps.getProperty("checkoutBtn"))).click();
		ClickHelper.clickElement(driver, checkoutBtn);
		driver.findElement(By.xpath(objectrepoProps.getProperty("emailId"))).sendKeys("sam@gmail.com");
		driver.findElement(By.xpath(objectrepoProps.getProperty("guestLink"))).click();

		//Verifying whether user is able to fill billing information
		driver.findElement(By.xpath(objectrepoProps.getProperty("firstName"))).sendKeys("Ram");
		driver.findElement(By.xpath(objectrepoProps.getProperty("lastName"))).sendKeys("Sharma");
		driver.findElement(By.xpath(objectrepoProps.getProperty("contactNumber"))).sendKeys("9856321245");
		driver.findElement(By.xpath(objectrepoProps.getProperty("address"))).sendKeys("Ramnagar, Mumbai-09");
		driver.findElement(By.xpath(objectrepoProps.getProperty("state"))).sendKeys("Maharashtra");
		
		//To handle checkbox
		WebElement state = driver.findElement(By.xpath(objectrepoProps.getProperty("stateCheckbox")));
		Select oSelect = new Select(state);
		oSelect.selectByVisibleText("AP");
		
		driver.findElement(By.xpath(objectrepoProps.getProperty("postalCode"))).sendKeys("658965");
		driver.findElement(By.xpath(objectrepoProps.getProperty("billingBtn"))).click();

		//Verifying whether user is able to fill shipping information

		//Handling checkbox
		WebElement option1 = driver.findElement(By.xpath(objectrepoProps.getProperty("shippingAddressCheckbox")));
		option1.click();	

		//Handling radiobutton
		WebElement radio1 = driver.findElement(By.xpath(objectrepoProps.getProperty("Standard")));							
		WebElement radio2 = driver.findElement(By.xpath(objectrepoProps.getProperty("Priority")));
		WebElement radio3 = driver.findElement(By.xpath(objectrepoProps.getProperty("Express")));							
		radio1.click();

		//To click on select shipping option
		driver.findElement(By.xpath(objectrepoProps.getProperty("shippingBtn"))).click();

		//To verify the payment method
		WebElement radiobtn1 = driver.findElement(By.xpath(objectrepoProps.getProperty("creditCard")));							
		WebElement radiobtn2 = driver.findElement(By.xpath(objectrepoProps.getProperty("PayPal")));
		WebElement radiobtn3 = driver.findElement(By.xpath(objectrepoProps.getProperty("collectOnDelivery")));
		radiobtn3.click();
		
		driver.findElement(By.xpath(objectrepoProps.getProperty("completeOrderBtn"))).click();;	
	}
@AfterTest
	private void tearDown()
	{
		driver.close();

	}
}