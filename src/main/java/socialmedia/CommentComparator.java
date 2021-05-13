package socialmedia;

import java.util.Comparator;

/**
* Comment Comparator Class
* This is a single-purpose class that takes two Comments and compares the chronological order in which they were posted
*/
public class CommentComparator implements Comparator<Comment> {

    @Override
    public int compare(Comment a, Comment b) {
        return Integer.compare(a.getID(), b.getID());
    }
}
