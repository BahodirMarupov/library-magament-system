package uz.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.librarysystem.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
