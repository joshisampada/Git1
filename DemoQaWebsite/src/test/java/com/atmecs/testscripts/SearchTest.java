package com.atmecs.testscripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.atmecs.helper.ExcelReading;
import com.atmecs.helper.Helper;
import com.atmecs.testsuite.TestBase;

public class SearchTest extends TestBase 
{
	@DataProvider
	public Object[][] getExcelData()
	{
		Object[][] data = ExcelReading.getTestData("Sheet1");
		return data;
	}

	@Test(dataProvider="getExcelData",priority=1)
		public void selectProduct(String product, String color, String size, String quantity) throws InterruptedException
		{
			Helper.clickOnElement("click_on_search");
			Helper.sendkeysToElement("search_for_product",product);
			scrollDown(driver);
			//Thread.sleep(2000);
			Helper.selectFromDropdown("select_color", color);
			//Thread.sleep(2000);
			Helper.selectFromDropdown("select_size", size);
			Helper.sendkeysToElement("select_quantity",quantity);
			//Helper.clickOnElement("click_on_cart");			
		}
	
	public void scrollDown(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000)");
	}
//		@Test(priority=2)
//		public void clickOnCart()
//		{
//			Helper.clickOnElement("click_on_cart_total");
//			double price1=Double.parseDouble(Helper.getTextOfElement("get_price_of_product1").substring(1));
//			double price2=Double.parseDouble(Helper.getTextOfElement("get_price_of_product2").substring(1));
//			double total=Double.parseDouble(Helper.getTextOfElement("get_total_amount").substring(1));
//			double grandTotal=price1+price2;
//			Assert.assertEquals(total, grandTotal,"grandtotal is verified");		
//		}
//		@Test(priority=3)
//		public void getTableCount() {
//			int count=Helper.getCountOfElement("get_numOfRows_inTable");
//			System.out.println(count);
//			Assert.assertEquals(count, 2,"products are added to cart");
//		}
	
	}
