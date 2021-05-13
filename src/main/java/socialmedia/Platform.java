package socialmedia;

import java.util.HashMap;

/**
 * The Platform Class.
 * This is the 'main' class that holds all HashMaps of Accounts, Comments, Originals and Endorsements
 * It also holds the counters, just before saving and loading
 * Required by {@link SocialMedia}, {@link Account}, {@link Post}, {@link Original}, {@link Comment} and {@link Endorsement}
 */
public class Platform {


    // Below are HashMaps that store each Account, Original, Comment and Endorsement respectively
    private HashMap<String, Account> accounts = new HashMap<>(100);
    private HashMap<Integer, Original> originals = new HashMap<>(100);
    private HashMap<Integer, Comment> comments = new HashMap<>(100);
    private HashMap<Integer, Endorsement> endorsements = new HashMap<>(100);

    // These variables hold the static counters from other classes and are not generally used during runtime
    private int numberOfAccounts;
    private int numberOfPosts;
    private int numberOfEndorsements;
    private int numberOfComments;
    private int numberOfOriginals;


    /**
     * Method to get HashMap of total Accounts
     * @return HashMap of Accounts
     */
    HashMap<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * Method to add an Account to the HashMap of Accounts
     * @param handle The String handle of the Account, to be used as the key
     * @param account The Account to be put in the HashMap to be used as the value
     */
    void addAccount(String handle, Account account){
        accounts.put(handle, account);
    }

    /**
     * Method to remove an Account form the HashMap of Accounts
     * @param handle The String handle of the Account to be removed
     * @return Returns the Account that has been removed
     */
    Account removeAccount(String handle) {
        return accounts.remove(handle);
    }

    /**
     * Method that returns an Account based on a String handle passed
     * @param handle The String handle of the Account to be added
     * @return Returns the Account that has been added
     */
    Account getAccount(String handle) {
        return accounts.get(handle);
    }

    /**
     * Method that adds an Original to the HashMap of Originals
     * @param original The Original to be added
     */
    void addOriginal(Original original){
        int id = original.getID(); // Gets ID of Account to be used as key
        originals.put(id, original);
    }

    /**
     * Method that adds Comment to the HashMap of Comments
     * @param comment The Comment to be added
     */
    void addComment(Comment comment) {
        comments.put(comment.getID(), comment);
    }

    /**
     * Method that adds Endorsement to the HashMap of Endorsements
     * @param endorsement The Endorsement to be added
     */
    void addEndorsement(Endorsement endorsement) {
        endorsements.put(endorsement.getID(), endorsement);
    }

    /**
     * Method to remove Endorsement from HashMap of Endorsements
     * @param endorsement The Endorsement to be removed
     */
    void removeEndorsement(Endorsement endorsement) {
        endorsements.remove(endorsement.getID(), endorsement);
    }

    /**
     * Method to return HashMap of Originals
     * @return HashMap of Originals
     */
    HashMap<Integer, Original> getOriginals() {
        return originals;
    }

    /**
     * Method to return HashMap of Comments
     * @return HashMap of Comments
     */
    HashMap<Integer, Comment> getComments() {
        return comments;
    }

    /**
     * Method to return HashMap of Endorsements
     * @return HashMap of Endorsements
     */
    HashMap<Integer, Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * Method to save counter variables to Platform object from relevant classes before saving it
     */
    void saveCounters() {
        this.numberOfAccounts = Account.getNumberOfAccounts();
        this.numberOfComments = Comment.getTotalNumberOfComments();
        this.numberOfEndorsements = Endorsement.getNumberOfEndorsements();
        this.numberOfOriginals = Original.getNumberOfPosts();
        this.numberOfPosts = Post.getNumberOfPosts();
    }

    /**
     * Method to load counter variables from Platform object to relevant classes after loading it
     */
    void loadCounters() {
        Account.setNumberOfAccounts(this.numberOfAccounts);
        Comment.setTotalNumberOfComments(this.numberOfComments);
        Endorsement.setNumberOfEndorsements(this.numberOfEndorsements);
        Original.setNumberOfOriginals(this.numberOfOriginals);
        Post.setNumberOfPosts(this.numberOfPosts);
    }

    /**
     * Method to empty the HashMaps in the Platform object
     */
    void eraseHashMaps() {
        accounts.clear();
        originals.clear();
        comments.clear();
        endorsements.clear();
    }

    /**
     * Method to reset each counter variable
     */
    void clearCounters() {
        Account.setNumberOfAccounts(0);
        Comment.setTotalNumberOfComments(0);
        Endorsement.setNumberOfEndorsements(0);
        Original.setNumberOfOriginals(0);
        Post.setNumberOfPosts(1);
    }

    /**
     *  Method used solely for testing.
     * @return HashMap of account handles and descriptions in the system.
     */
    HashMap<String, String> printAccounts(){

        HashMap<String, String> accountList = new HashMap<>();

        accounts.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        return accountList;
    }

}
