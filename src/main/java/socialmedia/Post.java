package socialmedia;

import java.util.HashMap;

public class Post {
    int uniqueID;
    String posterHandle;
    String message; // TODO - endorsements don't have messages, keeping it in relevant classes will improve encapsulation and memory usage
    Account originalPoster;

    // ONLY FOR USE OF ORIGINALS AND COMMENTS. NOT ENDORSEMENTS.
    // TODO - Keeping this in comments and original classes with improve Encapsulation and memory usage
    private HashMap<Integer, Comment> comments = new HashMap<>();
    private HashMap<Integer, Endorsement> endorsements = new HashMap<>();

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Platform platform) { //TODO - I think this would be better suited in a Post class super constructor
        this.uniqueID = platform.getCurrentPostID()+1;
    }

    public String getPosterHandle() {
        return posterHandle;
    }

    public String getMessage() {
        return message;
    }

    public Account getOriginalPoster() {
        return originalPoster;
    }

    public HashMap<Integer, Comment> getComments() {
        return comments;
    }

    public HashMap<Integer, Endorsement> getEndorsements() {
        return endorsements;
    }

    public void addEndorsement(Endorsement endorsement) {
        this.endorsements.put(endorsement.getUniqueID(), endorsement);
    }

    public void addComment (Comment comment) {
        this.comments.put(comment.getUniqueID(), comment);
    }

    public static void deletePost(Post post, Platform platform) {
        if (post.endorsements != null) {
            for (Endorsement endorsement : post.endorsements.values()) {
                platform.removePost(endorsement);
            }
        }

        if (post.comments != null) {
            for (Comment comment : post.comments.values()) {
                comment.setOriginal(new EmptyPost(platform));
            }
        }
    }

}
