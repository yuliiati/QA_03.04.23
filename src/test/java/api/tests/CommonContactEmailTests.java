package api.tests;

import api.email.EmailsApi;
import api.helpers.EmailHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactEmailTests {

    EmailsApi emailsApi = new EmailsApi();
    EmailHelper emailHelper = new EmailHelper();

    int contactId = 4983;


    @Test
    public void createEditDeleteContactEmailTest() {

        emailsApi.createEmail(201, contactId);
        Response response = emailsApi.getAllEmails(200, contactId);
        int emailId = response.jsonPath().getInt("[0].id");
        String email = response.jsonPath().getString("[0].email");
        Assert.assertEquals(email, emailsApi.randomDataBodyForCreateEmail(contactId).getEmail(), "Created email not equals");

        emailsApi.editExistingEmail(200, emailId, contactId);
        Response editedEmails = emailsApi.getAllEmails(200, contactId);
        String editedEmail = editedEmails.jsonPath().getString("[0].email");
        Assert.assertEquals(editedEmail, emailsApi.randomDataBodyForEditEmail(emailId, contactId).getEmail(), "Edited email not equals");

        //public Response getAllEmails(Integer code) {
//        String endpoint = "/api/email/4909/all"; - "захардкодили" параметр. ид статичный
//        response = getRequest(endpoint, code);
//        return response;

        emailHelper.deleteEmail(emailId);


    }
}
