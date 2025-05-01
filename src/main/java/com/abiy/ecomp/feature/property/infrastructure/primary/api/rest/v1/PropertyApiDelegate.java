package com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1;

import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.Property;
import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.PropertyCreate;
import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.PropertyPaginated;
import com.abiy.ecomp.feature.property.infrastructure.primary.api.rest.v1.model.PropertyUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PropertyApiDelegate implements PropertiesApiDelegate {

    @Override
    public ResponseEntity<PropertyPaginated> propertiesGet(Optional<Integer> page, Optional<Integer> limit) {
        return PropertiesApiDelegate.super.propertiesGet(page, limit);
    }

    @Override
    public ResponseEntity<Property> propertiesPost(PropertyCreate propertyCreate) {
        return PropertiesApiDelegate.super.propertiesPost(propertyCreate);
    }

    @Override
    public ResponseEntity<Void> propertiesPropertyIdDelete(String propertyId) {
        return PropertiesApiDelegate.super.propertiesPropertyIdDelete(propertyId);
    }

    @Override
    public ResponseEntity<Property> propertiesPropertyIdGet(String propertyId) {
        return PropertiesApiDelegate.super.propertiesPropertyIdGet(propertyId);
    }

    @Override
    public ResponseEntity<Void> propertiesPropertyIdMediaPost(String propertyId, String mediaType, List<MultipartFile> files) {
        return PropertiesApiDelegate.super.propertiesPropertyIdMediaPost(propertyId, mediaType, files);
    }

    @Override
    public ResponseEntity<Void> propertiesPropertyIdPut(String propertyId, PropertyUpdate propertyUpdate) {
        return PropertiesApiDelegate.super.propertiesPropertyIdPut(propertyId, propertyUpdate);
    }
}
