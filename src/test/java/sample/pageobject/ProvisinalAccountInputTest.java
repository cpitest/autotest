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
 * ユーザーの仮登録に関するテスト
 *
 */
public class ProvisinalAccountInputTest{
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
	public void ユーザーの仮登録を行う() throws Exception{
		driver.get(baseUrl + "sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		// String current_window_id = driver.getWindowHandle();

		// [仮登録メールが届かない方はこちら ]クリック
		driver.findElement(By.linkText("仮登録メールが届かない方はこちら")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/send_test_mail.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "mail-1.png");

		// 不正なメールアドレスを入力
		driver.findElement(By.id("txtMailAddress")).clear();
		driver.findElement(By.id("txtMailAddress")).sendKeys("あああああ");
		driver.findElement(By.id("ibtnConfirm")).click();

		// ダイアログMSGを判定
		TestUtil.takesScreenshot(driver, "mail-2.png");
		assertEquals("ご利用できないメールアドレスです。", driver.findElement(By.cssSelector("#cboxLoadedContent > div")).getText());
		driver.findElement(By.id("cboxClose")).click();

		// メールアドレスを入力(再入力)
		driver.findElement(By.id("txtMailAddress")).clear();
		driver.findElement(By.id("txtMailAddress")).sendKeys("test@aa.aa");
		assertEquals("test@aa.aa", driver.findElement(By.id("txtMailAddress")).getAttribute("value"));
		TestUtil.takesScreenshot(driver, "mail-3.png");
		driver.findElement(By.id("ibtnConfirm")).click();

		// 画面遷移を確認
		TestUtil.takesScreenshot(driver, "mail-4.png");
		// 入力したメールアドレスを確認
		assertEquals("test@aa.aa", driver.findElement(By.id("lblMailAddress")).getText());

		// [TOPへもどる]をクリック
		driver.findElement(By.id("Img1")).click();
		TestUtil.takesScreenshot(driver, "mail-5.png");
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
