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
 * トップページのヘッダーの各種コンビニアイコンのクリックに関するテスト
 *
 */
public class TopsHeaderConveniIconTest{
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
	public void 各コンビニのURLにアクセスする() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		// サークルK・サンクスをクリック
		driver.findElement(By.xpath("//img[@alt='サークルＫ・サンクス']")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("http://www.circleksunkus.jp/", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "convini_1.png");
		// ページを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);
		// セイコーマートをクリック
		driver.findElement(By.xpath("//img[@alt='セイコーマート']")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("http://www.seicomart.co.jp/", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "convini_2.png");
		// ページを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);
		// セーブオンをクリック
		driver.findElement(By.xpath("//img[@alt='セーブオン']")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("https://www.saveon.co.jp/", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "convini_3.png");
		// ページを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);
		// ファミリーマートをクリック
		driver.findElement(By.xpath("//img[@alt='ファミリーマート']")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("http://www.family.co.jp/", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "convini_4.png");
		// ページを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);
		// ローソンをクリック
		driver.findElement(By.xpath("//img[@alt='ローソン']")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("http://www.lawson.co.jp/index.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "convini_5.png");
		// ページを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);
		// END
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
