package socialmedia;

import java.util.HashSet;

/**
 * The Account class. An instance of this is created for each new user.
 *
 * contains static counter variable numberOfAccounts that tracks the total number of accounts
 * contains NUMERICAL_IDENTIFIED, handle, description, totalEndorsementsReceived and totalPosts
 * Contains 3 HashSets that hold each Original, Comment and Endorsement relevant to the Account
 */
public class Account {
    private static int numberOfAccounts = 0;
    private final int NUMERICAL_IDENTIFIER;
    private String handle;
    private String description;
    private int totalEndorsementsReceived = 0;
    private int totalPosts = 0;

    private HashSet<Original> originals = new HashSet<Original>();
    private HashSet<Comment> comments = new HashSet<Comment>();
    private HashSet<Endorsement> endorsements = new HashSet<Endorsement>();

    /**
     * Constructor method for Account
     * @param handle A String that represents the user handle that is to be associated with the Account
     * @param description A String paragraph that self-describes the user associated with the Account
     */
    public Account(String handle, String description){

        // Creates the account using the passed parameters
        this.handle = handle;
        this.description = description;
        NUMERICAL_IDENTIFIER = numberOfAccounts;
        numberOfAccounts += 1;

    }

    /**
     * Method that adds 1 to the totalEndorsementsReceived counter
     */
    public void addEndorsementsReceived() {
        totalEndorsementsReceived += 1;
    }

    /**
     * Method that removes 1 to the totalEndorsementsReceived counter
     */
    public void removeEndorsementsReceived() {
        totalEndorsementsReceived -= 1;
    }

    /**
     * Method that returns number of Endorsements received
     * @return Returns number of Endorsements the Account has received
     */
    public int getTotalEndorsementsReceived() {
        return totalEndorsementsReceived;
    }

    /**
     * Method that adds Comment to ArrayList of Comments associated with Account
     * @param comment Comment to be added to the ArrayList
     */
    public void addComment(Comment comment) {
        comments.add(comment);
        totalPosts += 1;
    }

    public HashSet<Comment> getComments() {
        return comments;
    }

    public HashSet<Original> getOriginals() {
        return originals;
    }

    /**
     * Method that removes Comment from ArrayList of Comments associated with Account
     * @param comment Comment to be removed from the ArrayList
     */
    public void removeComment(Comment comment) {
        comments.remove(comment);
        totalPosts -= 1;
    }

    /**
     * Method that adds Original to ArrayList of Originals associated with Account
     * @param original Original to be added to the ArrayList
     */
    public void addOriginal(Original original) {
        originals.add(original);
        totalPosts += 1;
    }

    /**
     * Method that removes Original from ArrayList of Originals associated with Account
     * @param original Original to be removed from the ArrayList
     */
    public void removeOriginal(Original original) {
        originals.remove(original);
        totalPosts -= 1;
    }

    /**
     * Method that adds Endorsement to ArrayList of Endorsements associated with Account
     * @param endorsement Endorsement to be added to the ArrayList
     */
    public void addEndorsement(Endorsement endorsement) {
        endorsements.add(endorsement);
        totalPosts += 1;
    }

    /**
     * Method that removes Endorsement from ArrayList of Endorsements associated with Account
     * @param endorsement Endorsement to be removed from the ArrayList
     */
    public void removeEndorsement(Endorsement endorsement) {
        endorsements.remove(endorsement);
        totalPosts -= 1;
    }

    /**
     * Method that returns the HashMap of Endorsements
     * @return Returns HashMap of Endorsements associated with the Account
     */
    public HashSet<Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * Method that returns the HashMap of Endorsements
     * @return Returns HashMap of Endorsements associated with the Account
     */
    public static int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    /**
     * Method that sets the counter variable numberOfAccounts
     * @param value New number to set the counter variable
     */
    public static void setNumberOfAccounts(int value) {
        numberOfAccounts = value;
    }

    /**
     * Method that gets the String handle associated with the Account
     * @return Returns the String handle associated with
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Method that returns the NUMERICAL_IDENTIFIER
     * @return Returns int of NUMERICAL_IDENTIFIER associated with the Account
     */
    public int getNUMERICAL_IDENTIFIER() {
        return NUMERICAL_IDENTIFIER;
    }

    /**
     * Method that returns the description
     * @return Returns String description associated with the Account
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that sets the description
     * @param description The new String description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method that resets the counter variable numberOfAccounts back to 0
     */
    public static void resetNumberOfAccounts() {
        numberOfAccounts = 0;
    }

    /**
     * Method that sets the handle
     * @param handle The new String handle to be set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * Method the returns the number of posts made by the Account
     * @return Returns the int totalPosts
     */
    public int getTotalPosts() {
        return totalPosts;
    }
}

