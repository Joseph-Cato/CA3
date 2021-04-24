package socialmedia;

public class Comment extends Post {

    private String message;
    private Post originalPost;
    private boolean actionable = true;

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

    @Override
    public void deletePost() {

        setHandle(null);

        message = "The original content was removed from the system and is no longer available.";

        actionable = false;
    }

    public boolean isActionable() {
        return actionable;
    }
}