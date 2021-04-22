package socialmedia;

public class Comment extends Post {
    Post original;

    public Comment(String handle, int id, String message, Platform platform) { //TODO - I feel the code would be much easier to debug using Post super constructor
        this.posterHandle = handle;
        this.message = message;
        setUniqueID(platform);
        originalPoster = platform.getAccount(handle);
        original = platform.getPost(id);
        if (original.getClass() == Endorsement.class) {
            original = ((Endorsement) original).getOriginal();
        }
        original.addComment(this);
    }

    public Post getOriginal() {
        return original;
    }

    public void setOriginal(Post original) {
        this.original = original;
    }
}
