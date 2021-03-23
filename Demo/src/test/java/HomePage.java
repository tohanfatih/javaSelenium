import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class HomePage{

    @Test
    public static void testCase() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ozdileto\\Desktop\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.hepsiburada.com/");

        String actualHomePageUrl = driver.getCurrentUrl();
        String expectedHomePageUrl = "https://www.hepsiburada.com/";

        Reporter.log("Will check homepage url", true);
        Assert.assertEquals(actualHomePageUrl, expectedHomePageUrl, "Unmatched");

        driver.findElement(By.xpath("//div[@id='myAccount']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@id='login']")).click();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains("LOGIN")) {
            System.out.println("Login page loaded successfully");
        } else {
            System.out.println("Login page does not loaded successfully");
        }

        driver.findElement(By.xpath("//input[@id='txtUserName']")).sendKeys("tohanfatih@hotmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Tohan123");
        driver.findElement(By.xpath("//button[@id='btnLogin']")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys("samsung");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='desktopOldAutosuggestTheme-input']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);

        if (driver.getCurrentUrl().endsWith("samsung") == true) {
            System.out.println("Searching 'samsung' keyword works properly");
        } else {
            System.out.println("Searching 'samsung' keyword does not works properly!");
        }

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class='page-2 ']")).click();

        if (driver.getCurrentUrl().endsWith("2") == true) {
            System.out.println("Second page loaded successfully");
        } else {
            System.out.println("Second page does not loaded successfully!");
        }
        driver.findElement(By.xpath("(//div[@class='product-detail'])[3]")).click();
        Thread.sleep(3000);
        String FavProduct = driver.findElement(By.xpath("//h1[@id='product-name']")).getText();
        System.out.println("Printing " + FavProduct);
        driver.findElement(By.xpath("//div[@class='Like-3B4x5']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='myAccount']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@title='Beğendiklerim']")).click();
        Thread.sleep(5000);

        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("(//div[@class='product-item '])[1]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("(//div[@class='product-item '])[1]"))).click().build().perform();
        Thread.sleep(5000);

        String FavProductOnAccount = driver.findElement(By.xpath("//h1[@id='product-name']")).getText();
        System.out.println("Printing " + FavProductOnAccount);
        Thread.sleep(5000);
        if (FavProduct.equals(FavProductOnAccount)) {
            System.out.println("Product added like page succesfully");
        } else {
            System.out.println("Product does not added like page succesfully!");
        }

        driver.findElement(By.xpath("//div[@class='Like-3B4x5 Like-3UU_x']")).click();

        driver.findElement(By.xpath("//div[@id='myAccount']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@title='Beğendiklerim']")).click();

        if (FavProduct.equals(FavProductOnAccount)) {
            System.out.println("Product does not deleted on like page succesfully!");
        } else {
            System.out.println("Product deleted on like page succesfully");
        }

        driver.close();
    }
}
