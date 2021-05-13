package socialmedia;

import java.util.Comparator;

public class CommentComparator implements Comparator<Comment> {

    @Override
    public int compare(Comment a, Comment b) {
        return Integer.compare(a.getId(), b.getId());
    }
}
