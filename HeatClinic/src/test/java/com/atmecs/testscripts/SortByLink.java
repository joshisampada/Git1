package com.atmecs.testscripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.pages.ClickHelper;
import com.atmecs.pages.GetTextHelper;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.PropertiesUtil;

/**
 * The SortByLink program implements an program 
 * that sorts the data on page according to the 
 * link selected
 * 
 * @author Sampada.Joshi
 *
 */
public class SortByLink extends TestBase
{
	String navigateToClearancePage = objectrepoProps.getProperty("navigateToClearancePage");
	String priceLink = objectrepoProps.getProperty("priceLink");
	String imageList = objectrepoProps.getProperty("imageList");
	String linkClick = objectrepoProps.getProperty("linkClick");
	String originalPriceList = objectrepoProps.getProperty("originalPriceList");
	
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
	 * The linksort method is regarding to the sorting of data
	 * as per the price range
	 * @throws InterruptedException 
	 */
	@Test
	public void linksort() 
	{
		//To navigate to the Clearance Page
		ClickHelper.clickElement(driver, navigateToClearancePage);
		
		//To click on price link
		ClickHelper.clickElement(driver, linkClick);
		
		//To count the no. of products on page
		List<WebElement> listImages = GetTextHelper.get_Elements(driver, imageList);
		System.out.println("No. of Product Images: "+listImages.size());
		
		//To get the data present on page
		List<WebElement> prices = GetTextHelper.get_Elements(driver, originalPriceList);
		List<Double> originalList = new ArrayList<>();

		//To get the data present on page
		for(WebElement price:prices) 
		{
			String actual = price.getText();
			originalList.add(Double.parseDouble(actual.replace("$", "")));
			System.out.println(actual);
		}
		System.out.println("original :"+originalList);

		//To verify whether values are in range	
		for (double itemPrice : originalList) 
		{
			if (itemPrice>0&&itemPrice<6) 
			{
				System.out.println("The values are in range");

			}

		}
	}

	/**
	 * This method is to close the browser
	 */
	@AfterTest
	public void tearDown()
	{
		driver.quit();

	}   	
}



