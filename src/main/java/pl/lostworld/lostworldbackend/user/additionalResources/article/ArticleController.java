package pl.lostworld.lostworldbackend.user.additionalResources.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/checkAll")
    public List<Article> checkAllArticles() {
        return articleService.checkAll();
    }

    @GetMapping("/add")
    public Article addArticle() {
        return new Article();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Article addArticle (@Valid @RequestBody Article article) {
        return articleService.save(article);
    }
}
