package sample.pageobject.DEMO;

import static org.hamcrest.CoreMatchers.*;
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

public class MyboxLayoutDEMO{
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
		//baseUrl = TestUtil.BASE_URL;
		baseUrl = "https://mikasa.sharp.co.jp:1443/";
	}

	@Test
	public void 登録ファイル確認_マイボックス表示_画像ファイル() throws Exception{
		// ログイン情報
		String txtId = TestUtil.USER_ID;
		String txtPw = TestUtil.USER_PASSWORD;
		// 使用するファイルパス
		String File1 = "C:\\x\\ファイル\\dog-jpg1.jpg";
		String File2 = "C:\\x\\ファイル\\dog-jpg2.jpg";
		String File3 = "C:\\x\\ファイル\\dog-jpg3.jpg";
		// キャプチャ
		String cap1 = "マイボックスレイアウト確認";
		// 使用量の判定(登録前)
		String Usable1 = "0MB";
		// 使用量の判定(登録後)
		String Usable2 = "0.4MB";
		// ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー
		// ログインする
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		assertEquals("ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷", driver.getTitle());
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys(txtId);
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys(txtPw);
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		// 遷移先の判定
		assertEquals(baseUrl + "sharp_netprint/ja/mypage.aspx", driver.getCurrentUrl());
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
		// 使用量の判定(登録前)
		assertEquals(Usable1, driver.findElement(By.id("lblUsableSpace")).getText());
		// マイボックスのレイアウト確認の為キャプチャを取得
		// ERROR: Caught exception [ERROR: Unsupported command [captureEntirePageScreenshot | C:\x\screenshot\${cap1}.png | ]]
		TestUtil.takesScreenshot(driver, cap1 + ".png");
		// 画像を登録するクリック
		driver.findElement(By.id("Img7")).click();
		// 画像ファイル登録1を登録
		driver.findElement(By.id("FileUpload")).clear();
		driver.findElement(By.id("FileUpload")).sendKeys(File1);
		driver.findElement(By.id("ibtnUpload")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.cssSelector("td.modalContent")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		// 続けて登録
		driver.findElement(By.id("Img12")).click();
		// 画像ファイル登録2を登録
		driver.findElement(By.id("FileUpload")).clear();
		driver.findElement(By.id("FileUpload")).sendKeys(File2);
		driver.findElement(By.id("ibtnUpload")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.cssSelector("td.modalContent")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		// 続けて登録
		driver.findElement(By.id("Img12")).click();
		// 画像ファイル登録3を登録
		driver.findElement(By.id("FileUpload")).clear();
		driver.findElement(By.id("FileUpload")).sendKeys(File3);
		driver.findElement(By.id("ibtnUpload")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.cssSelector("td.modalContent")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		// マイボックスに戻るクリック
		driver.findElement(By.id("ImgFinishJavaScript")).click();
		// 使用量の判定(登録後)
		assertEquals(Usable2, driver.findElement(By.id("lblUsableSpace")).getText());
		// ステータスの確認
		// ファイル3
		assertEquals("印刷できます", driver.findElement(By.xpath("//td[6]/span")).getText());
		// ファイル2
		assertEquals("印刷できます", driver.findElement(By.xpath("//tr[3]/td[6]/span")).getText());
		// ファイル1
		assertEquals("印刷できます", driver.findElement(By.xpath("//tr[4]/td[6]/span")).getText());
		// 各チェックボックスをONにする
		driver.findElement(By.xpath("//td/input")).click();
		// チェックの判定
		assertTrue(driver.findElement(By.xpath("//td/input")).isSelected());
		driver.findElement(By.xpath("//tr[3]/td/input")).click();
		// チェックの判定
		assertTrue(driver.findElement(By.xpath("//tr[3]/td/input")).isSelected());
		driver.findElement(By.xpath("//tr[4]/td/input")).click();
		// チェックの判定
		assertTrue(driver.findElement(By.xpath("//tr[4]/td/input")).isSelected());
		// 各チェックボックスOFFにする
		driver.findElement(By.xpath("//td/input")).click();
		// チェックの判定
		assertFalse(driver.findElement(By.xpath("//td/input")).isSelected());
		driver.findElement(By.xpath("//tr[3]/td/input")).click();
		// チェックの判定
		assertFalse(driver.findElement(By.xpath("//tr[3]/td/input")).isSelected());
		driver.findElement(By.xpath("//tr[4]/td/input")).click();
		// チェックの判定
		assertFalse(driver.findElement(By.xpath("//tr[4]/td/input")).isSelected());
		// [すべて選択]にて全ファイルをチェックONにする
		driver.findElement(By.id("ibtnSelectAllTop")).click();
		// チェックの判定
		assertTrue(driver.findElement(By.xpath("//td/input")).isSelected());
		// チェックの判定
		assertTrue(driver.findElement(By.xpath("//tr[3]/td/input")).isSelected());
		// チェックの判定
		assertTrue(driver.findElement(By.xpath("//tr[4]/td/input")).isSelected());
		// [すべて解除]にて全ファイルのチェックをOFFにする
		driver.findElement(By.id("ibtnSelectAllTop")).click();
		// チェックの判定(全ファイル)
		assertFalse(driver.findElement(By.xpath("//td/input")).isSelected());
		assertFalse(driver.findElement(By.xpath("//tr[3]/td/input")).isSelected());
		assertFalse(driver.findElement(By.xpath("//tr[4]/td/input")).isSelected());
		// チェックボックスON(３番目のファイル)
		driver.findElement(By.xpath("//td/input")).click();
		// 選択したファイルを削除(３番目のファイル)
		driver.findElement(By.id("Img12")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.xpath("//div[@id='modal']/table/tbody/tr[2]/td[2]")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("Img15")).click();
		// リスト最上部の登録名を判定(削除されている事)
		assertThat("dog-jpg3.jpg", is(not(driver.findElement(By.cssSelector("td.ListD > span")).getText())));
		// リスト最上部の登録名を判定(2番目のファイルがシフトしている事)
		assertEquals("dog-jpg2.jpg", driver.findElement(By.cssSelector("td.ListD > span")).getText());
		// すべて選択
		driver.findElement(By.id("ibtnSelectAllTop")).click();
		// 選択したファイルを削除(複数)
		driver.findElement(By.id("Img12")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.xpath("//div[@id='modal']/table/tbody/tr[2]/td[2]")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("Img15")).click();
		// リストにファイルが無い事の判定
		// assertFalse(isElementPresent(By.cssSelector("td.ListD > span")));
		assertEquals("リストにファイルが無い事の判定", 0, driver.findElements(By.cssSelector("td.ListD > span")).size());

		// ログアウトする
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
		driver.findElement(By.cssSelector("img[alt=\"ログアウト\"]")).click();
		// END
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
