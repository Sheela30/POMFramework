package Com.CucumberFramework.Framework1.helper.TestBase;


import java.io.File;




import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Com.CucumberFramework.Framework1.Configuration.Browser.BrowserType;
import Com.CucumberFramework.Framework1.Configuration.Browser.ChromeBrowser;
import Com.CucumberFramework.Framework1.Configuration.Browser.FirefoxBrowser;
import Com.CucumberFramework.Framework1.Configuration.Browser.IexplorerBrowser;
import Com.CucumberFramework.Framework1.Utility.DateTimeHelper;
import Com.CucumberFramework.Framework1.Utility.ResourceHelper;
import Com.CucumberFramework.Framework1.configreader.ObjectRepo;
import Com.CucumberFramework.Framework1.configreader.propertyFileReader;
import Com.CucumberFramework.Framework1.helper.Logger.LoggerHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestBase {
private final Logger log = LoggerHelper.getLogger(TestBase.class);
	
	public static WebDriver driver;

	public void waitForElement(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
	
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotInteractableException.class);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}

	private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
		return new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				log.debug("Waiting for Element : " + element);
				return element.isDisplayed();
			}
		};
	}

	public String takeScreenShot(String name) throws IOException {

		File destDir = new File(ResourceHelper.getResourcePath("screenshots/") + DateTimeHelper.getCurrentDate());
		if (!destDir.exists())
			destDir.mkdir();

		File destPath = new File(destDir.getAbsolutePath() + System.getProperty("file.separator") + name + ".jpg");
		try {
		
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destPath);
			//copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), destPath);
		} catch (IOException e) {
			log.error(e);
			throw e;
		}
		log.info(destPath.getAbsolutePath());
		return destPath.getAbsolutePath();
	}

	public WebElement getElement(By locator) {
		log.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);

		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			log.error(re);
			throw re;
		}
	}

	/**
	 * Check for element is present based on locator If the element is present
	 * return the web element otherwise null
	 * 
	 * @param locator
	 * @return WebElement or null
	 */

	public WebElement getElementWithNull(By locator) {
		log.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		log.info(flag);
		return flag;
	}

	
	public WebDriver getBrowserObject(BrowserType bType) throws Exception {
		try {
			log.info(bType);

			switch (bType) {

			case chrome:
				ChromeBrowser chrome = ChromeBrowser.class.getConstructor().newInstance();
				return chrome.getChromeDriver(chrome.getChromeCapabilities());

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.getConstructor().newInstance();
				return firefox.getFirefoxDriver(firefox.getFirefoxCapabilities());

			case Iexplorer:
				IexplorerBrowser iExplore = IexplorerBrowser.class.getConstructor().newInstance();
				return iExplore.getIExplorerDriver(iExplore.getIExplorerCapabilities());
			default:
				throw new Exception(" Driver Not Found : " + new propertyFileReader().getBrowser());
			}
		} catch (Exception e) {
			log.equals(e);
			throw e;
		}
	}
	
	public void setUpDriver(BrowserType bType) throws Exception {
		
		driver = getBrowserObject(bType);
		log.debug("InitializeWebDrive : " + driver.hashCode());
		driver.manage().timeouts().pageLoadTimeout(ObjectRepo.reader.getPageTimeout(),TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ObjectRepo.reader.getImplicitWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	//Here Browser will be launched
	@Before()
	public void before() throws Exception {
		
		//Here the property file gets loaded to the memory
		ObjectRepo.reader = new propertyFileReader();
		setUpDriver(ObjectRepo.reader.getBrowser());
		log.info(ObjectRepo.reader.getBrowser());
	}


	//Browser will be closed
	@After()
	public void after(Scenario scenario) throws Exception {
		//driver.quit();
		log.info("");
	}

}
