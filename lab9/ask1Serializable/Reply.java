package ask1Serializable;

import java.io.Serializable;

public class Reply implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID for better serialization compatibility

    private String result;

    public Reply(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}