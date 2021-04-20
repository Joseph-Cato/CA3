package socialmedia;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class SocialMediaTest {

    // Account Method Tests

    @Test
    public void createAccountTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Account> testMap = new HashMap<>();

        testMap.put("Jimmy", new Account("Jimmy", "Jimmy is super cool"));
        testMap.put("Billy_Bobby", new Account("Billy_Bobby", ""));
        testMap.put("Jessica", new Account("Jessica", "Not as cool as Jimmy :("));

        HashMap<String, String> accountList = new HashMap<>();

        testMap.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        Assert.assertEquals(accountList, sm.tempPlatform.printAccounts() );

    }

    @Test
    public void createAccountBadHandleTest() {

        SocialMedia sm = new SocialMedia();

        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("Dave "));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("", ""));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("Dave ", ""));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount(" jimbo", ""));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ""));

        TempPlatform tp = new TempPlatform();

        try {
            sm.createAccount("jiles");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertThrows(IllegalHandleException.class, () -> sm.createAccount("jiles"));

    }

    @Test
    public void removeAccountTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");
            sm.createAccount("JakeyBoi", "123123iuyadesegf9786q3w4w2gou");

            sm.removeAccount(2);
            sm.removeAccount("JakeyBoi");

        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Account> testMap = new HashMap<>();

        testMap.put("Jimmy", new Account("Jimmy", "Jimmy is super cool"));
        testMap.put("Billy_Bobby", new Account("Jessica", "Not as cool as Jimmy :("));

        HashMap<String, String> accountList = new HashMap<>();

        testMap.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        Assert.assertEquals(accountList, sm.tempPlatform.printAccounts() );

    }

    @Test
    public void removeAccountBadIdTest() {

        SocialMedia sm = new SocialMedia();

        SocialMedia finalSm1 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm1.removeAccount(1));

        sm = new SocialMedia();

        try {
            sm.createAccount("Jimbo", "po812`y3giu8y");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SocialMedia finalSm = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm.removeAccount(2));

        SocialMedia finalSm2 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm2.removeAccount(0));

        SocialMedia finalSm3 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm3.removeAccount(-1));

        SocialMedia finalSm4 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm4.removeAccount(-1239867));

        SocialMedia finalSm5 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm5.removeAccount(2318756));

    }

    @Test
    public void removeAccountBadHandleTest() {

        SocialMedia sm = new SocialMedia();

        SocialMedia finalSm = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm.removeAccount("Dave"));

        sm = new SocialMedia();

        try {
            sm.createAccount("Jimbo", "po812`y3giu8y");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SocialMedia finalSm1 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm1.removeAccount(" "));

        SocialMedia finalSm2 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm2.removeAccount("asefokiluhbv3124 "));

        SocialMedia finalSm3 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm3.removeAccount(""));

        SocialMedia finalSm4 = sm;
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm4.removeAccount(" Jimboo!!"));
    }
}
