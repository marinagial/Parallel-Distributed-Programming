package ask3;

public class PiCalc {
    public static double calculatePi(int n) {
        double step = 1.0 / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x = (i + 0.5) * step;
            sum += 4.0 / (1.0 + x * x);
        }
        return sum * step;
    }
  }
