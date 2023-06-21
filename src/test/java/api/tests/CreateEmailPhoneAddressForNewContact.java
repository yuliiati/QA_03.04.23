package api.tests;

import api.helpers.AddressesHelper;
import api.helpers.ContactHelper;
import api.helpers.EmailHelper;
import api.helpers.PhoneHelper;
import org.testng.annotations.Test;

public class CreateEmailPhoneAddressForNewContact {
    EmailHelper emailHelper = new EmailHelper();
    PhoneHelper phoneHelper = new PhoneHelper();
    AddressesHelper addressHelper = new AddressesHelper();
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void lifeCycleOfEmailPhoneAddressInContact() {
        Integer contactId = contactHelper.createContact();

        Integer emailId = emailHelper.createEmail(contactId);
        Integer phoneId = phoneHelper.addNewPhone(contactId);
        Integer addressId = addressHelper.addNewAddress(contactId);

//        emailHelper.updateExistedEmail(emailId, contactId);
        phoneHelper.updateExistedPhone(phoneId, contactId);
        addressHelper.updateExistedAddress(addressId, contactId);

        emailHelper.deleteEmail(emailId);
        phoneHelper.deleteExistedPhone(phoneId);
        addressHelper.deleteExistedAddress(addressId);

        contactHelper.deleteContact(contactId);
    }
}