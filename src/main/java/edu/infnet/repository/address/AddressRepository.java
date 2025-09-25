package edu.infnet.repository.address;

import edu.infnet.model.address.Address;
import edu.infnet.repository.Repository;

public class AddressRepository {
    static Repository addressRepository = new Repository("addressDB");

    public static void saveNewAddress(Address newAddress) throws Exception {
        String content = newAddress.getAllInfo();

        addressRepository.save(content);
    }
}
