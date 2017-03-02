package sample.pageobject;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sample.pageobject.util.TestUtil;
import selenium.WebDriverWrapper;

/**
 * トップページのサービスのご紹介はこちらのクリックに関するテスト
 *
 */
public class TopsServiceIntroductionTest{
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new WebDriverWrapper("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		baseUrl = TestUtil.BASE_URL;
	}

	@Test
	public void サービスのご紹介はこちらを開く() throws Exception{
		driver.get(baseUrl + "sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		driver.findElement(By.linkText("サービスのご紹介はこちら")).click();

		// 新しく開かれたウィンドウにフォーカスする
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("http://www.sharp.co.jp/multicopy/nps/", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "S-1.png");
		driver.close();
		driver.switchTo().window(current_window_id);

	}

	@After
	public void tearDown() throws Exception{
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if(!"".equals(verificationErrorString)){
			fail(verificationErrorString);
		}
	}
}
