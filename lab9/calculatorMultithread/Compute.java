
class Compute {
    public Result doServerComputation(String[] data) {
        Result result = new Result();
        char op = data[0].charAt(0);
        double a = Double.parseDouble(data[1]);
        double b = Double.parseDouble(data[2]);

        switch (op) {
            case '+':
                result.op = 'R';
                result.r = a + b;
                break;
            case '-':
                result.op = 'R';
                result.r = a - b;
                break;
            case '*':
                result.op = 'R';
                result.r = a * b;
                break;
            case '/':
                if (b == 0) {
                    result.op = 'E';
                    result.errCode = 1; //division with 0
                } else {
                    result.op = 'R';
                    result.r = a / b;
                }
                break;
            case '!':
                result.op = '!';
                result.r = 0;
                break;
            default:
                result.op = 'E';
                result.errCode = 2; // invalid operator
                break;
        }

        return result;
    }
}