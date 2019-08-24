package com.atmecs.testscripts;

import com.atmecs.testsuite.TestBase;

public class HomePage extends TestBase
{
	public void navigateToUrl()
	{
	//Verifying whether user is navigated to correct url welcome page.
			String expectedTitle = "ToolsQA Demo Site – ToolsQA – Demo E-Commerce Site";
			String actualTitle = "";
			actualTitle = driver.getTitle();
			log.printLog(actualTitle);
			asert.Assertion(actualTitle, expectedTitle, "Navigation");
	}
}
