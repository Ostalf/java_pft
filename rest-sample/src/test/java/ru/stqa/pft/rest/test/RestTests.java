package ru.stqa.pft.rest.test;

import com.solidfire.gson.Gson;
import com.solidfire.gson.JsonElement;
import com.solidfire.gson.JsonParser;
import com.solidfire.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(666);
        Set<Issue> before = getIssues();
        Issue after = new Issue().withSubject("Test issue 123").withDescription("New test issue");
        int issueId = createIssue(after);
        Set<Issue> newIssues = getIssues();
        before.add(after.withId(issueId));
        assertEquals(newIssues, before);
    }


    private Set<Issue> getIssues() throws IOException {
        String json=getExecutor().execute(Request.Get(app.getProperty("web.baseURL") + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,new TypeToken<Set<Issue>>() {}.getType() );
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json=getExecutor().execute(Request.Post(app.getProperty("web.baseURL") + ".json")
                .bodyForm(new BasicNameValuePair("subject",newIssue.getSubject()),new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}