package client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HTTPClient extends WebServiceClient{
	
	public HTTPClient() {
		
		super();
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fran\\Downloads\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--lang=es");
		
		resource = new ChromeDriver(options);
		
		}
	
	public Object getResource() {
		return (WebDriver) resource;
	}
	
}
