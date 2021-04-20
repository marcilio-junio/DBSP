package testesCompra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class commandsUsed {

	// instanciando a classe webdriver
	static WebDriver driver;

	public void clickByXPath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void clickByClassName(String className) {
		driver.findElement(By.className(className)).click();
	}

	public void clickByID(String id) {
		driver.findElement(By.id(id)).click();
	}

	public void clickLinkByText(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}

	public void selectCombo(String id, String text) {
		clickByID(id);
		Select select = new Select(driver.findElement(By.id(id)));
		select.selectByVisibleText(text);
	}

	public void sendText(String id, String text) {
		driver.findElement(By.id(id)).sendKeys(text);
	}

	public boolean isElementPresentVisibleEnabled(By by) {
		Boolean retorno = true;
		try {
			driver.findElement(by);
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}
	
}
