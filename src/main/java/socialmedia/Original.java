package socialmedia;

import java.util.HashSet;

/**
 * This is the Original Class the extends the {@link Post} Class
 *
 * Contains static counter variable numberOfOriginal that tracks the total number of Originals
 * Contains message, numberOfComments and numberOfEndorsements
 * Also Contains 2 HashMaps to contain Comments and Endorsements
 */
public class Original extends Post {

    private String message;
    private int numberOfEndorsements = 0;
    private int numberOfComments = 0;
    private static int numberOfOriginals = 0;

    private HashSet<Comment> comments = new HashSet<Comment>();
    private HashSet<Endorsement> endorsements = new HashSet<Endorsement>();

    /**
     * Constructor method for Original object
     * @param handle String handle that represents the Account that is to be associated with the Original
     * @param message String message that the Original will display
     */
    public Original(String handle, String message) {
        super(handle); // Calls super constructor
        actionable = true;
        this.message = message;
        numberOfOriginals += 1;
    }

    /**
     * Method that gets HashMap of Endorsements
     * @return Returns HashMap of Endorsements of Original
     */
    public HashSet<Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * Method that sets numberOfOriginals to a certain value
     * @param value int value that is to be set as numberOfOriginals
     */
    public static void setNumberOfOriginals(int value) {
        numberOfOriginals = value;
    }

    /**
     * Method that gets the numberOfOriginals
     * @return Returns int numberOfOriginals
     */
    public static int getNumberOfPosts() {
        return numberOfOriginals;
    }

    /**
     * Method that returns String message
     * @return Returns String message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Method that deletes the Original by clearing all its data and turning it into a generic empty message
     */
    @Override
    public void deletePost() {

        setHandle(null);

        message = "The original content was removed from the system and is no longer available.";

        numberOfEndorsements = 0;

        numberOfComments = 0;

        actionable = false;

        numberOfOriginals -= 1;
    }

    /**
     * Method that gets number of Endorsements
     * @return Returns int numberOfEndorsements
     */
    public int getNumberOfEndorsements() {
        return numberOfEndorsements;
    }

    /**
     * Method that adds Endorsement to HashMap of Endorsements
     * @param endorsement The Endorsement to be added
     */
    public void addEndorsement(Endorsement endorsement){
        endorsements.add(endorsement);
        numberOfEndorsements += 1;
    }

    /**
     * Method that removes Endorsement from HashMap of Endorsements
     * @param endorsement The Endorsement to be removed
     */
    public void removeEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
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

}
