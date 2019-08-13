package com.atmecs.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.atmecs.constants.PageActionGetText;
import com.atmecs.helper.ClickHelper;
import com.atmecs.helper.GetTextHelper;
import com.atmecs.testsuite.TestBase;

public class Checkout extends TestBase
{
	String selectprod= objectrepoProps.getProperty("loc.link.selectprod");
	String addProduct = objectrepoProps.getProperty("loc.symbol.add");
	String getItemCount = objectrepoProps.getProperty("loc.img.addtocartsymbol");
	String addtocartbtn = objectrepoProps.getProperty("loc.btn.addtocartbtn");
	String msg = objectrepoProps.getProperty("loc.alert.msg");
	String viewCart = objectrepoProps.getProperty("loc.link.viewcart");
	String removeitem = objectrepoProps.getProperty("loc.symbol.removeitem");
	String totalprodprice = objectrepoProps.getProperty("loc.link.totalprodprice");
	String updatecart = objectrepoProps.getProperty("loc.btn.updatecart");
	String checkout = objectrepoProps.getProperty("loc.btn.checkout");
	String firstName = objectrepoProps.getProperty("loc.txtbox.firstname");
	String lastName = objectrepoProps.getProperty("loc.txtbox.lastname");
	String country = objectrepoProps.getProperty("loc.drpdwn.country");
	String city = objectrepoProps.getProperty("loc.txtbox.city");
	String state = objectrepoProps.getProperty("loc.drpdwn.state");
	String postalCode = objectrepoProps.getProperty("loc.txtbox.postalcode");
	String phoneNo = objectrepoProps.getProperty("loc.txtbox.phoneno");
	String email = objectrepoProps.getProperty("loc.txtbox.email");
	String condition = objectrepoProps.getProperty("loc.chkbox.condition");
	String placeOrder = objectrepoProps.getProperty("loc.btn.placeorder");


	@Test
	public void checkout()
	{

		//Click to select product
		ClickHelper.clickElement(driver, selectprod);

		//Click to add product
		ClickHelper.clickElement(driver, addProduct);

		//To select color of item
		Select drpColor=new Select(driver.findElement(By.id("pa_color")));
		drpColor.selectByVisibleText("Grey");

		//To select size of item
		Select drpSize=new Select(driver.findElement(By.id("pa_size")));
		drpSize.selectByVisibleText("40");

		//Click on add to cart
		ClickHelper.clickElement(driver, addtocartbtn);

		//Verify whether after clicking on add to cart user is getting alert msg or not

		String expectedalert = "View cart\r\n" + 
				"2 × “playboy x missguided plus size grey lips print front cropped t shirt” have been added to your cart.";
		String actualalert = "";
		actualalert = GetTextHelper.getElement1(driver, msg);
		log.printLog("actualalert" +actualalert);
		asert.Assertion(actualalert, expectedalert, "results matched");

		//Verify whether after clicking on view cart we are getting payment page or not
		ClickHelper.clickElement(driver, viewCart);
		String expectedImgTitle = "Cart – ToolsQA Demo Site";
		String actualImgTitle = "";
		actualImgTitle = driver.getTitle();
		asert.Assertion(actualImgTitle, expectedImgTitle, "Navigation");


		//Verify whether clicking on minus symbol will remove the item from cart
		GetTextHelper.getElement(driver, totalprodprice);
		log.printLog(PageActionGetText.text);

		//Click on minus synbol
		ClickHelper.clickElement(driver, removeitem);

		//Click on update cart
		ClickHelper.clickElement(driver, updatecart);

		GetTextHelper.getElement(driver, totalprodprice);
		log.printLog(PageActionGetText.text);

		//Click on checkout button
		ClickHelper.clickElement(driver, checkout);


		//Verifying whether user is able to fill billing information
		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.firstname"))).sendKeys("Ram");
		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.lastname"))).sendKeys("Sharma");

				Select country=new Select(driver.findElement(By.xpath("(//span[@class=\"select2-selection__rendered\"])[1]")));
		//			country.selectByVisibleText("India");
		//			


		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.address"))).sendKeys("Ramnagar, Mumbai-09");
		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.city"))).sendKeys("nashik");

		//			Select state=new Select(driver.findElement(By.xpath("(//span[@class=\"select2-selection__rendered\"])[2]")));
		//			state.selectByVisibleText("Maharashtra");


		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.postalcode"))).sendKeys("09");
		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.phoneno"))).sendKeys("9023568547");
		driver.findElement(By.xpath(objectrepoProps.getProperty("loc.txtbox.email"))).sendKeys("sam@gmail.com");

		//			WebElement option1 = driver.findElement(By.xpath(objectrepoProps.getProperty("loc.chkbox.condition")));
		//			option1.click();
		//		

		//ClickHelper.clickElement(driver, placeOrder);


	}
}
