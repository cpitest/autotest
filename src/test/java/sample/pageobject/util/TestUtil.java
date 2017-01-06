package sample.pageobject.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;

public class TestUtil{
	/** ログインの実行結果 */
	public enum LoginResult{
		SUCCESS, // 成功
		URL_NG, // NWPSのURLが正しくない
		LOGIN_FAILED, // ログイン失敗
		TIMEOUT // タイムアウト
	}

	/** NWPSのURL */
	public static final String BASE_URL = "https://cvs.so.sh.airfolc.co.jp/";

	/** テストユーザーのID */
	public static final String USER_ID = "testselenium147258369@gmail.com";

	/** テストユーザーのパスワード */
	public static final String USER_PASSWORD = "abcd1234";

	/** キャプチャを出力する先のディレクトリのパス */
	private static final String SCREENSHOT_OUTPUT_PATH = "C:\\x\\screenshot\\";

	/**
	 * 一番新しく開かれたウィンドウにフォーカスする
	 *
	 * @param driver
	 * @param current_window_id
	 *            現在フォーカスされているウィンドウのID
	 * @return 一番新しく開かれたウィンドウのID
	 */
	public static String focusByNewWindow(WebDriver driver, String current_window_id){
		// 開いたウインドウも含め全部のウインドウIDを取得する
		java.util.Set<String> window_ids = driver.getWindowHandles();
		String new_window_id = TestUtil.getNewWindowHandle(window_ids, current_window_id);
		// 最後に格納したウインドウIDにスイッチ
		driver.switchTo().window(new_window_id);
		return new_window_id;
	}

	/**
	 * 一番新しく開かれたウィンドウのIDを返す
	 *
	 * @param window_ids
	 * @param current_window_id
	 * @return
	 */
	public static String getNewWindowHandle(Set<String> window_ids, String current_window_id){
		List<String> listWindowId = new ArrayList<String>();
		for(String id : window_ids){
			if(!id.equals(current_window_id)){
				// 現在とは異なるウィンドウID
				listWindowId.add(id);
			}
		}

		if(listWindowId.size() == 0){
			return null;
		}

		// 末尾のものを返す
		return listWindowId.get(listWindowId.size() - 1);
	}

	/**
	 * 対象画面のキャプチャを出力する
	 *
	 * @param driver
	 * @param outPutFileName
	 *            出力するキャプチャのファイル名
	 * @throws WebDriverException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void takesScreenshot(WebDriver driver, String outPutFileName) throws WebDriverException, IOException, InterruptedException{

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

		ImageIO.write(img, "png", new File(SCREENSHOT_OUTPUT_PATH + outPutFileName));
	}

	/**
	 * NWPSにログインする
	 *
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public static LoginResult login(WebDriver driver) throws InterruptedException{
		// ログインする
		driver.get(BASE_URL + "/sharp_netprint/ja/top.aspx");
		if(!"ネットワークプリント｜パソコン・スマホから登録、コンビニで印刷".equals(driver.getTitle())){
			return LoginResult.URL_NG;
		}
		driver.findElement(By.id("txtId")).clear();
		driver.findElement(By.id("txtId")).sendKeys(USER_ID);
		driver.findElement(By.id("txtPw")).clear();
		driver.findElement(By.id("txtPw")).sendKeys(USER_PASSWORD);
		driver.findElement(By.cssSelector("img[alt=\"ログイン\"]")).click();
		if(!"ネットワークプリントサービス".equals(driver.getTitle())){
			return LoginResult.LOGIN_FAILED;
		}
		// POPUP閉じる
		for(int second = 0;; second++){
			if(second >= 60)
				return LoginResult.TIMEOUT;
			try{
				if(driver.findElement(By.cssSelector("div.LinkArea")).isDisplayed())
					break;
			} catch(Exception e){
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.cssSelector("img[alt=\"閉じる\"]")).click();
		return LoginResult.SUCCESS;
	}
}
