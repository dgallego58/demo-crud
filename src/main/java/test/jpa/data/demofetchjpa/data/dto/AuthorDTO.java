package test.jpa.data.demofetchjpa.data.dto;


public class AuthorDTO {

    private String authorName;
    private String editorialName;

    public static AuthorDTO create(){
        return new AuthorDTO();
    }

    public String getAuthorName() {
        return authorName;
    }

    public AuthorDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getEditorialName() {

        return editorialName;
    }

    public AuthorDTO setEditorialName(String editorialName) {
        this.editorialName = editorialName;
        return this;
    }
}
