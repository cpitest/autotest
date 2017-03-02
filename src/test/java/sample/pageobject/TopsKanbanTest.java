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

		baseUrl = TestUtil.BASE_URL;
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
		TestUtil.focusByNewWindow(driver, current_window_id);
		TestUtil.takesScreenshot(driver, "kanban4-2.png");
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

	@After
	public void tearDown() throws Exception{
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if(!"".equals(verificationErrorString)){
			fail(verificationErrorString);
		}
	}
}
