package uz.pdp.post_jwt.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.post_jwt.security.UserDetelisLoad;
import uz.pdp.post_jwt.security.dto.LoginReq;
import uz.pdp.post_jwt.security.jwt.JwtUtils;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetelisLoad userDetelisLoad;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody LoginReq loginReq) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginReq.getUsername(),
                loginReq.getPassword())
        );

        return "Bearer " + jwtUtils.generateToken(loginReq.getUsername());


    }
}
