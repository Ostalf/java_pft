package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Anna", "Yrevna", "Gusennik", "ann28@mail.ru", "Viluiskaya 3", "+79831303737"));
        }
        app.getNavigationHelper().gotoContactCreationPage();
        app.getNavigationHelper().returnToHomePage();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().switchToAlertAccept();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
