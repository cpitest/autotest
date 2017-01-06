package sample.pageobject;

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

public class FooterMulticopyTest{
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
		driver.findElement(By.id("txtId")).sendKeys("testselenium147258369@gmail.com");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("abcd1234");
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
	public void マルチコピーサービス() throws Exception{
		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		// マルチコピー機サービスをクリック
		driver.findElement(By.linkText("マルチコピー機サービス")).click();

		// 新しく開かれたウィンドウにフォーカスする
		TestUtil.focusByNewWindow(driver, current_window_id);

		assertEquals("http://www.sharp.co.jp/multicopy/", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "multicopy_1.png");

		// 開いたウィンドウを閉じる
		driver.close();

		// 最後に格納したウインドウIDにスイッチ
		driver.switchTo().window(current_window_id);
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
