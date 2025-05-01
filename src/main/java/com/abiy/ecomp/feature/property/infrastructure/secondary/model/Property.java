package com.abiy.ecomp.feature.property.infrastructure.secondary.model;

import com.abiy.ecomp.feature.property.domain.model.enums.PropertyType;
import com.abiy.ecomp.shared.audit.secondary.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "p_property")
public class Property extends AbstractAuditingEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final String id;

    @Transient
    private String age;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "price", nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "property_type", nullable = false)
    private PropertyType propertyType;

    @Column(name = "bedrooms", nullable = false)
    private int bedrooms;

    @Column(name = "bathrooms", nullable = false)
    private int bathrooms;

    @Column(name = "size", nullable = false)
    private double size;

    @Column(name = "available", nullable = false)
    private boolean available;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id")
    private List<MediaItem> media;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Property property)) return false;

        return new EqualsBuilder().append(id, property.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
