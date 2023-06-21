package api.tests;

import api.contact.ContactApi;
import api.helpers.ContactHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEditDeleteNewContact() {
        // Создаем контакт, записываем ответ после создания контакта
        Integer contactId = contactHelper.createContact();

        // Изменяем данные контакта, но данный ендпоинт не имеет ответа (см. сваггер)
        editExistingContact(200, contactId);
        // Получаем измененный(отредактированный) контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (это те данные, которые мы вытащили с помощью запроса ГЕТ )
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");

//        deleteExistingContact(200, contactId);
//        Response actualDeletedResponse = getContact(500, contactId);
//        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");

        // вместо этого ContactHelper

        contactHelper.deleteContact(contactId);
    }


}
