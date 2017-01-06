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
public class TopsKanbanTest{
	private static final int WAIT_TIME = 3000;
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
	public void カンバンのURLにアクセスする() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		// スライド1○ボタンと看板をクリック
		driver.findElement(By.cssSelector("button[type=\"button\"]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban1-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[2]/a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban1-2.png");

		driver.close();
		driver.switchTo().window(current_window_id);

		// スライド7○ボタンと看板をクリック
		driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban7-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[8]/a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban7-2.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// スライド6○ボタンと看板をクリック
		driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban6-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[7]/a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban6-2.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// スライド5○ボタンと看板をクリック
		driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban5-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[6]/a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban5-2.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// スライド4○ボタンと看板をクリック
		driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban4-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[5]/a")).click();
		String forBizWindowId = TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban4-2.png");
		forBiz照会ページのテスト(driver, forBizWindowId);
		driver.close();
		driver.switchTo().window(current_window_id);

		// スライド3○ボタンと看板をクリック
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban3-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[4]/a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban3-2.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// スライド2○ボタンと看板をクリック
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		Thread.sleep(WAIT_TIME);
		TestUtil.takesScreenshot(driver, "kanban2-1.png");
		driver.findElement(By.xpath("//div[@id='slide_block']/div/div/div/div/div/div[3]/a")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);

		// FAQのキャプチャがとれないのでverifyLocationで判定
		try{
			assertEquals("https://cvs.so.sh.airfolc.co.jp/faq/ja/utilization_top.html", driver.getCurrentUrl());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		driver.close();
		driver.switchTo().window(current_window_id);

		// 終わり
	}

	/**
	 * forBiz照会ページのテストを行う
	 *
	 * @param driver
	 * @param windowId
	 *            forBiz照会ページのウィンドウID
	 * @return true=forBiz照会ページである、false=以外のページ
	 * @throws Exception
	 */
	private void forBiz照会ページのテスト(WebDriver driver, String windowId) throws Exception{
		// 画面最上部の[お問い合わせはこちら]ボタンをクリックする
		driver.findElement(By.linkText("お問い合わせはこちら")).click();
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "otoiawase1.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

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
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "otoiawase2.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

		// ネットワークプリント for Biz
		// [お問い合わせはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#contents02 > #btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "otoiawase3.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

		// ユーザー番号発行サービス
		// [お問い合わせははこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#content03_image > #btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "otoiawase4.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

		// [お申込みはこちら]ボタンをクリックする
		driver.findElement(By.linkText("お申し込みはこちら")).click();
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "omoushikomi1.png");
		// お申込みウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

		// ネットワークプリントWeb API
		// [お問い合わせははこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#contents04 > #btn01 > a")).click();
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "otoiawase5.png");
		// お問合わせウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

		// [お申込みはこちら]ボタンをクリックする
		driver.findElement(By.cssSelector("#contents04 > #btn05 > a")).click();
		TestUtil.focusByNewWindow(driver, windowId);
		TestUtil.takesScreenshot(driver, "omoushikomi2.png");
		// お申込みウインドウを閉じる
		driver.close();
		driver.switchTo().window(windowId);

		// ForBiz紹介ページに遷移して終わり
		driver.get(baseUrl + "/start/biz/");
		driver.switchTo().window(windowId);
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
