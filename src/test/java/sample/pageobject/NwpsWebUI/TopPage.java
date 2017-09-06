package sample.pageobject.NwpsWebUI;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;

import sample.pageobject.util.TestUtil;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class TopPage{
	public static void open(){
		Selenide.open(TestUtil.BASE_URL);
	}

	public static void close(){
		Selenide.close();
	}

	public static void 個人情報の取り扱いについてを開く(){
		$(By.linkText("個人情報の取り扱いについて")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("kojinzyouhoutori1.png");
	}

	public static void ご利用上の注意を開く(){
		$(By.linkText("ご利用上の注意")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("goriyouzyou1.png");
	}

	public static void 個人情報保護方針を開く(){
		$(By.linkText("個人情報保護方針")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("kojinzyouhouhogo1.png");
	}

	public static void サークルKサンクスを開く(){
		$(By.xpath("//img[@alt='サークルＫ・サンクス']")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("convini_1.png");
	}

	public static void セイコーマートを開く(){
		$(By.xpath("//img[@alt='セイコーマート']")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("convini_2.png");
	}

	public static void セーブオンを開く(){
		$(By.xpath("//img[@alt='セーブオン']")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("convini_3.png");
	}

	public static void ファミリーマートを開く(){
		$(By.xpath("//img[@alt='ファミリーマート']")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("convini_4.png");
	}

	public static void ローソンを開く(){
		$(By.xpath("//img[@alt='ローソン']")).click();
		TestUtil.focusByNewWindow(WebDriverRunner.getWebDriver(), WebDriverRunner.getWebDriver().getWindowHandle());
		Selenide.screenshot("convini_5.png");
	}

	public static String 遷移先URL取得(){
		return WebDriverRunner.currentFrameUrl();
	}
}
