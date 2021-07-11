package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Music implements ISearches{

	private String website;
	private String search;
	private WebDriver driver;
	
	public Music(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation() {
		driver.get(website);
		driver.findElement(By.id("searchInput")).sendKeys(search + Keys.ENTER);
		driver.findElement(By.xpath("(//span[text()='Play Music'])[1]")).click();
		
		for(String childTab: driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		
		driver.close();
		
		for(String childTab: driver.getWindowHandles()) {
			driver.switchTo().window(childTab);
		}
		
		driver.findElement(By.xpath("(//span[text()='Play Music'])[1]")).click();
		return driver.getCurrentUrl();
	}

}
