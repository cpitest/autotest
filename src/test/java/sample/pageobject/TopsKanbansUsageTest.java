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
 * カンバンから遷移している画面（活用方法ページ）をテストする
 *
 */
public class TopsKanbansUsageTest{
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
	public void 活用方法ページに遷移する() throws Exception{
		driver.get(baseUrl + "faq/ja/utilization_top.html");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		// [会員登録はこちら]クリック
		driver.findElement(By.id("regist_btn")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "会員登録はこちら.png");
		driver.findElement(By.id("cboxClose")).click();
		driver.close();
		driver.switchTo().window(current_window_id);

		// 活用方法その1
		driver.findElement(By.linkText("ネットワークプリントのスマホ専用アプリを使ってみよう！")).click();
		assertEquals("ネットワークプリントのスマホ専用アプリを使ってみよう！", driver.getTitle());
		TestUtil.takesScreenshot(driver, "活用方法その1.png");
		// ネットワークプリント専用アプリのダウンロードはこちら
		driver.findElement(By.linkText("こちら")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "link1-1.png");
		driver.close();
		driver.switchTo().window(current_window_id);

		// 画像をクリック(2か所)
		driver.findElement(By.xpath("//img[@alt='iOS端末の文書登録注意画面']")).click();
		TestUtil.takesScreenshot(driver, "画像クリック1-1.png");
		driver.findElement(By.xpath("//div[@id='img_modal_001']/div[2]/div/div/span")).click();
		driver.findElement(By.xpath("//img[@alt='Adobe Readerからネットワークプリントへの文書連携操作']")).click();
		TestUtil.takesScreenshot(driver, "画像クリック1-2.png");
		driver.findElement(By.xpath("//div[@id='img_modal_002']/div[2]/div/div/span")).click();

		// "このページのTOPへもどる"リンクをクリック(3か所)
		driver.findElement(By.linkText("このページのTOPへもどる")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'このページのTOPへもどる')])[2]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'このページのTOPへもどる')])[3]")).click();

		// [会員登録してプリントする]クリック
		driver.findElement(By.id("regist_btn")).click();
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "会員登録してプリントする_その1.png");
		driver.findElement(By.id("cboxClose")).click();
		driver.close();
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
