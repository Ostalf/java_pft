package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }
    public void switchToAlertAccept(){
        wd.switchTo().alert().accept();
    }
    protected boolean isElementPresents(By locator) {
        try {
            wd .findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
