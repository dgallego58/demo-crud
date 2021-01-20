package test.jpa.data.demofetchjpa.domain.entity;

import test.jpa.data.demofetchjpa.utils.Constants;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "editorials", schema = Constants.EDITORIAL_SCHEMA)
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editorial_seq")
    @SequenceGenerator(name = "editorial_seq", allocationSize = 5)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "editorial", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Author> authors;

    //synchronized methods
    public void addAuthor(Author author) {
        this.getAuthors().add(author);
        author.setEditorial(this);
    }

    public void removeAuthor(Author author) {
        this.getAuthors().remove(author);
        author.setEditorial(null);
    }

    public Long getId() {
        return id;
    }

    public Editorial setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Editorial setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Author> getAuthors() {
        return authors == null ? new LinkedHashSet<>() : authors;
    }

    public Editorial setAuthors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Editorial.class.getSimpleName() + "[", "]").add("id=" + id)
                                                                                 .add("name='" + name + "'")
                                                                                 .toString();
    }
}
