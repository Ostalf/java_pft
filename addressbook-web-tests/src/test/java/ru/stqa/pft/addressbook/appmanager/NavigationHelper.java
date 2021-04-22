package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {
    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoContactCreationPage() {
        click(By.linkText("add new"));
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoContactModificationPage() {
        click(By.xpath("//img[@alt='Edit']"));
    }
}
