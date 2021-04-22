package socialmedia;

public class Comment extends Post {

    private String message;
    private Post originalPost;

    public Comment(String handle, Original original) {
        super(handle);

        originalPost = original;
    }

    public Comment(String handle, Comment original) {
        super(handle);

        originalPost = original;
    }

    public String getMessage() {
        return message;
    }
}