package com.atmecs.pages;

import org.openqa.selenium.WebDriver;

import com.atmecs.constants.PageActionClick;
import com.atmecs.constants.PageActionGetText;

public class ClickHelper
{
	public static void clickElement(WebDriver driver, String xpath)
	{
		PageActionClick.clickable(driver, xpath);
	}
	
	
	public static void clickElements(WebDriver driver, String xpath)
	{
		PageActionClick.click(driver, xpath);
	}
	
	public static void clearElement(WebDriver driver, String xpath)
	{
		PageActionClick.clear(driver, xpath);
	}
	
	
}
