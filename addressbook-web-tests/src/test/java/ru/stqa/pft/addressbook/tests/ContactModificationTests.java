package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().list().size() == 0) {
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData().
                    withFirstName("Anna").
                    withMiddleName("Yrevna").
                    withLastName("Guselnik").
                    withEmail("enn322@mail.ru").
                    withAddress("Lenina 1").
                    withMobilePhoneNumber("+79831304444"));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Enna")
                .withMiddleName("Yrevna")
                .withLastName("Whatson")
                .withEmail("ann28@mail.ru")
                .withAddress("Viluiskaya 3")
                .withMobilePhoneNumber("+79831303737");

        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        System.out.println();
    }
}
