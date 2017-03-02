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

public class HeaderHowtoTest{
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
	public void ご利用方法() throws Exception{
		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		driver.findElement(By.cssSelector("img[alt=\"ご利用方法\"]")).click();

		// 新しく開かれたウィンドウにフォーカスする
		TestUtil.focusByNewWindow(driver, current_window_id);

		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"詳しくはこちら\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_member.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"文書プリントの登録方法\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_regist.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"img_ja/btn_howto_regist_photo_a.jpg\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_lphoto_regist.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"文書プリントの印刷方法\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"画像プリントの印刷方法\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_lphoto_convini.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[2]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini_s.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[3]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini_s.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[4]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.xpath("(//img[@alt='画像プリントの印刷方法'])[2]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_lphoto_convini.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[5]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_doc_convini.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.xpath("(//img[@alt='画像プリントの印刷方法'])[3]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_lphoto_convini.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.linkText("■サークルＫ・サンクス")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_price_c.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.linkText("■セイコーマート")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_price_s.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.linkText("■セーブオン")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_price_sa.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.linkText("■ファミリーマート")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_price_f.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());
		driver.findElement(By.linkText("■ローソン")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto_price_l.aspx", driver.getCurrentUrl());
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/howto.aspx", driver.getCurrentUrl());

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
