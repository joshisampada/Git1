package com.atmecs.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.helper.ClickHelper;
import com.atmecs.testsuite.TestBase;

public class HomePage extends TestBase
{
	
	String dismiss = objectrepoProps.getProperty("loc.link.displaymsg");
	
	@Test
	public void homePageTest() 
	{
		//Verifying whether user is navigated to correct url welcome page.
		String expectedTitle = "ToolsQA Demo Site – ToolsQA – Demo E-Commerce Site";
		String actualTitle = "";
		actualTitle = driver.getTitle();
		//System.out.println(actualTitle);
		asert.Assertion(actualTitle, expectedTitle, "Navigation");
		
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.=\"My Account\"]")));
		
		//To click on dismiss link
		ClickHelper.clickElement(driver, dismiss);
	
	
		
	driver.findElement(By.xpath("//a[.=\"My Wishlist\"]")).click();
		
	}
	
	
	
}
