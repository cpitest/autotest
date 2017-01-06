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
 * 店舗検索のテストを行う
 */
public class TopsHeaderSearchStoreTest{
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new WebDriverWrapper("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		baseUrl = "https://cvs.so.sh.airfolc.co.jp/";
	}

	@Test
	public void 店舗検索のページにアクセスする() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();
		driver.findElement(By.cssSelector("img[alt=\"店舗を探す\"]")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);

		assertNotEquals(-1, driver.getCurrentUrl().indexOf("http://cvsmap.cvs-sds.com/CS"));
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
