package av.by;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
	
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\snaip\\Desktop\\chromedriver.exe");

			// Initialize browser
			driver = new ChromeDriver();

			// Open url
			driver.get(args[0]);
			
			
		
			try {
				driver.findElement(By.className("card-contacts-phones")).click();
				//driver.manage().timeouts().implicitlyWait(20000000, TimeUnit.SECONDS);
				Thread.sleep(3000);
				System.out.println(driver.findElement(By.className("card-contacts-phones-in")).findElement(By.tagName("li")).getText());
				//WebElement element = driver.findElement(By.xpath("//div[@class='card-view-phone js-view-phone']/ul/li/b"));
				//JavascriptExecutor js = (JavascriptExecutor)driver;
				//js.executeScript("document.className(card-contacts-phones).click();");
			} catch (NoSuchElementException e) {
				System.out.println("Ошибка, цена авто в рублях | Parse/aloneAvto()");
				e.printStackTrace();
			}
			
	}

}
