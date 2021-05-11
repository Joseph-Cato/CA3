package socialmedia;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

        SocialMedia sm = new SocialMedia();

        Account.resetNumberOfAccounts();

        try {
            sm.createAccount("Jimmy", "Jimmy is super cool");
            sm.createAccount("Billy_Bobby");
            sm.createAccount("Jessica", "Not as cool as Jimmy :(");
            sm.createAccount("JakeyBoi", "123123iuyadesegf9786q3w4w2gou");

            sm.removeAccount(1);
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
        Assert.assertThrows(AccountIDNotRecognisedException.class, () -> finalSm2.removeAccount(1));

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

        try {

            String expected1 = """
                    ID: 0
                    Handle: 1
                    Description: first account
                    Post count: 4
                    Endorse count: 2
                    """;

            String expected2 = """
                    ID: 0
                    Handle: 1
                    Description: first account
                    Post count: 2
                    Endorse count: 1
                    """;

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1", "first account");

            sm.createPost("1", "post 0");

            sm.commentPost("1", 0, "post 1 (comment)");

            sm.endorsePost("1", 0); // post 2

            sm.endorsePost("1", 1); // post 3

            Account account = sm.platform.getAccount("1");

            Assert.assertEquals(expected1, sm.showAccount("1"));

            sm.deletePost(1);

            Assert.assertEquals(expected2, sm.showAccount("1"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void showAccountBadHandleTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("ben");

            Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.showAccount("hi"));
            Assert.assertThrows(HandleNotRecognisedException.class, () -> sm.showAccount(""));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Assert.assertEquals(sm.platform.getComments().get(1), actualComment2.getOriginalComment());
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

    @Test
    public void deletePostTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("ben");

            sm.createAccount("james");

            sm.createPost("ben", "hi");

            sm.commentPost("james", 0, "hello");

            sm.deletePost(0);


            Original deletedPost = sm.platform.getOriginals().get(0);

            Comment comment = sm.platform.getComments().get(1);


            Assert.assertEquals(null, deletedPost.getHandle());

            Assert.assertEquals("The original content was removed from the system and is no longer available.", deletedPost.getMessage());

            Assert.assertEquals(false, deletedPost.isActionable());

            Assert.assertEquals(deletedPost, comment.getOriginalPost());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePostBadIdTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("ben");

            sm.createPost("ben", "hi"); //0

            Assert.assertThrows(PostIDNotRecognisedException.class, () -> sm.deletePost(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showIndividualPostTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("ben");
            sm.createAccount("jim");
            sm.createAccount("bob");

            sm.createPost("ben", "one"); //0

            sm.commentPost("ben", 0, "two"); //1

            sm.endorsePost("jim", 1); //2

            sm.endorsePost("bob", 1);

            String expected1 = """
                    ID: 0
                    Account: ben
                    No. endorsements: 0 | No. comments: 1
                    one
                    """;

            String expected2 = """
                    ID: 1
                    Account: ben
                    No. endorsements: 2 | No. comments: 0
                    two
                    """;


            Assert.assertEquals(expected1, sm.showIndividualPost(0));

            Assert.assertEquals(expected2, sm.showIndividualPost(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showIndividualPostBadIdTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("ben");

            sm.createPost("ben", "test00");


            Assert.assertThrows(PostIDNotRecognisedException.class, () -> sm.deletePost(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNumberOfAccountsTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("0");
            sm.createAccount("1");
            sm.createAccount("2");
            sm.createAccount("3");

            Assert.assertEquals(4, sm.getNumberOfAccounts());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTotalOriginalPostsTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1");

            sm.createPost("1", "test");
            sm.createPost("1", "test");
            sm.createPost("1", "test");
            sm.createPost("1", "test");
            sm.createPost("1", "test");
            sm.createPost("1", "test");

            Assert.assertEquals(6, sm.getTotalOriginalPosts());

            sm.deletePost(3);

            Assert.assertEquals(5, sm.getTotalOriginalPosts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTotalEndorsmentPostsTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1");
            sm.createAccount("2");
            sm.createAccount("3");

            sm.createPost("1", "1");

            sm.endorsePost("2", 0);
            sm.endorsePost("3", 0);

            Assert.assertEquals(2, sm.getTotalEndorsmentPosts());

            sm.deletePost(2);

            Assert.assertEquals(1, sm.getTotalEndorsmentPosts());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTotalCommentPostsTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1");
            sm.createAccount("2");

            sm.createPost("1", "0");

            sm.commentPost("1", 0, "1");
            sm.commentPost("2", 0, "2");
            sm.commentPost("1", 2, "3");

            Assert.assertEquals(3, sm.getTotalCommentPosts());

            sm.deletePost(2);

            Assert.assertEquals(2, sm.getTotalCommentPosts());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMostEndorsedPostTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1");
            sm.createAccount("2");
            sm.createAccount("3");

            sm.createPost("1", "0");
            sm.createPost("2", "1");

            sm.endorsePost("1", 0);
            sm.endorsePost("2", 1);
            sm.endorsePost("3", 1);

            Assert.assertEquals(1, sm.getMostEndorsedPost());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMostEndorsedAccountTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("0"); // Account id 0
            sm.createAccount("1"); // Account id 1
            sm.createAccount("2"); // Account id 2

            sm.createPost("0", "0"); // post id 0
            sm.createPost("1", "1"); // post id 1

            sm.endorsePost("0", 1); // post id 2

            sm.endorsePost("1", 0); // post id 3
            sm.endorsePost("2", 0); // post id 4

            Assert.assertEquals(0, sm.getMostEndorsedAccount());

            sm.deletePost(3);
            sm.deletePost(4);

            Assert.assertEquals(1, sm.getMostEndorsedAccount());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showPostChildrenDetailsTest() {

        try {
            StringBuilder expectedOutputOne = new StringBuilder();
            expectedOutputOne.append("""
                    ID: 1
                    Account: user1
                    No. endorsements: 2 | No. comments: 3
                    I like examples.
                    |
                    | > ID: 3
                        Account: user2
                        No. endorsements 0 | No. comments: 1
                        No more than me...
                        |
                        | > ID: 5
                            Account: user1
                            No. endorsements: 0 | No. comments: 1
                            I can prove!
                            |
                            | > ID: 6
                                Account: user2
                                No. endorsements: 0 | No. comments: 0
                                prove it
                    | > ID: 4
                        Account: user3
                        No. endorsements: 4 | no. comments: 0
                        Can't you do better than this?
                        
                    | > ID: 7
                        Account: user5
                        No. endorsements: 0 | No. comments: 1
                        where is the example?
                        |
                        | > ID: 10
                            Account: user1
                            No. endorsements: 0 | No.comments: 0
                            This is the example!
                    """);

            StringBuilder expectedOutputTwo = new StringBuilder();
            expectedOutputTwo.append("""
                    ID: 5
                    Account: user1
                    No. endorsements: 0 | No. comments: 1
                    I can prove!
                    |
                    | > ID: 6
                        Account: user2
                        No. endorsements: 0 | No. comments: 0
                        prove it
                    """);


            SocialMedia sm = new SocialMedia();

            sm.createAccount("user1");
            sm.createAccount("user2");
            sm.createAccount("user3");
            sm.createAccount("user4");
            sm.createAccount("user5");

            sm.createPost("user1", "I like examples."); //1
            sm.endorsePost("user2", 0); //2
            sm.commentPost("user2", 0, "No more than me..."); //3
            sm.commentPost("user3", 0, "Can't you do better than this?"); //4
            sm.commentPost("user1", 2, "I can prove!"); //5
            sm.commentPost("user2", 4, "prove it"); //6
            sm.commentPost("user5", 0, "where is the example?"); //7

            sm.endorsePost("user4", 0); //8
            sm.endorsePost("user4", 3); //9

            sm.commentPost("user1", 6, "This is the example!"); //10

            sm.endorsePost("user1", 3); //11
            sm.endorsePost("user2", 3); //12
            sm.endorsePost("user3", 3); //13

            System.out.println(sm.showPostChildrenDetails(0));

            Assert.assertEquals(expectedOutputOne.toString(), sm.showPostChildrenDetails(0).toString());

            Assert.assertEquals(expectedOutputTwo.toString(), sm.showPostChildrenDetails(6).toString());

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void showPostChildrenDetailsBadIdTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1");

            sm.createPost("1", "yoooo");

            Assert.assertThrows(PostIDNotRecognisedException.class, () -> sm.showPostChildrenDetails(1));
            Assert.assertThrows(PostIDNotRecognisedException.class, () -> sm.showPostChildrenDetails(-2));

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void showPostChildrenDetailsBadPostTest() {

        try {

            SocialMedia sm = new SocialMedia();

            sm.createAccount("1");
            sm.createAccount("2");

            sm.createPost("1", "yoooo"); //0
            sm.endorsePost("2", 0); //1

            Assert.assertThrows(NotActionablePostException.class, () -> sm.showPostChildrenDetails(1));

            sm.deletePost(0);

            Assert.assertThrows(NotActionablePostException.class, () -> sm.showPostChildrenDetails(0));

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
