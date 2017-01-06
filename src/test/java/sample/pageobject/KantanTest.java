package sample.pageobject;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.WebDriverWrapper;

public class KantanTest{
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

	}

	@Test
	public void かんたん() throws Exception{
		takesScreenshot("C:\\x\\screenshot\\kantan.png");
		driver.findElement(By.cssSelector("a")).click();
		takesScreenshot("C:\\x\\screenshot\\kantan01.png");
		driver.findElement(By.cssSelector("div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom02 > a")).click();
		takesScreenshot("C:\\x\\screenshot\\kantan02.png");
		driver.findElement(By.cssSelector("#pop_zoom02 > div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom03 > a")).click();
		takesScreenshot("C:\\x\\screenshot\\kantan03.png");
		driver.findElement(By.cssSelector("#pop_zoom03 > div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom04 > a")).click();
		takesScreenshot("C:\\x\\screenshot\\kantan04.png");
		driver.findElement(By.cssSelector("#pop_zoom04 > div.modal_close")).click();
		driver.findElement(By.cssSelector("div.zoom05 > a")).click();
		takesScreenshot("C:\\x\\screenshot\\kantan05.png");
		driver.findElement(By.cssSelector("#pop_zoom05 > div.modal_close")).click();
		driver.findElement(By.id("chkReferenceDisable")).click();
		driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
		// driver.findElement(By.id("btnModalClose")).click();
		driver.findElement(By.cssSelector("img[alt=\"ログアウト\"]")).click();
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys("testselenium147258369@gmail.com");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("abcd1234");
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();

		boolean isElementPresent = false;
		try{
			isElementPresent = driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).isDisplayed();
		} catch(NoSuchElementException e){
			isElementPresent = false;
		}
		assertFalse(isElementPresent);
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
