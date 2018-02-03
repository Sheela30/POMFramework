package Com.CucumberFramework.Framework1.helper.Alert;

import org.openqa.selenium.Alert;


import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import Com.CucumberFramework.Framework1.helper.DropDown.DropDownHelper;
import Com.CucumberFramework.Framework1.helper.Logger.LoggerHelper;

public class AlertHelper {
	
	public WebDriver driver;
	private Logger log=LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	public Alert getAlert() {
		return driver.switchTo().alert();
	}
	
	public void acceptAlert() {
		getAlert().accept();
		log.info("");
	}
	
	public void dismissAlert() {
		getAlert().dismiss();
		log.info("");
	}
	
	public String getAlertText()
	{
		driver.switchTo().alert();
		return getAlert().getText();
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void acceptAlertIfPresent() {
		if(!isAlertPresent())
			return;
		acceptAlert();
		log.info("");
	}
	
	public void dismissAlertIfPresent() {
		if(!isAlertPresent())
			return;
		dismissAlert();
		log.info("");
	}

	public void acceptPromt(String text) {
		if(!isAlertPresent())
			return;
		Alert alert=getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
		
	}
}
