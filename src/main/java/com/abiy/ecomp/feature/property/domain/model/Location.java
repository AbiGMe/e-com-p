package com.abiy.ecomp.feature.property.domain.model;

public record Location(String address, String city, String state, String country, Coordinates coordinates) {
}