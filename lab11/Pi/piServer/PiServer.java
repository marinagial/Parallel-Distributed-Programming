package piServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PiServer {
    private static final String HOST = "localhost";
    private static final int PORT = Registry.REGISTRY_PORT;

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", HOST);
            piCalc calculator = new PiCalcImpl();
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("PiCalculator", calculator);
            System.out.println("Pi Server is running...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}