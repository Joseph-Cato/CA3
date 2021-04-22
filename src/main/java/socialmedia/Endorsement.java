package socialmedia;

public class Endorsement extends Post {

    private Post endorsedPost;
    private String message;

    public Endorsement(String handle, Original endorsedPost) {
        super(handle);

        this.endorsedPost = endorsedPost;
        this.message = "EP@" + handle + ":" + endorsedPost.getMessage();
    }

    public Endorsement(String handle, Comment endorsedPost) {
        super(handle);

        this.endorsedPost = endorsedPost;
        this.message = "EP@" + handle + ":" + endorsedPost.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
