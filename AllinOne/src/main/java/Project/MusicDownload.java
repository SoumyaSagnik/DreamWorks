package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MusicDownload implements ISearches{

	private WebDriver driver;
	private String website;
	private String search;
	
	public MusicDownload(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation() throws InterruptedException {
		driver.get(website);
		driver.findElement(By.id("searchInput")).sendKeys(search + Keys.ENTER);
		driver.findElement(By.xpath("(//span[text()='Download MP3'])[1]")).click();
		
		for(String childTab: driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		
		driver.close();
		
		for(String childTab: driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		
		driver.findElement(By.xpath("(//span[text()='Download MP3'])[1]")).click();	
		
		for(String childTab: driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='file' and text()='MP3'])[1]")).click();
		return driver.getCurrentUrl();
	}

}
