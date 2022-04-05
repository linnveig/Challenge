import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrontTests {

    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
    }

    @Test
    public void testSearch(){

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("michael jordan");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("michael jordan at DuckDuckGo", driver.getTitle());
    }

    @Test
    public void testImage(){

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("michael jordan");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("michael jordan at DuckDuckGo", driver.getTitle());

        WebElement image = driver.findElement(By.className("module--about__img"));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void wikiResult(){

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("michael jordan");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("michael jordan at DuckDuckGo", driver.getTitle());

        WebElement wikiLink = driver.findElement(By.partialLinkText("wikipedia"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void nbaResult(){

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("michael jordan");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("michael jordan at DuckDuckGo", driver.getTitle());

        WebElement nbaLink = driver.findElement(By.partialLinkText("nba.com"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void changeAndVerifyATheme(){

        WebElement menu = driver.findElement(By.xpath("//div/div/div/a"));
        menu.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement themeSettings = driver.findElement(By.partialLinkText("Themes"));
        themeSettings.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement themeTerminal = driver.findElement(By.xpath("//div[6]"));
        themeTerminal.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement backgroundColor = driver.findElement(By.id("pg-settings"));
        backgroundColor.getCssValue("background-color");
        assertTrue(backgroundColor.equals("#222222"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement saveButton = driver.findElement(By.partialLinkText("Save"));
        saveButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test //verifyTheme, method 2
    public void verifyTheme(){

        WebElement menu = driver.findElement(By.xpath("//div/div/div/a"));
        menu.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement themeSettings = driver.findElement(By.partialLinkText("Themes"));
        themeSettings.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement themeTerminal = driver.findElement(By.xpath("//div[6]"));
        themeTerminal.isSelected();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void changeLanguage(){

        WebElement menu = driver.findElement(By.xpath("//div/div/div/a"));
        menu.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement themeSettings = driver.findElement(By.partialLinkText("All"));
        themeSettings.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Select languageDropDownList = new Select(driver.findElement(By.id("setting_kad")));
        languageDropDownList.selectByVisibleText("Esperanto");

        WebElement label = driver.findElement(By.xpath("//form/div[2]/p"));
        assertEquals("Lingvo", label.getText());

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
