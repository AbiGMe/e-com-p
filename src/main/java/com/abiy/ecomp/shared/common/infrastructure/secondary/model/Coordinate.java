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
@Entity(name = "ab_coordinate")
@NoArgsConstructor(force = true)
public class Coordinate extends AbstractAuditingEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private final String id;

    private float longitude;

    private float latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Coordinate that)) return false;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
