package api.tests;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTest extends ContactApi {

    @Test
    public void createEditDeleteNewContact() {

        // Создаем контакт, записываем ответ после создания контакта
        Response actualResponse = createContact(201);
        // Из ответа вытаскиваем id для того, чтобы использовать данные id для получения данных
        int contactId = actualResponse.jsonPath().getInt("id");
        // Получаем данные по созданнному контакту
        Response expectedResponse = getContact(200, contactId);
        // Проверка!! Сравнивамем ответ эндпоинта по мозданию контакта с ответом энд

//        Assert.assertEquals(actualResponse, expectedResponse, "ОШИИИИИИИИИИИИИИБКАААААААА! " + "Responses contact not equal" + actualResponse + "/" + expectedResponse);
        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "firstName contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "lastName contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "description contact not equal");
        // Изменяем данные контакта, но данный ендпоинт не имеет ответа (см. сваггер)
        editExistingContact(200, contactId);
        // Получаем измененный(отредактированный) контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (это те данные, которые мы вытащили с помощью запроса ГЕТ )
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name contact not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description contact not equal");

        deleteExistingContact(200, contactId);

        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }


}
