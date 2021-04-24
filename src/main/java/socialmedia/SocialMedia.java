package socialmedia;

import java.io.IOException;

public class SocialMedia implements SocialMediaPlatform {

    public Platform platform;

    public SocialMedia() {

        platform = new Platform();

        Post.setNumberOfPosts(0);
    }

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {

        // Calls other method with blanc description string
        return createAccount(handle, "");
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {

        // Checks if handle is valid
        if (handle.equals("") || handle.length() > 30 || handle.contains(" ")) throw new InvalidHandleException();

        // Checks if handle already exists in platform
        if (platform.getAccount(handle) != null) throw new IllegalHandleException();

        //Creates new account
        Account newAccount = new Account(handle, description);

        // Adds account to platform
        platform.addAccount(handle, newAccount);

        // Returns the id of the created account
        return newAccount.getNUMERICAL_IDENTIFIER();

    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {

        // TODO Remove all types of posts associated with this account

        // Finds the account with the corresponding Id
        for (Account i : platform.getAccounts().values()) {
            if (i.getNUMERICAL_IDENTIFIER() == id) {
                try {
                    // Removes that account using the other method
                    removeAccount(i.getHandle());
                    // Returns void if this if is evaluated as true (i.e the id is found)
                    return;
                } catch (HandleNotRecognisedException e) {
                    e.printStackTrace();
                }
            }
        }

        // If the if statement above was never evaluated as true this exception will be thrown
        throw new  AccountIDNotRecognisedException();

    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {

        // TODO Remove all types of posts associated with this account

        // Removes account from the HashMap accounts
        Account account = platform.removeAccount(handle);

        // if HashMap.removeAccount() returns null then the value was not found
        // (no account with that handle exists)
        if (account == null) throw new HandleNotRecognisedException();

    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

        // Checks if old handle exists in system
        Account account = platform.getAccount(oldHandle);
        if (account == null) throw new HandleNotRecognisedException();

        // Checks if new handle already exists in system
        Account account1 = platform.getAccount(newHandle);
        if (account1 != null) throw new IllegalHandleException();

        // Checks if the new handle is valid
        if (newHandle.equals("") || newHandle.length() > 30 || newHandle.contains(" ")) throw new InvalidHandleException();

        // Removes old data from system
        platform.removeAccount(oldHandle);

        // Changes handle of old account
        account.setHandle(newHandle);

        // Adds new account and handle to system
        platform.addAccount(newHandle, account);

    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

        // Gets account from system, throws exception if user is not found.
        Account account = platform.getAccount(handle);
        if (account == null) throw new HandleNotRecognisedException();

        // Removes account from system
        platform.removeAccount(handle);

        // Changes description of account
        account.setDescription(description);

        // Add account back into system
        platform.addAccount(handle, account);

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {

        // TODO - create when basic post framework is in

        return null;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {

        // Checks if user handle exists on system
        if (!platform.getAccounts().containsKey(handle)) throw new HandleNotRecognisedException();

        // If message is empty or has more than 100 characters is it invalid
        if (message.equals("") || message.length() > 100) throw new InvalidPostException();

        // Creates new original
        Original original = new Original(handle, message);

        // Adds original to platform
        platform.addOriginal(original);

        // Adds original to Account
        platform.getAccount(handle).addOriginal(original);

        return original.getId();

    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

        // Checks if the user exists in the system, throws HandleNotRecognisedException otherwise
        if (platform.getAccount(handle) == null) throw new HandleNotRecognisedException();

        // Gets the original post, one of these will be null depending on the type of post
        // TODO .get() may throw NullPointerException instead of returning null
        Original original = platform.getOriginals().get(id);
        Comment comment = platform.getComments().get(id);
        Endorsement endorsement = platform.getEndorsements().get(id);


        if (original != null) {
            // If the post is a original:

            // If post is deleted, NotActionablePostException is thrown
            if (!original.isActionable()) throw new NotActionablePostException();

            //The endorsement object will be created with the original
            endorsement = new Endorsement(handle, original);
        } else if (comment != null) {
            // If the post is a comment:

            // If post is deleted, NotActionablePostException is thrown
            if (!comment.isActionable()) throw new NotActionablePostException();

            // The endorsement object will be created with the comment
            endorsement = new Endorsement(handle, comment);
        } else if (endorsement != null){

            // If the post is an endorsement a NotActionablePostException is thrown
            throw new NotActionablePostException();
        } else {

            // If no post object with the specified id is found a PostIDNotRecognisedException is thrown
            throw new PostIDNotRecognisedException();
        }

        // Adds endorsement to system
        platform.addEndorsement(endorsement);

        // Adds endorsement to Account
        platform.getAccount(handle).addEndorsement(endorsement);


        return endorsement.getId();

    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

        // If message is empty or greater than 100 character a InvalidPostException is thrown
        if (message.equals("") || message.length() > 100) throw new InvalidPostException();

        // Account is retrieved from the platform, if no account is found a HandleNotRecognisedException is thrown
        Account account = platform.getAccount(handle);
        if (account == null) throw new HandleNotRecognisedException();

        Comment newComment;

        // Gets the post from the systems collections, these objects will be null if they are the wrong type
        Comment comment = platform.getComments().get(id);
        Original original = platform.getOriginals().get(id);
        Endorsement endorsement = platform.getEndorsements().get(id);

        if (comment != null) {
            // If the post is a comment:

            // If post is deleted, NotActionablePostException is thrown
            if (!comment.isActionable()) throw new NotActionablePostException();

            // An appropriate Comment object is created
            newComment = new Comment(handle, comment, message);
        } else if (original != null) {
            // If the post is an Original:

            // If post is deleted, NotActionablePostException is thrown
            if (!original.isActionable()) throw new NotActionablePostException();

            // An appropriate Comment object is created
            newComment = new Comment(handle, original, message);
        } else if (endorsement != null) {

            // If the post is an endorsement a NotActionablePostException is thrown
            throw new NotActionablePostException();
        } else {

            // If the post is not found in the system a PostIDNotRecognisedException is thrown
            throw new PostIDNotRecognisedException();
        }

        // Adds comment to the users Account
        account.addComment(newComment);

        // Adds comment to the platform
        platform.addComment(newComment);

        return newComment.getId();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub

        // One of these variables will be not null depending on the type of object the post is
        Original original = platform.getOriginals().get(id);
        Comment comment = platform.getComments().get(id);
        Endorsement endorsement = platform.getEndorsements().get(id);

        if (original != null) {

            original.deletePost();
        } else if (comment != null) {

            comment.deletePost();
        } else if (endorsement != null) {

            endorsement.deletePost();
        } else {

            // If id does not match any post object type then it does not exist, PostIDNotRecognisedException is thrown
            throw new PostIDNotRecognisedException();
        }

    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id)
            throws PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberOfAccounts() {

        return Account.getNumberOfAccounts();

    }

    @Override
    public int getTotalOriginalPosts() { // TODO - Maybe we could have separate HashMaps for the types of posts?
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalEndorsmentPosts() { // TODO - Maybe we could have separate HashMaps for the types of posts?
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalCommentPosts() { // TODO - Maybe we could have separate HashMaps for the types of posts?
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedPost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void erasePlatform() {
        // TODO Auto-generated method stub

    }

    @Override
    public void savePlatform(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }

}