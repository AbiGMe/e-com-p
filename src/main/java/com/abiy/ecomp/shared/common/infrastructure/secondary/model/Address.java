package com.abiy.ecomp.shared.common.infrastructure.secondary.model;

import com.abiy.ecomp.shared.audit.secondary.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Entity(name = "ab_address")
@NoArgsConstructor(force = true)
public class Address extends AbstractAuditingEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private final String id;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    private String street;

    @Column(name = "sub_city")
    private String subCity;

    private String city;

    private String state;

    private String country;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;

    @Column(name = "postal_code")
    private String postalCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Address address)) return false;

        return new EqualsBuilder().append(id, address.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
