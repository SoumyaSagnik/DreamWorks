package Project;

import org.openqa.selenium.WebDriver;

public class Twitch implements ISearches{
	
	private WebDriver driver;
	private String website;
	private String search;
	
	public Twitch(WebDriver driver, String website, String search) {
		super();
		this.driver = driver;
		this.website = website;
		this.search = search;
	}

	@Override
	public String implementation() throws InterruptedException {
		driver.get(website + search);
		return driver.getCurrentUrl();
	}

}
