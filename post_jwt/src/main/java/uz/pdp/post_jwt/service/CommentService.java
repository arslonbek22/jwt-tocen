package uz.pdp.post_jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.post_jwt.dto.CommentReq;
import uz.pdp.post_jwt.entity.Comment;
import uz.pdp.post_jwt.entity.User;
import uz.pdp.post_jwt.repo.CommentRepo;
import uz.pdp.post_jwt.repo.UserRepo;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentRepo commentRepo;
    private final UserRepo userRepo;



    public Collection<?> getAll() {
        return commentRepo.findAll();
    }


    public Comment save(CommentReq commentReq) {
        User user = userRepo.findByUsername(commentReq.getAuthor());

        Comment comment = Comment.builder()
                .author(user)
                .comments(commentReq.getComments())
                .build();
        return commentRepo.save(comment);
    }


    public void delete(UUID id) {
        commentRepo.deleteById(id);
    }
}
