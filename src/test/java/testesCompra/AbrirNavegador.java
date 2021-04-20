package testesCompra;

import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AbrirNavegador extends commandsUsed{

	@BeforeClass
	public static void setUpBeforeClassFirefox() throws Exception {
		System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php?");
	}
	
	//@BeforeClass
	//public static void setUpBeforeClassChrome() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		//driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");
	//}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}		

}
