package edu.infnet.model.address;

public class Address {
    private int houseNumber;
    private String streetName;
    private String district;
    private String city;
    private String postalCode;
    private final String ADDRESS_ID;

    public Address() {
        this.ADDRESS_ID = java.util.UUID.randomUUID().toString();
    }

    public Address(String district, String city, String postalCode , String streetName, int houseNumber) {
        this.ADDRESS_ID = java.util.UUID.randomUUID().toString();
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public Address(String address_id, String district, String city, String postalCode , String streetName, int houseNumber) {
        this.ADDRESS_ID = address_id;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    public String getADDRESS_ID() {
        return ADDRESS_ID;
    }

    public void getAddress() {
        // Lógica para obter o endereço
        System.out.println(district + ", " + city + ", " + postalCode + ", " + streetName + ", " + houseNumber);
    }
    public void updateAddress(Address newAddress) {
        // Lógica para atualizar o endereço
        this.houseNumber = newAddress.houseNumber;
        this.streetName = newAddress.streetName;
        this.district = newAddress.district;
        this.city = newAddress.city;
        this.postalCode = newAddress.postalCode;
        System.out.println("Endereço atualizado com sucesso!");
    }

    public String getAllInfo(){
        return this.ADDRESS_ID + ";" + this.district + ";" + this.city + ";" + this.postalCode + ";" + this.streetName + ";" + this.houseNumber;
    }
}
