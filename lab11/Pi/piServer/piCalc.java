package piServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface piCalc extends Remote {
    double calculatePi(int n) throws RemoteException;
}