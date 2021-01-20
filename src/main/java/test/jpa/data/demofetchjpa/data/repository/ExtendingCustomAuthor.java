package test.jpa.data.demofetchjpa.data.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.jpa.data.demofetchjpa.data.dto.AuthorDTO;
import test.jpa.data.demofetchjpa.domain.entity.Author;

import java.util.Map;
import java.util.Optional;

public interface ExtendingCustomAuthor extends AuthorRepository {


    @Query(nativeQuery = true,
            value = "select * from library.authors a" +
                    " inner join library.editorials e" +
                    " on a.editorial_id = e.id" +
                    " where a.name like %:#{#dto.getAuthorName()}%" +
                    " or e.name like %:#{#dto.getEditorialName()}%")
    Optional<Author> findMatcher(@Param("dto") AuthorDTO dto);

    @Query(value = "select a from Author a" +
            " inner join fetch a.editorial e" +
            " where lower(a.name) like lower(concat('%', :#{#dto.getAuthorName()}, '%'))" +
            " or lower(e.name) like %:#{#dto.getEditorialName()}%")
    Optional<Author> jpqlMatcher(@Param("dto") AuthorDTO dto);


    @Query(value = "select a from Author a" +
            " inner join a.editorial e" +
            " where lower(a.name) like lower(concat('%', :#{#dto.getAuthorName()}, '%'))" +
            " or lower(e.name) like %:#{#dto.getEditorialName()}%")
    @EntityGraph(attributePaths = {"editorial", "articles"})
    Optional<Author> graphMatcher(@Param("dto") AuthorDTO dto);


    @Query(value = "select a from Author a" +
            " inner join a.editorial e" +
            " where lower(a.name) like lower(concat('%', :#{#dto.get('authorName')}, '%'))" +
            " or lower(e.name) like %:#{#dto['editorialName']}%")
    @EntityGraph(attributePaths = {"editorial", "articles"})
    Optional<Author> mapMatcher(@Param("dto") Map<String, Object> dto);
}
