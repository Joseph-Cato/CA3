package socialmedia;

import java.util.HashMap;
import java.util.List;

public class Comment extends Post {
    Post original;

    public Comment(String handle, int id, String message, TempPlatform tempPlatform) {
        this.posterHandle = handle;
        this.message = message;
        setUniqueID(tempPlatform);
        originalPoster = tempPlatform.getAccount(handle);
        original = tempPlatform.getPost(id);
        original.addComment(this);
    }

    public Post getOriginal() {
        return original;
    }

    public void setOriginal(Post original) {
        this.original = original;
    }
}
