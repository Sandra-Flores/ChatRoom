package edu.udacity.java.nano.it;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;


public class ChatServerIT {

    @Test
    public void testLoadChatroom() {
        WebDriver driver = new SafariDriver();

        driver.navigate().to("http://localhost:8080");

        System.out.println("Successfully opened chatroom");

        // Close the driver
        driver.quit();

    }

    @Test
    public void testUserLogin() throws InterruptedException {
        WebDriver driver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(driver , 10); // seconds

        driver.navigate().to("http://localhost:8080");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("Dan");

        WebElement loginbtn = driver.findElement(By.id("loginBtn"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));
        loginbtn.submit();

        driver.navigate().to("http://localhost:8080/index?username=Dan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

        System.out.println("Successfully joined chatroom");

        }

    @Test
    public void testUserSendMessage() throws InterruptedException {
        WebDriver driver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(driver , 10); // seconds

        driver.navigate().to("http://localhost:8080");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("Sandra");

        WebElement loginbtn = driver.findElement(By.id("loginBtn"));
        loginbtn.submit();

        driver.navigate().to("http://localhost:8080/index?username=Sandra");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));

        WebElement message = driver.findElement(By.id("msg"));
        message.sendKeys("Hello world! This is a test.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendBtn")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clearBtn")));
        driver.findElement(By.id("sendBtn"));


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message-container")));


        System.out.println("Successfully sent Message");

        // Close the driver
        driver.quit();
    }

    @Test
    public void testUserLeave() throws InterruptedException {
        WebDriver driver = new SafariDriver();
        WebDriverWait wait = new WebDriverWait(driver , 10); // seconds

        driver.navigate().to("http://localhost:8080");

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("Carlos");

        WebElement loginbtn = driver.findElement(By.id("loginBtn"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));
        loginbtn.submit();

        driver.navigate().to("http://localhost:8080/index?username=Carlos");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exit")));
        driver.findElement(By.id("exit")).click();

        System.out.println("Successfully left the chatroom");

        // Close the driver
        driver.quit();
    }


}

