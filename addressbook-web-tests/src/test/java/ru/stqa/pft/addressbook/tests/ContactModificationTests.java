package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().gotoContactModificationPage();
        app.getContactHelper().fillContactCreationForm(new ContactData("Anna", "Yrevna", "Gusennik", "ann28@mail.ru", "Viluiskaya 3", "+79831303737"));
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
    }
}
