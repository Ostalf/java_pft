package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class ContactDataTests extends TestBase {
    @Test
    public void testContactData() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertEquals(contact.getAllPhones(), mergePhones(contactInfoFromEditForm));
        assertEquals(contact.getEmail(), contactInfoFromEditForm.getEmail());
        assertEquals(contact.getAddress(), contactInfoFromEditForm.getAddress());
        verifyContactListInUI();
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream()
                .filter(s -> !s.isEmpty())
                .map(ContactDataTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
