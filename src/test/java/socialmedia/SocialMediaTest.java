package socialmedia;

import org.junit.Assert;
import org.junit.Before;
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

    /*
    -----------------Post Method Tests-----------------
     */

    @Test
    public void createPostTest() {

        try {
            SocialMedia sm = new SocialMedia();

            Post.setNumberOfPosts(0);

            sm.createAccount("Jimmy");
            sm.createAccount("Dave", "go away ");

            sm.createPost("Jimmy", "Hi I'm Jimmy!!!");
            sm.createPost("Jimmy", "I posted twice :)");
            sm.createPost("Dave", "nobody cares");

            Assert.assertTrue(sm.platform.getOriginals().containsKey(0));
            Assert.assertTrue(sm.platform.getOriginals().containsKey(1));
            Assert.assertTrue(sm.platform.getOriginals().containsKey(2));

            Post.setNumberOfPosts(0);

            Original o1 = new Original("Jimmy", "Hi I'm Jimmy!!!");
            Original o2 = new Original("Jimmy", "I posted twice :)");
            Original o3 = new Original("Dave", "nobody cares");

            Assert.assertEquals(o1.getMessage(), sm.platform.getOriginals().get(0).getMessage());
            Assert.assertEquals(o2.getMessage(), sm.platform.getOriginals().get(1).getMessage());
            Assert.assertEquals(o3.getMessage(), sm.platform.getOriginals().get(2).getMessage());

            Assert.assertEquals(o1.getHandle(), sm.platform.getOriginals().get(0).getHandle());
            Assert.assertEquals(o2.getHandle(), sm.platform.getOriginals().get(1).getHandle());
            Assert.assertEquals(o3.getHandle(), sm.platform.getOriginals().get(2).getHandle());

            Assert.assertEquals(o1.getId(), sm.platform.getOriginals().get(0).getId());
            Assert.assertEquals(o2.getId(), sm.platform.getOriginals().get(1).getId());
            Assert.assertEquals(o3.getId(), sm.platform.getOriginals().get(2).getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPostBadHandleTest() {

        try {
            SocialMedia sm = new SocialMedia();

            sm.createAccount("Ben");

            Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.createPost("Joe", "Hi"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPostBadMessageTest() {

        try  {
            SocialMedia sm = new SocialMedia();

            sm.createAccount("Ben");

            Assert.assertThrows(InvalidPostException.class, () -> sm.createPost("Ben", ""));
            Assert.assertThrows(InvalidPostException.class, () -> sm.createPost("Ben", "seFAWRFWETRHGEWN FKLUJIHAWEFRAWERGAERGesrfgsetjn0o87y4hg23498762rbouygbf9874362gbfkuwe3yg245    52t 3"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void endorsePostTest() {

        try {

            SocialMedia sm = new SocialMedia();

            Post.setNumberOfPosts(0);

            sm.createAccount("Ben");
            sm.createAccount("Jimmy", "Jimmy is cool");

            sm.createPost("Ben", "Hi I'm ben!");

            sm.endorsePost("Jimmy", 0);

            Endorsement actual = (Endorsement) sm.platform.getAccount("Jimmy").getEndorsements().toArray()[0];

            Assert.assertEquals( sm.platform.getEndorsements().get(1), actual);

            Assert.assertEquals( "Jimmy", actual.getHandle());
            Assert.assertEquals( 1, actual.getId());
            Assert.assertEquals( "EP@Jimmy:Hi I'm ben!", actual.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void endorsePostBadHandleTest() {
        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("John");

            sm.createPost("John", "one");

            Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.endorsePost("Ben", 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void endorsePostBadIdTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("Jim");

            Assert.assertThrows(PostIDNotRecognisedException.class, () -> sm.endorsePost("Jim", 0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void endorsePostEndorsementTest() {

        try {

            SocialMedia sm = new SocialMedia();

            Post.setNumberOfPosts(0);

            sm.createAccount("Jim", "jim");
            sm.createAccount("Jess");

            sm.createPost("Jim", "hi");

            sm.endorsePost("Jess", 0);

            Assert.assertThrows(NotActionablePostException.class, () -> sm.endorsePost("Jim", 1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentPostTest() {

        Post.setNumberOfPosts(0);

        SocialMedia sm = new SocialMedia();

        try {

            sm.createAccount("ben");

            sm.createAccount("dave");

            sm.createPost("ben", "1"); //0

            sm.commentPost("ben", 0, "1commentBen"); //1

            sm.commentPost("dave", 1, "1commentCommentDave"); //2

            sm.commentPost("dave", 0, "1commentDave"); //3

            Comment actualComment1 = sm.platform.getComments().get(1);
            Comment actualComment2 = sm.platform.getComments().get(2);
            Comment actualComment3 = sm.platform.getComments().get(3);

            Assert.assertEquals("1commentBen", actualComment1.getMessage());
            Assert.assertEquals("1commentCommentDave", actualComment2.getMessage());
            Assert.assertEquals("1commentDave", actualComment3.getMessage());

            Assert.assertEquals(sm.platform.getOriginals().get(0), actualComment1.getOriginalPost());
            Assert.assertEquals(sm.platform.getComments().get(1), actualComment2.getOriginalPost());
            Assert.assertEquals(sm.platform.getOriginals().get(0), actualComment3.getOriginalPost());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentPostBadHandleTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("j");

            sm.createPost("j", "j");

            Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.commentPost("daveyyyy", 0, "hi"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentPostBadIdTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("bob");

            sm.createPost("bob", "hi");

            Assert.assertThrows(PostIDNotRecognisedException.class, () -> sm.commentPost("bob", 5, "hi"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentPostBadPostTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("billy");
            sm.createAccount("Jess");

            sm.createPost("billy", " qawdilhyv"); //0

            sm.endorsePost("Jess", 0); //1

            Assert.assertThrows(NotActionablePostException.class, () -> sm.commentPost("billy", 1, "this won't work!"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commentPostBadMessageTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("Joe");

            sm.createPost("Joe", "hi");

            Assert.assertThrows(InvalidPostException.class, () -> sm.commentPost("Joe", 0, ""));

            Assert.assertThrows(InvalidPostException.class, () -> sm.commentPost("Joe", 0, "seFAWRFWETRHGEWN FKLUJIHAWEFRAWERGAERGesrfgsetjn0o87y4hg23498762rbouygbf9874362gbfkuwe3yg245    52t 3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
