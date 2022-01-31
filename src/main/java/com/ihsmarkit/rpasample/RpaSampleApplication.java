package com.ihsmarkit.rpasample;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@SpringBootApplication
public class RpaSampleApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(RpaSampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RpaSampleApplication.class, args);
	}

	private String userName = "login";
	private String password = "password" ;

	@Bean
	public Function<String, String> uppercase() {
		return value -> value.toUpperCase();
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("In startup command..");

		String chromeDriverPath = "/temp/chromedriver.exe" ;
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
		options.addArguments("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
		WebDriver driver = new ChromeDriver(options);

		//getUnicornName(driver);




		//googleSearch(driver);

		addSupplier(driver);
		driver.quit();

	}

	private void getUnicornName(WebDriver driver) throws InterruptedException {
		// Get the login page
		driver.get("https://www.rpasamples.com/findunicornname");
		Thread.sleep(5000);

		// Search for username / password input and fill the inputs
		driver.findElement(By.id("txtName")).sendKeys(userName);

		// Locate the login button and click on it
		driver.findElement(By.id("getNameButton")).click();


		if(driver.findElement(By.id("outputBlock")) == null){
			System.out.println("Didn't work");
			driver.quit();
			System.exit(1);
		}else{
			System.out.println("Successfully got the unicorn name");
		}

		// Take a screenshot of the current page
		//File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(screenshot, new File("screenshot.png"));
	}

	private void googleSearch(WebDriver driver) throws InterruptedException {
		driver.get("http://www.google.com/");
		Thread.sleep(5000);  // Let the user actually see something!
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		Thread.sleep(5000);  // Let the user actually see something!
	}

	private void addSupplier(WebDriver driver) throws InterruptedException {
		driver.get("https://www.rpasamples.com/suppliers");
		Thread.sleep(2000);

		WebElement newSupplierButton = driver.findElement(By.xpath(".//span[contains(.,'New Supplier')]"));
		newSupplierButton.click();

		// internal name //*[@id="TextField342"]
		WebElement txtInternalName = driver.findElement(By.name("internalName"));
		txtInternalName.sendKeys("Robo Internal");
		// external name //*[@id="TextField345"]
		WebElement txtExternalName = driver.findElement(By.name("externalName"));
		txtExternalName.sendKeys("Robo External");

		//description //*[@id="TextField348"]

		WebElement txtDescription = driver.findElement(By.xpath("//textarea[@name='description']"));
		txtDescription.sendKeys("Robo Description");
		//Industry //*[@id="industry-option"]
		WebElement selectIndustry = driver.findElement(By.id("industry-option"));
		//txtInternalName.s
		// no employees //*[@id="TextField354"]
		WebElement txtNoOfEmployee = driver.findElement(By.xpath("//input[@name='noEmployees']"));
		txtNoOfEmployee.sendKeys("55");
		Thread.sleep(5000);

		WebElement saveButton = driver.findElement(By.id("saveButton"));
		saveButton.click();

		Thread.sleep(5000);
	}
}
