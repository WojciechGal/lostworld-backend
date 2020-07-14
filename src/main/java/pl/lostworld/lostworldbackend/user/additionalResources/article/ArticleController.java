package pl.lostworld.lostworldbackend.user.additionalResources.article;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;
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
        return articleService.checkById(id);
    }

    @GetMapping("/add")
    public ResponseEntity<?> addArticle() {
        return ResponseUtils.designOkResponse(new Article());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArticle (@Valid @RequestBody Article article) {
        return ResponseUtils.designCreatedResponse(articleService.save(article));
    }

    @PostMapping("/addForLoggedUser")
    public ResponseEntity<?> addArticleForLoggedUser(@RequestBody Article article, @AuthenticationPrincipal CurrentUser currentUser) {
        return articleService.validateAndSave(article, currentUser.getActualUser());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        return articleService.deleteById(id);
    }

    //todo PUT MAPPING
}
