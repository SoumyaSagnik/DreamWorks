package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OxfordDictionary implements ISearches{

	private WebDriver driver;
	private String website;
	private String search;
	
	public OxfordDictionary(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation() throws InterruptedException {
		driver.get(website);
		driver.findElement(By.id("q")).sendKeys(search + Keys.ENTER);
		return driver.getCurrentUrl();
	}

}
