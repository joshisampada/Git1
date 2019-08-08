package com.atmecs.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommanUtils
{
	
	
	public static void waitForElement(WebDriver driver, String locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		try {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		}
		catch(Exception exception) {
			
		}
		
	}
	
	public boolean ignoreClickInterceptAndClickOnElement(WebDriver driver,final String xpath) {
		FluentWait<WebDriver> fluentWait =  new FluentWait<WebDriver>( driver)
		.ignoring(ElementClickInterceptedException.class)
		.pollingEvery(1, TimeUnit.SECONDS)
		.withTimeout(30,TimeUnit.SECONDS);

		fluentWait.until(new Function<WebDriver, Boolean>() {
		public Boolean apply(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
		return true;
		}
		});
		return true;
		}
}
