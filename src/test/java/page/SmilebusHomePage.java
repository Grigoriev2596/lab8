package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SmilebusHomePage {
    private static final String HOMEPAGE_URL = "https://618.by";
    private static final String LOCATION_INPUT_FROM_XPATH = "//*[@id=\"__layout\"]/div/div[2]/div[2]/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[2]/div/div[2]";
    private static final String LOCATION_INPUT_TO_XPATH = "//*[@id=\"__layout\"]/div/div[2]/div[2]/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div/div[2]";
    private static final String LOCATION_TO_RECHICA_XPATH = "//div[contains(@class, 'el-grid-col el-grid-col--span-12 el-grid-col--span-m-6 el-grid-col--span-l-3')][2]//span[contains(text(), 'Речица')]/parent::span";
    private static final String LOCATION_FROM_GOMEL_XPATH = "//div[contains(@class, 'el-grid-col el-grid-col--span-12 el-grid-col--span-m-6 el-grid-col--span-l-3')][1]//span[contains(text(), 'Гомель')]/parent::span";

    private WebDriver driver;

    public SmilebusHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public SmilebusHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public SmilebusHomePage openLocationFromForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOCATION_INPUT_FROM_XPATH))).click();
        return this;
    }

    public SmilebusHomePage chooseGomelStartLocation() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOCATION_FROM_GOMEL_XPATH))).click();
        return this;
    }

    public SmilebusHomePage openLocationToForm() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOCATION_INPUT_TO_XPATH))).click();
        return this;
    }

    public SmilebusHomePage chooseRechicaEndLocation() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(LOCATION_TO_RECHICA_XPATH))).click();
        return this;
    }

    public boolean checkRoutesFromGomelToRechica() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'search-page')][1]")));
        if (driver.findElement(By.xpath("//div[contains(text(), 'Рейсы не найдены')]")) != null)
            return true;

        boolean ifLocationsAreGomelAndRechica = true;

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='pt-item-tour']/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]")));
        List<WebElement> locationFromList = driver.findElements(By.xpath("//div[@class='pt-item-tour']/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='pt-item-tour']/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]")));
        List<WebElement> locationToList = driver.findElements(By.xpath("//div[@class='pt-item-tour']/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]"));
        for(int i = 0; i < locationFromList.size(); i++) {
            if (!locationFromList.get(i).getText().contains("Гомель") && !locationToList.get(i).getText().contains("Речица")){
                ifLocationsAreGomelAndRechica = false;
                break;
            }
        }
        return ifLocationsAreGomelAndRechica;
    }
}
