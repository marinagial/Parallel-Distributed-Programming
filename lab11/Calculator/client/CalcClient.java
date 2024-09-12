package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalcClient {
    private static final String HOST = "localhost";
    private static final int PORT = Registry.REGISTRY_PORT; // 1099

    public static void main(String[] args) {
        try {
            // Locate rmi registry
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);

            // Look up for a specific name and get remote reference (stub)
            String rmiObjectName = "MyCalculator";
            Calculator ref = (Calculator) registry.lookup(rmiObjectName);

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\nCalculator Menu:");
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("5. Exit");
                System.out.print("Enter the number of operation of want: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter first number: ");
                        double a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        double b = scanner.nextDouble();
                        double result = ref.add(a, b);
                        System.out.println("Result: " + result);
                        break;
                    case 2:
                        System.out.print("Enter first number: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        b = scanner.nextDouble();
                        result = ref.subtract(a, b);
                        System.out.println("Result: " + result);
                        break;
                    case 3:
                        System.out.print("Enter first number: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        b = scanner.nextDouble();
                        result = ref.multiply(a, b);
                        System.out.println("Result: " + result);
                        break;
                    case 4:
                        System.out.print("Enter first number: ");
                        a = scanner.nextDouble();
                        System.out.print("Enter second number: ");
                        b = scanner.nextDouble();
                        try {
                            result = ref.divide(a, b);
                            System.out.println("Result: " + result);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
            scanner.close();
        } catch (RemoteException re) {
            System.out.println("Remote Exception");
            re.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other Exception");
            e.printStackTrace();
        }
    }
}