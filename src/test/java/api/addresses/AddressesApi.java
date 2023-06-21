package api.addresses;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.addresses.AddressesDto;

public class AddressesApi extends ApiBase {
    Response response;
    AddressesDto dto;

    public AddressesDto randomDataForCreateAddress(Integer contactId) {
        dto = new AddressesDto();
        dto.setCity("Lotte");
        dto.setCountry("Germany");
        dto.setStreet("Germanstr. 23");
        dto.setZip("49504");
        dto.setContactId(contactId);
        return dto;
    }

    public AddressesDto randomDataForExistingAddress(Integer addressId, Integer contactId) {
        dto = new AddressesDto();
        dto.setCity("Lotte");
        dto.setCountry("Germany");
        dto.setStreet("Germanstr 23");
        dto.setZip("49504");
        dto.setContactId(contactId);
        dto.setId(addressId);
        return dto;
    }

    public void createAddress(Integer code, Integer contactId) {
        String endPoint = "/api/address";
        postRequest(endPoint, code, randomDataForCreateAddress(contactId));

    }

    public void updateAddress(Integer code, Integer addressId, Integer contactId) {
        String endPoint = "/api/address";
        putRequest(endPoint, code, randomDataForExistingAddress(addressId, contactId));
    }

    public void deleteAddress(Integer code, int addressId) {
        String endpoint = "/api/address/{id}";
        deleteRequest(endpoint, code, addressId);
    }

    public Response getCreatedAddressByAddressId(Integer code, int addressId) {
        String endpoint = "/api/address/{id}";
        response = getRequestWithParam(endpoint, code, addressId); //"id",
        return response;
    }

    public Response getAllAddressByContactId(Integer code, Integer contactId) {
        String endPoint = "/api/address/{contactId}/all";
        response = getRequestWithParam(endPoint, code, contactId); //"contactId",
        return response;
    }

}