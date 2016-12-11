package sample.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class MyWebElement implements WebElement{
	private WebElement element;

	@Override
	public void click(){
		element.click();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException{
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void submit(){
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void sendKeys(CharSequence... keysToSend){
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void clear(){
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getTagName(){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getAttribute(String name){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean isSelected(){
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isEnabled(){
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public String getText(){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public List<WebElement> findElements(By by){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public WebElement findElement(By by){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean isDisplayed(){
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Point getLocation(){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Dimension getSize(){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Rectangle getRect(){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getCssValue(String propertyName){
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
