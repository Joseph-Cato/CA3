package socialmedia;

public class Post {

    private static int numberOfPosts = 0;
    private int id;
    private String handle;

    public Post(String handle) {

        this.handle = handle;
        this.id = numberOfPosts;

        numberOfPosts += 1;

    }

    public void deletePost() {

        handle = null;

        numberOfPosts -= 1;

    }

    public static void setNumberOfPosts(int numberOfPosts) {
        Post.numberOfPosts = numberOfPosts;
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