package test.jpa.data.demofetchjpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.jpa.data.demofetchjpa.domain.entity.Revision;

public interface RevisionRepo extends JpaRepository<Revision, Long> {
}
