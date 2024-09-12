public class ServerProtocol {
    private static final String EXIT = "CLOSE";

	public String processRequest(String theInput) {

        System.out.println("Received message from client: " + theInput);
		String theOutput;
   
        if (theInput.startsWith("1 ")) {
            String text = theInput.substring(2);
            theOutput = text.toLowerCase(); // lowercase
        } else if (theInput.startsWith("2 ")) {
            String text = theInput.substring(2);
            theOutput = text.toUpperCase(); // uppercase
        } else if (theInput.startsWith("3 ")) {
            String[] parts = theInput.split(" ", 3); // simple encoding
            if (parts.length == 3) { // 3 parts : the choice , the offset and the text 
                String text = parts[2];
                int offset = Integer.parseInt(parts[1]); 
                theOutput = applyCaesarCipher(text, offset);
            } else {
                theOutput = "Invalid input. ";
            }
        } else if (theInput.startsWith("4 ")) {
            String[] parts = theInput.split(" ", 3); // decoding
            if (parts.length == 3) {
                String text = parts[2];
                int offset = Integer.parseInt(parts[1]);
                theOutput = decodeMessage(text, offset);
            } else {
                theOutput = "Invalid input. ";
            }
        } else if (theInput.equals(EXIT)) {
            theOutput = EXIT;
        } else {
            theOutput = "Invalid input. Please enter '1 <text>' for lowercase, '2 <text>' for uppercase, '3 <offset> <text>' for plain-encoding, or '4 <offset> <text>' for decoding.";
        }
        System.out.println("Send message to client: " + theOutput);
        return theOutput;
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