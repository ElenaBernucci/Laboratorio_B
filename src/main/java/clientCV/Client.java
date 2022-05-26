package clientCV;

import clientCV.condivisi.RichiestaServer;
import serverCV.OperazioniServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject {

    public Client() throws RemoteException, NotBoundException {
        exec();
    }

    public void exec() throws RemoteException, NotBoundException {

        Registry registro = LocateRegistry.getRegistry(1099);
        OperazioniServer stub = (OperazioniServer) registro.lookup("ServerAlDB");

    }
}
