package sample.pageobject;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

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

		// 開いたウインドウも含め全部のウインドウIDを取得する
		java.util.Set<String> window_ids = driver.getWindowHandles();
		String new_window_id = null;
		new_window_id = getNewWindowHandle(window_ids, current_window_id);
		// 最後に格納したウインドウIDにスイッチ
		driver.switchTo().window(new_window_id);

		assertEquals("http://www.sharp.co.jp/multicopy/", driver.getCurrentUrl());
		takesScreenshot("C:\\x\\screenshot\\multicopy_1.png");

		// 開いたウィンドウを閉じる
		driver.close();

		// 最後に格納したウインドウIDにスイッチ
		driver.switchTo().window(current_window_id);
	}

	private String getNewWindowHandle(Set<String> window_ids, String current_window_id){
		String new_window_id;
		for(String id : window_ids){
			if(!id.equals(current_window_id)){
				// 現在のウインドウIDと違っていたら格納
				// 最後に格納されたIDが一番新しく開かれたウインドウと判定
				new_window_id = id;
				return new_window_id;
			}
		}
		return null;
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

	public void takesScreenshot(String path) throws WebDriverException, IOException, InterruptedException{

		TakesScreenshot ts = (TakesScreenshot)new Augmenter().augment(driver);

		// JS実行用のExecuter
		JavascriptExecutor jexec = (JavascriptExecutor)driver;

		// 画面サイズで必要なものを取得
		int innerH = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerHeight")));
		int innerW = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerWidth")));
		int scrollH = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollHeight")));

		// イメージを扱うための準備
		BufferedImage img = new BufferedImage(innerW, scrollH, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();

		// スクロールを行うかの判定
		if(innerH > scrollH){
			BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
			g.drawImage(imageParts, 0, 0, null);
		} else{
			int scrollableH = scrollH;
			int i = 0;

			// スクロールしながらなんどもイメージを結合していく
			while(scrollableH > innerH){
				BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
				g.drawImage(imageParts, 0, innerH * i, null);
				scrollableH = scrollableH - innerH;
				i++;
				jexec.executeScript("window.scrollTo(0," + innerH * i + ")");
			}

			// 一番下まで行ったときは、下から埋めるように貼り付け
			BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
			g.drawImage(imageParts, 0, scrollH - innerH, null);
		}

		ImageIO.write(img, "png", new File(path));
	}
}
