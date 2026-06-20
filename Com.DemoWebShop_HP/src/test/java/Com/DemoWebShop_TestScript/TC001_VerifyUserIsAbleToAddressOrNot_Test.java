package Com.DemoWebShop_TestScript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import Com.DemoWebShop_GenericUtility.BaseTest;
import Com.DemoWebShop_POM.AddNewAddresses_Page;
import Com.DemoWebShop_POM.Addresses_Page;

public class TC001_VerifyUserIsAbleToAddressOrNot_Test extends BaseTest {
	@Test
	public void addAddress() throws EncryptedDocumentException, IOException, InterruptedException {
		webDriverUtility.javascriptScrollTillElement(driver, homepPage.getAddressesLink());
		homepPage.getAddressesLink().click();
		
		Addresses_Page addressesPage=new Addresses_Page(driver);
		webDriverUtility.javascriptScrollTillElement(driver, addressesPage.getAddNewButton());
		addressesPage.getAddNewButton().click();
		
		AddNewAddresses_Page newAddresses_Page= new AddNewAddresses_Page(driver);
		newAddresses_Page.getFirstNameTextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 0));
		newAddresses_Page.getLastNameTextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 1));
		newAddresses_Page.getEmailTextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 2));
		
		webDriverUtility.selectByVisibleText(newAddresses_Page.getCountryDropdown(),
				fileutility.readDataFromExcelFile("Sheet1", 1, 3));
		
		newAddresses_Page.getCityTextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 4));
		newAddresses_Page.getAddress1_TextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 5));
		newAddresses_Page.getZipPostalCodeTextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 6));
		newAddresses_Page.getPhoneNumberTextField().sendKeys(fileutility.readDataFromExcelFile("Sheet1", 1, 7));
		newAddresses_Page.getSaveButton().click();
		
		Thread.sleep(3000);
		
		webDriverUtility.webPageScreeenShot(driver);
		
	}

}
