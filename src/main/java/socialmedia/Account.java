package socialmedia;

public class Account {

    private static int numberOfAccounts = 0;

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

        numberOfAccounts += 1;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public static void resetNumberOfAccounts() {
        numberOfAccounts = 0;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }


}

