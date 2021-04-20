package socialmedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TempPlatform {

    private HashMap<String, Account> accounts = new HashMap<>();

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

    HashMap<String, String> printAccounts(){

        HashMap<String, String> accountList = new HashMap<>();

        accounts.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        return accountList;
    }
}
