package com.abiy.ecomp.shared.common.domain.model;

public record Address(String addressLine1, String addressLine2, String street, String subCity, String city,
                      String state, String country, Coordinate coordinate, String postalCode) {

    public Address updateAddress(Address address) {
        return new Address(address.addressLine1, address.addressLine2, address.street, address.subCity, address.city, address.state, address.country, coordinate != null ? coordinate.update(address.coordinate) : address.coordinate, address.postalCode);
    }
}
