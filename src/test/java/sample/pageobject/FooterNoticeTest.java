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

public class FooterNoticeTest{
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
	public void ご利用上の注意() throws Exception{
		// driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		// driver.findElement(By.linkText("ご利用上の注意")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | ネットワークプリントサービス");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		driver.findElement(By.linkText("ご利用上の注意")).click();

		// 新しく開かれたウィンドウにフォーカスする
		TestUtil.focusByNewWindow(driver, current_window_id);

		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/notice.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "goriyouzyou1.png");

		// 開いたウィンドウを閉じる
		driver.close();

		// 最後に格納したウインドウIDにスイッチ
		driver.switchTo().window(current_window_id);
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
