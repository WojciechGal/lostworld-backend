package pl.lostworld.lostworldbackend.user.additionalResources.article;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/checkAll")
    public ResponseEntity<?> checkAllArticles() {
        return ResponseUtils.designOkResponse(articleService.checkAll());
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<?> checkArticle(@PathVariable Long id) {
        return ResponseUtils.designOkResponse(articleService.checkById(id));
    }

    @GetMapping("/add")
    public ResponseEntity<?> addArticle() {
        return ResponseUtils.designOkResponse(new Article());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArticle (@Valid @RequestBody Article article) {
        return ResponseUtils.designCreatedResponse(articleService.save(article));
    }
}
