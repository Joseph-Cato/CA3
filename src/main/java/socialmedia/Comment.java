package socialmedia;

import java.util.HashSet;

/**
 * This is the Comment Class the extends the {@link Post} Class
 *
 * Contains static counter variable totalNumberOfComments that tracks the total number of Comments
 * Contains message, originalPost, numberOfEndorsements and numberOfComments
 * Also Contains 2 HashMaps to contain Comments and Endorsements
 */
public class Comment extends Post {
    private String message;
    private Post originalPost;
    private int numberOfEndorsements;
    private int numberOfComments;
    private static int totalNumberOfComments = 0;

    private HashSet<Comment> comments = new HashSet<Comment>();
    private HashSet<Endorsement> endorsements = new HashSet<Endorsement>();

    /**
     * Constructor method for Comment object
     * @param handle String handle that represents the Account that is to be associated with the Comment
     * @param original Original Post that is being commented on
     * @param message String message that the Original will display
     */
    public Comment(String handle, Original original, String message) {
        super(handle); // Super constructor called by default
        actionable = true; // Comments are actionable by default
        originalPost = original;
        this.message = message;
        totalNumberOfComments += 1;
    }

    /**
     * Overloaded Constructor method for Comment object
     * @param handle String handle that represents the Account that is to be associated with the Original
     * @param original Original Post that is being commented on
     * @param message String message that the Original will display
     */
    public Comment(String handle, Comment original, String message) {
        super(handle);
        originalPost = original;
        this.message = message;
        totalNumberOfComments += 1;
    }

    /**
     * Method that sets numberOfOriginals to a certain value
     * @param value int value that is to be set as totalNumberOfComments
     */
    public static void setTotalNumberOfComments(int value) {
        totalNumberOfComments = value;
    }

    /**
     * Method that returns String message
     * @return Returns String message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method that returns Original Post
     * @return Returns Original Post that is being commented on
     */
    public Original getOriginalPost() {
        return (Original) originalPost;
    }

    /**
     * Method that returns Comment Post
     * @return Returns Comment Post that is being commented on
     */
    public Comment getOriginalComment() {
        return (Comment) originalPost;
    }

    /**
     * Method that deletes the Comment by clearing all its data
     */
    @Override
    public void deletePost() {

        setHandle(null);

        message = "The original content was removed from the system and is no longer available.";

        actionable = false;

        numberOfComments = 0;

        numberOfEndorsements = 0;

        totalNumberOfComments -= 1;

    }

    /**
     * Method that gets number of Endorsements
     * @return Returns int numberOfEndorsements
     */
    public int getNumberOfEndorsements() {
        return numberOfEndorsements;
    }

    /**
     * Method that gets HashMap of Endorsements
     * @return Returns HashMap of Endorsements of Original
     */
    public HashSet<Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * Method that adds Endorsement to HashMap of Endorsements
     * @param endorsement The Endorsement to be added
     */
    public void addEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
        numberOfEndorsements += 1;
    }

    /**
     * Method that removes Endorsement from HashMap of Endorsements
     * @param endorsement The Endorsement to be removed
     */
    public void removeEndorsement(Endorsement endorsement) {
        endorsements.remove(endorsement);
        numberOfEndorsements -= 1;
    }

    /**
     * Method that gets numberOfComments
     * @return Returns int numberOfComments
     */
    public int getNumberOfComments() {
        return numberOfComments;
    }

    /**
     * Method that adds Comment to HashMap of Comments
     * @param comment The Comment to be added
     */
    public void addComment(Comment comment) {
        comments.add(comment);
        numberOfComments += 1;
    }

    /**
     * Method that gets totalNumberOfComments
     * @return Returns int totalNumberOfComments
     */
    public static int getTotalNumberOfComments() {
        return totalNumberOfComments;
    }
}