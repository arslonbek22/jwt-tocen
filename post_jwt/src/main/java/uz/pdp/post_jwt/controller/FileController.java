package uz.pdp.post_jwt.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.post_jwt.entity.Attachment;
import uz.pdp.post_jwt.entity.Post;
import uz.pdp.post_jwt.repo.AttachmentRepo;
import uz.pdp.post_jwt.repo.PostRepo;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

    private final PostRepo postRepo;
    private final AttachmentRepo attachmentRepo;

    @GetMapping("{id}")
    public void returnProductImg(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("post file not found"));
        Attachment attachment = post.getAttachment();
        response.getOutputStream().write(attachment.getContent());
    }


}
