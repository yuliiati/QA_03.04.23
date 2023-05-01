package api.tests;

import api.contact.ContactApi;
import api.email.EmailsApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEmailForNewContactTest {
    ContactApi contactApi = new ContactApi();
    EmailsApi emailsApi = new EmailsApi();

    @Test   // Создается постоянно новый контакт и потом в конце удаляется. Круто, он не будет мешать нам дальше
    public void createEmailForNewContact() {

        Response newContactResponse = contactApi.createContact(201);  // создаем новый контакт
        Integer contactId = newContactResponse.jsonPath().getInt("id"); // вытаскиваем айди
        // проверку на создание контакта не пишем, она у нас в контакт тест
        emailsApi.createEmail(201, contactId);
        Response newEmailResponse = emailsApi.getAllEmails(200, contactId);
        int emailId = newEmailResponse.jsonPath().getInt("[0].id"); // ответ приходит списком. чтобы достучаться

        emailsApi.editExistingEmail(200, emailId, contactId);

        emailsApi.deleteExistingEmail(200, emailId);
        Response errorMessage = emailsApi.getEmail(500, emailId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

        contactApi.deleteExistingContact(200, contactId);
        Response actualDeletedResponse = contactApi.getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
