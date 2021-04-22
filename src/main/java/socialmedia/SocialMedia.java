package socialmedia;

import java.io.IOException;

public class SocialMedia implements SocialMediaPlatform {

    public Platform platform = new Platform();

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
        if (platform.getAccount(handle) == null) throw new HandleNotRecognisedException();
        if (message.length() == 0 || message.length() > 100) throw new InvalidPostException();

        Original newOriginal = new Original(handle, message, platform);
        platform.addPost(newOriginal.uniqueID, newOriginal);
        platform.setCurrentPostID(platform.getCurrentPostID()+1); //TODO - I feel this would be better in a post super constructor, the code is slightly hard to follow like this
        return newOriginal.getUniqueID();
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        if (platform.getAccount(handle) == null) throw new HandleNotRecognisedException();
        if (platform.getPost(id) == null) throw new PostIDNotRecognisedException();
        if (platform.checkIfEndorsement(id) == false) throw new NotActionablePostException();
        if (platform.checkIfEmptyPost(id) == false) throw new NotActionablePostException();

        Endorsement newEndorsement = new Endorsement(handle, id, platform);
        platform.addPost(newEndorsement.uniqueID, newEndorsement);
        platform.setCurrentPostID(platform.getCurrentPostID()+1); //TODO - I feel this would be better in a post super constructor, the code is slightly hard to follow like this
        return newEndorsement.getUniqueID();
    }
// TODO explain why endorsed posts can't be commented on (contradictions)
    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        if (platform.getAccount(handle) == null) throw new HandleNotRecognisedException();
        if (platform.getPost(id) == null) throw new PostIDNotRecognisedException();
        if (platform.checkIfEmptyPost(id) == false) throw new NotActionablePostException();
        if (message.length() == 0 || message.length() > 100) throw new InvalidPostException();

        Comment newComment = new Comment(handle, id, message, platform);
        platform.addPost(newComment.uniqueID, newComment);
        platform.setCurrentPostID(platform.getCurrentPostID()+1); //TODO - I feel this would be better in a post super constructor, the code is slightly hard to follow like this
        return newComment.getUniqueID();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        if (platform.getPost(id) == null) throw new PostIDNotRecognisedException();
        Post postToBeDeleted = platform.getPost(id);
        Post.deletePost(postToBeDeleted, platform);
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        if (platform.getPost(id) == null) throw new PostIDNotRecognisedException();
        Post post = platform.getPost(id);
        String stat = "ID: " + post.getUniqueID() + "\n" +
                "Account: " + post.getPosterHandle() + "\n" +
                "No. endorsements: " + post.getEndorsements().size() +
                "No. comments: " + post.getComments().size() + "\n" +
                post.getMessage();
        return stat;
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
        int noOfOriginals = 0;
        for (Post post : platform.getPosts().values()) {
            if (post.getClass() == Original.class) {
                noOfOriginals += 1;
            }
        }
        return noOfOriginals;
    }

    @Override
    public int getTotalEndorsmentPosts() { // TODO - Maybe we could have separate HashMaps for the types of posts?
        int noOfEndorsements = 0;
        for (Post post : platform.getPosts().values()) {
            if (post.getClass() == Endorsement.class) {
                noOfEndorsements += 1;
            }
        }
        return noOfEndorsements;
    }

    @Override
    public int getTotalCommentPosts() { // TODO - Maybe we could have separate HashMaps for the types of posts?
        int noOfComments = 0;
        for (Post post : platform.getPosts().values()) {
            if (post.getClass() == Comment.class) {
                noOfComments += 1;
            }
        }
        return noOfComments;
    }

    @Override
    public int getMostEndorsedPost() {
        int noOfEndorsements = -1;
        Post mostEndorsedPost = null;
        for (Post post : platform.getPosts().values()) {
            if (post.getEndorsements() != null); { //TODO - Are you sure an empty HashMap returns null? I know about the existence of java.util.Collections.emptyMap()
                if (post.getEndorsements().size() > noOfEndorsements) { // TODO - HashMap.size() may produce a NullPointerException (Should we try-catch this?)
                    noOfEndorsements = post.getEndorsements().size();
                    mostEndorsedPost = post;
                }
            }
        }
        return mostEndorsedPost.getUniqueID(); //TODO - Post.getUniqueID may produce NullPointerException (should we try-catch this?)
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