package socialmedia;

import java.util.HashMap;

public class TempPlatform {
    private HashMap<String, Account> accounts = new HashMap<>();
    private HashMap<Integer, Post> posts = new HashMap<>();
    private int currentPostID;

    public TempPlatform() {
        this.currentPostID = 0;
    }

    public int getCurrentPostID() {
        return currentPostID;
    }

    public void setCurrentPostID(int currentPostID) {
        this.currentPostID = currentPostID;
    }

    Post getPost(Integer id) {
        return posts.get(id);
    }

    void addAccount(String handle, Account account){
        accounts.put(handle, account);
    }

    Account removeAccount(String handle) {
        return accounts.remove(handle);
    }

    Account getAccount(String handle) {
        return accounts.get(handle);
    }

    HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public HashMap<Integer, Post> getPosts() {
        return posts;
    }

    boolean checkIfActionablePost (Integer id) {
        if (posts.get(id).getClass() != Endorsement.class || posts.get(id).getClass() != EmptyPost.class ) {
            return true;
        } return false;
    }

    void addPost(Integer id, Post post) {
        posts.put(id, post);
    }

    void removePost(Post post) {
        posts.remove(post);
    }

    /**
     *  Method used solely for testing.
     * @return HashMap of account handles and descriptions in the system.
     */
    HashMap<String, String> printAccounts(){

        HashMap<String, String> accountList = new HashMap<>();

        accounts.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        return accountList;
    }

}
