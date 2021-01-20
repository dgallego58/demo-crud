package test.jpa.data.demofetchjpa.data.dto;

public class PostDetailDTO {

    private String postTitle;
    private Long idRevision;



    public String getPostTitle() {
        return postTitle;
    }

    public PostDetailDTO setPostTitle(String postTitle) {
        this.postTitle = postTitle;
        return this;
    }

    public Long getIdRevision() {
        return idRevision;
    }

    public PostDetailDTO setIdRevision(Long idRevision) {
        this.idRevision = idRevision;
        return this;
    }
}
