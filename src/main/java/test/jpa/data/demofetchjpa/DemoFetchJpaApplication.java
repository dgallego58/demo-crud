package test.jpa.data.demofetchjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import test.jpa.data.demofetchjpa.data.repository.*;
import test.jpa.data.demofetchjpa.domain.entity.*;

import java.time.LocalDate;

@SpringBootApplication
public class DemoFetchJpaApplication {

    @Autowired
    ExtendingCustomAuthor extendingCustomAuthor;
    @Autowired
    EditorialRepository editorialRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    PostRepo postRepo;
    @Autowired
    PostDetailRepo postDetailRepo;
    @Autowired
    RevisionRepo revisionRepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoFetchJpaApplication.class, args);

    }

    @Bean
    CommandLineRunner onInit() {
        return i -> setUp();
    }

    @Transactional
    void setUp() {

        Article article = new Article();
        article.setName("ArticleName");
        article.setPublishDate(LocalDate.now());

        Book book = new Book();
        book.setIsbn(1354);
        book.setTitle("The fck?");

        Author author = new Author();
        author.addArticle(article);
        author.addBook(book);
        author.setName("AuthorName");


        Editorial editorial = new Editorial();
        editorial.setName("EditorialName");
        editorial.addAuthor(author);

        editorialRepository.save(editorial);
        extendingCustomAuthor.save(author);
        bookRepository.save(book);
        articleRepository.save(article);


     /*   IntStream.range(1, 50).forEach(i -> {
            Author author = new Author();
            author.setEditorial(editorial);
            author.addArticle(article);
            author.addBook(book);
            author.setName("AuthorName");
            author.setName(author.getName() + i);
            authorRepository.save(author);
        });
*/
        Post post = new Post().setTitle("El Post");
        Revision revision = new Revision().setRevised(true);
        PostCompositeIds postCompositeIds = new PostCompositeIds().setPostId(post).setRevisionId(revision);
        PostDetail postDetail = new PostDetail().setPostCompositeIds(postCompositeIds);

        postRepo.save(post);
        revisionRepo.save(revision);
        postDetailRepo.save(postDetail);



    }



}
