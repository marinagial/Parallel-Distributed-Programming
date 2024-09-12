package piServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PiCalcImpl extends UnicastRemoteObject implements piCalc {
    private static final long serialVersionUID = 1L;
    private static final ConcurrentMap<Integer, Double> piCache = new ConcurrentHashMap<>();

    public PiCalcImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculatePi(int n) throws RemoteException {
        if (n == -1) {
            throw new RemoteException("Client termination requested.");
        }

        // Check if n is already in the cache
        Double cachedValue = piCache.get(n);
        if (cachedValue != null) {
            return cachedValue;
        }

        // Calculate pi
        double piValue = computePi(n);
        piCache.put(n, piValue);
        return piValue;
    }

    private double computePi(int n) {
        double step = 1.0 / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double x = (i + 0.5) * step;
            sum += 4.0 / (1.0 + x * x);
        }
        return sum * step;
    }
}