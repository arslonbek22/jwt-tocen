package uz.pdp.post_jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.post_jwt.entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostRepo extends JpaRepository<Post, UUID> {
    List<Post> findByUserId(UUID userId);
}
