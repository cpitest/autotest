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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.WebDriverWrapper;

public class AlterAccountTest{
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
	}

	@Test
	public void アカウント設定変更操作() throws Exception{
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
		// driver.findElement(By.id("btnModalClose")).click();
		// アカウント設定画面に遷移
		driver.findElement(By.linkText("アカウント設定")).click();
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		takesScreenshot("C:\\x\\screenshot\\アカウント設定画面.png");
		// アカウント設定を変更
		driver.findElement(By.id("txtName")).clear();
		driver.findElement(By.id("txtName")).sendKeys("selenium");
		driver.findElement(By.id("rbPermitIDLogin2")).click();
		driver.findElement(By.id("rbSendNotify2")).click();
		driver.findElement(By.id("chkReferenceDisable")).click();
		driver.findElement(By.id("txtOldPswd")).clear();
		driver.findElement(By.id("txtOldPswd")).sendKeys("abcd1234");
		driver.findElement(By.id("txtNewPswd")).clear();
		driver.findElement(By.id("txtNewPswd")).sendKeys("sele1234");
		driver.findElement(By.id("txtConfirmPswd")).clear();
		driver.findElement(By.id("txtConfirmPswd")).sendKeys("sele1234");
		driver.findElement(By.id("chk_mm_event")).click();
		takesScreenshot("C:\\x\\screenshot\\b.png");
		takesScreenshot("C:\\x\\screenshot\\アカウント設定変更2.png");
		// 設定をリセットし値が戻っている事をverify確認
		driver.findElement(By.id("ibtn_reset")).click();
		try{
			assertEquals("せれにうむ", driver.findElement(By.id("txtName")).getAttribute("value"));
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		try{
			assertTrue(driver.findElement(By.id("rbPermitIDLogin1")).isSelected());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		try{
			assertFalse(driver.findElement(By.id("rbPermitIDLogin2")).isSelected());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		try{
			assertTrue(driver.findElement(By.id("rbSendNotify1")).isSelected());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		try{
			assertFalse(driver.findElement(By.id("rbSendNotify2")).isSelected());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		try{
			assertFalse(driver.findElement(By.id("chkReferenceDisable")).isSelected());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		try{
			assertFalse(driver.findElement(By.id("chk_mm_event")).isSelected());
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		// 再度アカウント設定を変更
		driver.findElement(By.id("txtName")).clear();
		driver.findElement(By.id("txtName")).sendKeys("selenium");
		driver.findElement(By.id("rbPermitIDLogin2")).click();
		driver.findElement(By.id("rbSendNotify2")).click();
		driver.findElement(By.id("chkReferenceDisable")).click();
		driver.findElement(By.id("txtOldPswd")).clear();
		driver.findElement(By.id("txtOldPswd")).sendKeys("abcd1234");
		driver.findElement(By.id("txtNewPswd")).clear();
		driver.findElement(By.id("txtNewPswd")).sendKeys("sele1234");
		driver.findElement(By.id("txtConfirmPswd")).clear();
		driver.findElement(By.id("txtConfirmPswd")).sendKeys("sele1234");
		driver.findElement(By.id("chk_mm_event")).click();
		driver.findElement(By.id("ibtn_alteration")).click();
		driver.findElement(By.cssSelector("img[alt=\"変更する\"]")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.cssSelector("#cboxLoadedContent > div")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		// driver.findElement(By.id("btn_alteration")).click();
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		driver.findElement(By.id("cboxClose")).click();
		takesScreenshot("C:\\x\\screenshot\\アカウント変更後.png");
		driver.findElement(By.cssSelector("img[alt=\"ログアウト\"]")).click();
		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		// 変更前のID、パスワードでログインできない事を確認
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys("testselenium147258369@gmail.com");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("abcd1234");
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		try{
			assertTrue(driver.findElement(By.cssSelector("#cboxLoadedContent > div")).getText().matches("^ログインに失敗しました[\\s\\S]*$"));
		} catch(Error e){
			verificationErrors.append(e.toString());
		}
		takesScreenshot("C:\\x\\screenshot\\ログイン失敗.png");
		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		driver.findElement(By.id("cboxClose")).click();
		// 変更後のID、パスワードでログインできる事を確認
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys("testselenium147258369@gmail.com");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("sele1234");
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		takesScreenshot("C:\\x\\screenshot\\ログイン成功.png");
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		takesScreenshot("C:\\x\\screenshot\\アカウント変更後2.png");
		// アカウント設定の戻し作業
		driver.findElement(By.linkText("アカウント設定")).click();
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		driver.findElement(By.id("txtName")).clear();
		driver.findElement(By.id("txtName")).sendKeys("せれにうむ");
		driver.findElement(By.id("rbPermitIDLogin1")).click();
		driver.findElement(By.id("rbSendNotify1")).click();
		driver.findElement(By.id("chkReferenceDisable")).click();
		driver.findElement(By.id("txtOldPswd")).clear();
		driver.findElement(By.id("txtOldPswd")).sendKeys("sele1234");
		driver.findElement(By.id("txtNewPswd")).clear();
		driver.findElement(By.id("txtNewPswd")).sendKeys("abcd1234");
		driver.findElement(By.id("txtConfirmPswd")).clear();
		driver.findElement(By.id("txtConfirmPswd")).sendKeys("abcd1234");
		driver.findElement(By.id("chk_mm_event")).click();
		driver.findElement(By.id("ibtn_alteration")).click();
		driver.findElement(By.cssSelector("img[alt=\"変更する\"]")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.cssSelector("#cboxLoadedContent > div")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		// driver.findElement(By.id("btn_alteration")).click();
		assertEquals("ネットワークプリントサービス", driver.getTitle());
		driver.findElement(By.id("cboxClose")).click();
		driver.findElement(By.cssSelector("img[alt=\"ログアウト\"]")).click();
		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
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
