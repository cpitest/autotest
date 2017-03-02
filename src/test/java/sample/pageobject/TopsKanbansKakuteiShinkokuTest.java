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
 * カンバンから遷移している画面（確定申告ページ）をテストする
 *
 */
public class TopsKanbansKakuteiShinkokuTest{

	private static final String KAKUTEI_SHINKOKU_URL = "https://www.keisan.nta.go.jp/h28yokuaru/cat1/cat16/cat162/cid230.html";

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
	public void 確定申告用ページに遷移する() throws Exception{
		driver.get(baseUrl + "nta/kakutei_web.html");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		// [パソコンからの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"パソコンからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#h2_02", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-1.png");
		// [iPadからの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"iPadからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-2.png");
		// [パソコンからの登録方法はこちら]クリックしPC用ページに戻る
		// iPadページからの遷移確認
		driver.findElement(By.cssSelector("img[alt=\"パソコンからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-15.png");
		// [印刷方法について]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"印刷方法について\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents04", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-3.png");
		// [申告書の提出について]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"申告書の提出について\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-4.png");
		// [会員登録する(無料)]クリック1
		driver.findElement(By.cssSelector("img[alt=\"会員登録する（無料）\"]")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kakutei1-5.png");
		driver.findElement(By.id("cboxClose")).click();
		driver.close();
		driver.switchTo().window(current_window_id);

		// [会員登録する(無料)]クリック2
		driver.findElement(By.cssSelector("#contents03_03 > p.btn > a > img[alt=\"会員登録する（無料）\"]")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kakutei1-6.png");
		driver.findElement(By.id("cboxClose")).click();
		driver.close();
		driver.switchTo().window(current_window_id);

		// [上記店舗での印刷方法]クリック、画面遷移する(セイコーマート/セーブオン)
		driver.findElement(By.cssSelector("img[alt=\"上記店舗での印刷方法\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini_s.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-7.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());
		// [上記店舗での印刷方法]クリック、画面遷移する(ファミマなど)
		driver.findElement(By.xpath("(//img[@alt='上記店舗での印刷方法'])[2]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-8.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());
		// ページ上部のリンク"会員登録方法はこちらをご覧ください"をクリックし画面遷移
		driver.findElement(By.linkText("会員登録方法はこちらをご覧ください")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_member.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-9.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());

		// リンク"ネットワークプリントサービスWeb ページ"をクリックし画面遷移
		driver.findElement(By.linkText("ネットワークプリントサービスWeb ページ")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/top.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-10.png");
		driver.close();
		driver.switchTo().window(current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());

		// ページ中部のリンク"文書ファイルの登録方法はこちら"をクリックし画面遷移
		driver.findElement(By.linkText("文書ファイルの登録方法はこちら")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_regist.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-11.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());
		// ページ中部のリンク"会員登録方法はこちらをご覧ください"をクリックし画面遷移
		driver.findElement(By.xpath("(//a[contains(text(),'会員登録方法はこちらをご覧ください')])[2]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_member.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-12.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());
		// リンク"こちら"をクリックし画面遷移(外部サイト)
		driver.findElement(By.linkText("こちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals(KAKUTEI_SHINKOKU_URL, driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-13.png");
		driver.close();
		driver.switchTo().window(current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_web.html#contents06", driver.getCurrentUrl());

		// [Androidタブレット™からの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"Androidタブレット™からの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei1-14.png");
		// Androidタブレット版へ続く
		// ↓
		// [Androidタブレット™からの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"Androidタブレット™からの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#h2_02", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-1.png");
		// [iPadからの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"iPadからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-2.png");
		// [Androidタブレット™からの登録方法はこちら]クリックしPC用ページに戻る
		// iPadページからの遷移確認
		driver.findElement(By.cssSelector("img[alt=\"Androidタブレット™からの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-12.png");
		// [印刷方法について]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"印刷方法について\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#contents04", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-3.png");
		// [申告書の提出について]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"申告書の提出について\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#contents06", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-4.png");

		// [GooglePlay]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"会員登録する（無料）\"]")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertTrue(driver.getCurrentUrl().matches("^https://play\\.google\\.com/store/search[\\s\\S]q=pname:jp\\.co\\.sharp\\.networkprintlite$"));
		TestUtil.takesScreenshot(driver, "kakutei2-5.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// [上記店舗での印刷方法]クリック、画面遷移する(セイコーマート/セーブオン)
		driver.findElement(By.cssSelector("img[alt=\"上記店舗での印刷方法\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini_s.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-6.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#contents06", driver.getCurrentUrl());
		// [上記店舗での印刷方法]クリック、画面遷移する(ファミマなど)
		driver.findElement(By.xpath("(//img[@alt='上記店舗での印刷方法'])[2]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-7.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#contents06", driver.getCurrentUrl());
		// リンク"会員登録（無料）"をクリックし画面遷移
		driver.findElement(By.linkText("会員登録（無料）")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kakutei2-8.png");
		driver.findElement(By.id("cboxClose")).click();
		driver.close();
		driver.switchTo().window(current_window_id);

		// リンク"ネットワークプリントサービスはこちら"クリックし画面遷移
		driver.findElement(By.linkText("ネットワークプリントサービスはこちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/top.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-9.png");
		driver.close();
		driver.switchTo().window(current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#contents06", driver.getCurrentUrl());

		// リンク"こちら"をクリックし画面遷移(外部サイト)
		driver.findElement(By.linkText("こちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals(KAKUTEI_SHINKOKU_URL, driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-10.png");
		driver.close();
		driver.switchTo().window(current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html#contents06", driver.getCurrentUrl());

		// [iPadからの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"iPadからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei2-11.png");
		// iOS版へ続く
		// ↓
		// [iPadからの登録方法はこちら]クリックし、画面内遷移する
		driver.findElement(By.cssSelector("img[alt=\"iPadからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#h2_02", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-1.png");
		// [Androidタブレット™からの登録方法はこちら]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"Androidタブレット™からの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_android.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-2.png");
		// [iPadからの登録方法はこちら]クリックし戻る
		// Androidページからの遷移確認
		driver.findElement(By.cssSelector("img[alt=\"iPadからの登録方法はこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-2.png");
		// [印刷方法について]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"印刷方法について\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#contents04", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-3.png");
		// [申告書の提出について]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"申告書の提出について\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#contents06", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-4.png");
		// [AppStore]クリックし、画面遷移する
		driver.findElement(By.cssSelector("img[alt=\"会員登録する（無料）\"]")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("https://itunes.apple.com/jp/app/netopuri/id930713529?mt=8", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-5.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// [上記店舗での印刷方法]クリック、画面遷移する(セイコーマート/セーブオン)
		driver.findElement(By.cssSelector("img[alt=\"上記店舗での印刷方法\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini_s.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-6.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#contents06", driver.getCurrentUrl());
		// [上記店舗での印刷方法]クリック、画面遷移する(ファミマなど)
		driver.findElement(By.xpath("(//img[@alt='上記店舗での印刷方法'])[2]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-7.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#contents06", driver.getCurrentUrl());
		// リンク"会員登録（無料）"をクリックし画面遷移
		driver.findElement(By.linkText("会員登録（無料）")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kakutei3-8.png");
		driver.findElement(By.id("cboxClose")).click();
		driver.close();
		driver.switchTo().window(current_window_id);

		// リンク"ネットワークプリントサービスはこちら"クリックし画面遷移
		driver.findElement(By.linkText("ネットワークプリントサービスはこちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/top.aspx", driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-9.png");
		driver.close();
		driver.switchTo().window(current_window_id);
		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#contents06", driver.getCurrentUrl());

		// リンク"こちら"をクリックし画面遷移(外部サイト)
		driver.findElement(By.linkText("こちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		assertEquals(KAKUTEI_SHINKOKU_URL, driver.getCurrentUrl());
		TestUtil.takesScreenshot(driver, "kakutei3-10.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		assertEquals("https://cvs.so.sh.airfolc.co.jp/nta/kakutei_ios.html#contents06", driver.getCurrentUrl());
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
