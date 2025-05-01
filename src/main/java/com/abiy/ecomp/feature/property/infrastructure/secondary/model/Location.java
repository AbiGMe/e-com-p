package com.abiy.ecomp.feature.property.infrastructure.secondary.model;

import com.abiy.ecomp.shared.audit.secondary.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "p_location")
public class Location extends AbstractAuditingEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private final String id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Embedded
    private Coordinates coordinates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Location location)) return false;

        return new EqualsBuilder().append(id, location.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
