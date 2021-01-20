package test.jpa.data.demofetchjpa.domain.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table(name = "post_details")
public class PostDetail {

    @EmbeddedId
    private PostCompositeIds postCompositeIds;

    private LocalDate publishDate;

    public PostCompositeIds getPostCompositeIds() {
        return postCompositeIds;
    }

    public PostDetail setPostCompositeIds(PostCompositeIds postCompositeIds) {
        this.postCompositeIds = postCompositeIds;
        return this;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public PostDetail setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PostDetail.class.getSimpleName() + "[", "]").add("postCompositeIds=" + postCompositeIds)
                                                                                  .add("publishDate=" + publishDate)
                                                                                  .toString();
    }
}
