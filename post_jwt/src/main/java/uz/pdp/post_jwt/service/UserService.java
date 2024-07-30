package uz.pdp.post_jwt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.post_jwt.dto.UserReq;
import uz.pdp.post_jwt.entity.User;
import uz.pdp.post_jwt.repo.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Transactional
    public void save(UserReq userReq) {
        User user = User.builder()
                .username(userReq.getUsername())
                .password(passwordEncoder.encode(userReq.getPassword()))
                .build();
        userRepo.save(user);
    }


    @Transactional
    public void update(UserReq userReq, UUID id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setUsername(userReq.getUsername());
            user1.setPassword(userReq.getPassword());
            userRepo.save(user1);
        }else{
            throw new UsernameNotFoundException("User not found");
        }


    }

    @Transactional
    public void delete(UUID id) {
        userRepo.deleteById(id);
    }
}
