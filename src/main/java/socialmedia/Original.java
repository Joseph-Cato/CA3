package socialmedia;

public class Original extends Post {

    private String message;
    private int numberOfEndorsements = 0;
    private boolean actionable = true;

    public Original(String handle, String message) {
        super(handle);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void deletePost() {
        setHandle(null);

        message = "The original content was removed from the system and is no longer available.";

        numberOfEndorsements = 0;

        actionable = false;
    }

    public boolean isActionable() {
        return actionable;
    }
}
