package socialmedia;

import java.util.HashMap;

public class Platform {

    private HashMap<String, Account> accounts = new HashMap<>();
    private HashMap<Integer, Post> posts = new HashMap<>();
    private int currentPostID;

    public Platform() {
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

    boolean checkIfEndorsement (Integer id) {
        if (posts.get(id).getClass() != Endorsement.class) { //TODO - So method 'checkIfEndorsement' returns true if it's NOT an endorsement?
            return true;
        } return false;
    }

    boolean checkIfEmptyPost (Integer id) {
        if (posts.get(id).getClass() != EmptyPost.class ) { //TODO - same as checkIfEndorsement
            return true;
        } return false;
    }

    void addPost(Integer id, Post post) {
        posts.put(id, post);
    }

    void removePost(Post post) {
        posts.remove(post);
        //TODO - !I think this will throw a ClassCastException!
        // Platform's 'posts' HashMap's KEYS are Integers, the VALUES are posts
        // HashMap<K, V>
        // <K> – the type of keys maintained by this map
        // <V> – the type of mapped values
    }

    /**
     *  Method used solely for testing.
     * @return HashMap of account handles and descriptions in the system.
     */
    HashMap<String, String> printAccounts(){ //TODO - Learn how to properly test HashMaps instead of this horrible thing

        HashMap<String, String> accountList = new HashMap<>();

        accounts.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        return accountList;
    }

}
