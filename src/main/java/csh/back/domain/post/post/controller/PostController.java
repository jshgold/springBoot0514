package csh.back.domain.post.post.controller;

import csh.back.domain.post.post.entity.Post;
import csh.back.domain.post.post.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/write")
    @ResponseBody
    public String write() {
        return """
                <form action="http://localhost:8080/posts/doWrite">
                  <input type="text" name="title" placeholder="제목" value="안녕">
                  <br>
                  <textarea name="content" placeholder="내용">반가워.</textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """;
    }


    @GetMapping("/posts/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        Post post = postService.write(title, content);

        return "%d번 글이 생성되었습니다.".formatted(post.getId());
    }
}