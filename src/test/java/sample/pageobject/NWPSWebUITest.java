package sample.pageobject;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import sample.pageobject.util.TestUtil;
import selenium.WebDriverWrapper;

public class NWPSWebUITest{
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

		waitForPopUp = new WebDriverWait(driver, 120);
		// System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		// capabilities.setCapability("marionette", true);
		// driver = new FirefoxDriver(capabilities);

		baseUrl = "https://cvs.so.sh.airfolc.co.jp/";

		System.out.println("start NWPS test");

		// ログイン
		driver.get(baseUrl + "/sharp_netprint/ja/top.aspx");
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys("nakamura.hajime@sharp.co.jp");
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys("1111aaaa");
		driver.findElement(By.id("chkSaveId")).click();
		driver.findElement(By.id("chkSavePw")).click();
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt=\"閉じる\"]")));
		driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
	}

	@Test
	public void 画像登録フロー() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/mypage.aspx");
		driver.findElement(By.id("Img7")).click();
		TestUtil.takesScreenshot(driver, "画像登録フロー1_登録画面.jpg");
		driver.findElement(By.id("ImgBkMyBox")).click();
		TestUtil.takesScreenshot(driver, "画像登録フロー2_登録画面からマイボックスに戻る.jpg");

		driver.findElement(By.id("Img7")).click();
		// ↓jpgファイルを登録
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\6200x4100.jpg");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\image1.jpg");
		driver.findElement(By.id("ibtnUpload")).click();

		TestUtil.takesScreenshot(driver, "画像登録フロー3_登録完了ダイアログ.jpg");
		driver.findElement(By.id("Img12")).click();
		TestUtil.takesScreenshot(driver, "画像登録フロー4_続けてプリント押下後.jpg");
		// ↓jpegファイルを登録
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\6200x4101.jpeg");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\image2.jpeg");
		driver.findElement(By.id("ibtnUpload")).click();

		driver.findElement(By.id("Img12")).click();
		// ↓pngファイルを登録
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\6200x4100.png");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\image3.png");
		driver.findElement(By.id("ibtnUpload")).click();
		driver.findElement(By.id("ImgFinishJavaScript")).click();
		TestUtil.takesScreenshot(driver, "画像登録フロー5_登録完了ダイアログからマイボックスに戻る.jpg");
	}

	@Test
	public void 文書登録フロー() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/mypage.aspx");
		driver.findElement(By.id("Img6")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー1_登録画面に遷移.jpg");
		driver.findElement(By.id("ImgBkMyBox")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー2_登録画面からマイボックス画面に遷移.jpg");
		driver.findElement(By.id("Img6")).click();
		// pdfファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\用紙サイズごとのファイル作成中\\A4_50P.pdf");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\A4_50P.pdf");
		driver.findElement(By.id("ibtnUpload")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー3_PDFの登録ダイアログ（フィット）.jpg");

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
		TestUtil.takesScreenshot(driver, "文書登録フロー4_PDFの登録ダイアログ（原寸）.jpg");
		driver.findElement(By.id("modal_Upload_img")).click();
		// ↓登録完了ダイアログ
		TestUtil.takesScreenshot(driver, "文書登録フロー5_登録完了ダイアログ.jpg");

		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("Img9")));
		driver.findElement(By.id("Img9")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー6_続けて登録押下後.jpg");
		// pptxファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\dog-pptxP3 50.pptx");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\dog-pptxP3 50.pptx");
		driver.findElement(By.id("ibtnUpload")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー7_pptxの登録ダイアログ.jpg");
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
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("Img9")));
		driver.findElement(By.id("Img9")).click();
		// docxファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\ページ順確認[50P].docx");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\ページ順確認[50P].docx");
		driver.findElement(By.id("ibtnUpload")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー8_docxの登録ダイアログ.jpg");
		// ここからdocxファイルの設定
		driver.findElement(By.id("txtIptDataName")).clear();
		driver.findElement(By.id("txtIptDataName")).sendKeys("nnページ順確認[50P].docx");
		new Select(driver.findElement(By.id("ddlIptPaperSize"))).selectByVisibleText("A4");
		// ここまでdocxファイルの設定
		driver.findElement(By.id("modal_Upload_img")).click();
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("Img9")));
		driver.findElement(By.id("Img9")).click();
		// xlsxファイルを指定
		driver.findElement(By.id("FileUpload")).clear();
		// driver.findElement(By.id("FileUpload")).sendKeys("C:\\Users\\lx12080225\\Desktop\\ファイル\\用紙サイズごとのファイル作成中\\B5_50P(全シート用_1シート10P).xlsx");
		driver.findElement(By.id("FileUpload")).sendKeys("C:\\x\\ファイル\\B5_50P(全シート用_1シート10P).xlsx");
		driver.findElement(By.id("ibtnUpload")).click();
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("Img12")));
		driver.findElement(By.id("Img12")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー9_登録ダイアログのキャンセル押下後.jpg");
		driver.findElement(By.id("ibtnUpload")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー10_xlsxの登録ダイアログ.jpg");
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

		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("ImgFinishJavaScript")));
		driver.findElement(By.id("ImgFinishJavaScript")).click();
		TestUtil.takesScreenshot(driver, "文書登録フロー11_登録完了ダイアログからマイボックスへ戻る.jpg");
	}

	@Test
	public void 文書のデータ詳細確認() throws Exception{
		driver.get(baseUrl + "/sharp_netprint/ja/mypage.aspx");
		driver.findElement(By.xpath("(//img[@alt='詳細'])[4]")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認1_文書の詳細画面.jpg");
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("ibtnChgDocumentName")));
		driver.findElement(By.id("ibtnChgDocumentName")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認2_登録ダイアログ.jpg");
		// ここからpdf変更内容
		driver.findElement(By.id("txtIptDataName")).clear();
		driver.findElement(By.id("txtIptDataName")).sendKeys("B");
		new Select(driver.findElement(By.id("ddlIptPaperSize"))).selectByVisibleText("A4");
		driver.findElement(By.id("rbIptPrintPageAll")).click();
		new Select(driver.findElement(By.id("ddlIptFitToPage"))).selectByVisibleText("用紙に合わせて印刷");
		// ここまでpdf変更内容
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認4_登録ダイアログで設定内容入力後.jpg");
		driver.findElement(By.id("modal_Upload_img")).click();

		// driver.findElement(By.id("btnChange")).click();
		// ↓ステータスが「印刷できます」になるまで待機。とりあえずpauseコマンドを使用。

		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認5_登録完了かつ展開完了後.jpg");
		waitForPopUp.until(ExpectedConditions.textToBe(By.id("lblStatus"), "印刷できます"));

		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("ibtnChgPaperSize")));
		driver.findElement(By.id("ibtnChgPaperSize")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認6_用紙サイズの変更押下.jpg");
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("Img12")));
		driver.findElement(By.id("Img12")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認7_編集ダイアログのキャンセル押下.jpg");
		driver.findElement(By.id("ibtnChgPrintPage")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認8_ページ指定の変更押下.jpg");
		waitForPopUp.until(ExpectedConditions.visibilityOfElementLocated(By.id("Img12")));
		driver.findElement(By.id("Img12")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null");
		driver.findElement(By.id("imgPage1")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認9_1ページ目のプレビュー表示.jpg");
		driver.findElement(By.id("cboxClose")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認10_プレビューの閉じる押下.jpg");

		// 押せないので除外
		// driver.findElement(By.id("imgPage1")).click();
		// driver.findElement(By.id("cboxOverlay")).click();

		driver.findElement(By.id("WebPageTop_lbtn_5")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認11_ページ5に遷移.jpg");
		driver.findElement(By.id("imgPage41")).click();

		// 押せないので除外して、代わりにクローズボタン押下
		// driver.findElement(By.id("cboxOverlay")).click();
		driver.findElement(By.id("cboxClose")).click();

		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認12_プレビューの枠外押下.jpg");
		driver.findElement(By.id("ImgBkMyBox")).click();
		TestUtil.takesScreenshot(driver, "文書のデータ詳細確認13_編集画面からマイボックスに戻る.jpg");
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
}
