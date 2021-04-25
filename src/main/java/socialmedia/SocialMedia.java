package socialmedia;

import java.io.IOException;
import java.util.Map;

public class SocialMedia implements SocialMediaPlatform {

    public Platform platform;

    public SocialMedia() {

        platform = new Platform();

        Endorsement.setNumberOfEndorsements(0);

        Post.setNumberOfPosts(0);

        Original.setNumberOfOriginals(0);

        Comment.setTotalNumberOfComments(0);

        Account.setNumberOfAccounts(0);
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
        Original original = platform.getOriginals().get(id);
        Comment comment = platform.getComments().get(id);
        Endorsement endorsement = platform.getEndorsements().get(id);


        if (original != null) {
            // If the post is a original:

            // If post is deleted, NotActionablePostException is thrown
            if (!original.isActionable()) throw new NotActionablePostException();

            //The endorsement object will be created with the original
            endorsement = new Endorsement(handle, original);

            // The original will have 1 added to numberOfEndorsements
            original.addEndorsement();

            // One will be added to the account total number of endorsements
            Account account = platform.getAccounts().get( original.getHandle() );
            account.setTotalEndorsements( account.getTotalEndorsements() + 1);
        } else if (comment != null) {
            // If the post is a comment:

            // If post is deleted, NotActionablePostException is thrown
            if (!comment.isActionable()) throw new NotActionablePostException();

            // The endorsement object will be created with the comment
            endorsement = new Endorsement(handle, comment);

            // The comment will have 1 added to numberOfEndorsements
            comment.addEndorsement();

            // One will be added to the endorsed accounts total number of endorsements
            Account account = platform.getAccounts().get( comment.getOriginalPost().getHandle() );
            account.setTotalEndorsements( account.getTotalEndorsements() + 1);
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

            // Original post has 1 added to its numberOfComments
            comment.addComment(newComment);
        } else if (original != null) {
            // If the post is an Original:

            // If post is deleted, NotActionablePostException is thrown
            if (!original.isActionable()) throw new NotActionablePostException();

            // An appropriate Comment object is created
            newComment = new Comment(handle, original, message);

            // Original post has 1 added to its numberOfComments
            original.addComment(newComment);
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

        // One of these variables will be not null depending on the type of object the post is
        Original original = platform.getOriginals().get(id);
        Comment comment = platform.getComments().get(id);
        Endorsement endorsement = platform.getEndorsements().get(id);

        if (original != null) {

            original.deletePost();
        } else if (comment != null) {

            // Original post has one taken off its numberOfComments variable
            comment.getOriginalPost().removeComment();

            comment.deletePost();
        } else if (endorsement != null) {

            // Original is retrieved
            Original endorsedOriginal = platform.getOriginals().get( endorsement.getEndorsedPost().getId() );
            Comment endorsedComment = platform.getComments().get( endorsement.getEndorsedPost().getId() );

            // The type of object will be tested and one removed from its numberOfEndorsements variable
            if (endorsedOriginal != null) {

                // The account that was endorsed will have 1 subtracted from the totalEndorsements value
                Account account = platform.getAccounts().get( endorsedOriginal.getHandle() );
                account.setTotalEndorsements( account.getTotalEndorsements() - 1);

                // Endorsed object is removed from the accounts list of endorsements
                endorsedOriginal.removeEndorsement();
            } else if (endorsedComment != null) {

                // The account that was endorsed will have 1 subtracted from the totalEndorsements value
                Account account = platform.getAccounts().get( endorsedComment.getHandle() );
                account.setTotalEndorsements( Account.getNumberOfAccounts() - 1);

                // Endorsed object is removed from the accounts list of endorsements
                endorsedComment.removeEndorsement();
            }

            endorsement.deletePost();
        } else {

            // If id does not match any post object type then it does not exist, PostIDNotRecognisedException is thrown
            throw new PostIDNotRecognisedException();
        }

    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {

        // The post object is assigned to one of these variables depending on the object type
        Original original = platform.getOriginals().get(id);
        Comment comment = platform.getComments().get(id);
        Endorsement endorsement = platform.getEndorsements().get(id);

        String output;

        // The output string will be constructed based on what object type the post is
        if (original != null) {

            output = String.format("""
                ID: %o
                Account: %s
                No. endorsements: %o | No. comments: %o
                %s
                """, original.getId(), original.getHandle(), original.getNumberOfEndorsements(), original.getNumberOfComments(), original.getMessage());
        } else if (comment != null) {

            output = String.format("""
                ID: %o
                Account: %s
                No. endorsements: %o | No. comments: %o
                %s
                """, comment.getId(), comment.getHandle(), comment.getNumberOfEndorsements(), comment.getNumberOfComments(), comment.getMessage());
        } else if (endorsement != null) {

            //TODO - shouldn't an endorsement show a NotActionable exception???

            output = String.format("""
                    ID: %o
                    Account: %s
                    No. endorsements: 0 | No. comments: 0
                    %s
                    """, endorsement.getId(), endorsement.getHandle(), endorsement.getMessage());
        } else {

            // if all objets are null the post has not been found in the system so a PostIDNotRecognisedException will be thrown
            throw new PostIDNotRecognisedException();
        }

        return output;

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
    public int getTotalOriginalPosts() {

        return Original.getNumberOfPosts();
    }

    @Override
    public int getTotalEndorsmentPosts() {

        return Endorsement.getNumberOfEndorsements();
    }

    @Override
    public int getTotalCommentPosts() {

        return Comment.getTotalNumberOfComments();
    }

    @Override
    public int getMostEndorsedPost() {

        Original original = platform.getOriginals().get(0);
        Comment comment = platform.getComments().get(0);

        // If there is at least one original
        if (original != null) {

            // All originals will be tested
            for (Original i : platform.getOriginals().values()) {

                // If i is has more endorsements then original, original will be set as i
                if (i.getNumberOfEndorsements() > original.getNumberOfEndorsements()) {
                    original = i;
                }
            }
        }

        // If there is at least one comment
        if (comment != null) {

            // All comments will be tested
            for (Comment i : platform.getComments().values()) {

                // If i has more endorsements than comment, comment will be set as i
                if (i.getNumberOfEndorsements() > comment.getNumberOfEndorsements()) {
                    comment = i;
                }
            }
        }

        // 0 will be returned if there are no posts
        // If one is null and the other is not, the not not null objects ID will be returned
        if (original == null && comment == null) {
            return 0;
        } else if (original != null && comment == null) {
            return original.getId();
        } else if (comment != null && original == null) {
            return comment.getId();
        }

        // The most endorsed out of comment and original will be returned
        if (original.getNumberOfEndorsements() > comment.getNumberOfEndorsements()) {
            return original.getId();
        } else {
            return comment.getId();
        }

    }

    @Override
    public int getMostEndorsedAccount() {

        Map.Entry<String, Account> mostEndorsedAccount = platform.getAccounts().entrySet().stream().toList().get(0);

        // Accounts are checked
        for (Map.Entry<String, Account> i : platform.getAccounts().entrySet()) {

            // If i has more endorsements than mostEndorsedAccount, mostEndorsedAccount is set as i
            if (i.getValue().getTotalEndorsements() > mostEndorsedAccount.getValue().getTotalEndorsements()) {
                mostEndorsedAccount = i;
            }
        }

        // The ID of the account with most endorsements is returned
        return mostEndorsedAccount.getValue().getNUMERICAL_IDENTIFIER();

        /*
        Account mostEndorsedAccount = (Account) platform.getAccounts().values().toArray()[0];

        // If there are no accounts 0 will be returned
        if (mostEndorsedAccount == null) return 0;

        // All accounts will be checked
        for (Account i : platform.getAccounts().values()) {

            int totalEndorsements = 0;

            // Each account will have all comments and originals checked and number of endorsements will be summed
            for (Original j : i.getOriginals().stream().toList()) {

                totalEndorsements += j.getNumberOfEndorsements();
            }

            for (Comment j : i.getComments().stream().toList()) {

                totalEndorsements += j.getNumberOfEndorsements();
            }

            if (totalEndorsements > mostEndorsedAccount)
        }
        */
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