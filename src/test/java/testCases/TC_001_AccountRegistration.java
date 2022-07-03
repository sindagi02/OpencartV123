package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{	
	@Test(groups= {"regression","master"})
	public void test_account_registration()
	{
		
		logger.debug("debug logging....");
		logger.info(" Starting TC_001_AccountRegistration ");
		
		try
		{
			driver.get(rb.getString("appURL"));
			driver.manage().window().maximize();
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			
			regpage.setFirstName("John");
			regpage.setLastName("Canedy");
			regpage.setEmail(randomestring()+"@gmail.com");
			regpage.setTelephone("65656565");
			regpage.setPassword("abcxyz");
			regpage.setConfirmPassword("abcxyz");
			regpage.setPrivacyPolicy();
			
			regpage.clickContinue();
			
			String confmsg=regpage.getConfirmationMsg();
			
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				logger.info("Registration test passed ");
				Assert.assertTrue(true);
				
			}
			else
			{
				captureScreen(driver,"test_account_registration");
				logger.error("Registration test failed ");
				Assert.assertTrue(false);
			}
			
		}
		catch(Exception e)
		{
			logger.fatal("Account Registration Failed ");
			Assert.fail();
		}
		
		logger.info(" Finished TC_001_AccountRegistration ");
	}
	
}