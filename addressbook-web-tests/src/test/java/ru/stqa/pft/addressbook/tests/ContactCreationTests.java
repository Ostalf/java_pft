package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoContactCreationPage();
        ContactData contact = new ContactData("Enna", "Yrevna", "Whatson", "ann28@mail.ru", "Viluiskaya 3", "+79831303737");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        before.add(contact);

        Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(ById);
        after.sort(ById);

        Assert.assertEquals(before, after);

    }
}
