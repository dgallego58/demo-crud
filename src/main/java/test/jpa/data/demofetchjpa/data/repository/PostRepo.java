package test.jpa.data.demofetchjpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.jpa.data.demofetchjpa.domain.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
}
