package sample.pageobject.forBiz;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.WebDriverWrapper;

public class LoginTest{
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	// ポップアップダイアログのボタン押下用
	WebDriverWait waitForPopUp;

	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new WebDriverWrapper("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		waitForPopUp = new WebDriverWait(driver, 120);
		// System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		// capabilities.setCapability("marionette", true);
		// driver = new FirefoxDriver(capabilities);
		baseUrl = "https://cvs.so.sh.airfolc.co.jp/";
		System.out.println("start forBiz test");
		driver.get(baseUrl + "/forBiz/app");
	}

	@Test
	public void ログイン成功確認() throws Exception{
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys("nakamura.hajime2@sharp.co.jp");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("1111aaaa");
		driver.findElement(By.id("doLogin")).click();
		assertTrue(driver.getCurrentUrl().contains("https://cvs.so.sh.airfolc.co.jp/forBiz/view/main/status.html"));
	}

	@After
	public void tearDown() throws Exception{
		try{
			System.out.println("start NWPS end1");
			// driver.quit();
			driver.close();
		} catch(Exception e){
		}
		String verificationErrorString = verificationErrors.toString();
		if(!"".equals(verificationErrorString)){
			fail(verificationErrorString);
		}
		System.out.println("start NWPS end2");
	}
}
