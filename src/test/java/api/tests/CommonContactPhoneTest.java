package api.tests;

import api.phone.PhonesApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactPhoneTest extends PhonesApi {


    @Test
    public void createEditDeleteContactPhoneTest() {

        createPhone(201);
        Response response = getAllPhones(200);
        int phoneId = response.jsonPath().getInt("[0].id");
        String countryCode = response.jsonPath().getString("[0].countryCode");
        String phoneNumber = response.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(countryCode, randomDataBodyForCreatePhone().getCountryCode(), "Created countryCode not equals");
        Assert.assertEquals(phoneNumber, randomDataBodyForCreatePhone().getPhoneNumber(), "Created phoneNumber not equals");


        editExistingPhone(200, phoneId);
        Response editedPhoneResponse = getPhone(200, phoneId);
        String countryCode1 = editedPhoneResponse.jsonPath().getString("countryCode");
        String phoneNumber1 = editedPhoneResponse.jsonPath().getString("phoneNumber");
        Assert.assertEquals(countryCode1, randomDataBodyForEditPhone(phoneId).getCountryCode(), "Edited countryCode not equals");
        Assert.assertEquals(phoneNumber1, randomDataBodyForEditPhone(phoneId).getPhoneNumber(), "Edited phoneNumber not equals");

        deleteExistingPhone(200, phoneId);
        Response errorMessage = getPhone(500, phoneId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");


    }
}
