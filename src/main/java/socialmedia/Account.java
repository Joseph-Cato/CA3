package socialmedia;

public class Account {

    public static int numberOfAccounts;

    private final int NUMERICAL_IDENTIFIER;

    private String handle;

    private String description;

/* //TODO Format these according to classes
    private HashSet<Comment> comments = new HashSet<Comment>();

    private HashSet<Original> originals = new HashSet<Original>();

    private HashSet<Endorsement> endorsements = new HashSet<Endorsement>();
*/
    public Account(String handle, String description){

        this.handle = handle;

        this.description = description;

        numberOfAccounts =+ 1;

        NUMERICAL_IDENTIFIER = numberOfAccounts;
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
}
