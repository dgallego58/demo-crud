package test.jpa.data.demofetchjpa.domain.entity;

import test.jpa.data.demofetchjpa.utils.Constants;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books", schema = Constants.EDITORIAL_SCHEMA)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", allocationSize = 5)
    private Long id;

    private String title;
    private Integer isbn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_author_id"))
    private Author author;

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public Book setIsbn(Integer isbn) {
        this.isbn = isbn;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
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
        if (id == null){
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getIsbn(), book
                .getIsbn()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getIsbn(), getAuthor());
    }
}
