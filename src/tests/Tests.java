package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.AboutUs;
import objects.AlternativeReg;
import objects.Credentials;
import objects.Homepage;
import objects.Login;

public class Tests {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver1\\ChromeDriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void registration1Test() {

		driver.get(Homepage.HOMEPAGE_URL);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.name(Homepage.FULLNAME_NAME)).sendKeys(Credentials.USER1_NAME);
		driver.findElement(By.xpath("//*[@id=\"top-form\"]/div/div[2]/input")).sendKeys(Credentials.randomEmail());
		driver.findElement(By.id(Homepage.START_ID)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actual = driver.getCurrentUrl();
		String expected = "https://www.humanity.com/wizard-setup2/";

		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2)
	public void registration2Test() {
		driver.get(Homepage.HOMEPAGE_URL);
		driver.manage().window().maximize();
		// driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.xpath(AlternativeReg.STARTFREETRIAL_XPATH)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name(AlternativeReg.FULLNAME_NAME)).sendKeys(Credentials.USER2_NAME);
		driver.findElement(By.name(AlternativeReg.WORKMAIL_NAME)).sendKeys(Credentials.randomEmail());
		driver.findElement(By.xpath(AlternativeReg.STARTFREETRIALBUTTON_XPATH)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String actual = driver.getCurrentUrl();
		String expected = "https://www.humanity.com/wizard-setup2/";

		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 3)
	public void loginTest() {

		driver.get(Homepage.HOMEPAGE_URL);
		driver.manage().window().maximize();
		// driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.xpath(Login.LOGINOPTION_XPATH)).click();
		driver.findElement(By.id(Login.USERNAME_ID)).sendKeys(Credentials.LOGINUSERNAME);
		driver.findElement(By.id(Login.PASSWORD_ID)).sendKeys(Credentials.LOGINPASSWORD);
		driver.findElement(By.xpath(Login.LOGINBUTTON_XPATH)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		String actual = driver.getCurrentUrl();
		String expected = "https://danilovic.humanity.com/app/dashboard/";
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 4)
	public void aboutUsTest() {

		driver.get(Homepage.HOMEPAGE_URL);
		// driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(AboutUs.ABOUTUSHOVER_XPATH))).perform();
		driver.findElement(By.xpath(AboutUs.ABOUTUSBUTTON_XPATH)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(".\\screenshot.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		String actual = driver.getCurrentUrl();
		String expected = "https://www.humanity.com/about";
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 5)
	public void shiftPlanningTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.id("sn_schedule")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("ShiftPlanning - Humanity", driver.getTitle());

	}

	@Test(priority = 6)
	public void timeClockTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.xpath("//*[@id=\"sn_timeclock\"]/span")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("Timeclock - Overview - Humanity", driver.getTitle());

	}

	@Test(priority = 7)
	public void leaveTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.xpath("//*[@id=\"sn_requests\"]/span/p")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("Leave - Vacation - Humanity", driver.getTitle());

	}

	@Test(priority = 8)
	public void trainingTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.id("sn_training")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("Training - Overview - Humanity", driver.getTitle());

	}

	@Test(priority = 9)
	public void staffTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.id("sn_staff")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("Staff - List - Humanity", driver.getTitle());

	}

	@Test(priority = 10)
	public void availabilityTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.id("sn_availability")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertEquals("Humanity", driver.getTitle());

	}

	@Test(priority = 11)
	public void payrollTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.id("sn_payroll")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("Payroll - Scheduled-hours - Humanity", driver.getTitle());

	}

	@Test(priority = 12)
	public void reportsTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");
		driver.findElement(By.id("sn_reports")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertEquals("Reports - Reports Home - Humanity", driver.getTitle());
	}

	@Test(priority = 12)
	public void dashboardTest() {
		driver.get("https://danilovic.humanity.com/app/dashboard/");

		Assert.assertEquals("Dashboard - Dashboard - Humanity", driver.getTitle());

	}

	@Test(priority = 13)
	public void addEmployeeTest() throws InterruptedException {

		driver.get(Homepage.HOMEPAGE_URL);
		//driver.manage().window().maximize();
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.xpath(Login.LOGINOPTION_XPATH)).click();
		driver.findElement(By.id(Login.USERNAME_ID)).sendKeys(Credentials.LOGINUSERNAME);
		driver.findElement(By.id(Login.PASSWORD_ID)).sendKeys(Credentials.LOGINPASSWORD);
		driver.findElement(By.xpath(Login.LOGINBUTTON_XPATH)).click();
		driver.findElement(By.id("sn_staff")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("act_primary")).click();
		driver.findElement(By.id("_asf1")).sendKeys("Milorad");
		driver.findElement(By.id("_asl1")).sendKeys("Miloradovic");
		driver.findElement(By.id("_ase1")).sendKeys(Credentials.randomEmail());
		driver.findElement(By.id("_as_save_multiple")).click();
		Thread.sleep(500);
		WebElement alert1 = driver.findElement(By.id("_status"));
		Assert.assertTrue(alert1.isDisplayed());

	}

	@Test(priority = 14)
	public void languageTest() {
		driver.get(Homepage.HOMEPAGE_URL);
		//driver.manage().window().maximize();
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.xpath(Login.LOGINOPTION_XPATH)).click();
		driver.findElement(By.id(Login.USERNAME_ID)).sendKeys(Credentials.LOGINUSERNAME);
		driver.findElement(By.id(Login.PASSWORD_ID)).sendKeys(Credentials.LOGINPASSWORD);
		driver.findElement(By.xpath(Login.LOGINBUTTON_XPATH)).click();
		driver.findElement(By.xpath("//*[@id=\"sn_admin\"]/span")).click();
		// driver.findElement(By.name("language")).click();

		WebElement language = driver.findElement(By.name("language"));
		language.click();
		Select s = new Select(driver.findElement(By.name("language")));
		s.selectByVisibleText("Croatian (machine)");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.id("_save_settings_button")).click();
		driver.navigate().refresh();

		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"_cd_admin\"]/div[1]/div/div/div[3]/ul/li/a"))
				.getText().toString(), "Naplate");
	}

	@Test(priority = 15)
	public void nameChangeTest() {

		driver.get(Homepage.HOMEPAGE_URL);
		//driver.manage().window().maximize();
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.xpath(Login.LOGINOPTION_XPATH)).click();
		driver.findElement(By.id(Login.USERNAME_ID)).sendKeys(Credentials.LOGINUSERNAME);
		driver.findElement(By.id(Login.PASSWORD_ID)).sendKeys(Credentials.LOGINPASSWORD);
		driver.findElement(By.xpath(Login.LOGINBUTTON_XPATH)).click();
		driver.findElement(By.xpath("//*[@id=\"sn_staff\"]/span")).click(); // staff
		driver.findElement(By.xpath("//*[@id=\'7\']/a")).click(); // employee
		driver.findElement(By.xpath("//*[@id=\"_cd_staff\"]/div[2]/div[2]/div[1]/a[2]")).click(); // edit
		String actual1 = driver.findElement(By.id("first_name")).getAttribute("value");
		driver.findElement(By.id("first_name")).clear();
		driver.findElement(By.id("first_name")).sendKeys(Credentials.randomName());
		String actual2 = driver.findElement(By.id("first_name")).getAttribute("value");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.name("update")).click();
		driver.navigate().refresh();

		Assert.assertNotEquals(actual1, actual2);

	}

	@Test(priority = 16)
	public void excelFileTest() {

		File f = new File("file.xlsx");
		try {
			InputStream inp = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			SoftAssert sa = new SoftAssert();

			driver.get(Homepage.HOMEPAGE_URL);
			//driver.manage().window().maximize();
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
			driver.findElement(By.xpath(Login.LOGINOPTION_XPATH)).click();
			driver.findElement(By.id(Login.USERNAME_ID)).sendKeys(Credentials.LOGINUSERNAME);
			driver.findElement(By.id(Login.PASSWORD_ID)).sendKeys(Credentials.LOGINPASSWORD);
			driver.findElement(By.xpath(Login.LOGINBUTTON_XPATH)).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < 5; i++) {
				Row row = sheet.getRow(i);
				Cell c1 = row.getCell(0);
				Cell c2 = row.getCell(1);
				String name = c1.toString();
				String lastName = c2.toString();
				driver.findElement(By.xpath("//*[@id=\"sn_staff\"]/span")).click(); // click na staff
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				driver.findElement(By.id("act_primary")).click(); // click na add employee
				driver.findElement(By.id("_asf1")).sendKeys(name);
				driver.findElement(By.id("_asl1")).sendKeys(lastName);
				driver.findElement(By.id("_ase1")).sendKeys(Credentials.randomEmail());
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,1000)");
				driver.findElement(By.id("_as_save_multiple")).click();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				WebElement alert1 = driver.findElement(By.id("_status"));
				sa.assertTrue(alert1.isDisplayed());
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			}
			sa.assertAll();
			wb.close();

		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

	}
	
	@Test(priority = 17)
	public void checkBoxTest() {
		
		SoftAssert sa = new SoftAssert();
		
		driver.get(Homepage.HOMEPAGE_URL);
		driver.manage().window().maximize();
		// driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/button")).click();
		driver.findElement(By.xpath(Login.LOGINOPTION_XPATH)).click();
		driver.findElement(By.id(Login.USERNAME_ID)).sendKeys(Credentials.LOGINUSERNAME);
		driver.findElement(By.id(Login.PASSWORD_ID)).sendKeys(Credentials.LOGINPASSWORD);
		driver.findElement(By.xpath(Login.LOGINBUTTON_XPATH)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		WebElement checkbox = driver.findElement(By.id("pref_pref_email"));
		boolean actual = checkbox.isSelected();

		sa.assertEquals(actual, true);
		WebElement checkbox1 = driver.findElement(By.id("pref_pref_sms"));
		boolean actual1 = checkbox1.isSelected();
		
		sa.assertEquals(actual1, true);
		WebElement checkbox2 = driver.findElement(By.id("pref_pref_mobile_push"));
		boolean actual2 = checkbox2.isSelected();
		
		sa.assertEquals(actual2, true);
		
		driver.findElement(By.id("pref_pref_email")).click();
		driver.findElement(By.id("pref_pref_sms")).click();
		driver.findElement(By.id("pref_pref_mobile_push")).click();
		driver.findElement(By.xpath("//*[@id=\"_fbody\"]")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.id("_save_settings_button")).click();
		driver.navigate().refresh();
		
		
	}

}
