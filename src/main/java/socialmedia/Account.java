package socialmedia;

import java.util.HashSet;

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


    public static void setNumberOfAccounts(int value) {
        numberOfAccounts = value;
    }

    public Account(String handle, String description){

        this.handle = handle;

        this.description = description;

        NUMERICAL_IDENTIFIER = numberOfAccounts;

        numberOfAccounts += 1;

    }

    public void addEndorsementsReceived() {
        totalEndorsementsReceived += 1;
    }

    public void removeEndorsementsReceived() {
        totalEndorsementsReceived -= 1;
    }

    public int getTotalEndorsementsReceived() {
        return totalEndorsementsReceived;
    }

    public void addComment(Comment comment) {
        comments.add(comment);

        totalPosts += 1;
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);

        totalPosts -= 1;
    }

    public void addOriginal(Original original) {
        originals.add(original);

        totalPosts += 1;

    }

    public void removeOriginal(Original original) {

        originals.remove(original);

        totalPosts -= 1;
    }

    public void addEndorsement(Endorsement endorsement) {

        endorsements.add(endorsement);

        totalPosts += 1;
    }

    public void removeEndorsement(Endorsement endorsement) {

        endorsements.remove(endorsement);

        totalPosts -= 1;
    }

    public HashSet<Endorsement> getEndorsements() {
        return endorsements;
    }

    public static int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public String getHandle() {
        return handle;
    }

    public int getNUMERICAL_IDENTIFIER() {
        return NUMERICAL_IDENTIFIER;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void resetNumberOfAccounts() {
        numberOfAccounts = 0;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

}

