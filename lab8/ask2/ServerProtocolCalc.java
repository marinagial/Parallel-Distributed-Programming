public class ServerProtocolCalc {
    private static final String EXIT = "!";

    public String processRequest(String request) {

        if (request.equals(EXIT)) {
            return EXIT;
        }

        Compute computationHandler = new Compute();
        String[] requestData = getRequestData(request);
        Result result = computationHandler.doServerComputation(requestData);
        System.out.println("Result: " + result.r);
        return buildReplyMessage(result);
    }

    private String[] getRequestData(String request) {
        System.out.println("Requested:"+request);
        return request.split(" ");
    }

    private String buildReplyMessage(Result result) {
        if (result.op == 'R') {
            return "R " + result.r;
        } else if (result.op == 'E') {
            return "E " + result.errCode;
        } else if (result.op == '!') {
            return EXIT;
        } else {
            return "E -1"; 
        }
    }
}
