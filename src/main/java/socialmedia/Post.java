package socialmedia;

public class Post {

    private static int numberOfPosts = 0;
    private int id;
    private String handle;
    public boolean actionable;

    public Post(String handle) {

        this.handle = handle;
        this.id = numberOfPosts;

        numberOfPosts += 1;
    }

    public void deletePost() {

        handle = null;

        numberOfPosts -= 1;

    }

    public boolean isActionable() {
        return actionable;
    }

    public static void setNumberOfPosts(int numberOfPosts) {
        Post.numberOfPosts = numberOfPosts;
    }

    public static int getNumberOfPosts() {
        return numberOfPosts;
    }

    public int getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

}