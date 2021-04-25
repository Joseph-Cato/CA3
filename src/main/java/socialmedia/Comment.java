package socialmedia;

import java.util.ArrayList;

public class Comment extends Post {

    private String message;
    private Post originalPost;
    private boolean actionable = true;
    private int numberOfEndorsements;
    private int numberOfComments;

    private static int totalNumberOfComments = 0;

    private ArrayList<Comment> comments = new ArrayList<>();

    public Comment(String handle, Original original, String message) {
        super(handle);

        originalPost = original;
        this.message = message;

        totalNumberOfComments += 1;
    }

    public Comment(String handle, Comment original, String message) {
        super(handle);

        originalPost = original;
        this.message = message;

        totalNumberOfComments += 1;
    }

    public static void setTotalNumberOfComments(int value) {
        totalNumberOfComments = value;
    }

    public String getMessage() {
        return message;
    }

    public Original getOriginalPost() {
        return (Original) originalPost;
    }

    public Comment getOriginalComment() {
        return (Comment) originalPost;
    }

    @Override
    public void deletePost() {

        setHandle(null);

        message = "The original content was removed from the system and is no longer available.";

        actionable = false;

        numberOfComments = 0;

        numberOfEndorsements = 0;

        totalNumberOfComments -= 1;

    }

    public boolean isActionable() {
        return actionable;
    }

    public int getNumberOfEndorsements() {
        return numberOfEndorsements;
    }

    public void addEndorsement() {
        numberOfEndorsements += 1;
    }

    public void removeEndorsement() {
        numberOfEndorsements -= 1;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void addComment(Comment comment) {

        comments.add(comment);

        numberOfComments += 1;
    }

    public void removeComment() {
        numberOfComments -= 1;
    }

    public static int getTotalNumberOfComments() {

        return totalNumberOfComments;
    }
}