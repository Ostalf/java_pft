package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Objects;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class ContactAndGroupInteraction extends TestBase {

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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test1")
                    .withFooter("test2")
                    .withHeader("test1"));
        }
        Optional<ContactData> contactWithoutGroups = app.contact().findContactWithoutGroups(app.db().contacts());
        if (!contactWithoutGroups.isPresent()) {
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData()
                    .withFirstName("firstName0")
                    .withMiddleName("middleName0")
                    .withLastName("lastName0")
                    .withEmail("someboi0@gmail.com")
                    .withAddress("Lenina0")
                    .withMobile("999999990")
                    .withHome("999999990")
                    .withWork("999999990"), null);
        }

        Optional<ContactData> contactWithGroups = app.contact().findContactWithGroups(app.db().contacts());
        if (!contactWithGroups.isPresent()) {
            app.goTo().contactCreationPage();
            app.contact().create(new ContactData()
                    .withFirstName("firstName0")
                    .withMiddleName("middleName0")
                    .withLastName("lastName0")
                    .withEmail("someboi0@gmail.com")
                    .withAddress("Lenina0")
                    .withMobile("999999990")
                    .withHome("999999990")
                    .withWork("999999990"), app.db().groups().iterator().next());
        }

    }


    @Test
    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();

        GroupData group = app.db().groups().iterator().next();
        ContactData contactWithoutGroups = app.contact().findContactWithoutGroups(before).get();
        app.goTo().homePage();
        app.contact().selectContactById(contactWithoutGroups.getId());
        app.contact().addContactToGroup(group.getName());

        Contacts after = app.db().contacts();

        before = before.withAdded(contactWithoutGroups.inGroup(group));

        assertEquals(after.size(), before.size());
        assertEquals(after, before);
    }

    @Test
    public void testDeleteContactFromGroup() {
        Contacts before = app.db().contacts();

        ContactData contactWithGroups = app.contact().findContactWithGroups(before).get();
        GroupData group = app.contact().findContactWithGroups(before).get().getGroups().stream()
                .filter(Objects::nonNull)
                .findFirst()
                .get();

        app.goTo().homePage();
        app.contact().orderContactsByGroup(group.getName());
        app.contact().selectContactById(contactWithGroups.getId());
        app.contact().removeContactFromGroup();

        Contacts after = app.db().contacts();

        app.goTo().homePage();

        before = before.withoutGroup(contactWithGroups, group);

        assertEquals(after.size(), before.size());
        assertEquals(after, before);
    }
}
