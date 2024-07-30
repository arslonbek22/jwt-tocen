package uz.pdp.post_jwt.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.post_jwt.dto.UserReq;
import uz.pdp.post_jwt.entity.User;
import uz.pdp.post_jwt.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }


    @PostMapping
    public void createUser(@RequestBody UserReq userReq) {
        userService.save(userReq);
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody UserReq userReq, @PathVariable UUID id) {
        userService.update(userReq, id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.delete(id);
    }


}
