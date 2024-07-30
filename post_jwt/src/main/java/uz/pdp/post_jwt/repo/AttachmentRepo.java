package uz.pdp.post_jwt.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.post_jwt.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}
