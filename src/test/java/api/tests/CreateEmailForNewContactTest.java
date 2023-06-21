package api.tests;

import api.helpers.ContactHelper;
import api.helpers.EmailHelper;
import org.testng.annotations.Test;

public class CreateEmailForNewContactTest {
    EmailHelper emailHelper = new EmailHelper();
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEmailForNewContact() {
        Integer contactId = contactHelper.createContact();
        // Work with email
        Integer emailId = emailHelper.createEmail(contactId);
        emailHelper.editEmail(emailId, contactId);
        emailHelper.deleteEmail(emailId);
        contactHelper.deleteContact(contactId);
    }

//    @Test
//    public void createEditDeleteContactEmailTest() {
//
//        emailsApi.createEmail(201, contactId);
//        Response response  = emailsApi.getAllEmails(200, contactId);
//        int emailId = newEmailResponse.jsonPath().getInt("[0].id");
//        // редактирование сущ имейла
//        emailsApi.editExistingEmail(200, emailId, contactId);
//        // удаляем имейл
//        emailHelper.deleteEmail(emailId);
//        // удаляем контакт
//        contactHelper.deleteContact(contactId);
//    }
}