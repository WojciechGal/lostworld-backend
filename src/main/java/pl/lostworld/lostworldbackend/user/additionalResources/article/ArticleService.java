package pl.lostworld.lostworldbackend.user.additionalResources.article;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lostworld.lostworldbackend.user.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

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

    public Optional<Article> checkById(Long id) {
        return articleRepository.findById(id);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Article setUserAndSave(Article article, User user) {
        article.setUser(user);
        return articleRepository.save(article);
    }

    public Set<ConstraintViolation<Article>> setUserAndValidate(Article article, User user) {
        return validator.validate(article);
    }

    public Optional<Article> deleteById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            //dociągnięcie danych jest wymagane przed usunięciem obiektu z DB
            Hibernate.initialize(article.get().getContinents());
            Hibernate.initialize(article.get().getCountries());
            Hibernate.initialize(article.get().getCities());
            Hibernate.initialize(article.get().getRelics());
            articleRepository.deleteById(id);
            return article;
        } else {
            return article;
        }
    }
}
