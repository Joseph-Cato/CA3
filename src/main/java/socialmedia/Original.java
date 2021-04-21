package socialmedia;

import java.util.HashMap;


public class Original extends Post {

    public Original(String handle, String message, TempPlatform tempPlatform) {
        this.posterHandle = handle;
        this.message = message;
        originalPoster = tempPlatform.getAccount(handle);
        setUniqueID(tempPlatform);
    }


}
