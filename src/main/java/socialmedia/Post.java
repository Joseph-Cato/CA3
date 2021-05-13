package socialmedia;


/**
 * The Post class. This is a superclass for {@link Original}, {@link Comment} and {@link Endorsement}.
 *
 * Contains static counter variable numberOfPosts that tracks the total number of Posts that have ever been made
 * Contains id, handle and actionable
 */
public class Post {

    private static int numberOfPosts = 0;
    private final int ID;
    private String handle;
    private boolean actionable;


    /**
     * Constructor method for Post
     * @param handle A String that represents the Account that is to be associated with the Post
     */
    public Post(String handle) {
      
        this.handle = handle;
        numberOfPosts += 1;
        this.ID = numberOfPosts;

    }

    /**
     * Method that 'deletes' a Post by setting handle to null.
     * Also removes 1 from the numberOfPosts variable.
     */
    public void deletePost() {
        handle = null;
        numberOfPosts -= 1;
    }

    //TODO - comment this
    public void setActionable(boolean actionable) {
        this.actionable = actionable;
    }

    /**
     * Method that returns whether the Post is actionable or not
     * @return Returns boolean that determines whether Post is actionable
     */
    public boolean isActionable() {
        return actionable;
    }

    /**
     * Method that sets the counter variable numberOfPosts
     * @param numberOfPosts The int that will become the new numberOfPosts
     */
    public static void setNumberOfPosts(int numberOfPosts) {
        Post.numberOfPosts = numberOfPosts;
    }

    /**
     * Method that returns the number of total posts
     * @return Returns the int numberOfPosts
     */
    public static int getNumberOfPosts() {
        return numberOfPosts;
    }

    /**
     * Method that returns the Post's ID
     * @return Returns the int ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Method that returns the Post's handle
     * @return Returns the String handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Method that sets the String handle associated with the Account
     * @param handle The String handle that is to be set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

}