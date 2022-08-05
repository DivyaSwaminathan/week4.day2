package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SanpDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://www.snapdeal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		//driver.switchTo().alert().dismiss();
		WebElement findElement = driver.findElement(By.xpath("//span[contains(text(),'Men')]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(findElement).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		String text = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();

		System.out.println("The number of items : " + text);
		//Thread.sleep(700);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("(//li[@data-index='1'])[2]")).click();
		Thread.sleep(1000);
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='lfloat marR10']/span/following-sibling::span"));
		List<Integer> newLst=new ArrayList<Integer>();
		System.out.println("----The Price sorted in order -- low to high-------");
		Thread.sleep(2000);
		for (WebElement ele : findElements) {
			String price=ele.getText();
			price=price.replaceAll("[^0-9]", "");
			int price1=Integer.parseInt(price);
			newLst.add(price1);

		}
		Collections.sort(newLst);
		System.out.println(newLst);

		WebElement startPrice = driver.findElement(By.xpath("//input[@name='fromVal']"));
		startPrice.clear();
		startPrice.sendKeys("500");

		WebElement toPrice = driver.findElement(By.xpath("//input[@name='toVal']"));
		toPrice.clear();
		toPrice.sendKeys("1200");

		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(1000);
		WebElement ele = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	
		ele.click();
		Thread.sleep(1000);
		WebElement img1 = driver.findElement(By.xpath("(//picture[@class='picture-elem']/img)[1]"));
		//Thread.sleep(1000);
		builder.moveToElement(img1).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		String price = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span[1]")).getText();
		String discountPercentage = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span[2]")).getText();
		discountPercentage=discountPercentage.replaceAll("[A-Za-z]", "");
		System.out.println("The price of the shoe is : " + price);
		System.out.println("The discount percentage is : " + discountPercentage);
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("snapdeal.png");
		FileUtils.copyFile(source, dest);
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();


	}

}



/*
 * Assignment 5:SnapDeal
============
1. Launch https://www.snapdeal.com/
2. Go to Mens Fashion
3. Go to Sports Shoes
4. Get the count of the sports shoes
5. Click Training shoes
6. Sort by Low to High
7. Check if the items displayed are sorted correctly
8.Select the price range (900-1200)
9.Filter with color Navy 
10 verify the all applied filters 
11. Mouse Hover on first resulting Training shoes
12. click QuickView button
13. Print the cost and the discount percentage
14. Take the snapshot of the shoes.
15. Close the current window
16. Close the main window
 */
