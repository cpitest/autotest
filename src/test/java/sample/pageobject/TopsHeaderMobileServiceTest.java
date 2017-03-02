package sample.pageobject;

import static org.hamcrest.CoreMatchers.*;
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
 * トップページのヘッダーのモバイルサービスのクリックに関するテスト
 *
 */
public class TopsHeaderMobileServiceTest{
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
	public void モバイルサービスを開く() throws Exception{
		driver.get(baseUrl + "sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		driver.findElement(By.cssSelector("img[alt=\"モバイルサービス\"]")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertThat("ネットワークプリント専用アプリのご紹介", is(not(driver.findElement(By.tagName("BODY")).getText())));
		TestUtil.takesScreenshot(driver, "mobile1.png");

		driver.findElement(By.linkText("Android™ スマートフォン用アプリ")).click();
		TestUtil.takesScreenshot(driver, "mobile2.png");

		driver.findElement(By.linkText("iPhone/iPad用アプリ")).click();
		TestUtil.takesScreenshot(driver, "mobile3.png");

		driver.findElement(By.linkText("こちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertThat("ネットワークプリント専用アプリのご紹介", is(not(driver.findElement(By.tagName("BODY")).getText())));
		assertThat("ソフトウェア使用許諸契約書", is(not(driver.findElement(By.tagName("BODY")).getText())));
		TestUtil.takesScreenshot(driver, "mobile4.png");
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
