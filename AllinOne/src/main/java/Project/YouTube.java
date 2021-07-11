package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YouTube implements ISearches{

	private WebDriver driver;
	private String website;
	private String search;

	public YouTube(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation () throws InterruptedException{
		driver.get(website);
		driver.findElement(By.id("search")).sendKeys(search);
		driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("channel-info")).click();
		return driver.getCurrentUrl();
	}

}
