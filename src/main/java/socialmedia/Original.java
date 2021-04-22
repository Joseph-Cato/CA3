package socialmedia;

public class Original extends Post {

    private String message;
    private int numberOfEndorsements = 0;

    public Original(String handle, String message) {
        super(handle);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
