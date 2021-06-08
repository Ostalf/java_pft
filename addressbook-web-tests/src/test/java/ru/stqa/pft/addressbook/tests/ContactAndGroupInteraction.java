package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    }

    @Test
    public void testAddContactToGroup() {
        int contactId = app.db().contacts().iterator().next().getId();
        app.db().contacts().getContactDataById(contactId);
        GroupData group = app.db().groups().iterator().next();

        Groups before = app.db().contacts().getContactDataById(contactId).getGroups();

        app.goTo().homePage();
        app.contact().selectContactById(contactId);
        app.contact().addContactToGroup(group.getName());

        Groups after = app.db().contacts().getContactDataById(contactId).getGroups();

        assertEquals(after.size(), before.withAdded(group).size());
        assertEquals(after, before.withAdded(group));
    }

    @Test
    public void testDeleteContactFromGroup() {
        int contactId = app.db().contacts().iterator().next().getId();
        app.db().contacts().getContactDataById(contactId);
        GroupData group = app.db().groups().iterator().next();

        Groups before = app.db().contacts().getContactDataById(contactId).getGroups();

        app.goTo().homePage();
        app.contact().orderContactsByGroup(group.getName());
        app.contact().selectContactById(contactId);
        app.contact().removeContactFromGroup();

        Groups after = app.db().contacts().getContactDataById(contactId).getGroups();

        app.goTo().homePage();

        assertEquals(after.size(), before.without(group).size());
        assertEquals(after, before.without(group));
    }
}
