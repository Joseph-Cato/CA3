package socialmedia;

import java.util.ArrayList;

public class Endorsement extends Post {

    private Post endorsedPost;
    private String message;

    private static int numberOfEndorsements = 0;


    public Endorsement(String handle, Original endorsedPost) {
        super(handle);

        this.endorsedPost = endorsedPost;
        this.message = "EP@" + handle + ":" + endorsedPost.getMessage();

        numberOfEndorsements += 1;
    }

    public Endorsement(String handle, Comment endorsedPost) {
        super(handle);

        this.endorsedPost = endorsedPost;
        this.message = "EP@" + handle + ":" + endorsedPost.getMessage();
    }

    @Override
    public void deletePost() {

        setHandle(null);

        endorsedPost = null;

        message = null;

        numberOfEndorsements -= 1;
    }

    public String getMessage() {
        return message;
    }

    public Post getEndorsedPost() {
        return endorsedPost;
    }

    public static int getNumberOfEndorsements() {
        return numberOfEndorsements;
    }

    public static void setNumberOfEndorsements(int value) {
        numberOfEndorsements = value;
    }
}
