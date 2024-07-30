package uz.pdp.post_jwt.controller;


import jakarta.servlet.annotation.MultipartConfig;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.post_jwt.dto.PostReq;
import uz.pdp.post_jwt.entity.Post;
import uz.pdp.post_jwt.service.PostService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@MultipartConfig
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;


    @GetMapping
    public Collection<?> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPost(@PathVariable UUID id) {
        return postService.getPostById(id);
    }


    @Transactional
   /* @PreAuthorize("hasRole('ADMIN')")*/
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void save(@RequestBody PostReq postReq, @RequestParam("{file}") MultipartFile file) throws IOException {
        postService.addPost(postReq, file);
    }


    @Transactional
    /*@PreAuthorize("hasRole('ADMIN')")*/
    @PutMapping("{id}")
    public void update(@RequestBody PostReq postReq, @PathVariable UUID id) {
        postService.updatePost(postReq, id);
    }

    @Transactional
    /*@PreAuthorize("hasRole('ADMIN')")*/
    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        postService.delete(id);
    }
}
