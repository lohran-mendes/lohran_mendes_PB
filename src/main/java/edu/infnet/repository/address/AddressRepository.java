package edu.infnet.repository.address;

import edu.infnet.model.address.Address;
import edu.infnet.repository.Repository;

public class AddressRepository {
    static Repository addressRepository = new Repository("addressDB");

    public static void saveNewAddress(Address newAddress) throws Exception {
        String content = newAddress.getAllInfo();

        addressRepository.save(content);
    }

    public static Address getAddressById(String id) throws Exception {
        for (String[] address : addressRepository.getAll()) {
            if (address[AddressTable.ADDRESS_ID].equals(id)) {
                return new Address(address[AddressTable.ADDRESS_ID], address[AddressTable.DISTRICT], address[AddressTable.CITY], address[AddressTable.POSTAL_CODE], address[AddressTable.STREET], Integer.parseInt(address[AddressTable.NUMBER]));
            }
        }
        return null;
    }
}