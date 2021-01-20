package test.jpa.data.demofetchjpa.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class PostCompositeIds implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_post_id", foreignKey = @ForeignKey(name = "constraint_fk_post_id"))
    private Post postId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_revision_id", foreignKey = @ForeignKey(name = "constraint_fk_revision_id"))
    private Revision revisionId;

    public PostCompositeIds() {
        //jpa issues
    }

    public Post getPostId() {
        return postId;
    }

    public PostCompositeIds setPostId(Post postId) {
        this.postId = postId;
        return this;
    }

    public Revision getRevisionId() {
        return revisionId;
    }

    public PostCompositeIds setRevisionId(Revision revisionId) {
        this.revisionId = revisionId;
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

        PostCompositeIds that = (PostCompositeIds) o;

        if (!getPostId().equals(that.getPostId())) {
            return false;
        }
        return getRevisionId().equals(that.getRevisionId());
    }

    @Override
    public int hashCode() {
        int result = getPostId().hashCode();
        result = 31 * result + getRevisionId().hashCode();
        return result;
    }
}
