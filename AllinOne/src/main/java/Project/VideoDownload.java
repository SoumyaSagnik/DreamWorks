package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class VideoDownload implements ISearches{

	private WebDriver driver;
	private String website;
	private String search;
	
	
	public VideoDownload(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation() throws InterruptedException {
		driver.get(website);
		driver.findElement(By.id("search")).sendKeys(search);
		driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("channel-info")).click();
		String url = driver.getCurrentUrl();
		driver.get(Constants.SAVEFROM);
		driver.findElement(By.xpath("//input[@placeholder='Paste your video link here']")).sendKeys(url + Keys.ENTER);
		Thread.sleep(5000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//a[text()='Download']"));
		act.moveToElement(ele).click().build().perform();
		return driver.getCurrentUrl();
	}
}
