package com.atmecs.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.atmecs.constants.PageActionGetText;
import com.atmecs.helper.ClickHelper;
import com.atmecs.helper.GetTextHelper;
import com.atmecs.testsuite.TestBase;




@Test
public class AddToCart extends TestBase
{
	String imageList = objectrepoProps.getProperty("loc.image.imagelist");
	String chkprod = objectrepoProps.getProperty("loc.img.chkprod");
	String verifyprod = objectrepoProps.getProperty("loc.img.verifyprod");
	String prodchck = objectrepoProps.getProperty("loc.img.prodchck");
	String nextimg = objectrepoProps.getProperty("loc.link.nextimg");
	String selectOption = objectrepoProps.getProperty("loc.drpdown.option");
	String addToCartBtn = objectrepoProps.getProperty("loc.btn.addtocart");
	String clearLink = objectrepoProps.getProperty("loc.link.clear");
	String getItemCount = objectrepoProps.getProperty("loc.img.addtocartsymbol");
	String addProduct = objectrepoProps.getProperty("loc.symbol.add");
	String prodNo = objectrepoProps.getProperty("loc.link.prodno");


	public void addToCart() throws InterruptedException 
	{

		//Verifying whether the particular image is opening after clicking or not by PageTitle
		ClickHelper.clickElement(driver, imageList);
		String expectedImgTitle = "pink drop shoulder oversized t shirt – ToolsQA Demo Site";
		String actualImgTitle = "";
		actualImgTitle = driver.getTitle();
		asert.Assertion(actualImgTitle, expectedImgTitle, "Successfully Navigated");


		//Verify whether user can check the product thoroughly or not
		ClickHelper.clickElement(driver, chkprod);
		ClickHelper.clickElement(driver, verifyprod);
		ClickHelper.clickElement(driver, prodchck);
		ClickHelper.clickElement(driver, nextimg);
		ClickHelper.clickElement(driver, nextimg);
		ClickHelper.clickElement(driver, nextimg);
		ClickHelper.clickElement(driver, nextimg);
		log.printLog("Images are successfully loaded");


		//Verify whether without selecting an option product will added to cart or not
		ClickHelper.clickElement(driver, addToCartBtn);
		driver.switchTo().alert().accept();

		Select drpColor=new Select(driver.findElement(By.id("pa_color")));
		drpColor.selectByVisibleText("Pink");

		Select drpSize=new Select(driver.findElement(By.id("pa_size")));
		drpSize.selectByVisibleText("38");

		ClickHelper.clickElement(driver, addProduct);

		ClickHelper.clickElement(driver, addToCartBtn);


		//Verify whether the product is added to cart by the count of cart
		GetTextHelper.getElement(driver, prodNo);
		log.printLog(PageActionGetText.text);
		ClickHelper.clickElement(driver, addToCartBtn);
		GetTextHelper.getElement(driver, prodNo);
		log.printLog(PageActionGetText.text);
		asert.Assertion(PageActionGetText.text, PageActionGetText.text, "Products in cart");

	}
}
