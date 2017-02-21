package sample.pageobject;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import sample.pageobject.util.TestUtil;
import selenium.WebDriverWrapper;

public class AlterCancelTest{
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

		waitForPopUp = new WebDriverWait(driver, 600);
		// System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		// capabilities.setCapability("marionette", true);
		// driver = new FirefoxDriver(capabilities);

		baseUrl = "https://cvs.so.sh.airfolc.co.jp/";

		System.out.println("start NWPS test");

		// ログインする
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys("nakamura.hajime@sharp.co.jp");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("1111aaaa");
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		// POPUP閉じる
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.cssSelector("div.LinkArea")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
	}

	@Test
	public void 退会フロー() throws Exception{
		// 変数(退会時の入力パスワード)
		String pass = "1111aaaa";
		// 前提条件：一般ユーザーでログインしていること
		// driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
		// driver.findElement(By.id("btnModalClose")).click();
		driver.findElement(By.linkText("アカウント設定")).click();
		// assertEquals("id=Img3", driver.getCurrentUrl());
		assertThat("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/alter_account.aspx", is(not(driver.findElement(By.tagName("BODY")).getText())));
		driver.findElement(By.id("Img3")).click();
		assertThat("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/alter_cancel.aspx", is(not(driver.findElement(By.tagName("BODY")).getText())));
		TestUtil.takesScreenshot(driver, "taikai.png");
		driver.findElement(By.id("chkAgree")).click();
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys(pass);
		driver.findElement(By.id("ibtn_alteration")).click();
		// driver.findElement(By.id("Img4")).click();
		assertThat("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/cancel_finish.aspx", is(not(driver.findElement(By.tagName("BODY")).getText())));
		TestUtil.takesScreenshot(driver, "taikai1.png");
		driver.findElement(By.id("Img3")).click();
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
