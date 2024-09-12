package piClient;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PiClient {
    private static final String HOST = "localhost";
    private static final int PORT = Registry.REGISTRY_PORT;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            piCalc calculator = (piCalc) registry.lookup("PiCalculator");
            Scanner scanner = new Scanner(System.in); 

            while (true) {
                System.out.print("Enter the value of n (-1 to exit): ");
                int n = scanner.nextInt();

                if (n == -1) {
                    break;
                }

                try {
                    double piValue = calculator.calculatePi(n);
                    System.out.println("The value of pi for n = " + n + " is: " + piValue);
                } catch (RemoteException e) {
                    System.out.println("Client termination requested.");
                    break;
                }
            }
             scanner.close();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}