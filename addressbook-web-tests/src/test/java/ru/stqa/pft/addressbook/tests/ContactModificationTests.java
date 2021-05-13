package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoContactCreationPage();
            app.getContactHelper().createContact(new ContactData("null", "null", "null", "null", "null", "null"));
        }
        app.getNavigationHelper().returnToHomePage();

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("Emma", "?", "Whatson", "enn322@mail.ru", "Gurievskaya 3", "+79131304444");
        app.getContactHelper().fillContactCreationForm(contact);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.set(contact.getId(), contact);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(ById);
        after.sort(ById);

        Assert.assertEquals(before, after);

    }
}
