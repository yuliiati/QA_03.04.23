package api.helpers;

import api.addresses.AddressesApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class AddressesHelper {
    AddressesApi addressApi = new AddressesApi();

    public int addNewAddress(Integer contactId) {
        addressApi.createAddress(201, contactId);
        Response response = addressApi.getAllAddressByContactId(200, contactId);
        int addressId = response.jsonPath().getInt("[0].id");
        String addressCity = response.jsonPath().getString("[0].city");      // [0] -- все нули, так как обращение к первому элементу листа (адресов может быть много)
        String addressCountry = response.jsonPath().getString("[0].country"); // .country -- обращение к конкретному полю
        String addressStreet = response.jsonPath().getString("[0].street");
        String addressZip = response.jsonPath().getString("[0].zip");
        Assert.assertEquals(addressCity, addressApi.randomDataForCreateAddress(contactId).getCity(), "Created phone is not equals");
        Assert.assertEquals(addressCountry, addressApi.randomDataForCreateAddress(contactId).getCountry(), "Created phone is not equals");
        Assert.assertEquals(addressStreet, addressApi.randomDataForCreateAddress(contactId).getStreet(), "Created phone is not equals");
        Assert.assertEquals(addressZip, addressApi.randomDataForCreateAddress(contactId).getZip(), "Created phone is not equals");
        return addressId;
    }

    public void updateExistedAddress(Integer addressId, Integer contactId) {
        addressApi.updateAddress(200, addressId, contactId);
        Response updatedAddress = addressApi.getAllAddressByContactId(200, contactId);
        String updatedAddressCity = updatedAddress.jsonPath().getString("[0].city");
        String updatedAddressCountry = updatedAddress.jsonPath().getString("[0].country");
        String updatedAddressStreet = updatedAddress.jsonPath().getString("[0].street");
        String updatedAddressZip = updatedAddress.jsonPath().getString("[0].zip");
        Assert.assertEquals(updatedAddressCity, addressApi.randomDataForExistingAddress(addressId, contactId).getCity(), "Created address is not equals");
        Assert.assertEquals(updatedAddressCountry, addressApi.randomDataForExistingAddress(addressId, contactId).getCountry(), "Created address is not equals");
        Assert.assertEquals(updatedAddressStreet, addressApi.randomDataForExistingAddress(addressId, contactId).getStreet(), "Created address is not equals");
        Assert.assertEquals(updatedAddressZip, addressApi.randomDataForExistingAddress(addressId, contactId).getZip(), "Created address is not equals");
    }

    public void deleteExistedAddress(Integer addressId) {
        addressApi.deleteAddress(200, addressId);
        Response errorMessage = addressApi.getCreatedAddressByAddressId(500, addressId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This address doesn't exist in our DB");
    }

    //todo-----------------------Метод что б вытянуть addressId------------------------------------------------------------------------------------
    public Integer getAddressIdMy(Integer contactId) {
        Response response = addressApi.getAllAddressByContactId(200, contactId);
        int addressId = response.jsonPath().getInt("[0].id");
        return addressId;
    }


}