package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();

        app.goTo().contactCreationPage();
        app.contact().create(contact, null);

        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .max(Comparator.comparing(ContactData::getId))
                .map(ContactData::getId)
                .get()))));
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactAddToGroupInCreation(ContactData contact) {
        Contacts before = app.db().contacts();

        GroupData group = app.db().groups().iterator().next();
        app.goTo().contactCreationPage();
        contact.inGroup(group);
        app.contact().create(contact, group);

        Contacts after = app.db().contacts();

        assertEquals(before.withAdded(contact).size(), after.size());
        assertEquals(before.withAdded(contact.withId(after.stream()
                .max(Comparator.comparing(ContactData::getId))
                .map(ContactData::getId)
                .get())), after);
    }
}
