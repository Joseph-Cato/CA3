package socialmedia;

/**
 * This is the Endorsement Class the extends the {@link Post} Class
 *
 * Contains static counter variable numberOfEndorsements that tracks the total number of Endorsements
 * Contains message and endorsedPost
 */
public class Endorsement extends Post {
    private Post endorsedPost;
    private String message;
    private static int numberOfEndorsements = 0;

    /**
     * Constructor method for Endorsement object
     * @param handle String handle that represents the Account that is to be associated with the Endorsement
     * @param endorsedPost Original Post that is to be endorsed
     */
    public Endorsement(String handle, Original endorsedPost) {
        super(handle); // Calls Super Constructor
        actionable = false;
        this.endorsedPost = endorsedPost;
        this.message = "EP@" + handle + ":" + endorsedPost.getMessage();
        numberOfEndorsements += 1;
    }

    /**
     * Overloaded Constructor method for Endorsement object
     * @param handle String handle that represents the Account that is to be associated with the Endorsement
     * @param endorsedPost Comment Post that is to be endorsed
     */
    public Endorsement(String handle, Comment endorsedPost) {
        super(handle); // Calls Super Constructor
        actionable = false;
        this.endorsedPost = endorsedPost;
        this.message = "EP@" + handle + ":" + endorsedPost.getMessage();

        numberOfEndorsements += 1;
    }

    /**
     * Method that deletes the Endorsement by clearing all its data
     */
    @Override
    public void deletePost() {
        setHandle(null);
        endorsedPost = null;
        message = null;
        numberOfEndorsements -= 1;
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
     * @return Returns Original Post that is being endorsed
     */
    public Post getEndorsedPost() {
        return endorsedPost;
    }

    /**
     * Method that gets number of Endorsements
     * @return Returns int numberOfEndorsements
     */
    public static int getNumberOfEndorsements() {
        return numberOfEndorsements;
    }

    /**
     * Method that sets numberOfEndorsements to a certain value
     * @param value int value that is to be set as numberOfEndorsements
     */
    public static void setNumberOfEndorsements(int value) {
        numberOfEndorsements = value;
    }
}
