package Com.CucumberFramework.Framework1.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.CucumberFramework.Framework1.configreader.ObjectRepo;
import Com.CucumberFramework.Framework1.helper.DropDown.DropDownHelper;
import Com.CucumberFramework.Framework1.helper.Logger.LoggerHelper;
import Com.CucumberFramework.Framework1.helper.Wait.WaitHelper;

public class AccountAccessPage {
	WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(AccountAccessPage.class);
	WaitHelper waitHelper;
	DropDownHelper dropdownhelper;
	
	@FindBy(xpath="//input[@name='fundNbr']")
	WebElement FundNumber;
	
	@FindBy(xpath="//input[@name='accountNbr']")
	WebElement AccountNumber;
	
	@FindBy(xpath="//input[@name='ssn1']")
	WebElement SSNumber1;
	
	@FindBy(xpath="//input[@name='ssn2']")
	WebElement SSNumber2;
	
	@FindBy(xpath="//input[@name='ssn3']")
	WebElement SSNumber3;
	
	@FindBy(xpath="//input[@name='ein1']")
	WebElement EINumber1;
	
	@FindBy(xpath="//input[@name='ein2']")
	WebElement EINumber2;
	
	@FindBy(xpath="//select[@name='challengeQuestion1']")
	WebElement SecurityQuestion1;
	
	@FindBy(xpath="//select[@name='challengeQuestion2']")
	WebElement SecurityQuestion2;
	
	@FindBy(xpath="//select[@name='challengeQuestion3']")
	WebElement SecurityQuestion3;
	
	@FindBy(xpath="//select[@name='challengeQuestion4']")
	WebElement SecurityQuestion4;
	
	@FindBy(xpath="//select[@name='challengeAnswer1']")
	WebElement SecurityAnswer1;
	
	@FindBy(xpath="//select[@name='challengeAnswer2']")
	WebElement SecurityAnswer2;
	
	@FindBy(xpath="//select[@name='challengeAnswer3']")
	WebElement SecurityAnswer3;
	
	@FindBy(xpath="//select[@name='challengeAnswer4']")
	WebElement SecurityAnswer4;
	
//	@FindBy(xpath="//input[@name='acctInfo.accountOptions']")
//	WebElement AddAccounts;

	public AccountAccessPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(FundNumber, ObjectRepo.reader.getExplicitWait(), driver);
	}
	
	public void enterFundNumber(String fundNo) {
		log.info("Enter the fund number...");
		this.FundNumber.sendKeys(fundNo);
	}
	
	public void enterAccountNumber(String AccountNo) {
		log.info("Enter the fund number...");
		this.AccountNumber.sendKeys(AccountNo);
	}
	
	public void enterSSNumber1(int SSN1) {
		log.info("Enter the fund number...");
		this.SSNumber1.sendKeys(String.valueOf(SSN1));
	}
	
	public void enterSSNumber2(String SSN2) {
		log.info("Enter the fund number...");
		this.SSNumber2.sendKeys(String.valueOf(SSN2));
	}

	public void enterSSNumber3(String SSN3) {
		log.info("Enter the fund number...");
		this.SSNumber3.sendKeys(String.valueOf(SSN3));
	}

	public void enterEINumber1(String EIN1) {
		log.info("Enter the fund number...");
		this.EINumber1.sendKeys(String.valueOf(EIN1));
	}

	public void enterEINumber2(String EIN2) {
		log.info("Enter the fund number...");
		this.EINumber2.sendKeys(String.valueOf(EIN2));
	}

	public void SelectQuestion1(WebElement element,String text) {
		log.info("Select any one value from dropdown");
		dropdownhelper = new DropDownHelper(driver);
		dropdownhelper.selectByVissibleText(element, text);
	}
	

}
