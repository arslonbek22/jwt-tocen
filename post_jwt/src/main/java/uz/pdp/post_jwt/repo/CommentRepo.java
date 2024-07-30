package uz.pdp.post_jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.post_jwt.entity.Comment;

import java.util.UUID;

public interface CommentRepo extends JpaRepository<Comment, UUID> {
}
