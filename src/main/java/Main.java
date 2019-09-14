import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String baseURL = "http://facebook.com";
    public static final String emailXPath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/input[1]";
    public static final String passwordXPath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]";
    public static final String loginXPath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[3]/label[1]/input[1]";
    public static final String searchXPath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[2]";
    public static final String pageXPath = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[7]/a[1]/div[1]";
    public static final String searchElementsXPath = "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[3]/div[2]";


    public static void main(String[] args){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseURL);
        WebElement email = driver.findElement(By.xpath(emailXPath));
        WebElement password = driver.findElement(By.xpath(passwordXPath));
        WebElement loginButton = driver.findElement(By.xpath(loginXPath));

        email.sendKeys("***@***.***");
        password.sendKeys("*******");
        loginButton.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement search = driver.findElement(By.xpath(searchXPath));
        search.sendKeys("Search String");
        search.sendKeys(Keys.ENTER);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement page = driver.findElement(By.xpath(pageXPath));
        page.click();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> searchResults = driver.findElements(By.className("_32mo"));
        for (WebElement a :searchResults) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .click(a)
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();

        }

        //driver.quit();
        System.exit(0);
    }
}
