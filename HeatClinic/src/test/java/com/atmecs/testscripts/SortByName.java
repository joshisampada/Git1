package com.atmecs.testscripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
 * The SortByName program implements an program 
 * that sorts the data present on page (here product names)
 * in ascending and descending alphabetical order
 * 
 * @author Sampada.Joshi
 *
 */
public class SortByName extends TestBase
{
	String navigateToClearancePage = objectrepoProps.getProperty("navigateToClearancePage");
	String originalNameList = objectrepoProps.getProperty("originalNameList");
	String ascendingNamelink = objectrepoProps.getProperty("ascendingNamelink"); 
	String postClickNameList = objectrepoProps.getProperty("postClickNameList");
	String descendingNameLink = objectrepoProps.getProperty("descendingNameLink");
	String postClickNameDisplay = objectrepoProps.getProperty("postClickNameDisplay");
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
	 * The sort method is regarding to the sorting of data
	 * in alphabetical order.
	 */

	@Test
	public void sort() 
	{
		//To navigate to the Clearance Page
		ClickHelper.clickElement(driver, navigateToClearancePage);

		//Taking the price on the page in List & considering as original list
		List<WebElement> names = GetTextHelper.get_Elements(driver, originalNameList);
		List<String> originalList = new ArrayList<>();

		//To get the data present on page
		for(WebElement prodname:names) 
		{
			String actual = prodname.getText();
			originalList.add(actual);
			System.out.println(actual);
		}

		System.out.println("original :"+originalList);

		//To sort the collected data in ascending order
		List<String> expectedAscendingList = originalList;
		Collections.sort(expectedAscendingList);
		System.out.println("asc :"+expectedAscendingList);

		//To check whether data is getting sorted ascending by click
		ClickHelper.clickElement(driver, ascendingNamelink);

		//Taking the data list after sorting
		List<WebElement> beforeSort =GetTextHelper.get_Elements(driver, postClickNameList);
		List<String> actualListAfterSort = new ArrayList<>();

		//To get the data present on page after sorting
		for(WebElement name:beforeSort) 
		{
			String actual = name.getText();
			actualListAfterSort.add(actual);
			System.out.println(actual);
		}

		System.out.println("after sort click :"+actualListAfterSort);

		System.out.println(actualListAfterSort);
		System.out.println(expectedAscendingList);
		Assert.assertEquals(actualListAfterSort, expectedAscendingList);	

		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectrepoProps.getProperty("nameLinkClick"))));    
		
		
		//To check whether data is getting sorted descending
		List<String> expectedDesceningList = expectedAscendingList;
		Collections.reverse(expectedAscendingList);
		System.out.println("desc :"+expectedDesceningList);

		//To check whether data is getting sorted descending
		ClickHelper.clickElement(driver, descendingNameLink);

		//Taking the data list after sorting
		List<WebElement> AfterSort = GetTextHelper.get_Elements(driver, postClickNameDisplay); 
		List<String> actualListAfterSort1 = new ArrayList<>();

		//To get the data present on page after sorting
		for(WebElement name:AfterSort) 
		{
			String actual1 = name.getText();
			actualListAfterSort1.add(actual1);
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
