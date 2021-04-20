package socialmedia;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class SocialMedia implements SocialMediaPlatform {

    public TempPlatform tempPlatform = new TempPlatform();

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
        if (tempPlatform.getAccount(handle) != null) throw new IllegalHandleException();

        //Creates new account
        Account newAccount = new Account(handle, description);

        // Adds account to platform
        tempPlatform.addAccount(handle, newAccount);

        return newAccount.getNUMERICAL_IDENTIFIER();
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        // TODO Remove all types of posts associated with this account

        // Finds the account with the corresponding Id
        for (Account i : tempPlatform.getAccounts().values()) {
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
        Account account = tempPlatform.removeAccount(handle);

        // if HashMap.removeAccount() returns null then the value was not found
        // (no account with that handle exists)
        if (account == null) throw new HandleNotRecognisedException();

    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {

        // Checks if old handle exists in system
        Account account = tempPlatform.getAccount(oldHandle);
        if (account == null) throw new HandleNotRecognisedException();

        // Checks if new handle already exists in system
        Account account1 = tempPlatform.getAccount(newHandle);
        if (account1 != null) throw new IllegalHandleException();

        // Checks if the new handle is valid
        if (newHandle.equals("") || newHandle.length() > 30 || newHandle.contains(" ")) throw new InvalidHandleException();

        // Removes old data from system
        tempPlatform.removeAccount(oldHandle);

        // Changes handle of old account
        account.setHandle(newHandle);

        // Adds new account and handle to system
        tempPlatform.addAccount(newHandle, account);

    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalOriginalPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTotalCommentPosts() {
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