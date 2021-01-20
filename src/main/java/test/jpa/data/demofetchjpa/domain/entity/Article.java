package test.jpa.data.demofetchjpa.domain.entity;

import test.jpa.data.demofetchjpa.utils.Constants;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "articles", schema = Constants.EDITORIAL_SCHEMA)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_seq")
    @SequenceGenerator(name = "article_seq", allocationSize = 5)
    private Long id;

    private String name;
    private LocalDate publishDate;

    @ManyToMany(mappedBy = "articles")
    private Set<Author> authors;

    public void addAuthor(Author author){
        this.getAuthors().add(author);
        author.getArticles().add(this);
    }

    public void removeAuthor(Author author){
        this.getAuthors().remove(author);
        author.getArticles().remove(this);
    }

    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Article setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Article setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Set<Author> getAuthors() {
        return authors == null ? new HashSet<>() : authors;
    }

    public Article setAuthors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (getId() == null){
            return false;
        }
        Article article = (Article) o;
        return getId().equals(article.getId()) && Objects.equals(getName(), article.getName()) && Objects.equals(getPublishDate(), article
                .getPublishDate());
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
