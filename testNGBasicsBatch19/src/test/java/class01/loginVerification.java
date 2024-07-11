package class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class loginVerification {
    public static WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }
    @Test
    public void loginButtonIsDisplayed(){
        WebElement button= driver.findElement(By.xpath("//input[@id='btnLogin']"));
        Assert.assertTrue(button.isDisplayed());
    }
    @Test
    public void loginButtonIsEnabled(){
        WebElement button= driver.findElement(By.xpath("//input[@id='btnLogin']"));
        Assert.assertTrue(button.isEnabled());
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
