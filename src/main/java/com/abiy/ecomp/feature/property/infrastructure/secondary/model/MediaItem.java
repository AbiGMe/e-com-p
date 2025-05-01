package com.abiy.ecomp.feature.property.infrastructure.secondary.model;

import com.abiy.ecomp.feature.property.domain.model.enums.MediaType;
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
@Table(name = "p_media_item")
public class MediaItem extends AbstractAuditingEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final String id;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MediaType type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof MediaItem mediaItem)) return false;

        return new EqualsBuilder().append(id, mediaItem.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
