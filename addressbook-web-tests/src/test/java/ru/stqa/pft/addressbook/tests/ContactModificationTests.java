package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().
                    withFirstName("Anna").
                    withMiddleName("Yrevna").
                    withLastName("Guselnik").
                    withEmail("enn322@mail.ru").
                    withAddress("Lenina 1").
                    withMobile("+79831304444"), null);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();

        ContactData oldContact = before.iterator().next();
        ContactData modifiedContact = new ContactData()
                .withId(oldContact.getId())
                .withFirstName("Enna")
                .withMiddleName("Yrevna")
                .withLastName("Whatson")
                .withEmail("ann28@mail.ru")
                .withAddress("Viluiskaya 3")
                .withMobile("+79831303737");
        app.contact().modify(modifiedContact);
        app.goTo().homePage();

        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size());
        assertEquals(after, before.without(oldContact).withAdded(modifiedContact));
        verifyContactListInUI();
    }
}
