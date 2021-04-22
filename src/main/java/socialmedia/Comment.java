package socialmedia;

public class Comment extends Post {

    private String message;
    private Post originalPost;

    public Comment(String handle, Original original, String message) {
        super(handle);

        originalPost = original;
        this.message = message;
    }

    public Comment(String handle, Comment original, String message) {
        super(handle);

        originalPost = original;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Post getOriginalPost() {
        return originalPost;
    }
}