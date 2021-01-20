package test.jpa.data.demofetchjpa.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "revisions")
public class Revision {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
    @SequenceGenerator(name = "revision_seq", allocationSize = 5)
    private Long id;

    private boolean revised;

    public Long getId() {
        return id;
    }

    public Revision setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isRevised() {
        return revised;
    }

    public Revision setRevised(boolean revised) {
        this.revised = revised;
        return this;
    }
}
