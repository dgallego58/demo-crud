package test.jpa.data.demofetchjpa.domain.entity;

import test.jpa.data.demofetchjpa.utils.Constants;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "authors", schema = Constants.EDITORIAL_SCHEMA)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @SequenceGenerator(name = "author_seq", allocationSize = 5)
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editorial_id", foreignKey = @ForeignKey(name = "fk_editorial_id"))
    private Editorial editorial;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "author_article", schema = Constants.EDITORIAL_SCHEMA,
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_author_article_id"),
            inverseForeignKey = @ForeignKey(name = "fk_article_author_id"))
    private Set<Article> articles;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author")
    private Set<Book> books;


    //synchronized methods

    public void addArticle(Article article) {
        this.getArticles().add(article);
        article.getAuthors().add(this);
    }

    public void removeArticle(Article article) {
        this.getArticles().remove(article);
        article.getAuthors().remove(this);
    }

    public void addBook(Book book) {
        this.getBooks().add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        this.getBooks().remove(book);
        book.setAuthor(null);
    }


    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public Author setEditorial(Editorial editorial) {
        this.editorial = editorial;
        return this;
    }

    public Set<Article> getArticles() {
        return articles == null ? new HashSet<>() : articles;
    }

    public Author setArticles(Set<Article> articles) {
        this.articles = articles;
        return this;
    }

    public Set<Book> getBooks() {
        return books == null ? new LinkedHashSet<>() : books;
    }

    public Author setBooks(Set<Book> books) {
        this.books = books;
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
        if (getId() == null) {
            return false;
        }
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) && Objects.equals(getName(), author.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Author.class.getSimpleName() + "[", "]").add("id=" + id)
                                                                              .add("name='" + name + "'")
                                                                              .add("editorial=" + editorial)
                                                                              .toString();
    }
}
