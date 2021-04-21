package socialmedia;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class SocialMediaTest {

    /*
    -----------------Account Method Tests-----------------
     */

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

        Assert.assertEquals(accountList, sm.platform.printAccounts() );

    }

    @Test
    public void createAccountBadHandleTest() {

        SocialMedia sm = new SocialMedia();

        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("Dave "));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("", ""));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("Dave ", ""));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount(" jimbo", ""));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.createAccount("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", ""));

        Platform tp = new Platform();

        try {
            sm.createAccount("jiles");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertThrows(IllegalHandleException.class, () -> sm.createAccount("jiles"));

    }

    @Test
    public void removeAccountTest() {

        //TODO - check posts are removed

        SocialMedia sm = new SocialMedia();

        Account.resetNumberOfAccounts();

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

        Account.resetNumberOfAccounts();

        HashMap<String, Account> testMap = new HashMap<>();

        testMap.put("Jimmy", new Account("Jimmy", "Jimmy is super cool"));
        testMap.put("Jessica", new Account("Jessica", "Not as cool as Jimmy :("));

        HashMap<String, String> accountList = new HashMap<>();

        testMap.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        Assert.assertEquals(accountList, sm.platform.printAccounts() );

    }

    @Test
    public void removeAccountBadIdTest() {

        //TODO - check posts remain intact

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

        //TODO - check posts remain intact

        SocialMedia sm = new SocialMedia();

        SocialMedia finalSm = sm;
        Assert.assertThrows(HandleNotRecognisedException.class, () -> finalSm.removeAccount("Dave"));

        sm = new SocialMedia();

        try {
            sm.createAccount("Jimbo", "po812`y3giu8y");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SocialMedia finalSm1 = sm;
        Assert.assertThrows(HandleNotRecognisedException.class, () -> finalSm1.removeAccount(" "));

        SocialMedia finalSm2 = sm;
        Assert.assertThrows(HandleNotRecognisedException.class, () -> finalSm2.removeAccount("asefokiluhbv3124 "));

        SocialMedia finalSm3 = sm;
        Assert.assertThrows(HandleNotRecognisedException.class, () -> finalSm3.removeAccount(""));

        SocialMedia finalSm4 = sm;
        Assert.assertThrows(HandleNotRecognisedException.class, () -> finalSm4.removeAccount(" Jimboo!!"));

    }

    @Test
    public void changeAccountHandleTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");

            sm.changeAccountHandle("Jessica", "James");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Account.resetNumberOfAccounts();

        HashMap<String, Account> testMap = new HashMap<>();

        testMap.put("Jimmy", new Account("Jimmy", "Jimmy is super cool"));
        testMap.put("Billy_Bobby", new Account("Billy_Bobby", ""));
        testMap.put("James", new Account("James", "Not as cool as Jimmy :("));

        HashMap<String, String> accountList = new HashMap<>();

        testMap.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        HashMap<String, String> actualHashMap = sm.platform.printAccounts();

        Assert.assertEquals(testMap.size(), actualHashMap.size());

        Assert.assertTrue(actualHashMap.containsKey("Jimmy"));
        Assert.assertEquals(accountList.get("Jimmy"), actualHashMap.get("Jimmy"));

        Assert.assertTrue(actualHashMap.containsKey("Billy_Bobby"));
        Assert.assertEquals(accountList.get("Billy_Bobby"), actualHashMap.get("Billy_Bobby"));

        Assert.assertTrue(actualHashMap.containsKey("James"));
        Assert.assertEquals(accountList.get("James"), actualHashMap.get("James"));

    }

    @Test
    public void changeAccountHandleBadOldHandleTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");

            sm.changeAccountHandle("Jessica", "James");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.changeAccountHandle(" ", "Billy"));
        Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.changeAccountHandle("J1mmy", "Billy"));
        Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.changeAccountHandle("1233123sidugyvcfiahgvlkjhasebdvgfiuy34g913746fgoqu3hgbfljhsbfopiu7uw3ytt0987aepu;ygefo87ewsrabto872q3t987q23tp;", "Billy"));

    }

    @Test
    public void changeAccountHandleBadNewHandleTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertThrows(IllegalHandleException.class, () -> sm.changeAccountHandle("Jimmy", "Jessica"));
        Assert.assertThrows(IllegalHandleException.class, () -> sm.changeAccountHandle("Billy_Bobby", "Jimmy"));
        Assert.assertThrows(IllegalHandleException.class, () -> sm.changeAccountHandle("Jessica", "Jimmy"));

        Assert.assertThrows(InvalidHandleException.class, () -> sm.changeAccountHandle("Jimmy", "white space"));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.changeAccountHandle("Jimmy", "too_longggggggggggggggggggggggg"));
        Assert.assertThrows(InvalidHandleException.class, () -> sm.changeAccountHandle("Jimmy", "both too_longggg ggggggggggggggggggg"));

    }

    @Test
    public void updateAccountDescriptionTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");

            sm.updateAccountDescription("Billy_Bobby", "Proud to announce my name is Billy Bobby!");
            sm.updateAccountDescription("Jimmy", "Still cool but wouldn't say 'super cool' ://");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Account.resetNumberOfAccounts();

        HashMap<String, Account> testMap = new HashMap<>();

        testMap.put("Jimmy", new Account("Jimmy", "Still cool but wouldn't say 'super cool' ://"));
        testMap.put("Billy_Bobby", new Account("Billy_Bobby", "Proud to announce my name is Billy Bobby!"));
        testMap.put("James", new Account("James", "Not as cool as Jimmy :("));

        HashMap<String, String> accountList = new HashMap<>();

        testMap.forEach( (k,v) -> accountList.put(k, v.getDescription()));

        HashMap<String, String> actualHashMap = sm.platform.printAccounts();

        Assert.assertEquals(testMap.size(), actualHashMap.size());

        Assert.assertTrue(actualHashMap.containsKey("Jimmy"));
        Assert.assertEquals(accountList.get("Jimmy"), actualHashMap.get("Jimmy"));

        Assert.assertTrue(actualHashMap.containsKey("Billy_Bobby"));
        Assert.assertEquals(accountList.get("Billy_Bobby"), actualHashMap.get("Billy_Bobby"));

        Assert.assertTrue(actualHashMap.containsKey("Jessica"));
        Assert.assertEquals(accountList.get("Jessica"), actualHashMap.get("James"));

    }

    @Test
    public void updateAccountDescriptionBadHandleTest() {

        SocialMedia sm = new SocialMedia();

        try {
            sm.createAccount("Jim", "I'm Jim");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.updateAccountDescription("Timothy", "Not working!"));
        Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.updateAccountDescription("Jim ", "Not working!"));

    }

    @Test
    public void showAccountTest() {

        // TODO - create when basic post framework is in

    }

}
