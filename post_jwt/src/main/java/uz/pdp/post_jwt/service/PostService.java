package uz.pdp.post_jwt.service;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.post_jwt.dto.PostReq;
import uz.pdp.post_jwt.entity.Attachment;
import uz.pdp.post_jwt.entity.Comment;
import uz.pdp.post_jwt.entity.Post;
import uz.pdp.post_jwt.entity.User;
import uz.pdp.post_jwt.repo.AttachmentRepo;
import uz.pdp.post_jwt.repo.CommentRepo;
import uz.pdp.post_jwt.repo.PostRepo;
import uz.pdp.post_jwt.repo.UserRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

@RequiredArgsConstructor
public class PostService {

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;
    private final AttachmentRepo attachmentRepo;



    public void addPost(PostReq postReq, MultipartFile file) throws IOException {
        User byUsername = userRepo.findByUsername(postReq.getAuthor());
        Optional<Comment> comment = Optional.empty();
        if (postReq.getCommentId() != null) {
            comment = commentRepo.findById(postReq.getCommentId());
        }
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Attachment attachment = new Attachment();

        Post post = Post.builder()
                .title(postReq.getTitle())
                .description(postReq.getContent())
                .build();

        if (file.isEmpty()){
            attachment = attachmentRepo.save(Attachment.builder().content(file.getBytes()).build());
            post.setAttachment(attachment);
        }

        if (comment.isPresent()) {
            Comment commentObj = comment.get();
            post.setComment(commentObj);
        }

        postRepo.save(post);
    }

    public void updatePost(PostReq postReq, UUID id) {
        User byUsername = userRepo.findByUsername(postReq.getAuthor());
        if (byUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Optional<Post> byId = postRepo.findById(id);
        if (byId.isPresent()) {
            Post post = byId.get();
            post.setTitle(postReq.getTitle());
            post.setDescription(postReq.getContent());
            postRepo.save(post);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void delete(UUID id) {
        Post post = postRepo.findById(id).orElse(null);
        if (post == null) {
            throw new ObjectNotFoundException("Not found", new Exception());
        }
        postRepo.delete(post);
    }

    public List<Post> getPosts() {
        return postRepo.findAll();
    }

    public ResponseEntity<?> getPostById(UUID id) {
        Optional<Post> byId = postRepo.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return null;
    }
}
