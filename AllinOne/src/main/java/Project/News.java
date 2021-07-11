package Project;

import org.openqa.selenium.WebDriver;

public class News implements ISearches {
	
	private WebDriver driver;
	private String website;

	public News(WebDriver driver, String website) {
		super();
		this.driver = driver;
		this.website = website;
	}
	@Override
	public String implementation() throws InterruptedException {
		driver.get(website);
		return driver.getCurrentUrl();
	}

}
