package com.atmecs.utils;

import org.testng.Assert;

public class verifyAssertion 
{
	public void verifyAssertion(String actualTitle, String expectedTitle, String message) {
		try {
		Assert.assertEquals(actualTitle, expectedTitle);	
		System.out.println("Passed: "+ message + " EXPECTED: " + expectedTitle + " ACTUAL: "+ actualTitle);
		   } catch (AssertionError assertionError) {
		   	System.out.println("Failed: "+ message + " " +expectedTitle + " " +actualTitle);
		     }
		}
}
