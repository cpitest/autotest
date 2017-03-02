package sample.pageobject;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import sample.pageobject.util.TestUtil;
import selenium.WebDriverWrapper;

public class KantanTest{
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	// ポップアップダイアログのボタン押下用
	WebDriverWait waitForPopUp;

	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new WebDriverWrapper("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitForPopUp = new WebDriverWait(driver, 600);
		baseUrl = TestUtil.BASE_URL;

		// ログインする
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys(TestUtil.USER_ID);
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys(TestUtil.USER_PASSWORD);
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
	}

	@Test
	public void かんたん() throws Exception{
		TestUtil.takesScreenshot(driver, "kantan.png");
		driver.findElement(By.cssSelector("a")).click();
		TestUtil.takesScreenshot(driver, "kantan01.png");
		driver.findElement(By.cssSelector("div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom02 > a")).click();
		TestUtil.takesScreenshot(driver, "kantan02.png");
		driver.findElement(By.cssSelector("#pop_zoom02 > div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom03 > a")).click();
		TestUtil.takesScreenshot(driver, "kantan03.png");
		driver.findElement(By.cssSelector("#pop_zoom03 > div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom04 > a")).click();
		TestUtil.takesScreenshot(driver, "kantan04.png");
		driver.findElement(By.cssSelector("#pop_zoom04 > div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom05 > a")).click();
		TestUtil.takesScreenshot(driver, "kantan05.png");
		driver.findElement(By.cssSelector("#pop_zoom05 > div.modal_close")).click();
		driver.findElement(By.id("chkReferenceDisable")).click();
		driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
		// driver.findElement(By.id("btnModalClose")).click();
		driver.findElement(By.cssSelector("img[alt=\"ログアウト\"]")).click();
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys(TestUtil.USER_ID);
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys(TestUtil.USER_PASSWORD);
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();

		boolean isElementPresent = false;
		try{
			isElementPresent = driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).isDisplayed();
		} catch(NoSuchElementException e){
			isElementPresent = false;
		}
		assertFalse(isElementPresent);
	}

	@After
	public void tearDown() throws Exception{
		try{
			// driver.quit();
			driver.close();
		} catch(Exception e){
		}
		String verificationErrorString = verificationErrors.toString();
		if(!"".equals(verificationErrorString)){
			fail(verificationErrorString);
		}
	}
}
