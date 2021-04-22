package socialmedia;

public class Endorsement extends Post {
    Post original;

    public Endorsement(String handle, int id, Platform platform) { //TODO - I feel the code would be much easier to debug using Post super constructor
        this.posterHandle = handle;
        originalPoster = platform.getAccount(handle);
        original = platform.getPost(id);
        message = "EP@" + handle + ": " + platform.getPost(id).getMessage();
        original.addEndorsement(this);
        setUniqueID(platform);
    }

    public Post getOriginal() {
        return original;
    }
}
