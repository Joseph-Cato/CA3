package socialmedia;


public class Original extends Post {

    public Original(String handle, String message, Platform platform) {
        this.posterHandle = handle;
        this.message = message;
        originalPoster = platform.getAccount(handle);
        setUniqueID(platform);
    }


}
