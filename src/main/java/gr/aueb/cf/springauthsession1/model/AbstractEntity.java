package gr.aueb.cf.springauthsession1.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity implements Serializable {
    @CreatedDate
//    @ColumnDefault("2022-03-25T12:30")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
//    @ColumnDefault("2024-03-25T12:30")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isActive=" + isActive +
                '}';
    }
}
