package socialmedia;

public class Endorsement extends Post {
    Post original;

    public Endorsement(String handle, int id, TempPlatform tempPlatform) {
        this.posterHandle = handle;
        originalPoster = tempPlatform.getAccount(handle);
        original = tempPlatform.getPost(id);
        message = "EP@" + handle + ": " + tempPlatform.getPost(id).getMessage();
        original.addEndorsement(this);
        setUniqueID(tempPlatform);
    }

    public Post getOriginal() {
        return original;
    }
}
