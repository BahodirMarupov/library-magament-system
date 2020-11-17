package uz.librarysystem.entity.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(value = {""})
public class UserDateAudit extends DateAudit{

    @CreatedBy
    private Long createdBy;
    @LastModifiedBy
    private Long updatedBy;
}
