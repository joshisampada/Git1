package com.atmecs.testscripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.constants.ProjectPathConstants;
import com.atmecs.pages.ClickHelper;
import com.atmecs.pages.GetTextHelper;
import com.atmecs.testsuite.TestBase;
import com.atmecs.utils.PropertiesUtil;

/**
 * The SortingTest program implements an application that 
 * simply compares the prices & displays it in ascending 
 * and descending order
 * 
 * @author Sampada.Joshi
 *
 */
public class SortByPrice extends TestBase
{
	String navigateToClearancePage = objectrepoProps.getProperty("navigateToClearancePage");
	String originalPriceList = objectrepoProps.getProperty("originalPriceList");
	String ascendingPricelink = objectrepoProps.getProperty("ascendingPricelink");
	String postClickPriceList = objectrepoProps.getProperty("postClickPriceList");
	String descendingPriceLink = objectrepoProps.getProperty("descendingPriceLink");
	String postClickPriceDisplay = objectrepoProps.getProperty("postClickPriceDisplay");
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
     *The method is regarding to the sorting of price
     *present on the webpage 
     */

	@Test
	public void sortData()
	{
		//To navigate to the Clearance Page
		ClickHelper.clickElement(driver, navigateToClearancePage);

		//Taking the price on the page in List & considering as original list
		List<WebElement> prices = GetTextHelper.get_Elements(driver, originalPriceList);
		List<Double> originalList = new ArrayList<>();

		// To get the data present on page
		for(WebElement price:prices) 
		{
			String actual = price.getText();
			originalList.add(Double.parseDouble(actual.replace("$", "")));
			System.out.println(actual);
		}
		System.out.println("original :"+originalList);

		//To sort the collected data in ascending order
		List<Double> expectedAscendingList = originalList;
		Collections.sort(expectedAscendingList);
		System.out.println("asc :"+expectedAscendingList);

		//To check whether data is getting sorted ascending on the page by click
		ClickHelper.clickElement(driver, ascendingPricelink);

		//Taking the data list after sorting
		List<WebElement> beforesort = GetTextHelper.get_Elements(driver, postClickPriceList);
		List<Double> actualListAfterSort = new ArrayList<>();

		//To get the data present on page after sorting
		for(WebElement price:beforesort) 
		{
			String actual = price.getText();
			actualListAfterSort.add(Double.parseDouble(actual.replace("$", "")));
			System.out.println(actual);
		}
		System.out.println("after sort click :"+actualListAfterSort);

		System.out.println(actualListAfterSort);
		System.out.println(expectedAscendingList);
		Assert.assertEquals(actualListAfterSort, expectedAscendingList);

		//To check whether data is getting sorted descending
		List<Double> expectedDesceningList = expectedAscendingList;
		Collections.reverse(expectedAscendingList);
		System.out.println("desc :"+expectedDesceningList);

		//To check whether data is getting sorted descending
		//driver.findElement(By.xpath(objectrepoProps.getProperty("descendingPriceLink"))).click();
		ClickHelper.clickElement(driver, descendingPriceLink);

		//Taking the data list after sorting
		List<WebElement> afterSort = GetTextHelper.get_Elements(driver, postClickPriceDisplay);
		List<Double> actualListAfterSort1 = new ArrayList<>();

		//To get the data present on page after sorting
		for(WebElement price:afterSort) 
		{
			String actual1 = price.getText();
			actualListAfterSort1.add(Double.parseDouble(actual1.replace("$", "")));
			System.out.println(actual1);
		}
		System.out.println("after sort click :"+actualListAfterSort1);

		System.out.println(actualListAfterSort1);
		System.out.println(expectedDesceningList);
		Assert.assertEquals(actualListAfterSort1, expectedDesceningList);
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


