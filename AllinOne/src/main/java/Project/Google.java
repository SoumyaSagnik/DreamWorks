package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Google implements ISearches{
	
	private String website;
	private String search;
	private WebDriver driver;

	public Google(WebDriver driver, String website, String search) {
		super();
		this.website = website;
		this.search = search;
		this.driver = driver;
	}
	
	@Override
	public String implementation() {
		driver.get(website);
		driver.findElement(By.name("q")).sendKeys(search + Keys.ENTER);
		return driver.getCurrentUrl();
	}

}
