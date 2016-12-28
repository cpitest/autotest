package sample.potal.pageobject;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.WebDriverWrapper;

public class HowToPageTest{
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
	public void test() throws Exception{
		// ご利用方法/プリント料金画面から開始
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		driver.findElement(By.cssSelector("img[alt=\"ご利用方法 / プリント料金\"]")).click();

		// 新しく開かれたタブに切り替え
		String currentWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		windowHandles.remove(currentWindowHandle);
		driver.switchTo().window(windowHandles.iterator().next());

		driver.findElement(By.cssSelector("img[alt=\"詳しくはこちら\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"文書プリントの登録方法\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"img_ja/btn_howto_regist_photo_a.jpg\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		// コンビニエンスストアで印刷
		driver.findElement(By.cssSelector("img[alt=\"文書プリントの印刷方法\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[2]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[3]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[4]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='文書プリントの印刷方法'])[5]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"画像プリントの印刷方法\"]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='画像プリントの印刷方法'])[2]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.xpath("(//img[@alt='画像プリントの印刷方法'])[3]")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		// プリント料金
		driver.findElement(By.linkText("■サークルＫ・サンクス")).click();
		driver.findElement(By.id("Img1")).click();
		driver.findElement(By.linkText("■セイコーマート")).click();
		driver.findElement(By.id("Img1")).click();
		driver.findElement(By.linkText("■セーブオン")).click();
		driver.findElement(By.cssSelector("img[alt=\"もどる\"]")).click();
		driver.findElement(By.linkText("■ファミリーマート")).click();
		driver.findElement(By.id("Img1")).click();
		driver.findElement(By.linkText("■ローソン")).click();
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
