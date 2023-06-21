package api.phone;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.phone.PhoneDto;

public class PhonesApi extends ApiBase {
    Response response;
    PhoneDto dto;

    public PhoneDto randomDataBodyForCreatePhone() {  // Рандомная генерация данных body
        dto = new PhoneDto();
        dto.setCountryCode("+423");  // faker.internet().uuid()
        dto.setPhoneNumber("007");
        dto.setContactId(4983);
        return dto;
    }

    public PhoneDto randomDataBodyForEditPhone(Integer phoneId) {  // Рандомная генерация данных body
        dto = new PhoneDto();
        dto.setId(phoneId);
        dto.setCountryCode("+423");
        dto.setPhoneNumber("008");
        dto.setContactId(4983);
        return dto;
    }

    public void createPhone(Integer code) {
        String endPoint = "/api/phone";
        postRequest(endPoint, code, randomDataBodyForCreatePhone());
    }

    public String editExistingPhone(Integer code, Integer phoneId) {
        String endPoint = "/api/phone/";
        putRequest(endPoint, code, randomDataBodyForEditPhone(phoneId));
        return endPoint;
    }

    public void deleteExistingPhone(Integer code, Integer phoneId) { //int
        String endPoint = "/api/phone/{id}";    // phoneId
        deleteRequest(endPoint, code, phoneId);
    }

    public Response getAllPhones(Integer code) {    // Параметры
        String endPoint = "/api/phone/{id}/all"; // contactId
        response = getRequestWithParam(endPoint, code, 4983);    // Аргументы
        return response;
    }

    public Response getPhone(Integer code, Integer phoneId) { //int
        String endPoint = "/api/phone/{id}";
        response = getRequestWithParam(endPoint, code, phoneId); // "id",
        return response;
    }

    // /api/phone/{id}/ / getContact

}

