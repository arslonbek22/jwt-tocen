package uz.pdp.post_jwt.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.post_jwt.entity.User;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
