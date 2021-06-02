package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test3")
                    .withHeader("test2")
                    .withFooter("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData oldGroup = before.iterator().next();
        GroupData modifiedGroup = new GroupData()
                .withId(oldGroup.getId())
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(modifiedGroup);
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size());
        assertEquals(after, before.without(oldGroup).withAdded(modifiedGroup));
    }
}
