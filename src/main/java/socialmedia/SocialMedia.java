package socialmedia;

import java.io.IOException;

public class SocialMedia implements SocialMediaPlatform {

    public TempPlatform tempPlatform = new TempPlatform();

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void removeAccount(int id) throws AccountIDNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        // TODO Auto-generated method stub

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
        if (tempPlatform.getAccount(handle) == null) throw new HandleNotRecognisedException();
        if (message.length() == 0 || message.length() > 100) throw new InvalidPostException();

        Original newOriginal = new Original(handle, message, tempPlatform);
        tempPlatform.addPost(newOriginal.uniqueID, newOriginal);
        tempPlatform.setCurrentPostID(tempPlatform.getCurrentPostID()+1);
        return newOriginal.getUniqueID();
    }

    @Override
    public int endorsePost(String handle, int id)
            throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        if (tempPlatform.getAccount(handle) == null) throw new HandleNotRecognisedException();
        if (tempPlatform.getPost(id) == null) throw new PostIDNotRecognisedException();
        if (tempPlatform.checkIfEndorsement(id) == false) throw new NotActionablePostException();
        if (tempPlatform.checkIfEmptyPost(id) == false) throw new NotActionablePostException();

        Endorsement newEndorsement = new Endorsement(handle, id, tempPlatform);
        tempPlatform.addPost(newEndorsement.uniqueID, newEndorsement);
        tempPlatform.setCurrentPostID(tempPlatform.getCurrentPostID()+1);
        return newEndorsement.getUniqueID();
    }
// TODO explain why endorsed posts can't be commented on (contradictions)
    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
            PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        if (tempPlatform.getAccount(handle) == null) throw new HandleNotRecognisedException();
        if (tempPlatform.getPost(id) == null) throw new PostIDNotRecognisedException();
        if (tempPlatform.checkIfEmptyPost(id) == false) throw new NotActionablePostException();
        if (message.length() == 0 || message.length() > 100) throw new InvalidPostException();

        Comment newComment = new Comment(handle, id, message, tempPlatform);
        tempPlatform.addPost(newComment.uniqueID, newComment);
        tempPlatform.setCurrentPostID(tempPlatform.getCurrentPostID()+1);
        return newComment.getUniqueID();
    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        if (tempPlatform.getPost(id) == null) throw new PostIDNotRecognisedException();
        Post postToBeDeleted = tempPlatform.getPost(id);
        Post.deletePost(postToBeDeleted, tempPlatform);
    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        if (tempPlatform.getPost(id) == null) throw new PostIDNotRecognisedException();
        Post post = tempPlatform.getPost(id);
        String stat = "ID: " + post.getUniqueID() + "\n" +
                "Account: " + post.getPosterHandle() + "\n" +
                "No. endorsements: " + post.getEndorsements().size() +
                "No. comments: " + post.getComments().size() + "\n" +
                post.getMessage();
        return stat;
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
        int noOfOriginals = 0;
        for (Post post : tempPlatform.getPosts().values()) {
            if (post.getClass() == Original.class) {
                noOfOriginals += 1;
            }
        }
        return noOfOriginals;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        int noOfEndorsements = 0;
        for (Post post : tempPlatform.getPosts().values()) {
            if (post.getClass() == Endorsement.class) {
                noOfEndorsements += 1;
            }
        }
        return noOfEndorsements;
    }

    @Override
    public int getTotalCommentPosts() {
        int noOfComments = 0;
        for (Post post : tempPlatform.getPosts().values()) {
            if (post.getClass() == Comment.class) {
                noOfComments += 1;
            }
        }
        return noOfComments;
    }

    @Override
    public int getMostEndorsedPost() {
        int noOfEndorsements = -1;
        Post mostEndorsedPost = null;
        for (Post post : tempPlatform.getPosts().values()) {
            if (post.getEndorsements() != null); {
                if (post.getEndorsements().size() > noOfEndorsements) {
                    noOfEndorsements = post.getEndorsements().size();
                    mostEndorsedPost = post;
                }
            }
        }
        return mostEndorsedPost.getUniqueID();
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