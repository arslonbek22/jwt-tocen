package uz.pdp.post_jwt.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.post_jwt.dto.CommentReq;
import uz.pdp.post_jwt.entity.Comment;
import uz.pdp.post_jwt.repo.CommentRepo;
import uz.pdp.post_jwt.service.CommentService;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Collection<?> getComment() {
        return commentService.getAll();
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentReq commentReq) {
        return commentService.save(commentReq);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable UUID id) {
        commentService.delete(id);
    }



}
