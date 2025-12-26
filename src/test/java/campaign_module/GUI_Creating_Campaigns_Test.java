package campaign_module;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import ObjectRepo.Campaign_Verification_Msg_Page;
import ObjectRepo.CampaignsPage;
import ObjectRepo.Creating_New_Campaign_Page;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import generic_libraries.ExcelUtil;
import generic_libraries.FileUtils;
import generic_libraries.JavaUtils;
import generic_libraries.WebDriver_Utils;

public class GUI_Creating_Campaigns_Test {

	@Test
	public  void creating_Campaigns() throws IOException {
		//Creating the Objects of Generic utils
		
				FileUtils utils = new FileUtils();
				JavaUtils random = new JavaUtils();
				ExcelUtil excel = new ExcelUtil();
				WebDriver_Utils wd= new WebDriver_Utils();
				
				WebDriver driver= null;
				
				//Calling the methods
				String browser = utils.readDataFromProperties("browser");
				String url=utils.readDataFromProperties("url");
				String username=utils.readDataFromProperties("username");
				String password=utils.readDataFromProperties("password");
				
				if(browser.equalsIgnoreCase("chrome")) {
					driver=new ChromeDriver();
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				}
				driver.get(url);
				
				//Creating objects of POM Class
				LoginPage lp = new LoginPage(driver);
				HomePage hp = new HomePage(driver);
				CampaignsPage cp = new CampaignsPage(driver);
				Creating_New_Campaign_Page ccp = new Creating_New_Campaign_Page(driver);
				Campaign_Verification_Msg_Page cvm = new Campaign_Verification_Msg_Page(driver);
				
				
//				Login to application
				lp.login(username, password);
				
//				Navigate to Home Page Click on 'More'
//				driver.findElement(By.linkText("More")).click();
				hp.clickOnMoreLink();
				
//				Marketing under that Click on 'Campaign'
//				driver.findElement(By.name("Campaigns")).click();
				hp.clickOnCampaignsLink();
				
//				Click on 'Campaign'  lookup image
//				driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
				cp.clickonCampaignLookUp();
				
				
//				Enter mandatory fields with valid data
				String campaignname = excel.readDataFromExcel("Campaign", 0, 1)+"_"+random.getRandom();
				System.out.println("campaignname = "+campaignname);
				
//				driver.findElement(By.name("campaignname")).sendKeys(campaignname);

//				click on 'Save' button
//				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				ccp.createCampaign(campaignname);

//				String exp = campaignname;
//				String actual=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
//				
//				if(actual.contains(exp)) {
//					System.out.println("campaig nname is created");
//				}
//				else {
//					System.out.println("campaign name is not created");
//				}
				
				cvm.verifyCampaigCreated(campaignname);
				
				//sigout
//				driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
//				WebElement signout = driver.findElement(By.linkText("Sign Out"));
//				
////				Actions act = new Actions(driver);
////				act.moveToElement(signout).click().perform();
//				wd.mouseHoverAndClickOnEle(driver, signout);
				
				hp.signOut(wd, driver);
				
				driver.quit();

	}

}
