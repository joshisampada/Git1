package com.atmecs.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageActionClick 
{
	public static void clickable(WebDriver driver, String xpath)
	{
		driver.findElement(By.xpath(xpath)).click();
	}
	
	
	public static void click(WebDriver driver, String xpath)
	{
		((WebElement) driver.findElements(By.xpath(xpath))).click();
	}
	
	public static void clear(WebDriver driver, String xpath)
	{
		driver.findElement(By.xpath(xpath)).clear();
	}
	
	
}
