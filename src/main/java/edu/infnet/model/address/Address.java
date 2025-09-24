package edu.infnet.model.address;

public class Address {
    private int houseNumber;
    private String streetName;
    private String district;
    private String city;
    private String country;
    private String postalCode;

    public void getAddress() {
        // Lógica para obter o endereço
        System.out.println(houseNumber + ", " + streetName + ", " + district + ", " + city + ", " + country + ", " + postalCode);
    }
    public void updateAddress(Address newAddress) {
        // Lógica para atualizar o endereço
        this.houseNumber = newAddress.houseNumber;
        this.streetName = newAddress.streetName;
        this.district = newAddress.district;
        this.city = newAddress.city;
        this.country = newAddress.country;
        this.postalCode = newAddress.postalCode;
        System.out.println("Endereço atualizado com sucesso!");
    }
}
