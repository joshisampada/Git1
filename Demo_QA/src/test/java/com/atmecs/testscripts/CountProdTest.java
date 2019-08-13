package com.atmecs.testscripts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.helper.ClickHelper;
import com.atmecs.helper.GetTextHelper;
import com.atmecs.testsuite.TestBase;

public class CountProdTest extends TestBase 
{
	
	String imageList = objectrepoProps.getProperty("loc.image.imagelist");

	/**
	 * The method is regarding to count the elements present 
	 * on the webpage. 
	 * 
	 */
	@Test
	public void CountProdTest() 
	{
		//To check the url is correct or not
		String expectedUrl = "http://shop.demoqa.com/";
		String url=driver.getCurrentUrl();
		log.printLog("url" +url);
		asert.Assertion(url, expectedUrl, "Check URL");

		//To navigate to the Clearance Page
		//ClickHelper.clickElement(driver, imageList);

		//To get the list of product images present on page
		List<WebElement> listImages=GetTextHelper.get_Elements(driver, imageList);//(driver, imageList);
		log.printLog("No. of Product Images: "+listImages.size());
		
		//To click on a product
		ClickHelper.clickElement(driver, imageList);
		
		}


}
