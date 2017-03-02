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
 * カンバンの導線をテストする
 *
 */
public class TopsKanbansForBizTest{
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
	public void ForBiz紹介ページに遷移する() throws Exception{
		driver.get(baseUrl + "start/biz/");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		// 画面最上部の[お問い合わせはこちら]ボタンをクリックする
		driver.findElement(By.linkText("お問い合わせはこちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "otoiawase1.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// 3つのサービスをご用意
		// [forBiz 詳しくはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("img[alt=\"1:パソコン、タブレット、スマートフォンから文書や写真を登録\"]")).click();
		// locationの確認
		assertEquals("https://cvs.so.sh.airfolc.co.jp/start/biz/#contents02", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "forbiz_syoukai1.png");
		// [forBuzz 詳しくはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("img[alt=\"2:シャープマルチコピー機設置のコンビニ店舗へ\"]")).click();
		// locationの確認
		assertEquals("https://cvs.so.sh.airfolc.co.jp/start/biz/#contents03", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "forbiz_syoukai2.png");
		// [WebAPI 詳しくはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("img[alt=\"3:必要な部数を選んでプリント！\"]")).click();
		// locationの確認
		assertEquals("https://cvs.so.sh.airfolc.co.jp/start/biz/#contents04", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "forbiz_syoukai3.png");

		// [お問い合わせはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "otoiawase2.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// ネットワークプリント for Biz
		// [お問い合わせはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#contents02 > #btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "otoiawase3.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// ユーザー番号発行サービス
		// [お問い合わせははこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#content03_image > #btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "otoiawase4.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// [お申込みはこちら]ボタンをクリックする
		driver.findElement(By.linkText("お申し込みはこちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "omoushikomi1.png");
		// お申込みウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// ネットワークプリントWeb API
		// [お問い合わせははこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#contents04 > #btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "otoiawase5.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// [お申込みはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#contents04 > #btn05 > a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "omoushikomi2.png");
		// お申込みウインドウを閉じる
		driver.close();
		driver.switchTo().window(current_window_id);

		// ForBiz紹介ページに遷移して終わり
		driver.get(baseUrl + "/start/biz/");
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
