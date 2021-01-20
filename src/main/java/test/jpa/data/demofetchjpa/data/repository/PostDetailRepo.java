package test.jpa.data.demofetchjpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.jpa.data.demofetchjpa.data.dto.PostDetailDTO;
import test.jpa.data.demofetchjpa.domain.entity.PostCompositeIds;
import test.jpa.data.demofetchjpa.domain.entity.PostDetail;

import java.util.Optional;

public interface PostDetailRepo extends JpaRepository<PostDetail, PostCompositeIds> {



    @Query("select p from PostDetail p" +
            " join fetch p.postCompositeIds.postId poid" +
            " join fetch p.postCompositeIds.revisionId revid" +
            " where poid.title like :#{#dto.getPostTitle()}" +
            " and revid.id = :#{#dto.getIdRevision()}")
    Optional<PostDetail> matcher(@Param("dto") PostDetailDTO dto);
}
