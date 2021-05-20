package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        ContactData contact = new ContactData()
                .withFirstName("Enna")
                .withMiddleName("Yrevna")
                .withLastName("Whatson")
                .withEmail("ann28@mail.ru")
                .withAddress("Viluiskaya 3")
                .withMobilePhoneNumber("+79831303737");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .max(Comparator.comparing(ContactData::getId))
                .map(ContactData::getId)
                .get()))));

    }
}
