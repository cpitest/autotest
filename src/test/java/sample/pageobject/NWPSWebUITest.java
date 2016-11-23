package sample.pageobject;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class NWPSWebUITest{
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();

		// System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		// capabilities.setCapability("marionette", true);
		// driver = new FirefoxDriver(capabilities);

		baseUrl = "https://cvs.so.sh.airfolc.co.jp/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("start NWPS test");

		// ログイン
		// driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		// driver.findElement(By.id("txtId")).clear();
		// driver.findElement(By.id("txtId")).sendKeys("nakamura.hajime@sharp.co.jp");
		// driver.findElement(By.id("txtPw")).clear();
		// driver.findElement(By.id("txtPw")).sendKeys("2222aaaa");
		// driver.findElement(By.id("chkSaveId")).click();
		// driver.findElement(By.id("chkSavePw")).click();
		// driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		// Thread.sleep(3000);
		// driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
	}

	public void 文書登録フロー() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/mypage.aspx");
		driver.findElement(By.id("Img6")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー1_登録画面に遷移.jpg");
		driver.findElement(By.id("ImgBkMyBox")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー2_登録画面からマイボックス画面に遷移.jpg");

		Thread.sleep(3000);
		driver.findElement(By.id("Img6")).click();
		// pdfファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\用紙サイズごとのファイル作成中\\A4_50P.pdf");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\A4_50P.pdf");
		driver.findElement(By.id("ibtnUpload")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー3_PDFの登録ダイアログ（フィット）.jpg");

		// ここからPDFファイルの設定
		driver.findElement(By.id("txtIptDataName")).clear();
		driver.findElement(By.id("txtIptDataName")).sendKeys("A4_50P");
		new Select(driver.findElement(By.id("ddlIptPaperSize"))).selectByVisibleText("A3");
		new Select(driver.findElement(By.id("ddlIptFitToPage"))).selectByVisibleText("原寸で印刷");
		driver.findElement(By.id("rbIptPrintPageSelect")).click();
		driver.findElement(By.xpath("//div[@id='modalForm']/table/tbody/tr[2]/td[2]/table/tbody/tr[7]/td[3]/table/tbody/tr[2]/td/label")).click();
		driver.findElement(By.id("txtIptPrintStartPage")).clear();
		driver.findElement(By.id("txtIptPrintStartPage")).sendKeys("1");
		driver.findElement(By.id("txtIptPrintEndPage")).clear();
		driver.findElement(By.id("txtIptPrintEndPage")).sendKeys("30");
		// ここまでpdfファイルの設定
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー4_PDFの登録ダイアログ（原寸）.jpg");

		driver.findElement(By.id("modal_Upload_img")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("Img9")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		// ↓登録完了ダイアログ
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー5_登録完了ダイアログ.jpg");
		driver.findElement(By.id("Img9")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー6_続けて登録押下後.jpg");
		// pptxファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\dog-pptxP3 50.pptx");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\dog-pptxP3 50.pptx");
		driver.findElement(By.id("ibtnUpload")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー7_pptxの登録ダイアログ.jpg");
		// ここからpptxファイルの設定
		driver.findElement(By.id("txtIptDataName")).clear();
		driver.findElement(By.id("txtIptDataName")).sendKeys("aadog-pptxP3 50.pptx");
		new Select(driver.findElement(By.id("ddlIptPaperSize"))).selectByVisibleText("B4");
		driver.findElement(By.id("rbIptPrintPageSelect")).click();
		driver.findElement(By.xpath("//div[@id='modalForm']/table/tbody/tr[2]/td[2]/table/tbody/tr[7]/td[3]/table/tbody/tr[2]/td/label")).click();
		driver.findElement(By.id("txtIptPrintStartPage")).clear();
		driver.findElement(By.id("txtIptPrintStartPage")).sendKeys("2");
		driver.findElement(By.id("txtIptPrintEndPage")).clear();
		driver.findElement(By.id("txtIptPrintEndPage")).sendKeys("2");
		// ここまでpptxファイルの設定
		driver.findElement(By.id("modal_Upload_img")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("Img9")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.id("Img9")).click();
		// docxファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\ページ順確認[50P].docx");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\ページ順確認[50P].docx");
		driver.findElement(By.id("ibtnUpload")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー8_docxの登録ダイアログ.jpg");
		// ここからdocxファイルの設定
		driver.findElement(By.id("txtIptDataName")).clear();
		driver.findElement(By.id("txtIptDataName")).sendKeys("nnページ順確認[50P].docx");
		new Select(driver.findElement(By.id("ddlIptPaperSize"))).selectByVisibleText("A4");
		// ここまでdocxファイルの設定
		driver.findElement(By.id("modal_Upload_img")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("Img9")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.id("Img9")).click();
		// xlsxファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\用紙サイズごとのファイル作成中\\B5_50P(全シート用_1シート10P).xlsx");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\B5_50P(全シート用_1シート10P).xlsx");
		driver.findElement(By.id("ibtnUpload")).click();
		driver.findElement(By.id("Img12")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー9_登録ダイアログのキャンセル押下後.jpg");
		driver.findElement(By.id("ibtnUpload")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー10_xlsxの登録ダイアログ.jpg");
		// ここからxlsxファイルの設定
		driver.findElement(By.id("txtIptDataName")).clear();
		driver.findElement(By.id("txtIptDataName")).sendKeys("B5_50P(全シート用_1シート10P)");
		new Select(driver.findElement(By.id("ddlIptPrintSheet"))).selectByVisibleText("全シート");
		new Select(driver.findElement(By.id("ddlIptPaperSize"))).selectByVisibleText("B5");
		driver.findElement(By.id("rbIptPrintPageSelect")).click();
		driver.findElement(By.xpath("//div[@id='modalForm']/table/tbody/tr[2]/td[2]/table/tbody/tr[7]/td[3]/table/tbody/tr[2]/td/label")).click();
		driver.findElement(By.id("txtIptPrintStartPage")).clear();
		driver.findElement(By.id("txtIptPrintStartPage")).sendKeys("1");
		driver.findElement(By.id("txtIptPrintEndPage")).clear();
		driver.findElement(By.id("txtIptPrintEndPage")).sendKeys("50");
		// ここまで設定
		driver.findElement(By.id("modal_Upload_img")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("ImgFinishJavaScript")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("ImgFinishJavaScript")).click();
		takesScreenshot("C:\\x\\screenshot\\文書登録フロー11_登録完了ダイアログからマイボックスへ戻る.jpg");
	}

	public void 画像登録フロー() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/mypage.aspx");
		driver.findElement(By.id("Img7")).click();
		takesScreenshot("C:\\x\\screenshot\\画像登録フロー1_登録画面.jpg");
		driver.findElement(By.id("ImgBkMyBox")).click();
		takesScreenshot("C:\\x\\screenshot\\画像登録フロー2_登録画面からマイボックスに戻る.jpg");

		Thread.sleep(3000);
		driver.findElement(By.id("Img7")).click();
		// ↓jpgファイルを登録
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\6200x4100.jpg");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\image1.jpg");
		driver.findElement(By.id("ibtnUpload")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("Img12")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		takesScreenshot("C:\\x\\screenshot\\画像登録フロー3_登録完了ダイアログ.jpg");
		driver.findElement(By.id("Img12")).click();
		takesScreenshot("C:\\x\\screenshot\\画像登録フロー4_続けてプリント押下後.jpg");
		// ↓jpegファイルを登録
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\6200x4101.jpeg");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\image2.jpeg");
		driver.findElement(By.id("ibtnUpload")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("Img12")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("Img12")).click();
		// ↓pngファイルを登録
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\6200x4100.png");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\image3.png");
		driver.findElement(By.id("ibtnUpload")).click();
		for(int second = 0;; second++){
			if(second >= 60)
				fail("timeout");
			try{
				if(driver.findElement(By.id("ImgFinishJavaScript")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.id("ImgFinishJavaScript")).click();
		takesScreenshot("C:\\x\\screenshot\\画像登録フロー5_登録完了ダイアログからマイボックスに戻る.jpg");
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
		Thread.sleep(3000);
		//
		// TakesScreenshot ts = (TakesScreenshot)new Augmenter().augment(driver);
		//
		// // JS実行用のExecuter
		// JavascriptExecutor jexec = (JavascriptExecutor)driver;
		//
		// // 画面サイズで必要なものを取得
		// int innerH = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerHeight")));
		// int innerW = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerWidth")));
		// int scrollH = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollHeight")));
		//
		// // イメージを扱うための準備
		// BufferedImage img = new BufferedImage(innerW, scrollH, BufferedImage.TYPE_INT_ARGB);
		// Graphics g = img.getGraphics();
		//
		// // スクロールを行うかの判定
		// if(innerH > scrollH){
		// BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		// g.drawImage(imageParts, 0, 0, null);
		// } else{
		// int scrollableH = scrollH;
		// int i = 0;
		//
		// // スクロールしながらなんどもイメージを結合していく
		// while(scrollableH > innerH){
		// BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		// g.drawImage(imageParts, 0, innerH * i, null);
		// scrollableH = scrollableH - innerH;
		// i++;
		// jexec.executeScript("window.scrollTo(0," + innerH * i + ")");
		// }
		//
		// // 一番下まで行ったときは、下から埋めるように貼り付け
		// BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		// g.drawImage(imageParts, 0, scrollH - innerH, null);
		// }
		//
		// ImageIO.write(img, "png", new File(path));
	}
}
