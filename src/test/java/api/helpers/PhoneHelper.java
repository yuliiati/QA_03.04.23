package api.helpers;

import api.phone.PhonesApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class PhoneHelper extends PhonesApi {
    PhonesApi phoneApi = new PhonesApi();

    public int addNewPhone(Integer contactId) {
        phoneApi.createPhone(201, contactId);
        Response response = phoneApi.getAllPhoneByContactId(200, contactId);
        Integer phoneId = response.jsonPath().getInt("[0].id");
        String phone = response.jsonPath().getString("[0].phoneNumber");
        String countryCode = response.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(phone, phoneApi.randomDataForCreatePhone(contactId).getPhoneNumber(), "Created phone is not equals");
        Assert.assertEquals(countryCode, phoneApi.randomDataForCreatePhone(contactId).getCountryCode(), "Created countryCode is not equals");
        return phoneId;
    }

    public void updateExistedPhone(Integer phoneId, Integer contactId) {
        phoneApi.updatePhone(200, phoneId, contactId);
        Response updatedPhone = phoneApi.getAllPhoneByContactId(200, contactId);
        String updatedPhoneNumber = updatedPhone.jsonPath().getString("[0].phoneNumber");
        String updatedCountryCode = updatedPhone.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(updatedPhoneNumber, phoneApi.randomDataForExistingPhone(phoneId, contactId).getPhoneNumber(), "Updated phone is not equals");
        Assert.assertEquals(updatedCountryCode, phoneApi.randomDataForExistingPhone(phoneId, contactId).getCountryCode(), "Updated countryCode is not equals");
    }

    public void deleteExistedPhone(Integer phoneId) {
        phoneApi.deletePhone(200, phoneId);
        Response errorMessage = phoneApi.getCreatedPhoneByPhoneId(500, phoneId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");
    }

    public void addNewPhoneMy(Integer contactId) {
        phoneApi.createPhone(201, contactId);
        Response response = phoneApi.getAllPhoneByContactId(200, contactId);
        String phone = response.jsonPath().getString("[0].phoneNumber");
        String countryCode = response.jsonPath().getString("[0].countryCode");
        Assert.assertEquals(phone, phoneApi.randomDataForCreatePhone(contactId).getPhoneNumber(), "Created phone is not equals");
        Assert.assertEquals(countryCode, phoneApi.randomDataForCreatePhone(contactId).getCountryCode(), "Created countryCode is not equals");

    }

    public Integer getPhoneIdMy(Integer contactId) {
        Response response = phoneApi.getAllPhoneByContactId(200, contactId);
        int phoneId = response.jsonPath().getInt("[0].id");
        return phoneId;
    }

}

