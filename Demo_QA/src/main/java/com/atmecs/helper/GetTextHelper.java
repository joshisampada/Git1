package com.atmecs.helper;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.constants.PageActionGetText;

public class GetTextHelper 
{
	public static void getElement(WebDriver driver, String xpath)
	{
		PageActionGetText.getText(driver, xpath);
		
	}
	
	public static void getElements(WebDriver driver, String xpath)
	{
		PageActionGetText.readText(driver, xpath);
	}
	
	public static List<WebElement> get_Elements(WebDriver driver, String xpath)
	{
		
		return PageActionGetText.Text(driver, xpath);
		
		
	}
	
	public static String getElement1(WebDriver driver, String xpath)
	{
		return PageActionGetText.getText(driver, xpath);
		
	}
}
