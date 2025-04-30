package com.abiy.ecomp.shared.common.domain.model;

import com.abiy.ecomp.shared.error.domain.Assert;

public record Coordinate(float longitude, float latitude) {

    public Coordinate {
        Assert.field("Longitude", longitude)
            .min(-3.1416F)
            .max(3.1416F);

        Assert.field("Latitude", latitude)
            .min(-1.5708F)
            .max(1.5708F);
    }

    public Coordinate update(Coordinate coordinate) {
        return new Coordinate(coordinate.longitude, coordinate.latitude);
    }
}
