package test.jpa.data.demofetchjpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.jpa.data.demofetchjpa.domain.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
