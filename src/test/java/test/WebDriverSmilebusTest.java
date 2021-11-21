package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.SmilebusHomePage;

public class WebDriverSmilebusTest {
    private WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        options.add_experimental_option("excludeSwitches", ["enable-automation"])
        options.add_experimental_option('useAutomationExtension', False)
        options.add_argument("--disable-blink-features=AutomationControlled")
        driver = new ChromeDriver(options);
    }
    @Test
    public void checkSearchResult() throws InterruptedException {
        boolean expected = new SmilebusHomePage(driver)
                 .openPage()
                 .openLocationFromForm()
                 .openLocationToForm()
                 .chooseGomelStartLocation()
                 .chooseRechicaEndLocation()
                 .checkRoutesFromGomelToRechica();
        Assert.assertTrue(expected);
    }
    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
