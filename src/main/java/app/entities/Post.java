package app.entities;

public class Post {

    private int post_id;
    private String title;
    private String content;
    private String image;

    public Post(int post_id, String title, String content, String image) {
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
