package socialmedia;

import java.util.HashMap;

public class Platform {

    //TODO - set hashmaps to bigger default size to increase efficiency

    private HashMap<String, Account> accounts = new HashMap<>();

    private HashMap<Integer, Original> originals = new HashMap<>();

    private HashMap<Integer, Comment> comments = new HashMap<>();

    private HashMap<Integer, Endorsement> endorsements = new HashMap<>();



    HashMap<String, Account> getAccounts() {
        return accounts;
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

    void addOriginal(Original original){

        int id = original.getId();

        originals.put(id, original);
    }


    void addEndorsement(Endorsement endorsement) throws NullPointerException{
        endorsements.put(endorsement.getId(), endorsement);
    }

    HashMap<Integer, Original> getOriginals() {
        return originals;
    }

    HashMap<Integer, Comment> getComments() {
        return comments;
    }

    HashMap<Integer, Endorsement> getEndorsements() {
        return endorsements;
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
