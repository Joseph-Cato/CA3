package socialmedia;

public class Post extends Object{ //TODO - i think extends Object is not needed, used it when trying to sort stuff

    private static int numberOfPosts = 0;
    private int id;
    private String handle;
    public boolean actionable; //TODO - this doesn't need to be public


    public Post(String handle) {

        this.handle = handle;

        numberOfPosts += 1;

        this.id = numberOfPosts;

    }

    public void deletePost() {

        handle = null;

        numberOfPosts -= 1;

    }

    public static int getNumberOfPostsTestingFunc() {
        return numberOfPosts;
    }

    public boolean isActionable() {
        return actionable;
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