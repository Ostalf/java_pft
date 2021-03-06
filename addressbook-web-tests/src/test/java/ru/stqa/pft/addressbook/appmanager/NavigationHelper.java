package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void contactCreationPage() {
        click(By.linkText("add new"));
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void homePage() {
        click(By.linkText("home"));
    }
}
