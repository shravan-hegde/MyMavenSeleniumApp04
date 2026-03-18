package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // ✅ IMPORTANT: Set correct geckodriver path
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

        // ✅ Headless mode (recommended in VM, remove if you want UI)
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--headless");

        WebDriver driver = new FirefoxDriver(options);

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // ---------------- TAB 1 ----------------
            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            System.out.println("SauceDemo login success");

            Thread.sleep(3000);

            // ---------------- TAB 2 ----------------
            driver.switchTo().newWindow(WindowType.TAB);

            driver.get("https://practicetestautomation.com/practice-test-login/");

            driver.findElement(By.id("username")).sendKeys("student");
            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("submit")).click();

            System.out.println("Practice Test login success");

            Thread.sleep(3000);

            // ---------------- TAB 3 ----------------
            driver.switchTo().newWindow(WindowType.TAB);

            driver.get("https://automationexercise.com/");

            Thread.sleep(3000);

            // ✅ Close popup safely
            try {
                WebElement popup = driver.findElement(By.xpath("//svg"));
                popup.click();
                System.out.println("Ad popup closed");
            } catch (Exception e) {
                System.out.println("No popup ad");
            }

            // ✅ Scroll + click product
            WebElement product = driver.findElement(By.cssSelector("a[data-product-id='1']"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", product);

            Thread.sleep(1000);

            product.click();

            System.out.println("Product added to cart");

            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
