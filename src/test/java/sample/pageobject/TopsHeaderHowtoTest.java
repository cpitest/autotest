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
 * トップページのヘッダーのご利用方法のクリックに関するテスト
 *
 */
public class TopsHeaderHowtoTest{
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
	public void ご利用方法を開く() throws Exception{
		// ご利用方法/プリント料金画面から開始
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");

		// 現在のウインドウIDを取得
		String current_window_id = driver.getWindowHandle();

		driver.findElement(By.cssSelector("img[alt=\"ご利用方法 / プリント料金\"]")).click();

		// 新しく開かれたウィンドウにフォーカスする
		TestUtil.focusByNewWindow(driver, current_window_id);

		TestUtil.takesScreenshot(driver, "ご利用方法.png");
		driver.findElement(By.cssSelector("img[alt=\"詳しくはこちら\"]")).click();
		TestUtil.takesScreenshot(driver, "会員登録の方法.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"文書プリントの登録方法\"]")).click();
		TestUtil.takesScreenshot(driver, "文書プリントの登録方法.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"img_ja/btn_howto_regist_photo_a.jpg\"]")).click();
		TestUtil.takesScreenshot(driver, "画像プリントの登録方法.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();

		// コンビニエンスストアで印刷
		driver.findElement(By.cssSelector("img[alt=\"文書プリントの印刷方法\"]")).click();
		TestUtil.takesScreenshot(driver, "文書ck-su.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[2]")).click();
		TestUtil.takesScreenshot(driver, "文書セイコマ.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[3]")).click();
		TestUtil.takesScreenshot(driver, "文書セーブオン.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[4]")).click();
		TestUtil.takesScreenshot(driver, "文書_ファミマ.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[5]")).click();
		TestUtil.takesScreenshot(driver, "文書_ローソン.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"画像プリントの印刷方法\"]")).click();
		TestUtil.takesScreenshot(driver, "画像ck-su.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='画像プリントの印刷方法'])[2]")).click();
		TestUtil.takesScreenshot(driver, "画像_ファミマ.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='画像プリントの印刷方法'])[3]")).click();
		TestUtil.takesScreenshot(driver, "画像_ローソン.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();

		// プリント料金
		driver.findElement(By.linkText("■サークルＫ・サンクス")).click();
		TestUtil.takesScreenshot(driver, "プリント料金_ck-su.png");
		driver.findElement(By.id("Img1")).click();
		driver.findElement(By.linkText("■セイコーマート")).click();
		TestUtil.takesScreenshot(driver, "プリント料金_セイコマ.png");
		driver.findElement(By.id("Img1")).click();
		driver.findElement(By.linkText("■セーブオン")).click();
		TestUtil.takesScreenshot(driver, "プリント料金_セーブオン.png");
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.linkText("■ファミリーマート")).click();
		TestUtil.takesScreenshot(driver, "プリント料金_ファミマ.png");
		driver.findElement(By.id("Img1")).click();
		driver.findElement(By.linkText("■ローソン")).click();
		TestUtil.takesScreenshot(driver, "プリント料金_ローソン.png");
		driver.findElement(By.id("Img1")).click();
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
