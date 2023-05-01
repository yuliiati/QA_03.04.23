package api.contact;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.contact.ContactDto;


public class ContactApi extends ApiBase {
    Response response;
    ContactDto dto;
//    Faker faker = new Faker();


    public ContactDto randomDataBodyForCreateContact() {  // Рандомная генерация данных body
        dto = new ContactDto();
        dto.setFirstName("Hegllo");  // faker.internet().uuid()
        dto.setLastName("Worlgd");
        dto.setDescription("blagbla");
        return dto;
    }

    public ContactDto randomDataBodyForEditContact(Integer contactId) {  // Рандомная генерация данных body
        dto = new ContactDto();
        dto.setId(contactId);
        dto.setFirstName("gdg");
        dto.setLastName("Teazutzu");
        dto.setDescription("QAAAgAAAA");
        return dto;
    }

    public Response createContact(Integer code) {
        String endPoint = "/api/contact";
        response = postRequest(endPoint, code, randomDataBodyForCreateContact());
        response.as(ContactDto.class);  // Ответ как в классе ContactDto
        return response;
    }

    public void editExistingContact(Integer code, Integer contactId) {
        String endPoint = "/api/contact/";
        putRequest(endPoint, code, randomDataBodyForEditContact(contactId));
    }

    public void deleteExistingContact(Integer code, int contactId) {
        String endPoint = "/api/contact/{id}";
        deleteRequest(endPoint, code, contactId);
    }

    public Response getContact(Integer code, int contactId) {    // Параметры
        String endPoint = "/api/contact/{id}";
        response = getRequestWithParam(endPoint, code, contactId);    // Аргументы
        return response;
    }


}
