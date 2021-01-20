package test.jpa.data.demofetchjpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.jpa.data.demofetchjpa.domain.entity.Editorial;

import java.util.List;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {


    @Query("select e from Editorial e" +
            " join fetch e.authors a" +
            " join fetch a.books b" +
            " join fetch a.articles ar" +
            " where ar.name like 'ArticleName'")
    List<Editorial> joinFetchDemo();

}
