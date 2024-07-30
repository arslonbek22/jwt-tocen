package uz.pdp.post_jwt.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.post_jwt.entity.User;
import uz.pdp.post_jwt.repo.UserRepo;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        /*User user = User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .build();

        userRepo.save(user);*/
    }
}
