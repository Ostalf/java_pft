package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
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
                    withMobile("+79831304444"));
        }
    }
    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();

        ContactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size()-1);
        assertThat(after, equalTo(before.without(deleteContact)));
    }

}
