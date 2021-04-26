package socialmedia;

import java.util.ArrayList;

public class Original extends Post {

    private String message;
    private int numberOfEndorsements = 0;
    private int numberOfComments = 0;
    private boolean actionable = true;

    private static int numberOfOriginals = 0;

    private ArrayList<Comment> comments = new ArrayList<>();

    private ArrayList<Endorsement> endorsements = new ArrayList<>();

    public Original(String handle, String message) {
        super(handle);

        this.message = message;

        numberOfOriginals += 1;
    }

    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    public static void setNumberOfOriginals(int value) {
        numberOfOriginals = value;
    }

    public static int getNumberOfPosts() {
        return numberOfOriginals;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void deletePost() {

        setHandle(null);

        message = "The original content was removed from the system and is no longer available.";

        numberOfEndorsements = 0;

        numberOfComments = 0;

        actionable = false;

        numberOfOriginals -= 1;
    }

    public boolean isActionable() {
        return actionable;
    }

    public int getNumberOfEndorsements() {
        return numberOfEndorsements;
    }

    public void addEndorsement(Endorsement endorsement){

        endorsements.add(endorsement);

        numberOfEndorsements += 1;
    }

    public void removeEndorsement(Endorsement endorsement) {

        endorsements.add(endorsement);

        numberOfEndorsements -= 1;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void addComment(Comment comment) {

        comments.add(comment);

        numberOfComments += 1;
    }

    public void removeComment(Comment comment) {

        comments.add(comment);

        numberOfComments -= 1;
    }
}
