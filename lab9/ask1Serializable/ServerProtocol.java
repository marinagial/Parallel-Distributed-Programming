package ask1Serializable;

public class ServerProtocol {
    private static final String EXIT = "CLOSE";

    public Reply processRequest(Request request) {
        String text = request.getText();
        int offset = request.getOffset();
        int operation = request.getOperation();

        String result;

        if (operation == 1) {
            result = text.toLowerCase();
        } else if (operation == 2) {
            result = text.toUpperCase();
        } else if (operation == 3) {
            result = applyCaesarCipher(text, offset);
        } else if (operation == 4) {
            result = decodeMessage(text, offset);
        } else {
            result = "Invalid input. Please enter '1 <text>' for lowercase, '2 <text>' for uppercase, '3 <offset> <text>' for plain-encoding, or '4 <offset> <text>' for decoding.";
        }

        System.out.println("Send message to client: " + result);
        return new Reply(result);
    }

    private String applyCaesarCipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                result.append((char) ('A' + (character - base + offset) % 26));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
    private String decodeMessage(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition - offset + 26) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
