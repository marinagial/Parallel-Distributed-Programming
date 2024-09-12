package ask1Serializable;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serialVersionUID for better serialization compatibility

    private int operation;
    private String text;
    private int offset;

    public Request(int operation, String text, int offset) {
        this.operation = operation;
        this.text = text;
        this.offset = offset;
    }

    public int getOperation() {
        return operation;
    }

    public String getText() {
        return text;
    }

    public int getOffset() {
        return offset;
    }
}