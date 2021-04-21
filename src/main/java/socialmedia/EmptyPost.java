package socialmedia;

public class EmptyPost extends Post{

    //TODO explain why separate subclass instead of part of Post (actionable posts)
    public EmptyPost(TempPlatform tempPlatform) {
        this.message = "The original content was removed from the system and is no longer available.";
        setUniqueID(tempPlatform);
    }
}
