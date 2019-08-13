package com.atmecs.constants;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageActionGetText 
{
	public static String text;
	public static List<WebElement> text1;
	
	public static String getText(WebDriver driver, String xpath)
	{
			text=driver.findElement(By.xpath(xpath)).getText();
			return text;
	}
		
		
		public static String readText(WebDriver driver, String xpath)
		{
			text=((WebElement) driver.findElements(By.xpath(xpath))).getText();
			return text;
		}


		public static List<WebElement> Text(WebDriver driver, String xpath) {
			return text1=driver.findElements(By.xpath((xpath)));
			
		}
}
