package sample.pageobject.selenide;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

import sample.pageobject.NwpsWebUI.TopPage;

import com.codeborne.selenide.Configuration;

public class SelenideTest{

	@BeforeClass
	public static void beforeClass(){
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		Configuration.browser = BrowserType.CHROME;
	}

	@Test
	public void 個人情報の取り扱いについてを開く(){
		TopPage.open();
		TopPage.個人情報の取り扱いについてを開く();
		assertEquals("https://www.sharp-sbs.co.jp/contact/networkprint/", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void ご利用上の注意を開く(){
		TopPage.open();
		TopPage.ご利用上の注意を開く();
		assertEquals("https://cvs.so.sh.airfolc.co.jp/sharp_netprint/ja/notice.aspx", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void 個人情報保護方針を開く(){
		TopPage.open();
		TopPage.個人情報保護方針を開く();
		assertEquals("https://www.sharp-sbs.co.jp/privacypolicy/", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void サークルKサンクスを開く(){
		TopPage.open();
		TopPage.サークルKサンクスを開く();
		assertEquals("http://www.circleksunkus.jp/", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void セイコーマートを開く(){
		TopPage.open();
		TopPage.セイコーマートを開く();
		assertEquals("http://www.seicomart.co.jp/", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void セーブオンを開く(){
		TopPage.open();
		TopPage.セーブオンを開く();
		assertEquals("https://www.saveon.co.jp/", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void ファミリーマートを開く(){
		TopPage.open();
		TopPage.ファミリーマートを開く();
		assertEquals("http://www.family.co.jp/", TopPage.遷移先URL取得());
		TopPage.close();
	}

	@Test
	public void ローソンを開く(){
		TopPage.open();
		TopPage.ローソンを開く();
		assertEquals("http://www.lawson.co.jp/index.html", TopPage.遷移先URL取得());
		TopPage.close();
	}
}
