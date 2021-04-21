package socialmedia;

public class Endorsement extends Post {
    Post original;

    public Endorsement(String handle, int id, Platform platform) {
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
