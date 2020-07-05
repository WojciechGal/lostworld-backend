package pl.lostworld.lostworldbackend.user.additionalResources.article;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.templates.Pair;
import pl.lostworld.lostworldbackend.user.User;
import pl.lostworld.lostworldbackend.utils.ResponseUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {

    private ArticleRepository articleRepository;

    private Validator validator;

    public ArticleService(ArticleRepository articleRepository, Validator validator) {
        this.articleRepository = articleRepository;
        this.validator = validator;
    }

    public List<Article> checkAll() {
        return articleRepository.findAll();
    }

    public Article checkById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public ResponseEntity<?> validateAndSave(Article article, User user) {

        article.setUser(user);

        Set<ConstraintViolation<Article>> violations = validator.validate(article);

        if (violations.isEmpty()) {
            return ResponseUtils.designCreatedResponse(articleRepository.save(article));
        } else {
            return ResponseUtils.designBadRequestResponse(violations
                    .stream()
                    .map(violation -> new Pair<>(violation.getPropertyPath().toString(), violation.getMessage()))
                    .collect(Collectors.toList()));
        }
    }
}
