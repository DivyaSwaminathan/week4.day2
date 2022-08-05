package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		Actions builder=new Actions(driver);
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));

		builder.moveToElement(brands).perform();

		Thread.sleep(1000);
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		WebElement loreal = driver.findElement(By.linkText("L'Oreal Paris"));
		builder.moveToElement(loreal);
		loreal.click();
		String title = driver.getTitle();
		System.out.println("Title : " + title);

		driver.findElement(By.xpath("//button[@class=' css-n0ptfk']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']/following::div[1]")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		try {
			driver.findElement(By.xpath("//span[text()='Hair']/following::span[1]")).click();}
		catch (StaleElementReferenceException e) {
			Thread.sleep(1000);
			System.out.println("Handling staleelement exception");
			driver.findElement(By.xpath("//span[text()='Hair']/following::span[1]")).click();
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']/following::div[1]")).click();
		WebElement concern = driver.findElement(By.xpath("//span[text()='Concern']"));

		concern.click();
		driver.findElement(By.xpath("//span[text()='Color Protection']/following::div[1]")).click();

		//check for filters 
		List<WebElement> filters = driver.findElements(By.xpath("//span[@class='filter-value']"));
		for (WebElement ele : filters) {
			String text=ele.getText();
			if(text.equals("Shampoo"))
				System.out.println("The filter is applied with Shampoo ");
		}

		driver.findElement(By.xpath("//img[contains(@alt,'Oreal Paris Colour Protect Shampoo')]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winLst=new ArrayList<String>(windowHandles);
		String secWin=winLst.get(1);
		driver.switchTo().window(secWin);

		//select class
		WebElement ops = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select option=new Select(ops);
		option.selectByVisibleText("175ml");
		String mrp = driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText();
		mrp=mrp.replace('₹',' ');

		System.out.println("The MRP of the product is " + mrp);
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		//frame is opened as below 
		WebElement frameEle = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(frameEle);
		Thread.sleep(1000);
		String text = driver.findElement(By.xpath("//span[text()='Grand Total']/following::div[1]")).getText();
		text=text.replace('₹',' ');
		text=text.replaceAll(" ", "");
		int 	price1=Integer.parseInt(text);
		System.out.println("The Grand Total Amount is : " + text);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		String text2 = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']/div/span")).getText();
		System.out.println("The final grand total : " + text2);
		int price2=Integer.parseInt(text);
		if(price1==price2) {
			System.out.println("The GrandTotal is same ");

		}
		else {
			System.out.println("The GrandTotal is not same  ");
		}
	}
}

/*
 * Assignment 4:Nykaa
=============
1) Go to https://www.nykaa.com/
2) Mouseover on Brands and Search L'Oreal Paris
3) Click L'Oreal Paris
4) Check the title contains L'Oreal Paris(Hint-GetTitle)
5) Click sort By and select customer top rated
6) Click Category and click Hair->Click haircare->Shampoo
7) Click->Concern->Color Protection
8)check whether the Filter is applied with Shampoo
9) Click on L'Oreal Paris Colour Protect Shampoo
10) GO to the new window and select size as 175ml
11) Print the MRP of the product
12) Click on ADD to BAG
13) Go to Shopping Bag 
14) Print the Grand Total amount
15) Click Proceed
16) Click on Continue as Guest
17) Check if this grand total is the same in step 14
18) Close all windows
 */




