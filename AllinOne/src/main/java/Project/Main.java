package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static WebDriver driver;

	public static String website;
	public static String search;
	public static String link;
	public static String web;

	public static void main(String[] args) throws InterruptedException {
		ISearches in;

		System.out.println("1-> Google Search");
		System.out.println("2-> Wikipedia Search");
		System.out.println("3-> Play Music");
		System.out.println("4-> YouTube Play");
		System.out.println("5-> Twitch");
		System.out.println("6-> Download Music");
		System.out.println("7-> Video Download");
		System.out.println("8-> Amazon");
		System.out.println("9-> Oxford Dictionary");
		System.out.println("10-> News Headlines");
		System.out.println("Enter choice");
		String choice = sc.nextLine();
		if (!choice.equals("10")) {
			System.out.println("Enter search item");
			search = sc.nextLine();
		}

		switch (choice) {
		case "1":
			startDriver();
			website = Constants.GOOGLE;
			web = WebSearchConstants.GOOGLE;
			in = new Google(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "2":
			startDriver();
			website = Constants.WIKIPEDIA;
			web = WebSearchConstants.WIKIPEDIA;
			in = new Wikipedia(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "3":
			startDriver();
			website = Constants.MP3QUACK;
			web = WebSearchConstants.MP3QUACK;
			in = new Music(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "4":
			startDriver();
			website = Constants.YOUTUBE;
			web = WebSearchConstants.YOUTUBE;
			in = new YouTube(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "5":
			startDriver();
			website = Constants.TWITCH;
			web = WebSearchConstants.TWITCH;
			in = new Twitch(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "6":
			startDriverWithoutNotifications();
			website = Constants.MP3QUACK;
			web = WebSearchConstants.MP3QUACK;
			in = new MusicDownload(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "7":
			startDriver();
			website = Constants.YOUTUBE;
			web = WebSearchConstants.SAVEFROMNET;
			in = new VideoDownload(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "8":
			startDriver();
			website = Constants.AMAZON;
			web = WebSearchConstants.AMAZON;
			in = new Amazon(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "9":
			startDriver();
			website = Constants.OXFORD;
			web = WebSearchConstants.OXFORD;
			in = new OxfordDictionary(driver, website, search);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		case "10":
			startDriver();
			website = Constants.TIMESOFINDIA;
			web = WebSearchConstants.TIMESOFINDIA;
			in = new News(driver, website);
			link = in.implementation();
			jdbc();
			closeDriver();
			break;
		default:
			System.out.println("Wrong choice");
		}
	}

	public static void startDriver() {
		System.setProperty("webdriver.chrome.driver", Constants.DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public static void startDriverWithoutNotifications() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", Constants.DRIVER);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public static void closeDriver() {
		System.out.println("Enter any key to close the driver");
		String close = sc.nextLine();
		System.out.println(close);
		driver.quit();
	}

	public static void jdbc() {
		List<Pojo> alist = new ArrayList<Pojo>();
		Pojo p = new Pojo(web, search, link);
		alist.add(p);

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			String sql = "";
			conn.setAutoCommit(false);
			sql = "INSERT INTO history (Website, Search, Link, TIME) VALUES(?, ?, ?, NOW())";
			PreparedStatement st = conn.prepareStatement(sql);

			for (Pojo i : alist) {
				st.setString(1, i.getWeb());
				st.setString(2, i.getSearch());
				st.setString(3, i.getLink());

				st.addBatch();
			}
			st.executeBatch();
			conn.commit();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
	}
}