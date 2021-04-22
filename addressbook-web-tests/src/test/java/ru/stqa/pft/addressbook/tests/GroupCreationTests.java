package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactCreationPage();
        app.fillContactCreationForm(new ContactData("Anna", "Yrevna", "Gusennik", "ann28@mail.ru", "Viluiskaya 3", "+79831303737"));
        app.submitContactCreation();
        app.returnToHomePage();
    }

}
