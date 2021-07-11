package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Wikipedia implements ISearches{

	private String website;
	private String search;
	private WebDriver driver;
	
	public Wikipedia(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation() {
		driver.get(website);
		driver.findElement(By.id("searchInput")).sendKeys(search + Keys.ENTER);
		return driver.getCurrentUrl();
	}

}
