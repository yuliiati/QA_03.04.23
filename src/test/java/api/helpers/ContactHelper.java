package api.helpers;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.LinkedHashMap;

public class ContactHelper extends ContactApi {

    public int createContact() {
        // Создаем контакт, записываем ответ после создания контакта
        Response actualResponse = createNewContact(201);
        // Из ответа вытаскиваем id для того, чтобы использовать данные id для получения данных
        int contactId = actualResponse.jsonPath().getInt("id");
        // Получаем данные по созданнному контакту
        Response expectedResponse = getContact(200, contactId);
//        LinkedHashMap<String, String> newContactObject = new LinkedHashMap<>();
//        newContactObject.put("myName", expectedResponse.jsonPath().getString("firstName"));
//        newContactObject.put("myLastName", expectedResponse.jsonPath().getString("lastName"));//Вытаскиваем через апи ключ, гетом, вытащить вэлью, записать его для нового ключа и перезаписать
//        newContactObject.put("myDescription", expectedResponse.jsonPath().getString("description"));//Для чего хэшмапы. Перезаписать обьект! Таким образом по ключу вытаскиваем вэлью и присваиваем новый ключ
//
//        newContactObject.values();  // будут все наши вэлью в этой переменной!!! Хэшсап - массив обьектов
//        [{"key": "value"},
//        {"key": "value"},
//        {"key": "value"}]

        // Проверка!! Сравнивамем ответ эндпоинта по мозданию контакта с ответом энд
//        Assert.assertEquals(actualResponse, expectedResponse, "ОШИИИИИИИИИИИИИИБКАААААААА! " + "Responses contact not equal" + actualResponse + "/" + expectedResponse);
        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "First name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "Last name contact not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "Description contact not equal");
        return contactId;
    }

    public void deleteContact(Integer contactId) {
        deleteExistingContact(200, contactId);
        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");
    }
}
