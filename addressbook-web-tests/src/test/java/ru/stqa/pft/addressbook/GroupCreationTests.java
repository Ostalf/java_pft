package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase  {

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
    }

    @Test
    public void testContactCreation() throws Exception {
        gotoContactCreationPage();
        fillContactCreationForm(new ContactData("Anna", "Yrevna", "Gusennik", "ann28@mail.ru", "Viluiskaya 3", "+79831303737"));
        submitContactCreation();
        returnToHomePage();
    }

}
