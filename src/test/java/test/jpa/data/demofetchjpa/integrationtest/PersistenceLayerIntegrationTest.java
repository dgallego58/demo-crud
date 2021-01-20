package test.jpa.data.demofetchjpa.integrationtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import test.jpa.data.demofetchjpa.DemoFetchJpaApplication;
import test.jpa.data.demofetchjpa.data.dto.AuthorDTO;
import test.jpa.data.demofetchjpa.data.dto.PostDetailDTO;
import test.jpa.data.demofetchjpa.data.repository.*;
import test.jpa.data.demofetchjpa.domain.entity.*;
import test.jpa.data.demofetchjpa.testconfig.ConfigTest;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;


//@SpringJUnitConfig(classes = {ConfigTest.class, DemoFetchJpaApplication.class})
@SpringBootTest(classes = {ConfigTest.class, DemoFetchJpaApplication.class})
@Sql(value = "/initializationSchema.sql")
class PersistenceLayerIntegrationTest {


    @Autowired
    ExtendingCustomAuthor authorRepository;
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


    @BeforeEach
    void setUp() {
        Article article = new Article();
        article.setName("ArticleName");
        article.setPublishDate(LocalDate.now());

        Book book = new Book();
        book.setIsbn(1234);
        book.setTitle("The fck?");

        Author author = new Author();
        author.addArticle(article);
        author.addBook(book);
        author.setName("AuthorName");

        authorRepository.save(author);
        authorRepository.save(author.setName("AuthorName1"));
        List<Editorial> editorials = new ArrayList<>();
        IntStream.range(1, 5)
                 .forEach(i -> {
                     Editorial editorial = new Editorial();
                     editorial.setName("EditorialName" + i);
                     editorial.addAuthor(author);
                     editorials.add(editorial);
                 });
        IntStream.range(1, 5)
                 .forEach(i -> {
                     Editorial editorial = new Editorial();
                     editorial.setName("EditorialName" + i);
                     editorial.addAuthor(author.setName("AuthorName1"));
                     editorials.add(editorial);
                 });
        editorials.forEach(System.out::println);
        editorialRepository.saveAll(editorials);


        Post post = new Post().setTitle("El Post");
        Revision revision = new Revision().setRevised(true).setId(200L);
        PostCompositeIds postCompositeIds = new PostCompositeIds().setPostId(post).setRevisionId(revision);
        PostDetail postDetail = new PostDetail().setPostCompositeIds(postCompositeIds);

        postRepo.save(post);
        revisionRepo.save(revision);
        postDetailRepo.save(postDetail);

    }


    @Test
    @Transactional(readOnly = true)
    void readInMemory() {

        System.out.println("MATCHER WITH OBJECT NATIVE JOIN");
        authorRepository.findMatcher(AuthorDTO.create()
                                              .setAuthorName("AuthorName")
                                              .setEditorialName("EditorialName1"))
                        .ifPresent(System.out::println);


        System.out.println("MATCHER WITH OBJECT FETCH JOIN");
        authorRepository.jpqlMatcher(AuthorDTO.create()
                                              .setAuthorName("AuthorName")
                                              .setEditorialName("EditorialName1"))
                        .ifPresent(System.out::println);

        authorRepository.graphMatcher(AuthorDTO.create()
                                               .setAuthorName("AuthorName")
                                               .setEditorialName("EditorialName1"))
                        .ifPresent(o -> {
                            System.out.println("MATCHER WITH OBJECT GRAPH");
                            System.out.println(o.getEditorial());
                            System.out.println(o.getArticles());
                        });


        Map<String, Object> filters = new HashMap<>();
        filters.put("authorName", "AuthorName");
        filters.put("editorialName", "EditorialName1");
        authorRepository.mapMatcher(filters)
                        .ifPresent(o -> {
                            System.out.println("MATCHER WITH MAP");
                            System.out.println(o.getEditorial());
                            System.out.println(o.getArticles());
                        });
    }

    @Test
    @Transactional(readOnly = true)
    void readOneToOne() {

        System.out.println("POST DETAIL FETCH");
        postRepo.findAll()
                .stream()
                .findFirst()
                .ifPresent(pd -> {
                    System.out.println(pd.getTitle());
                });

        revisionRepo.findAll()
                    .stream()
                    .findFirst()
                    .ifPresent(r -> System.out.println(r.isRevised()));

        System.out.println("FIND ALL POSTS");
        postDetailRepo.findAll()
                      .stream()
                      .findFirst()
                      .ifPresent(pd -> {
                          System.out.println(pd.getPostCompositeIds()
                                               .getPostId());
                          System.out.println(pd.getPostCompositeIds()
                                               .getRevisionId());
                          System.out.println(pd.getPublishDate());
                      });

        System.out.println("************************ POST COMPOSITE IDS");
        PostDetailDTO postDetailDTO = new PostDetailDTO().setPostTitle("El Post").setIdRevision(1L);
        Optional<PostDetail> optionalPostDetail = postDetailRepo.matcher(postDetailDTO);
        optionalPostDetail.ifPresent(pd ->{
            System.out.println(pd.getPostCompositeIds().getPostId().getTitle());
            System.out.println(pd.getPostCompositeIds().getRevisionId().getId());
        });
        System.out.println(optionalPostDetail.isPresent());

    }

}
