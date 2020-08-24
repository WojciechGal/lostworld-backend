package pl.lostworld.lostworldbackend.user.additionalResources.article;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lostworld.lostworldbackend.user.CurrentUser;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;
import pl.lostworld.lostworldbackend.utils.ValidationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

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
        Optional<Article> article = articleService.checkById(id);
        if (article.isPresent()) {
            return ResponseUtils.designOkResponse(article.get());
        } else {
            return ResponseUtils.designBadRequestSingletonResponse("No such element");
        }
    }

    @GetMapping("/add")
    public ResponseEntity<?> addArticle() {
        return ResponseUtils.designOkResponse(new Article());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArticle (@Valid @RequestBody Article article) {
//        System.out.println(article.getUser());
//        System.out.println(article.getUser().getId());
//        System.out.println(article.getUser().getUsername());
        return ResponseUtils.designCreatedResponse(articleService.save(article));
    }

    @PostMapping("/addForLoggedUser")
    public ResponseEntity<?> addArticleForLoggedUser(@RequestBody Article article, @AuthenticationPrincipal CurrentUser currentUser) {
        Set<ConstraintViolation<Article>> violations = articleService.setUserAndValidate(article, currentUser.getActualUser());
        if (violations.isEmpty()) {
            return ResponseUtils.designCreatedResponse(articleService.setUserAndSave(article, currentUser.getActualUser()));
        } else {
            return ResponseUtils.designBadRequestResponse(ValidationUtils.mapViolationsForResponse(violations));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateArticle (@Valid @RequestBody Article article) {
        return ResponseUtils.designOkResponse(articleService.save(article));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        Optional<Article> article = articleService.deleteById(id);
        if (article.isPresent()) {
            return ResponseUtils.designOkResponse(article.get());
        } else {
            return ResponseUtils.designBadRequestSingletonResponse("No such element");
        }
    }
}
