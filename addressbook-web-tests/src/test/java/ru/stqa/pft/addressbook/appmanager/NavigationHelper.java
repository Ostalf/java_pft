package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {
    public NavigationHelper(WebDriver wd) {
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

    public void returnToHomePage() {
        click(By.linkText("home"));
    }
}
